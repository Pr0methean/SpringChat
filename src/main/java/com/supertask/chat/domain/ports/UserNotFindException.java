package com.supertask.chat.domain.ports;

public class UserNotFindException extends Throwable {

    public UserNotFindException(String mes) {
        super(mes);
    }
}
