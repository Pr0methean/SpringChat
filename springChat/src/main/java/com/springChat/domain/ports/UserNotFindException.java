package com.springChat.domain.ports;

public class UserNotFindException extends RuntimeException {

    public UserNotFindException(String mes) {
        super(mes);
    }
}
