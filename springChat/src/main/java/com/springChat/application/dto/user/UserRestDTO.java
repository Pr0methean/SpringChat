package com.springChat.application.dto.user;

import org.springframework.hateoas.ResourceSupport;

public class UserRestDTO extends ResourceSupport {
    private Integer idUser;
    private String nick;


    public UserRestDTO() {
    }

    public UserRestDTO(int idUser, String nick){
        this.idUser = idUser;
        this.nick = nick;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "idUser=" + idUser +
                ", nick='" + nick + '\'' +
                '}';
    }
}
