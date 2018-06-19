package br.com.kleston.projects.delivery.model.exceptions;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() { }

    public AuthenticationException(String message) {
        super( message );
    }

    public AuthenticationException(String message, Throwable cause) {
        super( message, cause );
    }
}