package com.messageRepisitory.applications.exceprions;

public class MessagesNotFoundException extends RuntimeException{

    public MessagesNotFoundException(String message) {
        super(message);
    }
}
