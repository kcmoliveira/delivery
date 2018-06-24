package br.com.kleston.projects.delivery.auth.restapi.controllers;

import br.com.kleston.projects.delivery.auth.restapi.routes.AuthRoutes;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spark.Service;

@Controller
public class AuthController {
    @Autowired
    private AuthRoutes authRoutes;

    public void configureRoutes() {
        Service http = Service.ignite();

        http.port( 8001 );

        http.after( ( (request, response) -> response.type("application/json" ) ) );

        http.post( "/account/authenticate", this.authRoutes.authenticate );

        http.post( "*", (req, res) -> {
            res.status( HttpStatus.NOT_FOUND_404 );

            return "{}";
        } );
    }
}