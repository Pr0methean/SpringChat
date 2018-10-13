package com.chat.domain.ports;

import com.chat.domain.model.NewUser;
import com.chat.domain.model.User;
import com.userRepository.applications.exceptions.ErrorDuringSaveUserException;
import com.userRepository.applications.exceptions.RepositorySQLException;
import com.userRepository.applications.exceptions.UserNotExistException;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface UserReposytory {

    /**
     *
     * @param user new user to add to db
     * @return id of new user
     */
    int saveUser(NewUser user) throws ErrorDuringSaveUserException, RepositorySQLException;
    User fetchUserBy(int id) throws UserNotExistException, RepositorySQLException;
    boolean userExistBy(String nick) throws RepositorySQLException;
    /**
     * @param nick
     * @param password
     * @return User for given data: nick adn password if user exist in DB
     * @throws UserNotFindException  when user not exist
     */
    User fetchUserBy(String nick, String password) throws UserNotExistException, RepositorySQLException;
    List<User> fetchAllUsers() throws RepositorySQLException;
    void deleteUserBy(int id) throws RepositorySQLException;
    void updateUser(NewUser newUser) throws RepositorySQLException;
    NewUser fetchNewUserBy(int id) throws UserPrincipalNotFoundException, RepositorySQLException;

}
