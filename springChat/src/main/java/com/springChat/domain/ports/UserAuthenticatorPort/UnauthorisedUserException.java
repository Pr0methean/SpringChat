package com.springChat.domain.ports.UserAuthenticatorPort;

public class UnauthorisedUserException extends Exception {
    public UnauthorisedUserException(String message) {
        super(message);
    }
}
