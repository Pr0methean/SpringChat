package com.chat.domain.ports;

import com.chat.domain.model.Message;

import java.util.List;

public interface MessageRepository {
    void saveMessage(Message messageToSave);
    void deleteMessageBy(Long id);
    void updateMessageBy(Message message);

    Message fetchMessageBy(Long id);
    List<Message> listMessages();
    List<Message> listMessagesContainPhrase(String phrase);

    /**
     *
     * @param YYYY-MM-DD HH:MM:SS
     * @excample '2018-09-23 20:
     * @return
     */
    List<Message> listMessagesInDate(String dateTime);
    List<Message> listMessagesSender(Long idUser);
    List<Message> listMessagesReceived(Long idUser);
    List<Message> listMessagesBy(Long idSender, Long idReceiver, int startBound, int toBound);
}
