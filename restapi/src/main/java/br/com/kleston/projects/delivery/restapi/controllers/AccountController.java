package br.com.kleston.projects.delivery.restapi.controllers;

import br.com.kleston.projects.delivery.model.dtos.AccountDTO;
import br.com.kleston.projects.delivery.model.dtos.LoginDTO;
import br.com.kleston.projects.delivery.model.exceptions.AccountAlreadyExistsException;
import br.com.kleston.projects.delivery.model.exceptions.AuthenticationException;
import br.com.kleston.projects.delivery.model.services.AccountService;
import br.com.kleston.projects.delivery.model.util.JsonUtils;
import br.com.kleston.projects.delivery.restapi.response.Response;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.eclipse.jetty.http.HttpStatus;
import spark.Spark;

import java.util.Date;

public class AccountController {
    static final long EXPIRATION_TIME = 864_000_000; // 10 days
    static final String SECRET = "nAnDapArbAT";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";

    public static void configureRoutes() {
        Spark.post( "/account/signup", (req, res) -> {
            try {
                AccountDTO accountDTO = JsonUtils.convertFromJson(req.body(), AccountDTO.class);

                accountDTO = new AccountService().signUp(accountDTO);

                return "";
            } catch (Exception e) {
                if( e instanceof AccountAlreadyExistsException ) {

                }

                return "";
            }
        } );

        Spark.post( "/account/authenticate", (req, res) -> {
            try {
                LoginDTO loginDTO = JsonUtils.convertFromJson(req.body(), LoginDTO.class);

                AccountDTO accountDTO = new AccountService().authenticate(loginDTO);


                final String jwtToken = Jwts.builder()
                        .setSubject(accountDTO.getUsername())
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                        .signWith(SignatureAlgorithm.HS512, SECRET)
                        .compact();

                res.status( HttpStatus.ACCEPTED_202 );
                res.header( HEADER_STRING, TOKEN_PREFIX + jwtToken );

                return "";
            } catch (Exception e) {
                Response response = new Response();

                if( e instanceof AuthenticationException ) {
                    response.setStatusCode( HttpStatus.UNAUTHORIZED_401 );
                    response.setMessage( e.getMessage() );
                } else {
                    response.setStatusCode( HttpStatus.INTERNAL_SERVER_ERROR_500 );
                    response.setMessage( "Error authenticating." );
                }

                return response;
            }
        } );
    }
}