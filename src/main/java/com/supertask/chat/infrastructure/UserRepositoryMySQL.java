package com.supertask.chat.infrastructure;


import com.supertask.chat.api.RestUser;
import com.supertask.chat.api.restUser.UserNew;
import com.supertask.chat.domain.model.User;
import com.supertask.chat.domain.ports.UserNotFindException;
import com.supertask.chat.domain.ports.UserReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository

public class UserRepositoryMySQL implements UserReposytory {


    private DataSource dataSource;
    private Connection connection;

    @Autowired
    public UserRepositoryMySQL(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.connection = dataSource.getConnection();
    }

    @Override
    public int saveUser(UserNew userNew) {

        try {
            Connection connection = dataSource.getConnection();
            String userName = userNew.getNick();
            String userPass = userNew.getPassword();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPass);
            preparedStatement.executeUpdate();

            ResultSet set = preparedStatement.getGeneratedKeys();
            if (set.next()) {
                return set.getInt(1);
            } else {
                return -1;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }


    }

//    public void print() throws SQLException {
//        int idq = 1;
//        Connection connection = dataSource.getConnection();
//        Statement staconnection = connection.createStatement();
//        ResultSet resultSet = staconnection.executeQuery("SELECT * FROM users WHERE nick=" +"\"+ nick +\"");
//
//        while (resultSet.next()) {
//            System.out.println("user: ");
//            int id = resultSet.getInt("id");
//            System.out.println("Id : " + id);
//            String nick = resultSet.getString("nick");
//            System.out.println("Nick : " + nick);
//            String pass = resultSet.getString("pass");
//            System.out.println("Pass : " + pass);
//
//        }
//    }

    @Override
    public User fetchUserBy(int id) throws UserNotFindException {
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id=" + id);
            if (resultSet.next()) {
                String nick = resultSet.getString("nick");
                return new User(id, nick);
            } else {
                throw new UserNotFindException("User not find");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public UserNew fetchUserNewBy(int id) throws UserNotFindException {
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id=" + id);
            if (resultSet.next()) {
                String nick = resultSet.getString("nick");
                String pass = resultSet.getString("pass");
                return new UserNew(id,nick,pass);
            } else {
                throw new UserNotFindException("User not find");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean userExistBy(String nick) {
        try {
            Statement statement = connection.createStatement();
//            string test = "\"testowanie\"";

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nick=" + "\"" + nick + "\"");

            if (resultSet.first()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User fetchUserBy(String nick, String password) {
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nick=" + "\"" + nick + "\"" + " AND " + "pass=" + "\"" + password + "\"");
            resultSet.next();
            int id = resultSet.getInt("id");

            return new User(id, nick);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<User> fetchAllUsers() throws SQLException {

        Statement statement = connection.createStatement();
        List<User> users = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String nick = resultSet.getString("nick");
            users.add(new User(id, nick));
        }

        return users;
    }

    @Override
    public void deleteUserBy(int id) throws SQLException {

        Statement statement = connection.createStatement();
        try {
            statement.execute("DELETE FROM users WHERE id=\"" + id + "\"");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void updateUser(UserNew userNew) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute("UPDATE users SET nick=\"" + userNew.getNick() + "\", pass=\"" + userNew.getPassword() + "\" WHERE id=\"" + userNew.getId() + "\"");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
