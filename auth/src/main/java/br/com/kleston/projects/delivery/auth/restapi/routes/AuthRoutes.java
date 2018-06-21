package br.com.kleston.projects.delivery.auth.restapi.routes;

import br.com.kleston.projects.delivery.auth.restapi.services.AuthService;
import br.com.kleston.projects.delivery.model.dtos.AccountDTO;
import br.com.kleston.projects.delivery.model.dtos.LoginDTO;
import br.com.kleston.projects.delivery.model.exceptions.AuthenticationException;
import br.com.kleston.projects.delivery.model.security.SecurityUtils;
import br.com.kleston.projects.delivery.model.util.JsonUtils;
import br.com.kleston.projects.delivery.model.ws.Response;
import org.eclipse.jetty.http.HttpStatus;
import spark.Route;

import static br.com.kleston.projects.delivery.model.security.SecurityConstraints.HEADER_STRING;
import static br.com.kleston.projects.delivery.model.security.SecurityConstraints.TOKEN_PREFIX;

public class AuthRoutes {
    public static final Route authenticate = (req, res) -> {
        try {
            LoginDTO loginDTO = JsonUtils.convertFromJson(req.body(), LoginDTO.class);

            AccountDTO accountDTO = new AuthService().authenticate( loginDTO );

            final String jwtToken = SecurityUtils.generateToken( accountDTO.getUsername() );

            res.status(HttpStatus.OK_200);
            res.header(HEADER_STRING, TOKEN_PREFIX + jwtToken);

            return "";
        } catch (Exception e) {
            Response response = new Response();

            if (e instanceof AuthenticationException) {
                response.setStatusCode(HttpStatus.FORBIDDEN_403);
                response.setMessage(e.getMessage());
            } else {
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR_500);
                response.setMessage("Error authenticating.");
            }

            return response;
        }
    };
}