package com.supertask.chat.infrastructure;

import com.supertask.chat.domain.model.Message;
import com.supertask.chat.domain.model.ServerLog;
import com.supertask.chat.domain.ports.LogReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Repository
public class LogRepositoryMySQL implements LogReposytory {

    private DataSource dataSource;

    @Autowired
    public LogRepositoryMySQL(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
    }


    @Override
    public void saveLog(ServerLog serverLog) {
        try(Connection connection = this.dataSource.getConnection()) {

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
    public List<ServerLog> listLogsOnDate(String dateTime) {

        try(Connection connection = this.dataSource.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM system_logs WHERE dateOfLog LIKE '" + dateTime +"%'");

            List<ServerLog> listServerLog = new ArrayList<>();
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                Timestamp dataMySQL = resultSet.getTimestamp("dateOfLog");
                String typeOfAction = resultSet.getString("typeOfAction");
                String contentOfAction = resultSet.getString("contentOfAction");
                Integer status = resultSet.getInt("status");

                Instant dateOfLog = dataMySQL.toInstant();

                listServerLog.add(new ServerLog(id,dateOfLog,typeOfAction,contentOfAction,status));
            }
            return listServerLog;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding message");
        }


    }
}
