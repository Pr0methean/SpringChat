package com.supertask.chat;

import com.supertask.chat.domain.model.User;
import com.supertask.chat.infrastructure.UserRepositoryMySQL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import java.sql.SQLException;

@SpringBootApplication
@ImportResource("classpath:app-context.xml")
public class ChatApplication {

	public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext app = SpringApplication.run(ChatApplication.class, args);


        ApplicationContext context = app;

        User user = new User(1L,"qq");

        UserRepositoryMySQL userRepositoryMySQL = context.getBean(UserRepositoryMySQL.class);
    }

}
