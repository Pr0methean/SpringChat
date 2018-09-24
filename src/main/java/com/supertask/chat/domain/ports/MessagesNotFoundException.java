package com.supertask.chat.domain.ports;

public class MessagesNotFoundException extends RuntimeException {

    public MessagesNotFoundException (String msg){
        super(msg);
    }

}
