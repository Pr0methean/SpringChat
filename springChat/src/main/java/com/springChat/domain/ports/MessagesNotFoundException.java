package com.springChat.domain.ports;

public class MessagesNotFoundException extends RuntimeException {

    public MessagesNotFoundException (String msg){
        super(msg);
    }

}
