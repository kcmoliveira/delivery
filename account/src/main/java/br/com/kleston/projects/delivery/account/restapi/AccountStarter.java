package br.com.kleston.projects.delivery.account.restapi;

import br.com.kleston.projects.delivery.account.restapi.controllers.AccountController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( "br.com.kleston.projects.delivery" )
public class AccountStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AccountStarter.class);

        AccountController accountController = context.getBean( AccountController.class );

        accountController.configureRoutes();
    }
}