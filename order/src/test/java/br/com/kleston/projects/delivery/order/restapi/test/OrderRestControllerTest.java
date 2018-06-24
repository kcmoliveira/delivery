package br.com.kleston.projects.delivery.order.restapi.test;

import br.com.kleston.projects.delivery.auth.restapi.AuthStarter;
import br.com.kleston.projects.delivery.model.dtos.LoginDTO;
import br.com.kleston.projects.delivery.model.dtos.OrderDTO;
import br.com.kleston.projects.delivery.model.dtos.OrderProductDTO;
import br.com.kleston.projects.delivery.model.util.JsonUtils;
import br.com.kleston.projects.delivery.order.restapi.OrderStarter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

import java.time.LocalDateTime;

import static br.com.kleston.projects.delivery.model.security.SecurityConstraints.AUTHORIZATION_HEADER;

public class OrderRestControllerTest {
    public static final String ORDER_PRODUCT = "http://localhost:9001/api/account/USER/orders";
    public static final String AUTH = "http://localhost:8001/account/authenticate";

    private OrderDTO orderDTO;

    @BeforeClass
    public static void beforeClassTest() {
        OrderStarter.main( new String[] { } );

        AuthStarter.main( new String[] { } );

//        Spark.awaitInitialization();
    }

    @Before
    public void beforeTest() {
        this.orderDTO = new OrderDTO();

        this.orderDTO.setIdRestaurant( 1L );
        this.orderDTO.setDateOrder( LocalDateTime.now() );

        OrderProductDTO whooper = new OrderProductDTO();
        whooper.setIdProduct( 1L );

        OrderProductDTO chickenCrisp = new OrderProductDTO();
        chickenCrisp.setIdProduct( 2L );

        this.orderDTO.getOrderProductsList().add( whooper );
        this.orderDTO.getOrderProductsList().add( chickenCrisp );
    }

    @Test
    public void orderProductAuthFailTest() throws Exception {
        String orderJson = JsonUtils.convertToPrettyJson( this.orderDTO );

        HttpResponse<JsonNode> response = Unirest.post( ORDER_PRODUCT )
                .header("accept", "application/json")
                .body( orderJson )
                .asJson();

        System.out.println( response.getBody() );

        Assert.assertEquals( HttpStatus.UNAUTHORIZED_401, response.getStatus() );
    }

    @Test
    public void orderProductTest() throws Exception {
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setUsername( "kmacedo" );
        loginDTO.setPassword( "123456" );

        String loginJson = JsonUtils.convertToPrettyJson( loginDTO );

        HttpResponse<JsonNode> loginResponse = Unirest.post( AUTH )
                .header("accept", "application/json")
                .body( loginJson )
                .asJson();

        System.out.println( String.format( "Login: %s", loginResponse.getBody() ) );

        Assert.assertEquals( HttpStatus.OK_200, loginResponse.getStatus() );
        Assert.assertNotNull( loginResponse.getHeaders() );
        Assert.assertFalse( loginResponse.getHeaders().isEmpty() );
        Assert.assertNotNull( loginResponse.getHeaders().getFirst( AUTHORIZATION_HEADER ) );

        String token = loginResponse.getHeaders().getFirst( AUTHORIZATION_HEADER );

        String orderJson = JsonUtils.convertToPrettyJson( this.orderDTO );

        HttpResponse<JsonNode> orderResponse = Unirest.post( ORDER_PRODUCT.replace( "USER", loginDTO.getUsername() ) )
                .header("accept", "application/json" )
                .header( AUTHORIZATION_HEADER, token )
                .body( orderJson )
                .asJson();

        System.out.println( String.format( "Order: %s", orderResponse.getBody() ) );

        Assert.assertEquals( HttpStatus.CREATED_201, orderResponse.getStatus() );
    }
}