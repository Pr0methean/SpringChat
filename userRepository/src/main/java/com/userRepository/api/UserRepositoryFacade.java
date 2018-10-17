package com.userRepository.api;

import com.userRepository.applications.dto.NewUserDTO;
import com.userRepository.applications.dto.UserDTO;
import com.userRepository.applications.exceptions.ErrorDuringSaveUserException;
import com.userRepository.applications.exceptions.RepositorySQLException;
import com.userRepository.applications.exceptions.UserNotExistException;
import com.userRepository.infrastructure.UserRepositoryMySQL;

import javax.sql.DataSource;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public class UserRepositoryFacade{

    public static UserRepositoryFacade config(DataSource dataSource){

        UserRepositoryMySQL userRepositoryMySQL = new UserRepositoryMySQL(dataSource);
        return new UserRepositoryFacade(userRepositoryMySQL);
    }

    private UserRepositoryMySQL userRepositoryMySQL;
    private UserRepositoryFacade(UserRepositoryMySQL userRepositoryMySQL) {
        this.userRepositoryMySQL = userRepositoryMySQL;
    }


    public int saveUser(NewUserDTO user) throws ErrorDuringSaveUserException, RepositorySQLException {
        return this.userRepositoryMySQL.saveUser(user);
    }

    public UserDTO fetchUserBy(int id) throws UserNotExistException, RepositorySQLException {
        return this.userRepositoryMySQL.fetchUserBy(id);
    }

    public boolean userExistBy(String nick) throws RepositorySQLException {
        return this.userRepositoryMySQL.userExistBy(nick);
    }

    public UserDTO fetchUserBy(String nick, String password) throws UserNotExistException, RepositorySQLException {
        return this.userRepositoryMySQL.fetchUserBy(nick,password);
    }

    public List<UserDTO> fetchAllUsers() throws RepositorySQLException {
        return this.userRepositoryMySQL.fetchAllUsers();
    }

    public void deleteUserBy(int id) throws RepositorySQLException {
        this.userRepositoryMySQL.deleteUserBy(id);
    }

    public void updateUser(NewUserDTO newUser) throws RepositorySQLException {
        this.userRepositoryMySQL.updateUser(newUser);
    }

    public NewUserDTO fetchUserNewBy(int id) throws UserPrincipalNotFoundException, RepositorySQLException {
        return this.userRepositoryMySQL.fetchNewUserBy(id);
    }
}
