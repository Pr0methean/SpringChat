package com.testUserInfrastructure;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionFactory {
    private static DataSource dataSource;

    public ConnectionFactory() {
    }


    public static Connection getConnection() throws SQLException {
        String user = "root";
        String password = "mysqlroot404";
        String url = "jdbc:mysql://51.38.133.76:3306/chattest?verifyServerCertificate=false&useSSL=false&requireSSL=false";

        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(url);
        mysqlDS.setUser(user);
        mysqlDS.setPassword(password);

        dataSource = mysqlDS;

        return mysqlDS.getConnection();

    }
}
