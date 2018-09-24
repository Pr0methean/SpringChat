package com.supertask.chat.domain.ports;

import com.supertask.chat.domain.model.Message;
import com.supertask.chat.domain.model.User;

import java.util.List;

public interface MessageRepository {
    void saveMessage(Message messageToSave);
    void deleteMessageBy(Long id);
    void updateMessageBy(Message message);

    Message fetchMessageBy(Long id);
    List<Message> listMessages();
    List<Message> listMessagesContainPhrase(String phrase);
    List<Message> listMessagesInDate(Integer sendDate);
    List<Message> listMessagesSent(Long idUser);
    List<Message> listMessagesReceived(Long idUser);
    List<Message> listMessagesBy(Long idSender, Long idReceiver, int startBound, int toBound);
}
