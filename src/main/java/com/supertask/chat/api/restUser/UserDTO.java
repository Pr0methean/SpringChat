package com.supertask.chat.api.restUser;

import com.supertask.chat.domain.model.User;
import com.supertask.chat.domain.model.dto.Link;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private int id;
    private String nick;
    private List<Link> _links = new ArrayList();

    public UserDTO(User user) {
        this.id = user.getId();
        this.nick = user.getNick();
    }

    public void addLik(Link link) {
        this._links.add(link);
    }

    public int getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public List<Link> get_links() {
        return _links;
    }
}