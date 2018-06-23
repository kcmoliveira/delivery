package br.com.kleston.projects.delivery.auth.restapi.test;

import br.com.kleston.projects.delivery.auth.restapi.AuthStarter;
import br.com.kleston.projects.delivery.model.dtos.LoginDTO;
import br.com.kleston.projects.delivery.model.util.JsonUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

import static br.com.kleston.projects.delivery.model.security.SecurityConstraints.AUTHORIZATION_HEADER;

//@SpringBootTest
//@RunWith( SpringRunner.class )
//@AutoConfigureMockMvc
//@ContextConfiguration( classes = AuthStarter.class )
public class AuthRestControllerTest {
    public static final String AUTHENTICATE = "http://localhost:8001/account/authenticate";

    @BeforeClass
    public static void beforeTest() {
        AuthStarter.main( new String[] { } );

        Spark.awaitInitialization();
    }

    @Test
    public void authenticateTest() throws Exception {
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setUsername( "kmacedo" );
        loginDTO.setPassword( "123456" );

        String signInData = JsonUtils.convertToPrettyJson( loginDTO );

        HttpResponse<JsonNode> response = Unirest.post( AUTHENTICATE )
                .header("accept", "application/json")
                .body( signInData )
                .asJson();

        Assert.assertEquals( HttpStatus.OK_200, response.getStatus() );
        Assert.assertNotNull( response.getHeaders() );
        Assert.assertFalse( response.getHeaders().isEmpty() );
        Assert.assertNotNull( response.getHeaders().get( AUTHORIZATION_HEADER ) );
    }

    @Test
    public void authenticateFailedTest() throws Exception {
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setUsername( "kmacedo2" );
        loginDTO.setPassword( "123456" );

        String signInData = JsonUtils.convertToPrettyJson( loginDTO );

        HttpResponse<JsonNode> response = Unirest.post( AUTHENTICATE )
                .header("accept", "application/json")
                .body( signInData )
                .asJson();

        Assert.assertEquals( HttpStatus.FORBIDDEN_403, response.getStatus() );
    }
}