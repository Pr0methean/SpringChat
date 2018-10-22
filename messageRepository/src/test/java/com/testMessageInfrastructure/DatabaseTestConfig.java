package com.testMessageInfrastructure;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTestConfig {

    private Logger log = LogManager.getLogger(String.valueOf(DatabaseTestConfig.class));
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
            statement.executeUpdate("DROP TABLE IF EXISTS chattest.messages");
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }
    public void createDefaultsTable() {

        try (Connection connection = this.dataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `chattest`.`messages` (\n" +
                            "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `content` TEXT NULL,\n" +
                            "  `dateSent` DATETIME NULL,\n" +
                            "  `idSender` INT(11) NULL,\n" +
                            "  `idReceiver` INT(11) NULL,\n" +
                            "  PRIMARY KEY (`id`));");

        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
