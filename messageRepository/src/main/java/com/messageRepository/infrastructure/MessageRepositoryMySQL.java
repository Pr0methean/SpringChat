package com.messageRepository.infrastructure;


import com.messageRepository.applications.exceprions.MessagesNotFoundException;
import com.messageRepository.domain.model.Message;
import com.messageRepository.domain.port.MessageRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class MessageRepositoryMySQL implements MessageRepository {

    private DataSource dataSource;

    public MessageRepositoryMySQL(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveMessage(Message messageToSave) {
        try (Connection connection = this.dataSource.getConnection()) {
            String content = messageToSave.getContent();
            Instant sendDate = messageToSave.getSentDate();
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
        //todo : should write test
        try (Connection connection = this.dataSource.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM messages WHERE id=" + id);
            if (resultSet.next()) {
                String content = resultSet.getString("content");
                Timestamp dateSQL = resultSet.getTimestamp("dateSent");
                Long idSender = resultSet.getLong("idSender");
                Long idReceiver = resultSet.getLong("idReceiver");
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
        //todo : should write test
        try (Connection connection = this.dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM messages WHERE id=\"" + id + "\"");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new MessagesNotFoundException("Messages not find");
        }

    }

    @Override
    public void updateMessageBy(Message message) {
        //todo : should write test
        try (Connection connection = this.dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            Timestamp dateInSQL = Timestamp.from(message.getSentDate());
            statement.execute("UPDATE messages SET content=\"" + message.getContent() + "\", dateSent=\"" + dateInSQL + "\", idSender=\"" + message.getIdSender() + "\", idReceiver=\"" + message.getIdReceiver() + "\" WHERE id=\"" + message.getId() + "\"");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public List<Message> listMessages() {
        //todo : should write test
        try (Connection connection = this.dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            List<Message> listMessages = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM messages");
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                String content = resultSet.getString("content");
                Timestamp dataMySQL = resultSet.getTimestamp("dateSent");
                Instant sentData = dataMySQL.toInstant();
                Long idSender = resultSet.getLong("idSender");
                Long idReceiver = resultSet.getLong("idReceiver");

                listMessages.add(new Message(id, content, sentData, idSender, idReceiver));
            }

            return listMessages;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MessagesNotFoundException("Messages not found");
        }
    }


    @Override
    public List<Message> listMessagesContainPhrase(String phrase) {
        //todo : should write test
        try (Connection connection = this.dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            List<Message> listMessages = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM messages WHERE content LIKE '%" + phrase + "%'");
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                String content = resultSet.getString("content");
                Timestamp dataMySQL = resultSet.getTimestamp("dateSent");
                Long idSender = resultSet.getLong("idSender");
                Long idReceiver = resultSet.getLong("idReceiver");

                Instant sentData = dataMySQL.toInstant();

                listMessages.add(new Message(id, content, sentData, idSender, idReceiver));
            }

            return listMessages;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MessagesNotFoundException("Messages not found");
        }
    }


    @Override
    public List<Message> listMessagesInDate(String dateTime) {
        //todo : should write test
        try (Connection connection = this.dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            List<Message> listMessages = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM messages WHERE dateSent LIKE" + "'" + dateTime + "%'");
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                String content = resultSet.getString("content");
                Timestamp dataMySQL = resultSet.getTimestamp("dateSent");
                Long idSender = resultSet.getLong("idSender");
                Long idReceiver = resultSet.getLong("idReceiver");

                Instant sentData = dataMySQL.toInstant();

                listMessages.add(new Message(id, content, sentData, idSender, idReceiver));
            }

            return listMessages;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MessagesNotFoundException("Messages not found");
        }
    }


    @Override
    public List<Message> listMessagesSender(Long idSender) {
        //todo : should write test
        try (Connection connection = this.dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            List<Message> listMessages = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM messages WHERE idSender=" + idSender);
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                String content = resultSet.getString("content");
                Timestamp dataMySQL = resultSet.getTimestamp("dateSent");
//                Long idSender = resultSet.getLong("users_id_sender");
                Long idReceiver = resultSet.getLong("idReceiver");

                Instant sentData = dataMySQL.toInstant();

                listMessages.add(new Message(id, content, sentData, idSender, idReceiver));
            }

            return listMessages;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MessagesNotFoundException("Messages not found");
        }
    }


    @Override
    public List<Message> listMessagesReceived(Long idReceived) {
        //todo : should write test
        try (Connection connection = this.dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            List<Message> listMessages = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM messages WHERE idReceiver=" + idReceived);
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                String content = resultSet.getString("content");
                Timestamp dataMySQL = resultSet.getTimestamp("dateSent");
                Long idSender = resultSet.getLong("idSender");
                Long idReceiver = resultSet.getLong("idReceiver");

                Instant sentData = dataMySQL.toInstant();

                listMessages.add(new Message(id, content, sentData, idSender, idReceiver));
            }

            return listMessages;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MessagesNotFoundException("Messages not found");
        }
    }


    @Override
    public List<Message> listMessagesBy(Long idSender, Long idReceiver, int limit, int startBound) {
        //todo : should write test
        try (Connection connection = this.dataSource.getConnection()) {
            List<Message> listMessages = new ArrayList<>();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM messages WHERE idSender = ? AND idReceiver = ? LIMIT ? OFFSET ?");

            preparedStatement.setLong(1, idSender);
            preparedStatement.setLong(2, idReceiver);
            preparedStatement.setInt(3, limit);
            preparedStatement.setInt(4, startBound);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                String content = resultSet.getString("content");
                Timestamp dataMySQL = resultSet.getTimestamp("dateSent");

                Instant sentData = dataMySQL.toInstant();

                listMessages.add(new Message(id, content, sentData, idSender, idReceiver));
            }

            return listMessages;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MessagesNotFoundException("Messages not found");
        }

    }

    @Override
    public List<Message> getConversationFor(Long idSender, Long idReceiver, int limit, int startBound) {
        //todo : should write test
        try (Connection connection = this.dataSource.getConnection()) {
            List<Message> listMessages = new ArrayList<>();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM messages WHERE idSender = ? AND idReceiver = ? OR idSender = ? AND idReceiver = ? ORDER BY dateSent asc LIMIT ? OFFSET ?");

            preparedStatement.setLong(1, idSender);
            preparedStatement.setLong(4, idSender);

            preparedStatement.setLong(2, idReceiver);
            preparedStatement.setLong(3, idReceiver);

            preparedStatement.setInt(5, limit);
            preparedStatement.setInt(6, startBound);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                String content = resultSet.getString("content");
                Timestamp dataMySQL = resultSet.getTimestamp("dateSent");
                Long idSenderDb = resultSet.getLong("idSender");
                Long idReceiverDb = resultSet.getLong("idReceiver");

                Instant sentData = dataMySQL.toInstant();

                listMessages.add(new Message(id, content, sentData, idSenderDb, idReceiverDb));
            }
            return listMessages;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MessagesNotFoundException("Messages not found");
        }
    }
}
