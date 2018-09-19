package com.supertask.chat.infrastructure;


import com.supertask.chat.domain.model.User;
import com.supertask.chat.domain.model.UserNew;
import com.supertask.chat.domain.ports.UserReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;

@Repository

public class UserRepositoryMySQL implements UserReposytory {

    @Autowired
    private DataSource dataSource;



    @Override
    public void saveUser(UserNew user) throws SQLException {
        Connection connection = dataSource.getConnection();

        PreparedStatement  preparedStatement = connection.prepareStatement("INSERT INTO user VALUES (null, ?, ?)");


        preparedStatement.setString(1,"Janek");
        preparedStatement.setInt(2,2 );
        preparedStatement.execute();
    }

    public void saveMassage() throws SQLException {

        Connection connection = dataSource.getConnection();

        Instant data = Instant.now();

        Date date = new Date(2000,11,10);

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO massages VALUES (?, ?, ?, ? )");

        preparedStatement.setInt(1,1);
        preparedStatement.setString(2,"teskst testowy");
        preparedStatement.setDate(3, date);
        preparedStatement.setInt(4,1);
        preparedStatement.execute();
    }



    @Override
    public User fetchUserBy(Long id) {
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
