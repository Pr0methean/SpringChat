package com.supertask.chat.api.restMessage;


import com.supertask.chat.domain.model.Message;
import com.supertask.chat.domain.model.ServerLog;
import com.supertask.chat.domain.model.dto.Link;
import com.supertask.chat.domain.ports.MessageRepository;
import com.supertask.chat.domain.ports.MessagesNotFoundException;
import com.supertask.chat.vendors.LoggerServiceModule.DbLogger;
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
    @CrossOrigin
    @GetMapping("/messages")
    public List<MessageDTO> getMessages(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<MessageDTO> messageDTOList = new ArrayList<>();

            List<Message> messageList = new ArrayList<>();

            messageList.addAll(messageRepository.listMessages());


            for (Message message : messageList) {
                MessageDTO messageDTO = new MessageDTO(message);
                messageDTO.addLik(new Link("self", "/messages/"));
                messageDTOList.add(messageDTO);
            }
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type", "application/json");
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
    @CrossOrigin
    @GetMapping("/messages/{id}")
    public MessageDTO getMessageById(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long idMessage) {
        try {
            MessageDTO messageDTO = new MessageDTO(messageRepository.fetchMessageBy(idMessage));

            messageDTO.addLik(new Link("self", "/messages/" + idMessage));


            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            response.setStatus(201);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type", "application/json");
            return messageDTO;
        } catch (MessagesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return null;
    }

    @ResponseBody
    @CrossOrigin
    @GetMapping("/messages/phrase/{phrase}")
    public List<MessageDTO> getMessageContainPhrase(HttpServletRequest request, HttpServletResponse response, @PathVariable("phrase") String phrase) {
        try {
            List<MessageDTO> messageDTOList = new ArrayList<>();
            List<Message> listMessage = new ArrayList<>();

            listMessage.addAll(messageRepository.listMessagesContainPhrase(phrase));

            for (Message message : listMessage) {

                MessageDTO messageDTO = new MessageDTO(message);
                messageDTO.addLik(new Link("self", "/messages/" + message.getId()));

                messageDTOList.add(messageDTO);
            }
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            response.setStatus(201);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type", "application/json");
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
    @CrossOrigin
    @GetMapping("/messages/time/{dateTime}")
    public List<MessageDTO> getMessageContainDate(HttpServletRequest request, HttpServletResponse response, @PathVariable("dateTime") String dateTime) {
        try {
            if (dateTime.contains("T")){
                dateTime = dateTime.replace("T"," ");
            }
            List<MessageDTO> listMessageDTO = new ArrayList<>();
            List<Message> listMessage = new ArrayList<>();
            listMessage.addAll(messageRepository.listMessagesInDate(dateTime));

            for (Message message : listMessage) {
                MessageDTO messageDTO = new MessageDTO(message);
                messageDTO.addLik(new Link("self", "/messages/" + message.getId()));
                listMessageDTO.add(messageDTO);
            }
            response.setStatus(201);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type", "application/json");

            return listMessageDTO;
        } catch (MessagesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return null;
    }

    @ResponseBody
    @CrossOrigin
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
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type", "application/json");

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
    @CrossOrigin
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
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type", "application/json");

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
    @CrossOrigin
    @PostMapping("/messages")
    public void postMassageFromTo(HttpServletRequest request, HttpServletResponse response, @RequestBody Message message) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Content-type", "application/json");
        try {
            message.setSentDate(Instant.now());

            messageRepository.saveMessage(message);

            response.setStatus(201);

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
        } catch (MessagesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
        }
    }

    @ResponseBody
    @CrossOrigin
    @DeleteMapping("/messages/{id}")
    public void deleteMassage(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long idMessage) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Content-type", "application/json");
        try {
            messageRepository.deleteMessageBy(idMessage);
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            response.setStatus(201);
        } catch (MessagesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
    }

    @ResponseBody
    @CrossOrigin
    @GetMapping("/messages/by/{idSender},{idReceiver}/{startBound},{toBound}")
    public List<MessageDTO> listMessagesBy(HttpServletRequest request, HttpServletResponse response,
                                           @PathVariable("idSender") Long idSender, @PathVariable("idReceiver") Long idReceiver,
                                           @PathVariable("startBound") int startBound, @PathVariable("toBound") int toBound) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Content-type", "application/json");
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
