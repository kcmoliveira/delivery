package br.com.kleston.projects.delivery.restapi;

import br.com.kleston.projects.delivery.restapi.controllers.AccountController;
import br.com.kleston.projects.delivery.restapi.filters.JWTAuthorizationFilter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import spark.Spark;

@Configuration
@ComponentScan( "br.com.kleston.projects.delivery" )
public class DeliveryRestLauncher {
    public static void main(String[] args) {
        Spark.port( 8080 );

        new AnnotationConfigApplicationContext( DeliveryRestLauncher.class );

        Spark.before( "/api/*", JWTAuthorizationFilter::doFilter );

        AccountController.configureRoutes();
    }
}