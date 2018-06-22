package br.com.kleston.projects.delivery.account.restapi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( "br.com.kleston.projects.delivery" )
public class AccountStarter {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext( AccountStarter.class );

//        Spark.before( "/api/*", JWTAuthorizationFilter::doFilter );

//        AccountController.configureRoutes();
    }
}