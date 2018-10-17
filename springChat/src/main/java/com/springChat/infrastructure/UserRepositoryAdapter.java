package com.springChat.infrastructure;

import com.springChat.application.services.NewUserMapper;
import com.springChat.application.services.UserMapper;
import com.springChat.domain.model.NewUser;
import com.springChat.domain.model.User;
import com.springChat.domain.ports.UserReposytory;
import com.userRepository.api.UserRepositoryFacade;
import com.userRepository.applications.dto.NewUserDTO;
import com.userRepository.applications.dto.UserDTO;
import com.userRepository.applications.exceptions.ErrorDuringSaveUserException;
import com.userRepository.applications.exceptions.RepositorySQLException;
import com.userRepository.applications.exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryAdapter implements UserReposytory {

    private UserRepositoryFacade userRepository;
    private UserMapper userMapper;
    private NewUserMapper newUserMapper;

    @Autowired
    public UserRepositoryAdapter(DataSource dataSource, UserMapper userMapper, NewUserMapper newUserMapper) {
        this.userRepository = UserRepositoryFacade.config(dataSource);
        this.userMapper = userMapper;
        this.newUserMapper = newUserMapper;
    }

    @Override
    public int saveUser(NewUser user) throws ErrorDuringSaveUserException, RepositorySQLException {
        NewUserDTO newUserDTO = this.newUserMapper.mapToRepositoryUserDTO(user);
        return this.userRepository.saveUser(newUserDTO);
    }

    @Override
    public User fetchUserBy(int id) throws UserNotExistException, RepositorySQLException {
        UserDTO userDTO = this.userRepository.fetchUserBy(id);
        return this.userMapper.mapToDomainUser(userDTO);
    }


    @Override
    public boolean userExistBy(String nick) throws RepositorySQLException {

        return this.userRepository.userExistBy(nick);
    }

    @Override
    public User fetchUserBy(String nick, String password) throws UserNotExistException, RepositorySQLException {
        UserDTO userDTO = this.userRepository.fetchUserBy(nick, password);

        return this.userMapper.mapToDomainUser(userDTO);
    }

    @Override
    public List<User> fetchAllUsers() throws RepositorySQLException {
        List<UserDTO> userDTOS = this.userRepository.fetchAllUsers();
        List<User> users = new ArrayList<>();
        for (UserDTO userDTO : userDTOS) {
            users.add(this.userMapper.mapToDomainUser(userDTO));
        }
        return users;
    }

    @Override
    public void deleteUserBy(int id) throws RepositorySQLException {
        this.userRepository.deleteUserBy(id);
    }

    @Override
    public void updateUser(NewUser newUser) throws RepositorySQLException {
        this.userRepository.updateUser(this.newUserMapper.mapToRepositoryUserDTO(newUser));
    }

    @Override
    public NewUser fetchNewUserBy(int id) throws UserPrincipalNotFoundException, RepositorySQLException {
        NewUserDTO newUserDTO = this.userRepository.fetchUserNewBy(id);

        return this.newUserMapper.mapToDomainUser(newUserDTO);
    }
}
