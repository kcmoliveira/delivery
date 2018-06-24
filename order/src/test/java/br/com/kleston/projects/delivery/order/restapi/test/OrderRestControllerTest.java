package br.com.kleston.projects.delivery.order.restapi.test;

import br.com.kleston.projects.delivery.model.dtos.OrderDTO;
import br.com.kleston.projects.delivery.model.dtos.OrderProductDTO;
import br.com.kleston.projects.delivery.model.util.JsonUtils;
import br.com.kleston.projects.delivery.order.restapi.OrderStarter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

import java.time.LocalDateTime;

public class OrderRestControllerTest {
    public static final String ORDER_PRODUCT = "http://localhost:9001/account/orders";

    @BeforeClass
    public static void beforeTest() {
        OrderStarter.main( new String[] { } );

        Spark.awaitInitialization();
    }

    @Test
    public void orderProductTest() throws Exception {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setIdAccount( 1L );
        orderDTO.setIdRestaurant( 1L );
        orderDTO.setDateOrder( LocalDateTime.now() );

        OrderProductDTO whooper = new OrderProductDTO();
        whooper.setIdProduct( 1L );

        OrderProductDTO chickenCrisp = new OrderProductDTO();
        chickenCrisp.setIdProduct( 2L );

        orderDTO.getOrderProductsList().add( whooper );
        orderDTO.getOrderProductsList().add( chickenCrisp );

        String orderJson = JsonUtils.convertToPrettyJson( orderDTO );

        HttpResponse<JsonNode> response = Unirest.post(ORDER_PRODUCT)
                .header("accept", "application/json")
                .body( orderJson )
                .asJson();

        System.out.println( response.getBody() );

        Assert.assertEquals( HttpStatus.CREATED_201, response.getStatus() );
        Assert.assertNotNull( response.getBody() );
    }
}