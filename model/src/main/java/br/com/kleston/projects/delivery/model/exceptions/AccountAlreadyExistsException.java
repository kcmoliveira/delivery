package br.com.kleston.projects.delivery.model.exceptions;

public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException() { }

    public AccountAlreadyExistsException(String message) {
        super( message );
    }

    public AccountAlreadyExistsException(String message, Throwable cause) {
        super( message, cause );
    }
}