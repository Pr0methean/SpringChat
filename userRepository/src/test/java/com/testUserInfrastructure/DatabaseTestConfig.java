package com.testUserInfrastructure;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DatabaseTestConfig {

    Logger log = Logger.getLogger(String.valueOf(DatabaseTestConfig.class));
    private DataSource dataSource;

    private final String user = "root";
    private final String password = "mysqlroot404";
    private final String url = "jdbc:mysql://51.38.133.76:3306/chattest?verifyServerCertificate=false&useSSL=false&requireSSL=false";

    //TODO: add database.propertis
    public DatabaseTestConfig() {

        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(url);
        mysqlDS.setUser(user);
        mysqlDS.setPassword(password);

        this.dataSource = mysqlDS;

    }
    public void dropTables() {

        try (Connection connection = this.dataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS chattest.users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void createDefaultsTable() {

        try (Connection connection = this.dataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "  CREATE TABLE `chattest`.`users` (\n" +
                            "    `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                            "    `nick` VARCHAR(45) NOT NULL,\n" +
                            "    `pass` VARCHAR(45) NULL,\n" +
                            "    PRIMARY KEY (`id`))\n" +
                            "  ENGINE = InnoDB\n" +
                            "  DEFAULT CHARACTER SET = utf8\n" +
                            "  COLLATE = utf8_polish_ci;");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
