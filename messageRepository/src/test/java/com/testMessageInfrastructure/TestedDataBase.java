package com.testMessageInfrastructure;


import com.messageRepository.applications.exceprions.MessagesNotFoundException;
import com.messageRepository.domain.model.Message;
import com.messageRepository.infrastructure.MessageRepositoryMySQL;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;


public class TestedDataBase {


    private Logger log = Logger.getLogger(String.valueOf(TestedDataBase.class));
    private DatabaseTestConfig databaseTestConfig;
    private MessageRepositoryMySQL messageRepositoryMySQL;


    public TestedDataBase() throws SQLException {
        this.databaseTestConfig = new DatabaseTestConfig();
        this.messageRepositoryMySQL = new MessageRepositoryMySQL(databaseTestConfig.getDataSource());

    }

    @BeforeEach
    public void beforeTest() {
        databaseTestConfig.dropTables();
        databaseTestConfig.createDefoultTable();
    }

    @AfterEach
    public void afterTest() {
        databaseTestConfig.dropTables();
    }

    @Test
    public void test() {
        log.info("Test RUN !");
    }

    @Test
    public void shouldSaveMessage() {

        //given
        databaseTestConfig.dropTables();
        databaseTestConfig.createDefoultTable();
        Message message = new Message();
        message.setContent("Testing content ");
        message.setIdReceiver(1L);
        message.setIdSender(2L);
        message.setSentDate(Instant.now());

        //when
        messageRepositoryMySQL.saveMessage(message);
        Message messageFromDB = messageRepositoryMySQL.fetchMessageBy(1L);

        //then
        assertEquals(message, messageFromDB);
        log.info("Success test : shouldSaveMessage");

    }

    @Test
    public void shouldDeleteMessageAndThrowExceptionNotFound() {

        //given
        Message message = new Message();
        message.setContent("Testing content ");
        message.setIdReceiver(1L);
        message.setIdSender(2L);
        message.setSentDate(Instant.now());


        //when
        messageRepositoryMySQL.saveMessage(message);
        messageRepositoryMySQL.deleteMessageBy(1L);

        //then
        Executable code = () -> {
            messageRepositoryMySQL.fetchMessageBy(1L);
        };
        assertThrows(MessagesNotFoundException.class, code);
        log.info("Success test : shouldDeleteMessageAndThrowExceptionNotFound");
    }

    @Test
    public void shouldUpdateMessage() {

        //given
        Message message = new Message();
        message.setContent("Testing content ");
        message.setIdReceiver(1L);
        message.setIdSender(2L);
        message.setSentDate(Instant.now());

        Message messageUpdated = new Message();
        messageUpdated.setId(1L);
        messageUpdated.setContent("Change valueOf content");
        messageUpdated.setIdReceiver(message.getIdReceiver());
        messageUpdated.setIdSender(message.getIdSender());
        messageUpdated.setSentDate(message.getSentDate());

        //when
        messageRepositoryMySQL.saveMessage(message);
        messageRepositoryMySQL.updateMessageBy(messageUpdated);

        //then
        assertNotEquals(message, messageUpdated);
        assertTrue(messageUpdated.getId().equals(1L));
        log.info("Success test : shouldUpdateMessage");

    }

    @Test
    public void shouldFetchMessageBy() {

        //given
        Message message = new Message();
        message.setContent("Testing content ");
        message.setIdReceiver(1L);
        message.setIdSender(2L);
        message.setSentDate(Instant.now());

        Message message2 = new Message();
        message2.setContent("Different content ");
        message2.setIdReceiver(1L);
        message2.setIdSender(2L);
        message2.setSentDate(Instant.now());

        //when
        messageRepositoryMySQL.saveMessage(message);
        messageRepositoryMySQL.saveMessage(message2);

        Message messageFromDb = messageRepositoryMySQL.fetchMessageBy(1L);

        //then

        assertTrue(messageFromDb.getId().equals(1L));

        log.info("Success test : shouldFetchMessageBy");
    }

    @Test
    public void shouldReturnThreeMessages() {

        //given
        Message message = new Message();
        message.setContent("Testing content ");
        message.setIdReceiver(1L);
        message.setIdSender(2L);
        message.setSentDate(Instant.now());

        Message message1 = new Message();
        message1.setContent("Testing content ");
        message1.setIdReceiver(1L);
        message1.setIdSender(2L);
        message1.setSentDate(Instant.now());

        Message message2 = new Message();
        message2.setContent("Testing content ");
        message2.setIdReceiver(1L);
        message2.setIdSender(2L);
        message2.setSentDate(Instant.now());

        //when
        messageRepositoryMySQL.saveMessage(message);
        messageRepositoryMySQL.saveMessage(message1);
        messageRepositoryMySQL.saveMessage(message2);

        List<Message> messageList = messageRepositoryMySQL.listMessages();

        //then
        assertEquals(3, messageList.size());
        log.info("Success test : shouldReturnThreeMessages");
    }

