package com.messageRepisitory.applications.services;

import com.messageRepisitory.applications.dto.MessageDTOout;
import com.messageRepisitory.domain.model.Message;


public class MapperMessage {

    public static MessageDTOout returnMessageDTOout(Message message){
        return new MessageDTOout(message.getId(),message.getContent(),message.getSentDate(),message.getIdSender(),message.getIdReceiver());
    }

    public static Message returnMessage(MessageDTOout messageDTOout){
        return new Message(messageDTOout.getId(), messageDTOout.getContent(), messageDTOout.getSentDate(), messageDTOout.getIdSender(), messageDTOout.getIdReceiver());
    }


}
