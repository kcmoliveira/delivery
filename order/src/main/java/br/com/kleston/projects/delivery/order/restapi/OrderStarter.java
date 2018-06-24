package br.com.kleston.projects.delivery.order.restapi;

import br.com.kleston.projects.delivery.order.restapi.controllers.OrderController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( "br.com.kleston.projects.delivery" )
public class OrderStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( OrderStarter.class );

        OrderController orderController = context.getBean( OrderController.class );

        orderController.configureRoutes();
    }
}