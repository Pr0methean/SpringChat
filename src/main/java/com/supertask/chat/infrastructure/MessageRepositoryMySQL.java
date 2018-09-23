package com.supertask.chat.infrastructure;

import com.supertask.chat.domain.model.Message;
import com.supertask.chat.domain.model.User;
import com.supertask.chat.domain.ports.MessageRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.util.List;

@Repository
public class MessageRepositoryMySQL implements MessageRepository {

    private DataSource dataSource;
    private Connection connection;

    public MessageRepositoryMySQL(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.connection = dataSource.getConnection();
    }

    @Override
    public void saveMessage(Message messageToSave) {
        try {
        String content = messageToSave.getContent();
        Instant sendDate = messageToSave.getSendDate();
        Long sender = messageToSave.getIdSender();
        Long receiver = messageToSave.getIdReceiver();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO messages values(null,?,?,?,?)");

            preparedStatement.setString(1,content);
            preparedStatement.setTimestamp(2,Timestamp.from(sendDate));
            preparedStatement.setLong(3,sender);
            preparedStatement.setLong(4,receiver);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Message fetchMessageBy(Long id) {

//        Statement statement = connection.
        return null;
    }

    @Override
    public void deleteMessageBy(Long id) {

    }

    @Override
    public void updateMessageBy(Long id) {

    }


    @Override
    public List<Message> listMessages() {
        return null;
    }

    @Override
    public List<Message> listMessagesContainPhrase(String phrase) {
        return null;
    }

    @Override
    public List<Message> listMessagesInDate(Integer sendDate) {
        return null;
    }

    @Override
    public List<Message> listMessagesSent(Long idUser) {
        return null;
    }

    @Override
    public List<Message> listMessagesReceived(Long idUser) {
        return null;
    }

    @Override
    public List<Message> listMessagesBy(Long idSender, Long idReceiver, int startBound, int toBound) {
        return null;
    }

}
