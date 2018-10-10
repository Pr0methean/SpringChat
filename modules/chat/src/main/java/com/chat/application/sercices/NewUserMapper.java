package com.chat.application.sercices;

import com.chat.domain.model.NewUser;
import com.chat.domain.model.User;
import com.userRepository.applications.dto.NewUserDTO;
import com.userRepository.applications.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class NewUserMapper {

    public NewUserDTO mapToRepositoryUserDTO(NewUser user){
        return new NewUserDTO(user.getId(),user.getNick(),user.getPassword());
    }
    public com.chat.application.dto.NewUserDTO mapToNewUserDTO(NewUser user){
        return new com.chat.application.dto.NewUserDTO(user.getId(),user.getNick(),user.getPassword());
    }
    public NewUser mapToDomainUser(NewUserDTO userDTO){
        return new NewUser(userDTO.getId(),userDTO.getNick(),userDTO.getPassword());
    }
}
