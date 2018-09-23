package com.supertask.chat.api.rest;


import com.supertask.chat.domain.model.Message;
import com.supertask.chat.domain.model.ServerLog;
import com.supertask.chat.domain.ports.MessageRepository;
import com.supertask.chat.domain.services.DbLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.List;

@Controller
public class RestMessage {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private DbLogger dbLogger;

    @ResponseBody
    @GetMapping("/messages")
    public List<MessageDTO> getMessages(HttpServletRequest request, HttpServletResponse response) {
        messageRepository.listMessages();

        return null;
    }

    @ResponseBody
    @GetMapping("/messages/{id}")
    public MessageDTO getMessageById(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long idMessage) {

        messageRepository.fetchMessageBy(idMessage);
        return null;
    }

    @ResponseBody
    @GetMapping("/messages/{phrase}")
    public List<MessageDTO> getMessageContain(HttpServletRequest request, HttpServletResponse response, @PathVariable("phrase") String phrase) {
        messageRepository.listMessagesContainPhrase(phrase);
        return null;
    }

    @ResponseBody
    @GetMapping("/messages/{date}")
    public List<MessageDTO> getMessageContain(HttpServletRequest request, HttpServletResponse response, @PathVariable("date") Integer date) {
        messageRepository.listMessagesInDate(date);

        return null;
    }

    @ResponseBody
    @GetMapping("/messages/sender/{idUserSent}")
    public List<MessageDTO> getMessageSent(HttpServletRequest request, HttpServletResponse response, @PathVariable("idUserSent") Long idUserSent) {
        messageRepository.listMessagesSent(idUserSent);
        return null;
    }

    @ResponseBody
    @GetMapping("/messages/receiver/{idUserReceive}")
    public List<MessageDTO> getMessageReceived(HttpServletRequest request, HttpServletResponse response, @PathVariable("idUserReceive") Long idUserReceive) {
        messageRepository.listMessagesReceived(idUserReceive);
        return null;
    }

    @ResponseBody
    @PostMapping("/messages")
    public void postMassageFromTo(HttpServletRequest request, HttpServletResponse response, @RequestBody Message message) {
        ServerLog serverLog = new ServerLog(Instant.now(),request.getMethod(),"Sent message by: Id sender: " +message.getIdSender() + " to Id receiver: " + message.getIdReceiver());
        dbLogger.log(serverLog);
        System.out.println(request.getMethod());
        message.setSendDate(Instant.now());
        response.setStatus(201);
        messageRepository.saveMessage(message);
    }

    @ResponseBody
    @DeleteMapping("/messages/{id}")
    public void deleteMassage(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long idMessage) {
        messageRepository.deleteMessageBy(idMessage);
    }

    @ResponseBody
    @DeleteMapping("/messages/by/{idSender},{idReceiver}/{startBound},{toBound}")
    public List<MessageDTO> listMessagesBy(HttpServletRequest request, HttpServletResponse response,
                                           @PathVariable("idSender") Long idSender, @PathVariable("idReceiver") Long idReceiver,
                                           @PathVariable("startBound") int startBound, @PathVariable("toBound") int toBound) {
        messageRepository.listMessagesBy(idSender, idReceiver, startBound, toBound);
        return null;
    }


}
