package br.com.kleston.projects.delivery.auth.restapi.services;

import br.com.kleston.projects.delivery.model.dtos.AccountDTO;
import br.com.kleston.projects.delivery.model.dtos.LoginDTO;
import br.com.kleston.projects.delivery.model.exceptions.AuthenticationException;
import br.com.kleston.projects.delivery.model.repository.AccountRepository;
import br.com.kleston.projects.delivery.model.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountDTO authenticate(LoginDTO loginDTO) {
        try {
            final AccountDTO fromDatabase = this.accountRepository.findByUsername( loginDTO.getUsername() );

           if( fromDatabase == null ) {
                throw new AuthenticationException( "User not found." );
            }

            if ( ! SecurityUtils.isPasswordCorrect( loginDTO.getPassword(), fromDatabase.getPassword() ) ) {
                throw new AuthenticationException( "Bad credentials." );
            }

            return fromDatabase;
        } catch (Exception e) {
            if( e instanceof  AuthenticationException ) {
                throw e;
            }

            throw new RuntimeException( "Error authenticating.", e );
        }
    }
}