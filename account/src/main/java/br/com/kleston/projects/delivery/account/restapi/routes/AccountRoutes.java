package br.com.kleston.projects.delivery.account.restapi.routes;

import br.com.kleston.projects.delivery.model.dtos.AccountDTO;
import br.com.kleston.projects.delivery.model.exceptions.AccountAlreadyExistsException;
import br.com.kleston.projects.delivery.account.restapi.services.AccountService;
import br.com.kleston.projects.delivery.model.util.JsonUtils;
import br.com.kleston.projects.delivery.model.ws.Response;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.Route;

@Service
public class AccountRoutes {
    @Autowired
    private AccountService accountService;

    public final Route signup = (req, res) -> {
        try {
            AccountDTO accountDTO = JsonUtils.convertFromJson(req.body(), AccountDTO.class);

            accountDTO = this.accountService.signUp(accountDTO);

            Response response = new Response();
            response.setStatusCode( HttpStatus.CREATED_201 );
            response.setMessage( "" );
            response.setData( accountDTO );

            return JsonUtils.convertToPrettyJson( response );
        } catch (Exception e) {
            Response response = new Response();

            if (e instanceof AccountAlreadyExistsException) {
                response.setStatusCode( HttpStatus.CONFLICT_409 );
                response.setMessage( e.getMessage() );
            } else {
                response.setStatusCode( HttpStatus.INTERNAL_SERVER_ERROR_500 );
                response.setMessage( "Error signing up." );
            }

            return JsonUtils.convertToPrettyJson( response );
        }
    };
}