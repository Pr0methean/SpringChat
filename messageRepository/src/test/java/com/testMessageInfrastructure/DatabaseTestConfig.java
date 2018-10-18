package com.testMessageInfrastructure;

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


    public DatabaseTestConfig() {
        //        Properties props = new Properties();
//        FileInputStream fis = null;
//        MysqlDataSource mysqlDS = null;
//        try {
//            fis = new FileInputStream("com.mysql.jdbc.Driver");
//            props.load(fis);
//            mysqlDS = new MysqlDataSource();
//            mysqlDS.setURL(props.getProperty("jdbc:mysql://51.38.133.76:3306/chattest?verifyServerCertificate=false&useSSL=false&requireSSL=false"));
//            mysqlDS.setUser(props.getProperty("root"));
//            mysqlDS.setPassword(props.getProperty("mysqlroot404"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return mysqlDS;
//        https://www.journaldev.com/2509/java-datasource-jdbc-datasource-example
//    }


        MysqlDataSource mysqlDS = null;

        mysqlDS = new MysqlDataSource();
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
        }

    }
    public void createDefoultTable() {

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
        }

    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
