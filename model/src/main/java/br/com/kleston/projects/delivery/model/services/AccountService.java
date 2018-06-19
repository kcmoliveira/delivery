package br.com.kleston.projects.delivery.model.services;

import br.com.kleston.projects.delivery.model.dtos.AccountDTO;
import br.com.kleston.projects.delivery.model.exceptions.AccountAlreadyExistsException;
import br.com.kleston.projects.delivery.model.repository.AccountRepository;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String login(AccountDTO accountDTO) {
        try {
//            final AccountDTO fromDatabase = this.accountRepository.findBy( Account.ACCOUNT.ID );
//
//            if ( ! BCrypt.checkpw( accountDTO.getPassword(), new BCryptPasswordEncoder() .encode( accountDTO.getPassword() ) ) ) {
//                res.status(401 );
//
//                return "";
//            }
//
//            final String jwtToken = Jwts.builder()
//                    .setSubject( accountDTO.getUsername() )
//                    .setExpiration( new Date( System.currentTimeMillis() + EXPIRATION_TIME) )
//                    .signWith( SignatureAlgorithm.HS512, SECRET )
//                    .compact();
//
//            res.status(200 );
//            res.header( HEADER_STRING, TOKEN_PREFIX + jwtToken );

            return "";
        } catch (Exception e) {
            e.printStackTrace();

            return null;
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