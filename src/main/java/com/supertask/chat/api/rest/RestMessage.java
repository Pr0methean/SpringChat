package com.supertask.chat.api.rest;


import com.supertask.chat.domain.model.Message;
import com.supertask.chat.domain.model.ServerLog;
import com.supertask.chat.domain.model.dto.Link;
import com.supertask.chat.domain.ports.MessageRepository;
import com.supertask.chat.domain.ports.MessagesNotFoundException;
import com.supertask.chat.domain.services.DbLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.ArrayList;
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
        try {
            List<MessageDTO> messageDTOList = new ArrayList<>();

            List<Message> messageList = new ArrayList<>();

            messageList.addAll(messageRepository.listMessages());


            for (Message message : messageList) {
                MessageDTO messageDTO = new MessageDTO(message);
                messageDTO.addLik(new Link("self", "/messages/" + message.getId()));
                messageDTOList.add(messageDTO);
            }
            response.setStatus(201);
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 202));

            return messageDTOList;
        } catch (MessagesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return null;
    }

    @ResponseBody
    @GetMapping("/messages/{id}")
    public MessageDTO getMessageById(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long idMessage) {
        try {
            MessageDTO messageDTO = new MessageDTO(messageRepository.fetchMessageBy(idMessage));

            messageDTO.addLik(new Link("self", "/messages/" + idMessage));
            response.setStatus(201);
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            return messageDTO;
        } catch (MessagesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return null;
    }

//    @ResponseBody
//    @GetMapping("/messages/{phrase}")
//    public List<MessageDTO> getMessageContain(HttpServletRequest request, HttpServletResponse response, @PathVariable("phrase") String phrase) {
//        List<MessageDTO> messageDTOList = new ArrayList<>();
//
//
//
//
//
//
//        messageRepository.listMessagesContainPhrase(null);
//        return messageDTOList;
//    }
//
//    @ResponseBody
//    @GetMapping("/messages/{date}")
//    public List<MessageDTO> getMessageContain(HttpServletRequest request, HttpServletResponse response, @PathVariable("date") String dateTime) {
//        messageRepository.listMessagesInDate(dateTime);
//
//        return null;
//    }

    @ResponseBody
    @GetMapping("/messages/sender/{idUserSent}")
    public List<MessageDTO> getMessageSent(HttpServletRequest request, HttpServletResponse response, @PathVariable("idUserSent") Long idUserSent) {
        try {
            List<MessageDTO> messageDTOList = new ArrayList<>();

            List<Message> messageList = new ArrayList<>();

            messageList.addAll(messageRepository.listMessagesSender(idUserSent));


            for (Message message : messageList) {
                MessageDTO messageDTO = new MessageDTO(message);
                messageDTO.addLik(new Link("self", "/messages/" + idUserSent));
                messageDTOList.add(messageDTO);
            }
            response.setStatus(201);
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            return messageDTOList;
        } catch (MessagesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return null;
    }


    @ResponseBody
    @GetMapping("/messages/receiver/{idUserReceive}")
    public List<MessageDTO> getMessageReceived(HttpServletRequest request, HttpServletResponse response, @PathVariable("idUserReceive") Long idUserReceive) {
        try {
            List<MessageDTO> messageDTOList = new ArrayList<>();

            List<Message> messageList = new ArrayList<>();

            messageList.addAll(messageRepository.listMessagesReceived(idUserReceive));


            for (Message message : messageList) {
                MessageDTO messageDTO = new MessageDTO(message);
                messageDTO.addLik(new Link("self", "/messages/" + idUserReceive));
                messageDTOList.add(messageDTO);
            }
            response.setStatus(201);
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            return messageDTOList;
        } catch (MessagesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return null;
    }

    @ResponseBody
    @PostMapping("/messages")
    public void postMassageFromTo(HttpServletRequest request, HttpServletResponse response, @RequestBody Message message) {
        try {
            message.setSentDate(Instant.now());
            response.setStatus(201);

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            messageRepository.saveMessage(message);
        } catch (MessagesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
        }
    }

    @ResponseBody
    @DeleteMapping("/messages/{id}")
    public void deleteMassage(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long idMessage) {
        try {
            messageRepository.deleteMessageBy(idMessage);
            response.setStatus(201);

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
        } catch (MessagesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
    }

    @ResponseBody
    @GetMapping("/messages/by/{idSender},{idReceiver}/{startBound},{toBound}")
    public List<MessageDTO> listMessagesBy(HttpServletRequest request, HttpServletResponse response,
                                           @PathVariable("idSender") Long idSender, @PathVariable("idReceiver") Long idReceiver,
                                           @PathVariable("startBound") int startBound, @PathVariable("toBound") int toBound) {
        try {
            List<MessageDTO> messageDTOList = new ArrayList<>();

            List<Message> messageList = new ArrayList<>();

            messageList.addAll(messageRepository.listMessagesBy(idSender, idReceiver, startBound, toBound));


            for (Message message : messageList) {
                MessageDTO messageDTO = new MessageDTO(message);
                messageDTO.addLik(new Link("self", "/users/" + message.getId()));
                messageDTOList.add(messageDTO);
            }

            response.setStatus(201);
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            return messageDTOList;
        } catch (MessagesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return null;
    }


}
