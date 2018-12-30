package com.testMessageInfrastructure;


import com.messageRepository.applications.exceprions.MessagesNotFoundException;
import com.messageRepository.domain.model.Message;
import com.messageRepository.infrastructure.MessageRepositoryMySQL;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


public class MessageTestMySql {


    private Logger log = LogManager.getLogger(String.valueOf(MessageTestMySql.class));
    private DatabaseTestConfig databaseTestConfig;
    private MessageRepositoryMySQL messageRepositoryMySQL;


    public MessageTestMySql() throws SQLException {
        this.databaseTestConfig = new DatabaseTestConfig();
        this.messageRepositoryMySQL = new MessageRepositoryMySQL(databaseTestConfig.getDataSource());

    }

    @BeforeEach
    public void beforeTest() {
        databaseTestConfig.dropTables();
        databaseTestConfig.createDefaultsTable();
    }

    @AfterEach
    public void afterTest() {
        databaseTestConfig.dropTables();
    }

    @Test
    public void test() {
        log.info("Test info");
        log.debug("Test debug");
        log.error("Test error");
        log.fatal("Test fatal");

    }

    @Test
    public void shouldSaveMessage() {

        try {
            //given
            databaseTestConfig.dropTables();
            databaseTestConfig.createDefaultsTable();
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
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }

    }

    @Test
    public void shouldDeleteMessageAndThrowExceptionNotFound() {

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }

    }

    @Test
    public void shouldUpdateMessage() {

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }


    }

    @Test
    public void shouldFetchMessageBy() {

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }


    }

    @Test
    public void shouldReturnThreeMessages() {

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }

    }

    @Test
    public void shouldReturnTwoMessagesContainPhrase() {

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }


    }

    @Test
    public void shouldReturnTwoMessagesByDate() {

        try {
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
            data = data.substring(0, 10);

            //when
            messageRepositoryMySQL.saveMessage(message);
            messageRepositoryMySQL.saveMessage(message1);
            messageRepositoryMySQL.saveMessage(message2);

            List<Message> messageList = messageRepositoryMySQL.listMessagesInDate(data);

            //then
            assertEquals(3, messageList.size());
            log.info("Success test : shouldReturnTwoMessagesByDate");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }

    }

    @Test
    public void shouldReturnTwoMessagesSender() {

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }

    @Test
    public void shouldReturnOneMessagesReceived() {

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }

    @Test
    public void shouldReturnMessagesInterlocutorsByLimitAndStartBound() {

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }

    @Test
    public void shouldReturnConversationOrderedByDay() {

        try {
            //given
            Long userMark = 1L;
            Long userJohn = 2L;
            Long userAnna = 3L;

            Message message1 = null;
            Message message2 = null;
            Message message3 = null;
            Message message4 = null;
            Message message5 = null;

            message1 = new Message();
            message1.setContent("Mark say to John1");
            message1.setIdSender(userMark);
            message1.setIdReceiver(userJohn);
            message1.setSentDate(Instant.now());

            message2 = new Message();
            message2.setContent("Mark say to John2");
            message2.setIdSender(userMark);
            message2.setIdReceiver(userJohn);
            message2.setSentDate(Instant.now());


            message3 = new Message();
            message3.setContent("Anna say to John3");
            message3.setIdSender(userAnna);
            message3.setIdReceiver(userJohn);
            message3.setSentDate(Instant.now());

            message4 = new Message();
            message4.setContent("John say to Mark4");
            message4.setIdSender(userJohn);
            message4.setIdReceiver(userMark);
            message4.setSentDate(Instant.now());

            message5 = new Message();
            message5.setContent("Anna say to John5");
            message5.setIdSender(userAnna);
            message5.setIdReceiver(userJohn);
            message5.setSentDate(Instant.now());


            //when
            messageRepositoryMySQL.saveMessage(message3);
            messageRepositoryMySQL.saveMessage(message2);
            messageRepositoryMySQL.saveMessage(message5);
            messageRepositoryMySQL.saveMessage(message4);
            messageRepositoryMySQL.saveMessage(message1);

            //then
            List<Message> messageList = messageRepositoryMySQL.getConversationFor(userMark, userJohn, 10, 0);
            List<Message> messageList2 = messageRepositoryMySQL.getConversationFor(userJohn, userMark, 10, 0);

            assertEquals(messageList, messageList2);
            log.info("Success test: shouldReturnConversationOrderedByDay");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }
}
