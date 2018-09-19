package com.supertask.chat.domain.model;

public class UserNew {
    private Long id;
    private String nick;
    private String password;

    public UserNew(Long id, String nick, String password){
        this.id = id;
        this.nick = nick;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
