package br.com.kleston.projects.delivery.account.restapi.controllers;

import br.com.kleston.projects.delivery.account.restapi.routes.AccountRoutes;
import spark.Spark;

public class AccountController {
    public static void configureRoutes() {
        Spark.port( 7001 );

        Spark.post( "/account/signup", AccountRoutes.signup);

        Spark.post( "/account/authenticate", AuthRoutes.authenticate );
    }
}