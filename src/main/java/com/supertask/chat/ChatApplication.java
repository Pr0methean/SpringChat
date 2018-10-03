package com.supertask.chat;

import com.supertask.chat.api.restUser.UserNew;
import com.supertask.chat.domain.model.Message;
import com.supertask.chat.domain.model.ServerLog;
import com.supertask.chat.domain.ports.LogReposytory;
import com.supertask.chat.domain.ports.MessageRepository;
import com.supertask.chat.infrastructure.LogRepositoryMySQL;
import com.supertask.chat.infrastructure.MessageRepositoryMySQL;
import com.supertask.chat.infrastructure.UserRepositoryMySQL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ImportResource("classpath:app-context.xml")
public class ChatApplication {

    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext app = SpringApplication.run(ChatApplication.class, args);


        ApplicationContext context = app;

        System.out.println("TESTOWY COMMIT");
        System.out.println("TESTOWY COMMIT2");
        System.out.println("TESTOWY COMMIT2");
////
//        UserNew userNew1 = new UserNew("jarek", "test123");
//        UserNew userNew2 = new UserNew("tomek", "test123");

//        MessageRepository messageRepository = context.getBean(MessageRepositoryMySQL.class);
//
//        List<Message> messageList = new ArrayList<>();
//
//        messageList = messageRepository.listMessagesContainPhrase("nowa");
//
//        for (Message message : messageList) {
//            System.out.println(message.getId());
//            System.out.println(message.getContent());
//
//        }
//
//        LogReposytory logReposytory = context.getBean(LogRepositoryMySQL.class);
//
//        List<ServerLog> serverLogList = new ArrayList<>();
//
//        serverLogList.addAll(logReposytory.listLogsOnDate("2018"));
//
//        System.out.println(serverLogList.get(1).getStatus());
//
//
//
//
//        UserRepositoryMySQL userRepositoryMySQL = context.getBean(UserRepositoryMySQL.class);
//        userRepositoryMySQL.saveUser(userNew1);
//        userRepositoryMySQL.saveUser(userNew2);


//        User fetchUserFromMYSQL =  userRepositoryMySQL.fetchUserBy(2);
//        System.out.println("nick user by id 1 : " +fetchUserFromMYSQL.getNick());
//
//        User fetchUserFromMYSQL2 =  userRepositoryMySQL.fetchUserBy(5);
//        System.out.println("nick user by id 5 : " +fetchUserFromMYSQL2.getNick());
//
//        boolean userExistBy = userRepositoryMySQL.userExistBy("Igor");
//        System.out.println("UserExistby Igor: " + userExistBy);
//
//        boolean userExistBy2 = userRepositoryMySQL.userExistBy("Marek");
//        System.out.println("UserExistby Marek: " + userExistBy2);
//
//
//        User fetchUserByNickPass = userRepositoryMySQL.fetchUserBy("Igor", "test123");
//
//        System.out.println("Nr id Igor: " + fetchUserByNickPass.getId());



    }

}
