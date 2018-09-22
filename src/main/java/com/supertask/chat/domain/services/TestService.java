package com.supertask.chat.domain.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class TestService {

    @Autowired
    DataSource dataSource;

    @Value("${hello.value}")
    String id;

    @PostConstruct
    public void init(){
        System.out.println("id: "+this.id);
        System.out.println(dataSource);
    }

    public TestService() throws SQLException {
        System.out.println(this.id);
    }
}
