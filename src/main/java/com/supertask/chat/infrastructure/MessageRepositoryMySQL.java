package com.supertask.chat.infrastructure;

import com.supertask.chat.domain.model.Message;
import com.supertask.chat.domain.model.User;
import com.supertask.chat.domain.ports.MessageRepository;
import com.supertask.chat.domain.ports.MessagesNotFoundException;
import com.supertask.chat.domain.ports.UserNotFindException;
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

            preparedStatement.setString(1, content);
            preparedStatement.setTimestamp(2, Timestamp.from(sendDate));
            preparedStatement.setLong(3, sender);
            preparedStatement.setLong(4, receiver);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message fetchMessageBy(Long id) {
        //todo : should test method
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM messages WHERE id=" + id);
            if (resultSet.next()) {
                String content = resultSet.getString("content");
                Timestamp dateSQL = resultSet.getTimestamp("date_sent");
                Long idSender = resultSet.getLong("users_id_sender");
                Long idReceiver = resultSet.getLong("users_id_receiver");
                return new Message(id, content, dateSQL.toInstant(), idSender, idReceiver);
            } else {
                throw new MessagesNotFoundException("Messages not find");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteMessageBy(Long id) {
        //todo : should test method
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM messages WHERE id=\"" + id + "\"");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new MessagesNotFoundException("Messages not find");
        }

    }

    @Override
    public void updateMessageBy(Message message) {
        //todo : should test method
        try {
            Statement statement = connection.createStatement();
            Timestamp dateInSQL = Timestamp.from(message.getSendDate());
            statement.execute("UPDATE messages SET content=\"" + message.getContent() + "\", date_sent=\"" + dateInSQL + "\", users_id_sender=\"" + message.getIdSender() + "\", users_id_receiver=\"" + message.getIdReceiver() + "\" WHERE id=\"" + message.getId() + "\"");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
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
