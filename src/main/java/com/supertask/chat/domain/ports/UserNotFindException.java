package com.supertask.chat.domain.ports;

public class UserNotFindException extends RuntimeException {

    public UserNotFindException(String mes) {
        super(mes);
    }
}
