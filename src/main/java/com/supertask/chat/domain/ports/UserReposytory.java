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
    int saveUser(UserNew user);
    User fetchUserBy(int id);
    boolean userExistBy(String nick);
    /**
     *
     * @param nick
     * @param password
     * @return User for given data: nick adn password if user exist in DB
     * @throws UserNotFindException  when user not exist
     */
    User fetchUserBy(String nick, String password);
    List<User> fetchAllUsers();
    void deleteUserBy(int id);
    void updateUser(UserNew userNew);
    UserNew fetchUserNewBy(int id);

}
