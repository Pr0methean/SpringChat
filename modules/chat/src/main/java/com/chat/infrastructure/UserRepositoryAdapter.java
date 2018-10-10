package com.chat.infrastructure;

import com.chat.application.sercices.NewUserMapper;
import com.chat.application.sercices.UserMapper;
import com.chat.domain.model.NewUser;
import com.chat.domain.model.User;
import com.chat.domain.ports.UserReposytory;
import com.userRepository.api.UserRepositoryFacade;
import com.userRepository.applications.dto.NewUserDTO;
import com.userRepository.applications.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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
    }

    @Override
    public int saveUser(NewUser user) {
        NewUserDTO newUserDTO = this.newUserMapper.mapToRepositoryUserDTO(user);
        return this.userRepository.saveUser(newUserDTO);
    }

    @Override
    public User fetchUserBy(int id) {
        UserDTO userDTO = this.userRepository.fetchUserBy(id);
        return this.userMapper.mapToDomainUser(userDTO);
    }


    @Override
    public boolean userExistBy(String nick) {

        return this.userRepository.userExistBy(nick);
    }

    @Override
    public User fetchUserBy(String nick, String password) {
        UserDTO userDTO = this.userRepository.fetchUserBy(nick, password);

        return this.userMapper.mapToDomainUser(userDTO);
    }

    @Override
    public List<User> fetchAllUsers() {
        List<UserDTO> userDTOS = this.userRepository.fetchAllUsers();
        List<User> users = new ArrayList<>();
        for (UserDTO userDTO : userDTOS) {
            users.add(this.userMapper.mapToDomainUser(userDTO));
        }
        return users;
    }

    @Override
    public void deleteUserBy(int id) {
        this.userRepository.deleteUserBy(id);
    }

    @Override
    public void updateUser(NewUser newUser) {
        this.userRepository.updateUser(this.newUserMapper.mapToRepositoryUserDTO(newUser));
    }

    @Override
    public NewUser fetchNewUserwBy(int id) {
        NewUserDTO newUserDTO = this.userRepository.fetchUserNewBy(id);

        return this.newUserMapper.mapToDomainUser(newUserDTO);
    }
}
