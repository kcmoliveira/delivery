package br.com.kleston.projects.delivery.auth.restapi.controllers;

import br.com.kleston.projects.delivery.auth.restapi.routes.AuthRoutes;
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

        Spark.post( "/account/authenticate", this.authRoutes.authenticate );
    }
}