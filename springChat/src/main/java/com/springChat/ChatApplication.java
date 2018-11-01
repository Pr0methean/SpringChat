package com.springChat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(scanBasePackages = {"com.springChat"})
@PropertySources({
        @PropertySource("classpath:config/database.properties"),
        @PropertySource("classpath:config/log4j.properties"),
        @PropertySource("classpath:config/application.properties")
})
public class ChatApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(ChatApplication.class, args);
    }
}
