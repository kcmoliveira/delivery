package br.com.kleston.projects.delivery.account.restapi.controllers;

import br.com.kleston.projects.delivery.account.restapi.routes.AccountRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spark.Spark;

@Controller
public class AccountController {
    @Autowired
    private AccountRoutes accountRoutes;

    public void configureRoutes() {
        Spark.port( 7001 );

//        Spark.before( "/api/*", JWTAuthorizationFilter::doFilter );

        Spark.post( "/account/signup", this.accountRoutes.signup );
    }
}