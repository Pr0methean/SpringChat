package com.testMessageInfrastructure;

import com.messageRepository.domain.model.Message;
import com.testMessageInfrastructure.DatabaseTestConfig;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;
import static org.assertj.core.api.Assertions.*;

public class TestedDataBase {

    Logger log = Logger.getLogger(String.valueOf(TestedDataBase.class));

    private final String host = "";
    private final String user = "";
    private final String password = "";
    private DatabaseTestConfig databaseTest;


    public TestedDataBase() throws ClassNotFoundException {
        this.databaseTest = new DatabaseTestConfig(host, user, password);

        }

    @Test
    public void test() {
        log.info("Test");
    }

    @Test
    public void shouldSaveMessage(Message messageToSave) {

        //given

//        MessageRepository

    }

    @Test
    public void deleteMessageBy(Long id) {

    }

    @Test
    public void updateMessageBy(Message message) {

    }

    @Test
    public Message fetchMessageBy(Long id) {
        return null;
    }

    @Test
    public List<Message> listMessages() {
        return null;
    }

    @Test
    public List<Message> listMessagesContainPhrase(String phrase) {
        return null;
    }

    @Test
    public List<Message> listMessagesInDate(String dateTime) {
        return null;
    }

    @Test
    public List<Message> listMessagesSender(Long idUser) {
        return null;
    }

    @Test
    public List<Message> listMessagesReceived(Long idUser) {
        return null;
    }

    @Test
    public List<Message> listMessagesBy(Long idSender, Long idReceiver, int startBound, int toBound) {
        return null;
    }
}