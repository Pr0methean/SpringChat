package com.supertask.chat.domain.ports;

import com.supertask.chat.domain.model.User;

import java.sql.SQLException;

public interface UserReposytory {

    public void saveUser(User user) throws SQLException;
    public User fetchUserBy(Long id);
    public boolean userExistBy(String nick);
    /**
     *
     * @param nick
     * @param password
     * @return User for given data: nick adn password if user exist in DB
     * @throws UserNotFindException  when user not exist
     */
    public User fetchUserBy(String nick, String password);

}