    @Test
    public void shouldReturnTwoMessagesContainPhrase() {

        //given
        String phraseInContent = "content";
        Message message = new Message();
        message.setContent("Testing content ");
        message.setIdReceiver(1L);
        message.setIdSender(2L);
        message.setSentDate(Instant.now());

        Message message1 = new Message();
        message1.setContent("Testing content ");
        message1.setIdReceiver(1L);
        message1.setIdSender(2L);
        message1.setSentDate(Instant.now());

        Message message2 = new Message();
        message2.setContent("Different text");
        message2.setIdReceiver(1L);
        message2.setIdSender(2L);
        message2.setSentDate(Instant.now());

        //when
        messageRepositoryMySQL.saveMessage(message);
        messageRepositoryMySQL.saveMessage(message1);
        messageRepositoryMySQL.saveMessage(message2);

        List<Message> messageList = messageRepositoryMySQL.listMessagesContainPhrase(phraseInContent);

        //then
        assertEquals(2, messageList.size());
        log.info("Success test : shouldReturnTwoMessagesContainPhrase");

    }

    @Test
    public void shouldReturnTwoMessagesByDate() {

        //given
        Instant dateInstant = Instant.now();



        Message message = new Message();
        message.setContent("Testing content ");
        message.setIdReceiver(1L);
        message.setIdSender(2L);
        message.setSentDate(Instant.now());

        Message message1 = new Message();
        message1.setContent("Testing content ");
        message1.setIdReceiver(1L);
        message1.setIdSender(2L);
        message1.setSentDate(Instant.now());

        Message message2 = new Message();
        message2.setContent("Different text");
        message2.setIdReceiver(1L);
        message2.setIdSender(2L);
        message2.setSentDate(Instant.now());

        String data = Instant.now().toString();
        data = data.substring(0,10);

        //when
        messageRepositoryMySQL.saveMessage(message);
        messageRepositoryMySQL.saveMessage(message1);
        messageRepositoryMySQL.saveMessage(message2);

        List<Message> messageList = messageRepositoryMySQL.listMessagesInDate(data);

        //then
        assertEquals(3, messageList.size());
        log.info("Success test : shouldReturnTwoMessagesByDate");

    }

    @Test
    public void shouldReturnTwoMessagesSender() {

        //given
        Message message = new Message();
        message.setContent("Testing content ");
        message.setIdReceiver(1L);
        message.setIdSender(2L);
        message.setSentDate(Instant.now());

        Message message1 = new Message();
        message1.setContent("Testing content ");
        message1.setIdReceiver(1L);
        message1.setIdSender(2L);
        message1.setSentDate(Instant.now());

        Message message2 = new Message();
        message2.setContent("Different text");
        message2.setIdReceiver(1L);
        message2.setIdSender(3L);
        message2.setSentDate(Instant.now());

        //when
        messageRepositoryMySQL.saveMessage(message);
        messageRepositoryMySQL.saveMessage(message1);
        messageRepositoryMySQL.saveMessage(message2);

        List<Message> messageList = messageRepositoryMySQL.listMessagesSender(2L);

        //then
        assertEquals(messageList.size(), 2);
        log.info("Success test : shouldReturnTwoMessagesSender");
    }

    @Test
    public void shouldReturnOneMessagesReceived() {

        //given
        Message message = new Message();
        message.setContent("Testing content ");
        message.setIdReceiver(1L);
        message.setIdSender(2L);
        message.setSentDate(Instant.now());

        Message message1 = new Message();
        message1.setContent("Testing content ");
        message1.setIdReceiver(2L);
        message1.setIdSender(3L);
        message1.setSentDate(Instant.now());

        Message message2 = new Message();
        message2.setContent("Different text");
        message2.setIdReceiver(2L);
        message2.setIdSender(3L);
        message2.setSentDate(Instant.now());

        //when
        messageRepositoryMySQL.saveMessage(message);
        messageRepositoryMySQL.saveMessage(message1);
        messageRepositoryMySQL.saveMessage(message2);

        List<Message> messageList = messageRepositoryMySQL.listMessagesReceived(1L);

        //then
        assertEquals(messageList.size(), 1);
        log.info("Success test : shouldReturnOneMessagesReceived");
    }

    @Test
    public void shouldReturnMessagesInterlocutorsByLimitAndStartBound() {

        //given
        Long senderTesting = 1L;
        Long receiverTesting = 2L;
        int limit = 10;
        int startBound = 0;

        Message message = new Message();
        message.setContent("Testing content ");
        message.setIdSender(1L);
        message.setIdReceiver(2L);
        message.setSentDate(Instant.now());

        Message message1 = new Message();
        message1.setContent("Testing content ");
        message1.setIdSender(1L);
        message1.setIdReceiver(3L);
        message1.setSentDate(Instant.now());

        Message message2 = new Message();
        message2.setContent("Different text");
        message2.setIdSender(2L);
        message2.setIdReceiver(3L);
        message2.setSentDate(Instant.now());

        Message message3 = new Message();
        message3.setContent("Different text");
        message3.setIdSender(3L);
        message3.setIdReceiver(2L);
        message3.setSentDate(Instant.now());

        //when
        messageRepositoryMySQL.saveMessage(message);
        messageRepositoryMySQL.saveMessage(message1);
        messageRepositoryMySQL.saveMessage(message2);
        messageRepositoryMySQL.saveMessage(message3);

        List<Message> messageList = messageRepositoryMySQL.listMessagesBy(senderTesting, receiverTesting, limit, startBound);

        //then
        assertEquals(messageList.size(), 1);
        log.info("Success test : shouldReturnOneMessagesReceived");
    }

    //todo: should write test to method ordered list
}
