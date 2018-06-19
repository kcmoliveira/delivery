package br.com.kleston.projects.delivery.restapi.controllers;

import br.com.kleston.projects.delivery.model.services.AccountService;
import spark.Spark;

public class AccountController {
    public static void configureRoutes() {
        Spark.post( "/account/signup", (req, res) -> new AccountService().signUp( req, res ) );

        Spark.post( "/account/login", (req, res) -> new AccountService().login( req, res ) );

        Spark.post( "/api/account/:username", (req, res) -> new AccountService().username( req, res ) );
    }
}