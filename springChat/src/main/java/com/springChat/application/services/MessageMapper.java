package com.springChat.application.services;

import com.springChat.application.dto.message.MessageRestDTO;
import com.springChat.domain.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper {

    public MessageRestDTO mapToMessageRestDTO(Message message){
        return new MessageRestDTO(message.getId(),message.getContent(),message.getSentDate(),message.getIdSender(),message.getIdReceiver());
    }
}
