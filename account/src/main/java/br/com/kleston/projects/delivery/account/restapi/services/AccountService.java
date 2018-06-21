package br.com.kleston.projects.delivery.account.restapi.services;

import br.com.kleston.projects.delivery.model.dtos.AccountDTO;
import br.com.kleston.projects.delivery.model.dtos.LoginDTO;
import br.com.kleston.projects.delivery.model.exceptions.AccountAlreadyExistsException;
import br.com.kleston.projects.delivery.model.exceptions.AuthenticationException;
import br.com.kleston.projects.delivery.model.repository.AccountRepository;
import br.com.kleston.projects.delivery.model.security.SecurityUtils;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountDTO signUp(AccountDTO accountDTO) {
        try {
            accountDTO.setPassword( SecurityUtils.hashPassword( accountDTO.getPassword() ) );

            return this.accountRepository.save( accountDTO );
        } catch (Exception e) {
            Throwable cause = e.getCause();

            if( cause instanceof MySQLIntegrityConstraintViolationException && cause.getMessage().contains( "Duplicate entry" ) ) {
                throw new AccountAlreadyExistsException();
            }

            throw new RuntimeException( "Error signing up.", e );
        }
    }
}