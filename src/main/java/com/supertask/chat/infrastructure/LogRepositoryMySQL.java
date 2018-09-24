package com.supertask.chat.infrastructure;

import com.supertask.chat.domain.model.ServerLog;
import com.supertask.chat.domain.ports.LogReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


@Repository
public class LogRepositoryMySQL implements LogReposytory {

    private DataSource dataSource;
    private Connection connection;

    public LogRepositoryMySQL(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.connection = dataSource.getConnection();
    }


    @Override
    public void saveLog(ServerLog serverLog) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO system_logs values (null,?,?,?,?)");
            preparedStatement.setTimestamp(1,Timestamp.from(serverLog.getDateOfLog()));
            preparedStatement.setString(2, serverLog.getTypeOfAction());
            preparedStatement.setString(3,serverLog.getContentOfAction());
            preparedStatement.setInt(4,serverLog.getStatus());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding message");
        }

    }

    @Override
    public List<ServerLog> listLogsOnDay(String dateTime) {

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM system_logs WHERE date_Of_Log LIKE '" + dateTime +"'%");


            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding message");
        }


    }
}
