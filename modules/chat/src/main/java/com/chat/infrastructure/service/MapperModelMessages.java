package com.chat.infrastructure.service;


import com.chat.domain.model.Message;
import com.messageRepisitory.applications.dto.MessageDTOout;

import java.util.ArrayList;
import java.util.List;

public class MapperModelMessages {

    public static Message returnMessage(MessageDTOout messageDTOout) {
        return new Message(messageDTOout.getId(), messageDTOout.getContent(), messageDTOout.getSentDate(), messageDTOout.getIdSender(), messageDTOout.getIdReceiver());
    }

    public static MessageDTOout returnMessageDTOout(Message message) {
        return new MessageDTOout(message.getId(), message.getContent(), message.getSentDate(), message.getIdSender(), message.getIdReceiver());
    }

    public static List<Message> returnMessageList(List<MessageDTOout> messageDTOoutList) {
        List<Message> messageList = new ArrayList<>();

        for (MessageDTOout messageDTOout : messageDTOoutList) {
            Message message = MapperModelMessages.returnMessage(messageDTOout);
            messageList.add(message);

        }
        return messageList;
    }

    public static List<MessageDTOout> returnMessageDTOout(List<Message> messageList){
        List<MessageDTOout> messageDTOoutList = new ArrayList<>();

        for (Message message : messageList)
        {
        MessageDTOout messageDTOout1 = MapperModelMessages.returnMessageDTOout(message);

        messageDTOoutList.add(messageDTOout1);
        }
    return messageDTOoutList;
    }
}
