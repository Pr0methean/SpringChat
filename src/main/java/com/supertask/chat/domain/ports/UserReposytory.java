package com.supertask.chat.domain.ports;

import com.supertask.chat.api.RestUser;
import com.supertask.chat.api.restUser.UserNew;
import com.supertask.chat.domain.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserReposytory {

    /**
     *
     * @param user new user to add to db
     * @return id of new user
     */
    public int saveUser(UserNew user);
    public User fetchUserBy(int id) throws UserNotFindException;
    public boolean userExistBy(String nick);
    /**
     *
     * @param nick
     * @param password
     * @return User for given data: nick adn password if user exist in DB
     * @throws UserNotFindException  when user not exist
     */
    public User fetchUserBy(String nick, String password);
    public List<User> fetchAllUsers() throws SQLException;
    public void deleteUserBy(int id) throws SQLException;
    public void updateUser(UserNew userNew) throws SQLException;

}
