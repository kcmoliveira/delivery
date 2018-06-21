package br.com.kleston.projects.delivery.account.restapi.test;

import br.com.kleston.projects.delivery.model.config.PersistenceContext;
import br.com.kleston.projects.delivery.model.dtos.AccountDTO;
import br.com.kleston.projects.delivery.model.exceptions.AccountAlreadyExistsException;
import br.com.kleston.projects.delivery.account.restapi.services.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = PersistenceContext.class, loader = AnnotationConfigContextLoader.class )
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    private AccountDTO accountDTO;

    @Before
    public void before() {
        this.accountDTO = new AccountDTO();

        this.accountDTO.setName( "Kleverton Oliveira" );
        this.accountDTO.setUsername( "kmacedo" );
        this.accountDTO.setPassword( "123456" );
    }

    @Test
    public void signUpTest() {
        try {
            this.accountDTO = this.accountService.signUp( this.accountDTO );

            Assert.assertNotNull( "The user already exists.", this.accountDTO.getId() );
        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail( "Something is wrong!" );
        }
    }

    @Test( expected = AccountAlreadyExistsException.class )
    public void accountAlreadyExistsTest() {
        this.accountDTO = this.accountService.signUp( this.accountDTO );
    }
}