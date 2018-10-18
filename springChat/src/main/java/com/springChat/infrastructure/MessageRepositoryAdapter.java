package com.springChat.infrastructure;

import com.messageRepository.api.MessageRepositoryFacade;
import com.messageRepository.applications.dto.MessageDTOout;
import com.springChat.domain.model.Message;
import com.springChat.domain.ports.MessageRepository;
import com.springChat.infrastructure.service.MapperModelMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepositoryAdapter implements MessageRepository {

    private MessageRepositoryFacade messageRepository;

    @Autowired
    public MessageRepositoryAdapter(DataSource dataSource) {
        this.messageRepository = MessageRepositoryFacade.config(dataSource);
    }

    @Override
    public void saveMessage(Message messageToSave) {
        MessageDTOout messageDTOout = MapperModelMessages.returnMessageDTOout(messageToSave);
        messageRepository.saveMessage(messageDTOout);
    }

    @Override
    public void deleteMessageBy(Long id) {

        messageRepository.deleteMessageBy(id);

    }

    @Override
    public void updateMessageBy(Message message) {

        MessageDTOout messageDTOout = MapperModelMessages.returnMessageDTOout(message);
        messageRepository.updateMessageBy(messageDTOout);
    }

    @Override
    public Message fetchMessageBy(Long id) {
        MessageDTOout messageDTOout = messageRepository.fetchMessageBy(id);
        Message message = MapperModelMessages.returnMessage(messageDTOout);

        return message;
    }

    @Override
    public List<Message> listMessages() {
        List<MessageDTOout> messageDTOoutList = messageRepository.listMessages();
        List<Message> messageList = new ArrayList<>();

        for (MessageDTOout messageDTOout : messageDTOoutList) {
            Message message = MapperModelMessages.returnMessage(messageDTOout);

            messageList.add(message);

        }

        return messageList;
    }

    @Override
    public List<Message> listMessagesContainPhrase(String phrase) {
        List<MessageDTOout> messageDTOoutList = messageRepository.listMessagesContainPhrase(phrase);

        List<Message> messageList = new ArrayList<>();

        for (MessageDTOout messageDTOout : messageDTOoutList) {
            Message message = MapperModelMessages.returnMessage(messageDTOout);

            messageList.add(message);

        }

        return messageList;
    }

    @Override
    public List<Message> listMessagesInDate(String dateTime) {
        List<MessageDTOout> messageDTOoutList = messageRepository.listMessagesInDate(dateTime);

        List<Message> messageList = new ArrayList<>();

        for (MessageDTOout messageDTOout : messageDTOoutList) {
            Message message = MapperModelMessages.returnMessage(messageDTOout);
            messageList.add(message);
        }


        return messageList;
    }

    @Override
    public List<Message> listMessagesSender(Long idUser) {
        List<MessageDTOout> messageDTOoutList = messageRepository.listMessagesSender(idUser);

        List<Message> messageList = new ArrayList<>();

        for (MessageDTOout messageDTOout : messageDTOoutList) {
            Message message = MapperModelMessages.returnMessage(messageDTOout);
            messageList.add(message);

        }


        return messageList;
    }

    @Override
    public List<Message> listMessagesReceived(Long idUser) {
        List<MessageDTOout> messageDTOoutList = messageRepository.listMessagesReceived(idUser);

        List<Message> messageList = new ArrayList<>();
        for (MessageDTOout messageDTOout : messageDTOoutList) {

            Message message = MapperModelMessages.returnMessage(messageDTOout);

            messageList.add(message);
        }


        return messageList;
    }

    @Override
    public List<Message> listMessagesBy(Long idSender, Long idReceiver, int limit, int startBound) {
        List<MessageDTOout> messageDTOoutList = messageRepository.listMessagesBy(idSender, idReceiver, limit, startBound);

        List<Message> messageList = new ArrayList<>();

        for (MessageDTOout messageDTOout : messageDTOoutList) {
            Message message = MapperModelMessages.returnMessage(messageDTOout);
            messageList.add(message);
        }


        return messageList;
    }

    @Override
    public List<Message> getConversationFor(Long idSender, Long idReceiver, int limit, int startBound) {
        List<MessageDTOout> messageDTOoutList = messageRepository.getConversationFor(idSender, idReceiver, limit, startBound);

        List<Message> messageList = new ArrayList<>();

        for (MessageDTOout messageDTOout : messageDTOoutList) {
            Message message = MapperModelMessages.returnMessage(messageDTOout);
            messageList.add(message);
        }


        return messageList;

    }
}
