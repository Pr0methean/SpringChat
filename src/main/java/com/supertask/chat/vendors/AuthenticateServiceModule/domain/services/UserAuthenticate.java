package com.supertask.chat.vendors.AuthenticateServiceModule.domain.services;

import com.supertask.chat.domain.model.User;
import com.supertask.chat.domain.ports.UserReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UserAuthenticate {


    private UserReposytory userReposytory;

    @Autowired
    public UserAuthenticate(UserReposytory userReposytory) {
        this.userReposytory = userReposytory;
    }


    public User authenticate(String nick, String password){
        try {
            User user = userReposytory.fetchUserBy(nick, password);

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

