package br.com.kleston.projects.delivery.auth.restapi;

import br.com.kleston.projects.delivery.auth.restapi.controllers.AuthController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( "br.com.kleston.projects.delivery" )
public class AuthStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( AuthStarter.class );

        AuthController authController = context.getBean( AuthController.class );

        authController.configureRoutes();
    }
}