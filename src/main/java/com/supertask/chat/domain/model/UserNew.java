package com.supertask.chat.domain.model;

public class User {
    Long id;
    String nick;

    public User(Long id, String nick){
        this.id = id;
        this.nick = nick;
    }
}
