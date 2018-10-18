package com.testMessageInfrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.logging.Logger;

public class DatabaseTestConfig {

    Logger log = Logger.getLogger(String.valueOf(DatabaseTestConfig.class));
    private Connection connection;
    private final String user;
    private final String password;
    private final String host;


    public DatabaseTestConfig(String host, String username, String password) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        this.host = host;
        this.user = username;
        this.password = password;

    }

    public void connect() {

        try {
            if (!connection.isClosed()) {

                this.connection = DriverManager.getConnection(host, user, password);
                log.info("Connected to database");

            } else {
                log.info("Connecting is available");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getSQLState());
        }
    }

    public void disconnect() {

        try {
            if (!connection.isClosed()) {
                connection.close();
                log.info("Connected to database");
            } else {
                log.info("Connecting is available");
            }
        } catch (SQLException e) {
            log.info(e.getSQLState());
            throw new RuntimeException(e.getSQLState());
        }
    }


}
