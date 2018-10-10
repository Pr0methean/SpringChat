package com.userRepository.domain.ports;

import com.userRepository.applications.dto.NewUserDTO;
import com.userRepository.applications.dto.UserDTO;

import java.util.List;

public interface UserRepository {
    /**
     *
     * @param user new user to add to db
     * @return id of new user
     */
    int saveUser(NewUserDTO user);
    UserDTO fetchUserBy(int id);
    boolean userExistBy(String nick);
    /**
     * @param nick
     * @param password
     * @return UserDTO for given data: nick adn password if user exist in DB
     * @throws UserNotExistException  when user not exist
     */
    UserDTO fetchUserBy(String nick, String password);
    List<UserDTO> fetchAllUsers();
    void deleteUserBy(int id);
    void updateUser(NewUserDTO newUser);
    NewUserDTO fetchUserNewBy(int id);
}
