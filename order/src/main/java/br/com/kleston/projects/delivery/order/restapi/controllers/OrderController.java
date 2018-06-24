package br.com.kleston.projects.delivery.order.restapi.controllers;

import br.com.kleston.projects.delivery.model.filters.JWTAuthorizationFilter;
import br.com.kleston.projects.delivery.order.restapi.routes.OrderRoutes;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spark.Service;

@Controller
public class OrderController {
    @Autowired
    private OrderRoutes orderRoutes;

    public void configureRoutes() {
        Service http = Service.ignite();

        http.port( 9001 );

        http.after( ( (request, response) -> response.type("application/json" ) ) );

        http.before( "/api/*", JWTAuthorizationFilter::doFilter );

        http.post( "/api/account/:username/orders", this.orderRoutes.createOrder );

        http.post( "*", (req, res) -> {
            res.status( HttpStatus.NOT_FOUND_404 );

            return "{}";
        } );
    }
}