package com.springChat.domain.ports.UserAuthenticatorPort;

public class UserNotExistException extends Exception {
    public String dueTo;
    public UserNotExistException(String message) {
        super(message);
    }
}
