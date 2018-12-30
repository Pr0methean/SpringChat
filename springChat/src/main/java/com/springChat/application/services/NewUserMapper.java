package com.springChat.application.services;

import com.springChat.domain.model.NewUser;
import com.userRepository.applications.dto.NewUserDTO;
import org.springframework.stereotype.Service;

@Service
public class NewUserMapper {

    public NewUserDTO mapToRepositoryUserDTO(NewUser user){
        return new NewUserDTO(user.getId(),user.getNick(),user.getPassword());
    }
    public com.springChat.application.dto.user.NewUserDTO mapToNewUserDTO(NewUser user){
        return new com.springChat.application.dto.user.NewUserDTO(user.getId(),user.getNick(),user.getPassword());
    }
    public NewUser mapToDomainUser(NewUserDTO userDTO){
        return new NewUser(userDTO.getId(),userDTO.getNick(),userDTO.getPassword());
    }
}
