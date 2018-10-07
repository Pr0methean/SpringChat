package com.supertask.chat.domain.ports;

import com.supertask.chat.domain.model.User;

public interface UserAuthenticator {

    /**
     * Method check eixist
     * @param nick nick from user
     * @param password password from user
     * @return
     */
    public User authenticateUserBy(String nick, String password);

}
