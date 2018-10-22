package com.userRepository.api;

import com.userRepository.applications.dto.NewUserDTO;
import com.userRepository.applications.dto.UserDTO;
import com.userRepository.applications.exceptions.ErrorDuringSaveUserException;
import com.userRepository.applications.exceptions.RepositorySQLException;
import com.userRepository.applications.exceptions.UserNotExistException;
import com.userRepository.domain.model.NewUser;
import com.userRepository.domain.model.User;
import com.userRepository.domain.ports.UserRepository;
import com.userRepository.domain.service.MapperUser;
import com.userRepository.infrastructure.UserRepositoryMySQL;


import javax.sql.DataSource;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public class UserRepositoryFacade {

    public static UserRepositoryFacade config(DataSource dataSource) {

        UserRepository userRepositoryMySQL = new UserRepositoryMySQL(dataSource);
        return new UserRepositoryFacade(userRepositoryMySQL);
    }

    private UserRepository userRepository;

    private UserRepositoryFacade(UserRepository userRepositoryMySQL) {
        this.userRepository = userRepositoryMySQL;
    }


    public int saveUser(NewUserDTO userDTO) throws ErrorDuringSaveUserException, RepositorySQLException {
        NewUser newUser = MapperUser.getNewUser(userDTO);
        return this.userRepository.saveUser(newUser);
    }

    public UserDTO fetchUserBy(int id) throws UserNotExistException, RepositorySQLException {
        User user = this.userRepository.fetchUserBy(id);

        UserDTO userDTO = MapperUser.getUserDto(user);

        return userDTO;
    }

    public boolean userExistBy(String nick) throws RepositorySQLException {

        return this.userRepository.userExistBy(nick);
    }

    public UserDTO fetchUserBy(String nick, String password) throws UserNotExistException, RepositorySQLException {
        User user = this.userRepository.fetchUserBy(nick, password);

        UserDTO userDTO = MapperUser.getUserDto(user);

        return userDTO;
    }

    public List<UserDTO> fetchAllUsers() throws RepositorySQLException {

        List<User> userList = this.userRepository.fetchAllUsers();

        List<UserDTO> userDTOList = MapperUser.getListUsersDto(userList);
        return userDTOList;
    }

    public void deleteUserBy(int id) throws RepositorySQLException {
        this.userRepository.deleteUserBy(id);
    }

    public void updateUser(NewUserDTO newUserDTO) throws RepositorySQLException {
        NewUser newUser = MapperUser.getNewUser(newUserDTO);

        this.userRepository.updateUser(newUser);
    }

    public NewUserDTO fetchUserNewBy(int id) throws UserPrincipalNotFoundException, RepositorySQLException {
        NewUser newUser = this.userRepository.fetchNewUserBy(id);

        NewUserDTO newUserDTO = MapperUser.getNewUserDto(newUser);
        return newUserDTO;
    }
}
