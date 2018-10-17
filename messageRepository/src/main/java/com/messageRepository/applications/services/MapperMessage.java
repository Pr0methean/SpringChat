package com.messageRepository.applications.services;


import com.messageRepository.applications.dto.MessageDTOout;
import com.messageRepository.domain.model.Message;

public class MapperMessage {

    public static MessageDTOout returnMessageDTOout(Message message){
        return new MessageDTOout(message.getId(),message.getContent(),message.getSentDate(),message.getIdSender(),message.getIdReceiver());
    }

    public static Message returnMessage(MessageDTOout messageDTOout){
        return new Message(messageDTOout.getId(), messageDTOout.getContent(), messageDTOout.getSentDate(), messageDTOout.getIdSender(), messageDTOout.getIdReceiver());
    }


}
