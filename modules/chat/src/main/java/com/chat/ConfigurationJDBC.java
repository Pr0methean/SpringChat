package com.chat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
    public class ConfigurationJDBC {

    @Value("${db.url}") String url;
    @Value("${db.driverClassName}") String driverClass;
    @Value("${db.username}") String username;
    @Value("${db.password}") String password;

    @Bean
    public DataSource dataSource() {

        DataSource dataSource = DataSourceBuilder.create()
                .type(DriverManagerDataSource.class)
                .url(url)
                .driverClassName(driverClass)
                .username(username)
                .password(password)
                .build();

        return dataSource;
    }
}