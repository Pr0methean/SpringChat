package com.messageRepository.api;


import com.messageRepository.applications.dto.MessageDTOout;
import com.messageRepository.applications.services.MapperMessage;
import com.messageRepository.domain.model.Message;
import com.messageRepository.domain.port.MessageRepository;
import com.messageRepository.infrastructure.MessageRepositoryMySQL;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


public class MessageRepositoryFacade {

    public static MessageRepositoryFacade config(DataSource dataSource) {
        MessageRepository messageRepositoryMySQL = new MessageRepositoryMySQL(dataSource);
        return new MessageRepositoryFacade(messageRepositoryMySQL);
    }


    private MessageRepository messageRepositoryMySQL;


    private MessageRepositoryFacade(MessageRepository messageRepositoryMySQL) {
        this.messageRepositoryMySQL = messageRepositoryMySQL;
    }

    public void saveMessage(MessageDTOout messageToSave) {
        Message message = MapperMessage.returnMessage(messageToSave);
        messageRepositoryMySQL.saveMessage(message);
    }


    public void deleteMessageBy(Long id) {
        messageRepositoryMySQL.deleteMessageBy(id);
    }


    public void updateMessageBy(MessageDTOout messageDTOout) {
        Message message = MapperMessage.returnMessage(messageDTOout);
        messageRepositoryMySQL.updateMessageBy(message);
    }


    public MessageDTOout fetchMessageBy(Long id) {
        Message message = messageRepositoryMySQL.fetchMessageBy(id);

        return MapperMessage.returnMessageDTOout(message);
    }


    public List<MessageDTOout> listMessages() {
        List<Message> messageList = messageRepositoryMySQL.listMessages();

        List<MessageDTOout> messageDTOoutList = new ArrayList<>();

        for (Message message : messageList) {
            MessageDTOout messageDTOout = MapperMessage.returnMessageDTOout(message);
            messageDTOoutList.add(messageDTOout);
        }
        return messageDTOoutList;
    }


    public List<MessageDTOout> listMessagesContainPhrase(String phrase) {
        List<Message> messageList = messageRepositoryMySQL.listMessagesContainPhrase(phrase);

        List<MessageDTOout> messageDTOoutList = new ArrayList<>();

        for (Message message : messageList) {
            MessageDTOout messageDTOout = MapperMessage.returnMessageDTOout(message);
            messageDTOoutList.add(messageDTOout);
        }
        return messageDTOoutList;
    }


    public List<MessageDTOout> listMessagesInDate(String dateTime) {
        List<Message> messageList = messageRepositoryMySQL.listMessagesInDate(dateTime);

        List<MessageDTOout> messageDTOoutList = new ArrayList<>();

        for (Message message : messageList) {
            MessageDTOout messageDTOout = MapperMessage.returnMessageDTOout(message);

            messageDTOoutList.add(messageDTOout);
        }

        return messageDTOoutList;
    }


    public List<MessageDTOout> listMessagesSender(Long idUser) {
        List<Message> messageList = messageRepositoryMySQL.listMessagesSender(idUser);

        List<MessageDTOout> messageDTOoutList = new ArrayList<>();

        for (Message message: messageList) {
            MessageDTOout messageDTOout = MapperMessage.returnMessageDTOout(message);
            messageDTOoutList.add(messageDTOout);

        }

        return messageDTOoutList;
    }


    public List<MessageDTOout> listMessagesReceived(Long idUser) {
        List<Message> messageList = messageRepositoryMySQL.listMessagesReceived(idUser);
        List<MessageDTOout> messageDTOoutList = new ArrayList<>();

        for (Message message: messageList) {
            MessageDTOout messageDTOout = MapperMessage.returnMessageDTOout(message);

            messageDTOoutList.add(messageDTOout);
        }
        return messageDTOoutList;
    }


    public List<MessageDTOout> listMessagesBy(Long idSender, Long idReceiver, int startBound, int toBound) {
        List<Message> messageList = messageRepositoryMySQL.listMessagesBy(idSender, idReceiver, startBound, toBound);
        List<MessageDTOout> messageDTOoutList = new ArrayList<>();

        for (Message message : messageList) {

            MessageDTOout messageDTOout = MapperMessage.returnMessageDTOout(message);
            messageDTOoutList.add(messageDTOout);
        }

        return messageDTOoutList;
    }
}
