package com.chat.application.services;

import com.chat.domain.model.NewUser;
import com.userRepository.applications.dto.NewUserDTO;
import org.springframework.stereotype.Service;

@Service
public class NewUserMapper {

    public NewUserDTO mapToRepositoryUserDTO(NewUser user){
        return new NewUserDTO(user.getId(),user.getNick(),user.getPassword());
    }
    public com.chat.application.dto.user.NewUserDTO mapToNewUserDTO(NewUser user){
        return new com.chat.application.dto.user.NewUserDTO(user.getId(),user.getNick(),user.getPassword());
    }
    public NewUser mapToDomainUser(NewUserDTO userDTO){
        return new NewUser(userDTO.getId(),userDTO.getNick(),userDTO.getPassword());
    }
}
