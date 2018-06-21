package br.com.kleston.projects.delivery.auth.restapi.controllers;

import br.com.kleston.projects.delivery.auth.restapi.routes.AuthRoutes;
import spark.Spark;

public class AuthController {
    public static void configureRoutes() {
        Spark.port( 8001 );

        Spark.post( "/account/authenticate", AuthRoutes.authenticate );
    }
}