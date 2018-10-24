package com.userRepository.domain.service;


import com.userRepository.applications.dto.NewUserDTO;
import com.userRepository.applications.dto.UserDTO;
import com.userRepository.domain.model.NewUser;
import com.userRepository.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class MapperUser {

    public static NewUserDTO getNewUserDto (NewUser newUser) {
        return new NewUserDTO(newUser.getId(), newUser.getNick(), newUser.getPassword());
    }

    public static UserDTO getUserDto(User user) {
        return new UserDTO(user.getId(), user.getNick());
    }

    public static List<UserDTO> getListUsersDto(List<User> userList) {
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userList) {

            UserDTO userDTO = new UserDTO(user.getId(), user.getNick());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }


    public static NewUser getNewUser(NewUserDTO newUserDTO) {
        return new NewUser(newUserDTO.getId(),newUserDTO.getNick(),newUserDTO.getPassword());
    }

    public static User getUser(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getNick());
    }

    public static List<User> getListUsers(List<UserDTO> userDTOList) {
        List<User> userList = new ArrayList<>();

        for (UserDTO userDTO : userDTOList) {

            User user = new User(userDTO.getId(), userDTO.getNick());
            userList.add(user);
        }
        return userList;
    }

}
