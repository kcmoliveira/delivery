package br.com.kleston.projects.delivery.restapi.controllers;

import br.com.kleston.projects.delivery.model.dtos.AccountDTO;
import br.com.kleston.projects.delivery.model.dtos.LoginDTO;
import br.com.kleston.projects.delivery.model.services.AccountService;
import br.com.kleston.projects.delivery.model.util.JsonUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import spark.Spark;

import java.util.Date;

public class AccountController {
    static final long EXPIRATION_TIME = 864_000_000; // 10 days
    static final String SECRET = "nAnDapArbAT";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";

    public static void configureRoutes() {
        Spark.post( "/account/signup", (req, res) -> {
            AccountDTO accountDTO = JsonUtils.convertFromJson( req.body(), AccountDTO.class );

            accountDTO = new AccountService().signUp( accountDTO );

            final String jwtToken = Jwts.builder()
                    .setSubject( accountDTO.getUsername() )
                    .setExpiration( new Date( System.currentTimeMillis() + EXPIRATION_TIME) )
                    .signWith( SignatureAlgorithm.HS512, SECRET )
                    .compact();

            res.status(200 );
            res.header( HEADER_STRING, TOKEN_PREFIX + jwtToken );

            return "";
        } );

        Spark.post( "/account/authenticate", (req, res) -> {
            LoginDTO loginDTO = JsonUtils.convertFromJson( req.body(), LoginDTO.class );

            AccountDTO accountDTO = new AccountService().authenticate(loginDTO);

            return "";
        } );
    }
}