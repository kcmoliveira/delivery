package br.com.kleston.projects.delivery.restapi.filters;

import io.jsonwebtoken.Jwts;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.Optional;

public class JWTAuthorizationFilter {
    static final long EXPIRATION_TIME = 864_000_000; // 10 days
    static final String SECRET = "nAnDapArbAT";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";

    public static void doFilter(Request req, Response res) {
        final Optional<String> token = Optional.ofNullable( req.headers( HEADER_STRING ) );

        if ( ! token.isPresent() ) {
            Spark.halt(401 );

            return;
        }

        final Optional<String> user = Optional.ofNullable(
                Jwts.parser()
                        .setSigningKey( SECRET )
                        .parseClaimsJws( token.get().replace( TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject()
        );

        if ( ! user.isPresent() ) {
            Spark.halt(401 );

            return;
        }
    }
}