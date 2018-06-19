package br.com.kleston.projects.delivery.model.services;

import br.com.kleston.projects.delivery.model.dtos.AccountDTO;
import br.com.kleston.projects.delivery.model.dtos.LoginDTO;
import br.com.kleston.projects.delivery.model.exceptions.AccountAlreadyExistsException;
import br.com.kleston.projects.delivery.model.exceptions.AuthenticationException;
import br.com.kleston.projects.delivery.model.repository.AccountRepository;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
//    static final long EXPIRATION_TIME = 864_000_000; // 10 days
//    static final String SECRET = "nAnDapArbAT";
//    static final String TOKEN_PREFIX = "Bearer ";
//    static final String HEADER_STRING = "Authorization";
//
//    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AccountRepository accountRepository;

    public AccountDTO authenticate(LoginDTO loginDTO) {
        try {
            final AccountDTO fromDatabase = this.accountRepository.findByUsername( loginDTO.getUsername() );
//
            if ( ! BCrypt.checkpw( loginDTO.getPassword(), fromDatabase.getPassword() ) ) {
                throw new AuthenticationException( "Bad credentials." );
            }

            return fromDatabase;
        } catch (Exception e) {
            throw new RuntimeException( "Error authenticating.", e );
        }
    }

    public AccountDTO signUp(AccountDTO accountDTO) {
        try {
            accountDTO.setPassword( new BCryptPasswordEncoder().encode( accountDTO.getPassword() ) );

            return this.accountRepository.save( accountDTO );

//            return this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString( accountDTO );
        } catch (Exception e) {
            Throwable cause = e.getCause();

            if( cause instanceof MySQLIntegrityConstraintViolationException && cause.getMessage().contains( "Duplicate entry" ) ) {
                throw new AccountAlreadyExistsException();
            }

            throw new RuntimeException( "Error signing up.", e );
        }
    }
}