package com.userRepository.api;

import com.userRepository.applications.dto.NewUserDTO;
import com.userRepository.applications.dto.UserDTO;
import com.userRepository.infrastructure.UserRepositoryMySQL;

import javax.sql.DataSource;
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


    public int saveUser(NewUserDTO user) {
        return this.userRepositoryMySQL.saveUser(user);
    }

    public UserDTO fetchUserBy(int id) {
        return this.userRepositoryMySQL.fetchUserBy(id);
    }

    public boolean userExistBy(String nick) {
        return this.userRepositoryMySQL.userExistBy(nick);
    }

    public UserDTO fetchUserBy(String nick, String password) {
        return this.userRepositoryMySQL.fetchUserBy(nick,password);
    }

    public List<UserDTO> fetchAllUsers() {
        return this.userRepositoryMySQL.fetchAllUsers();
    }

    public void deleteUserBy(int id) {
        this.userRepositoryMySQL.deleteUserBy(id);
    }

    public void updateUser(NewUserDTO newUser) {
        this.userRepositoryMySQL.updateUser(newUser);
    }

    public NewUserDTO fetchUserNewBy(int id) {
        return this.userRepositoryMySQL.fetchUserNewBy(id);
    }
}
