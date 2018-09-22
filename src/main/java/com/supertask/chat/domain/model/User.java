package com.supertask.chat.domain.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String nick;


    public User() {
    }

    public User(int id, String nick){
        this.id = id;
        this.nick = nick;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                '}';
    }
}
