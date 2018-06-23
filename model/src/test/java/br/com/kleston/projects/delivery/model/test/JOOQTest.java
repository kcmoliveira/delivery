package br.com.kleston.projects.delivery.model.test;

import br.com.kleston.projects.delivery.model.config.PersistenceContext;
import br.com.kleston.projects.delivery.model.dtos.AccountDTO;
import br.com.kleston.projects.delivery.model.jooq.tables.Accounts;
import br.com.kleston.projects.delivery.model.jooq.tables.records.AccountsRecord;
import org.jooq.DSLContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = PersistenceContext.class, loader=AnnotationConfigContextLoader.class )
public class JOOQTest {
    @Autowired
    private DSLContext dsl;

    @Test
    public void insertTestSemDTO() {
        try {
            Accounts account = Accounts.ACCOUNTS;

            this.dsl.insertInto( account )
                    .set( account.NAME, "Kleverton Oliveira" )
                    .set( account.USERNAME, "kmacedo")
                    .set( account.PASSWORD, "123456")
                    .execute();

            System.out.println( account );
        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail( "Something is wroong!" );
        }
    }

    @Test
    public void insertTestComDTO() {
        try {
            AccountDTO accountDTO = new AccountDTO();

            accountDTO.setName( "Kleverton Oliveira" );
            accountDTO.setUsername( "kmacedo" );
            accountDTO.setPassword( "123456" );

            AccountsRecord account = this.dsl.newRecord( Accounts.ACCOUNTS, accountDTO );

            account.store();

            Assert.assertNotNull( account.getId() );
        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail( "Something is wroong!" );
        }
    }
}