package com.supertask.chat.infrastructure;


import com.supertask.chat.domain.model.User;
import com.supertask.chat.domain.ports.UserReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository

public class UserRepositoryMySQL implements UserReposytory {

    @Autowired
    private DataSource dataSource;



    @Override
    public void saveUser(User user) throws SQLException {
        Connection connection = dataSource.getConnection();

        PreparedStatement  preparedStatement = connection.

        dataSource.p
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
