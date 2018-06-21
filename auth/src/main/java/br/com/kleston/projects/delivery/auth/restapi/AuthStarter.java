package br.com.kleston.projects.delivery.auth.restapi;

import br.com.kleston.projects.delivery.auth.restapi.controllers.AuthController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( "br.com.kleston.projects.delivery" )
public class AuthStarter {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext( AuthStarter.class );

        AuthController.configureRoutes();
    }
}
