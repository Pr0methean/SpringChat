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

            PreparedStatement  preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (null, ?, ?)");


            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,userPass );
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void saveMassage() throws SQLException {
//
//        Connection connection = dataSource.getConnection();
//
//        Instant data = Instant.now();
//
//        Date date = new Date(2000,11,10);
//
//        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO massages VALUES (?, ?, ?, ? )");
//
//        preparedStatement.setInt(1,1);
//        preparedStatement.setString(2,"teskst testowy");
//        preparedStatement.setDate(3, date);
//        preparedStatement.setInt(4,1);
//        preparedStatement.execute();
//    }



    @Override
    public User fetchUserBy(Long id){
        try {
            String userName;
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet= statement.executeQuery("SELECT id FROM users WHERE id="+id);

            String nick = resultSet.getString("nick");

            return  new User(id,nick);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public boolean userExistBy(String nick) {
        return false;
    }

    @Override
    public User fetchUserBy(String nick, String password) {
        return null;
    }
}
