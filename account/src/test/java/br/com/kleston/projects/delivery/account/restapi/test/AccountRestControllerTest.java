package br.com.kleston.projects.delivery.account.restapi.test;

import br.com.kleston.projects.delivery.account.restapi.AccountStarter;
import br.com.kleston.projects.delivery.model.dtos.AccountDTO;
import br.com.kleston.projects.delivery.model.dtos.LoginDTO;
import br.com.kleston.projects.delivery.model.util.JsonUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

public class AccountRestControllerTest {
    public static final String AUTHENTICATE = "http://localhost:7001/account/signup";

    private AccountDTO accountDTO;

    @BeforeClass
    public static void beforeTest() {
        AccountStarter.main( new String[] { } );

        Spark.awaitInitialization();
    }

    @Before
    public void before() {
        this.accountDTO = new AccountDTO();

        this.accountDTO.setName( "Delivery Test" );
        this.accountDTO.setUsername( "deliverytest" );
        this.accountDTO.setPassword( "123456" );
    }

    @Test
    public void authenticateTest() throws Exception {
        String signInData = JsonUtils.convertToPrettyJson( this.accountDTO );

        HttpResponse<JsonNode> response = Unirest.post( AUTHENTICATE )
                .header("accept", "application/json")
                .body( signInData )
                .asJson();

        Assert.assertEquals( HttpStatus.CREATED_201, response.getStatus() );
        Assert.assertNotNull( response.getBody() );
    }

    @Test
    public void authenticateFailedTest() throws Exception {
        String signInData = JsonUtils.convertToPrettyJson( this.accountDTO );

        HttpResponse<JsonNode> response = Unirest.post( AUTHENTICATE )
                .header("accept", "application/json")
                .body( signInData )
                .asJson();

        Assert.assertEquals( HttpStatus.INTERNAL_SERVER_ERROR_500, response.getStatus() );
        Assert.assertNotNull( response.getBody() );
    }
}