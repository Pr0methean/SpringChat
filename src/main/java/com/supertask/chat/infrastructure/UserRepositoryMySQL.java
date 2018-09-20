package com.supertask.chat.infrastructure;


import com.supertask.chat.domain.model.User;
import com.supertask.chat.domain.model.UserNew;
import com.supertask.chat.domain.ports.UserReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;

@Repository

public class UserRepositoryMySQL implements UserReposytory {

    @Autowired
    private DataSource dataSource;


    @Override
    public void saveUser(UserNew userNew) {
        try {
            Connection connection = dataSource.getConnection();
            String userName = userNew.getNick();
            String userPass = userNew.getPassword();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (null, ?, ?)");


            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPass);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void print() throws SQLException {
//        int idq = 1;
//        Connection connection = dataSource.getConnection();
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nick=" +"\"+ nick +\"");
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
    public User fetchUserBy(int id) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id=" + id);
            resultSet.next();
            String nick = resultSet.getString("nick");

            return new User(id, nick);
        } catch (SQLException e) {
            e.printStackTrace();

        }


        return null;
    }

    @Override
    public boolean userExistBy(String nick) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
//            string test = "\"testowanie\"";

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nick=" + "\""+nick+"\"");

            if (resultSet.first()){
                return true;
            }else{
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
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nick=" + "\""+nick+"\"" +" AND "+"pass=" + "\""+password+"\"" );
            resultSet.next();
            int id = resultSet.getInt("id");

            return new User(id, nick);
        } catch (SQLException e) {
            e.printStackTrace();

        }


        return null;
    }

}
