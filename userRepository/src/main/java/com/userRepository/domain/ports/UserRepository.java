package com.userRepository.domain.ports;


import com.userRepository.applications.exceptions.ErrorDuringSaveUserException;
import com.userRepository.applications.exceptions.RepositorySQLException;
import com.userRepository.applications.exceptions.UserNotExistException;
import com.userRepository.domain.model.NewUser;
import com.userRepository.domain.model.User;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface UserRepository {
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
     * @return UserDTO for given data: nick adn password if user exist in DB
     * @throws UserNotExistException  when user not exist
     */
    User fetchUserBy(String nick, String password) throws RepositorySQLException, UserNotExistException;
    List<User> fetchAllUsers() throws RepositorySQLException;
    void deleteUserBy(int id) throws RepositorySQLException;
    void updateUser(NewUser newUser) throws RepositorySQLException;
    NewUser fetchNewUserBy(int id) throws UserPrincipalNotFoundException, RepositorySQLException;
}
