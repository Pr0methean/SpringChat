package com.supertask.chat.domain.model;

public class UserNew {
    Long id;
    String nick;
    String password;

    public UserNew(Long id, String nick, String password){
        this.id = id;
        this.nick = nick;
        this.password = password;
    }
}
