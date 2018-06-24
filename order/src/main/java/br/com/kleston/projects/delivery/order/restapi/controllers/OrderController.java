package br.com.kleston.projects.delivery.order.restapi.controllers;

import br.com.kleston.projects.delivery.order.restapi.routes.OrderRoutes;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spark.Spark;

@Controller
public class OrderController {
    @Autowired
    private OrderRoutes orderRoutes;

//    @PostConstruct
    public void configureRoutes() {
        Spark.port( 9001 );

        Spark.after( ( (request, response) -> response.type("application/json" ) ) );

        Spark.post( "/account/orders", this.orderRoutes.createOrder );

        Spark.post( "*", (req, res) -> {
            res.status( HttpStatus.NOT_FOUND_404 );

            return "{}";
        } );
    }
}