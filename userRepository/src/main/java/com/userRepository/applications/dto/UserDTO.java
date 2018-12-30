package com.userRepository.applications.dto;

import com.userRepository.domain.model.User;

public class UserDTO {

    private int id;
    private String nick;

    public UserDTO(int id, String nick) {
        this.id = id;
        this.nick = nick;
    }

    public UserDTO(User user){
        this.id = user.getId();
        this.nick = user.getNick();
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
}
