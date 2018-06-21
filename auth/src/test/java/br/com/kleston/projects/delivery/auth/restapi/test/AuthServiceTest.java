package br.com.kleston.projects.delivery.auth.restapi.test;

import br.com.kleston.projects.delivery.auth.restapi.services.AuthService;
import br.com.kleston.projects.delivery.model.config.PersistenceContext;
import br.com.kleston.projects.delivery.model.dtos.AccountDTO;
import br.com.kleston.projects.delivery.model.dtos.LoginDTO;
import br.com.kleston.projects.delivery.model.exceptions.AuthenticationException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = PersistenceContext.class, loader = AnnotationConfigContextLoader.class )
public class AuthServiceTest {
    @Autowired
    private AuthService authService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void authenticateTest() {
        try {
            LoginDTO loginDTO = new LoginDTO();

            loginDTO.setUsername( "kmacedo" );
            loginDTO.setPassword( "123456" );

            AccountDTO account = this.authService.authenticate( loginDTO );

            Assert.assertNotNull( "User not found.", account );
            Assert.assertNotNull( "User not found.", account.getId() );
        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail( "Something is wrong!" );
        }
    }

    @Test//( expected = AuthenticationException.class )
    public void userNotFoundTest() {
        this.thrown.expect( AuthenticationException.class );
        this.thrown.expectMessage( "User not found." );

        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setUsername( "usernotfound" );
        loginDTO.setPassword( "123456" );

        this.authService.authenticate( loginDTO );
    }

    @Test//( expected = AuthenticationException.class )
    public void badCredentialsFoundTest() {
        this.thrown.expect( AuthenticationException.class );
        this.thrown.expectMessage( "Bad credentials." );

        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setUsername( "kmacedo" );
        loginDTO.setPassword( "321321" );

        this.authService.authenticate( loginDTO );
    }
}