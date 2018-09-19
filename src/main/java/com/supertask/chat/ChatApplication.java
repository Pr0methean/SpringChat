package com.supertask.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:app-context.xml")
public class ChatApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(ChatApplication.class, args);
    }

}
