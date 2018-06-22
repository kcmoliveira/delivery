package br.com.kleston.projects.delivery.auth.restapi.test;

import br.com.kleston.projects.delivery.auth.restapi.AuthStarter;
import br.com.kleston.projects.delivery.model.dtos.LoginDTO;
import br.com.kleston.projects.delivery.model.util.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;

@SpringBootTest
@RunWith( SpringRunner.class )
@AutoConfigureMockMvc
@ContextConfiguration( classes = AuthStarter.class )
public class AuthRestControllerTest {
    public static final String AUTHENTICATE = "http://localhost:8001/authenticate";

    private MediaType contentTypeJson = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void authenticateTest() throws Exception {
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setUsername( "kmacedo" );
        loginDTO.setPassword( "123456" );

        String signInData = JsonUtils.convertToJson( loginDTO );

        // SIGN_IN
        MockHttpServletRequestBuilder contentRequestSignIn =
                MockMvcRequestBuilders
                        //.post( AUTHENTICATE )
                        .post( AUTHENTICATE )
                        .contentType( contentTypeJson )
                        .content( signInData );

        final ResultMatcher resultExpect = MockMvcResultMatchers.status().isOk();

        this.mockMvc.perform( contentRequestSignIn ).andExpect( resultExpect );
    }
}