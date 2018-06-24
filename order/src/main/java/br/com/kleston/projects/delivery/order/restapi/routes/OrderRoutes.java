package br.com.kleston.projects.delivery.order.restapi.routes;

import br.com.kleston.projects.delivery.model.dtos.OrderDTO;
import br.com.kleston.projects.delivery.model.util.JsonUtils;
import br.com.kleston.projects.delivery.model.ws.Response;
import br.com.kleston.projects.delivery.order.restapi.services.OrderService;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.Route;

@Service
public class OrderRoutes {
    @Autowired
    private OrderService orderService;

    public final Route createOrder = (req, res) -> {
        try {
            OrderDTO orderDTO = JsonUtils.convertFromJson( req.body(), OrderDTO.class );

            orderDTO = this.orderService.saveOrder( orderDTO );

            res.status( HttpStatus.CREATED_201 );

            Response response = new Response();

            response.setStatusCode( HttpStatus.CREATED_201 );
            response.setMessage( "Order created." );
            response.setData( orderDTO );

            return JsonUtils.convertToPrettyJson( response );
        } catch (Exception e) {
            e.printStackTrace();

            res.status( HttpStatus.INTERNAL_SERVER_ERROR_500 );

            Response response = new Response();

            response.setStatusCode( HttpStatus.INTERNAL_SERVER_ERROR_500 );
            response.setMessage( "Error creating order." );

            return JsonUtils.convertToPrettyJson( response );
        }
    };
}