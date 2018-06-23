package br.com.kleston.projects.delivery.auth.restapi.controllers;

import br.com.kleston.projects.delivery.auth.restapi.routes.AuthRoutes;
import br.com.kleston.projects.delivery.model.util.JsonUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spark.Spark;

import javax.annotation.PostConstruct;

@Controller
public class AuthController {
    @Autowired
    private AuthRoutes authRoutes;

    @PostConstruct
    public void configureRoutes() {
        Spark.port( 8001 );

        Spark.after( ( (request, response) -> response.type("application/json" ) ) );

        Spark.post( "/account/authenticate", this.authRoutes.authenticate );

        Spark.post( "*", (req, res) -> {
            res.status( HttpStatus.NOT_FOUND_404 );

            return "{}";
        } );
    }
}