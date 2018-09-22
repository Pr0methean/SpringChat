package com.supertask.chat.api.restUser;

public class UserNew {
    private int id;
    private String nick;
    private String password;

    public UserNew() {
    }


    public UserNew(int id, String nick, String password) {
        this.id = id;
        this.nick = nick;
        this.password = password;
    }


    public UserNew(String nick, String password) {
        this(0, nick, password);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserNew{" +
                "nick='" + nick + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}