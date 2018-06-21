package br.com.kleston.projects.delivery.account.restapi.routes;

import br.com.kleston.projects.delivery.model.dtos.AccountDTO;
import br.com.kleston.projects.delivery.model.dtos.LoginDTO;
import br.com.kleston.projects.delivery.model.exceptions.AccountAlreadyExistsException;
import br.com.kleston.projects.delivery.model.exceptions.AuthenticationException;
import br.com.kleston.projects.delivery.account.restapi.services.AccountService;
import br.com.kleston.projects.delivery.model.util.JsonUtils;
import br.com.kleston.projects.delivery.model.ws.Response;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.eclipse.jetty.http.HttpStatus;
import spark.Route;

import java.util.Date;

public class AccountRoutes {
    public static final Route signup = (req, res) -> {
        try {
            AccountDTO accountDTO = JsonUtils.convertFromJson(req.body(), AccountDTO.class);

            accountDTO = new AccountService().signUp(accountDTO);

            return "";
        } catch (Exception e) {
            if (e instanceof AccountAlreadyExistsException) {
                return e;
            }

            return "";
        }
    };
}