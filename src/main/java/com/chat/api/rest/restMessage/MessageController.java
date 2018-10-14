package com.chat.api.rest.restMessage;


import com.chat.application.dto.message.MessageRestDTO;
import com.chat.application.services.MessageMapper;
import com.chat.domain.model.Message;
import com.chat.domain.model.ServerLog;
import com.chat.domain.ports.MessageRepository;
import com.chat.domain.ports.MessagesNotFoundException;
import com.chat.infrastructure.DbLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {


    private MessageRepository messageRepository;
    private DbLogger dbLogger;
    private MessageMapper messageMapper;

    @Autowired
    public MessageController(MessageRepository messageRepository, DbLogger dbLogger, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.dbLogger = dbLogger;
        this.messageMapper = messageMapper;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin", "*");
        httpHeaders.add("Content-type", "application/json");
        return httpHeaders;
    }


    @CrossOrigin
    @GetMapping("/messages")
    public ResponseEntity<List<MessageRestDTO>> getMessages(HttpServletRequest request) {

        HttpHeaders httpHeaders = getHeaders();
        try {

            List<MessageRestDTO> messageRestDTOList = new ArrayList<>();
            List<Message> messageList = messageRepository.listMessages();

            for (Message message : messageList) {
                messageRestDTOList.add(messageMapper.mapToMessageRestDTO(message));
            }
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 202));
            return new ResponseEntity<>(messageRestDTOList, httpHeaders, HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT);
    }

    @CrossOrigin
    @GetMapping("/messages/{id}")
    public ResponseEntity<MessageRestDTO> getMessageById(HttpServletRequest request, @PathVariable("id") Long idMessage) {

        HttpHeaders httpHeaders = getHeaders();
        try {

            MessageRestDTO messageDTO = messageMapper.mapToMessageRestDTO(messageRepository.fetchMessageBy(idMessage));
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            return new ResponseEntity<>(messageDTO, httpHeaders, HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();
            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT);
    }

    @CrossOrigin
    @GetMapping("/messages/phrase/{phrase}")
    public ResponseEntity<List<MessageRestDTO>> getMessageContainPhrase(HttpServletRequest request, HttpServletResponse response, @PathVariable("phrase") String phrase) {
        HttpHeaders httpHeaders = getHeaders();
        try {
            List<MessageRestDTO> messageRestDTOSList = new ArrayList<>();
            List<Message> listMessage = new ArrayList<>();

            listMessage.addAll(messageRepository.listMessagesContainPhrase(phrase));

            for (Message message : listMessage) {
                MessageRestDTO messageDTO = messageMapper.mapToMessageRestDTO(message);
                messageRestDTOSList.add(messageDTO);
            }

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            return new ResponseEntity<>(messageRestDTOSList, httpHeaders, HttpStatus.OK);

        } catch (Exception e) {

            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT);
    }

    @CrossOrigin
    @GetMapping("/messages/time/{dateTime}")
    public ResponseEntity<List<MessageRestDTO>> getMessageContainDate(HttpServletRequest request, @PathVariable("dateTime") String dateTime) {

        HttpHeaders httpHeaders = getHeaders();
        try {
            if (dateTime.contains("T")) {
                dateTime = dateTime.replace("T", " ");
            }
            List<MessageRestDTO> messageRestDTOS = new ArrayList<>();
            List<Message> listMessage = new ArrayList<>();
            listMessage.addAll(messageRepository.listMessagesInDate(dateTime));

            for (Message message : listMessage) {
                messageRestDTOS.add(messageMapper.mapToMessageRestDTO(message));
            }
            return new ResponseEntity<>(messageRestDTOS, httpHeaders, HttpStatus.OK);


        } catch (Exception e) {

            e.printStackTrace();
            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT);
    }

    @CrossOrigin
    @GetMapping("/messages/sender/{idUserSent}")
    public ResponseEntity<List<MessageRestDTO>> getMessageSent(HttpServletRequest request, HttpServletResponse response, @PathVariable("idUserSent") Long idUserSent) {

        HttpHeaders httpHeaders = getHeaders();
        try {
            List<MessageRestDTO> messageDTOList = new ArrayList<>();

            List<Message> messageList = new ArrayList<>();

            messageList.addAll(messageRepository.listMessagesSender(idUserSent));


            for (Message message : messageList) {
                messageDTOList.add(messageMapper.mapToMessageRestDTO(message));
            }

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            return new ResponseEntity<>(messageDTOList, httpHeaders, HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();
            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT);
    }


    @CrossOrigin
    @GetMapping("/messages/receiver/{idUserReceive}")
    public ResponseEntity<List<MessageRestDTO>> getMessageReceived(HttpServletRequest request, HttpServletResponse response, @PathVariable("idUserReceive") Long idUserReceive) {

        HttpHeaders httpHeaders = getHeaders();
        try {
            List<MessageRestDTO> messageDTOList = new ArrayList<>();

            List<Message> messageList = new ArrayList<>();

            messageList.addAll(messageRepository.listMessagesReceived(idUserReceive));


            for (Message message : messageList) {
                messageDTOList.add(messageMapper.mapToMessageRestDTO(message));
            }

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            return new ResponseEntity<>(messageDTOList, httpHeaders, HttpStatus.OK);
        } catch (MessagesNotFoundException e) {

            e.printStackTrace();
            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/messages")
    public ResponseEntity postMassageFromTo(HttpServletRequest request, HttpServletResponse response, @RequestBody Message message) {


        HttpHeaders httpHeaders = getHeaders();
        try {
            message.setSentDate(Instant.now());
            messageRepository.saveMessage(message);

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            return new ResponseEntity(httpHeaders,HttpStatus.CREATED);
        } catch (MessagesNotFoundException e) {

            e.printStackTrace();
            httpHeaders.set("Error-message", e.getMessage());
        }
        return new ResponseEntity(httpHeaders,HttpStatus.CONFLICT);
    }

    @CrossOrigin
    @DeleteMapping("/messages/{id}")
    public ResponseEntity deleteMassage(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long idMessage) {
        HttpHeaders httpHeaders = getHeaders();
        try {
            messageRepository.deleteMessageBy(idMessage);
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));

            return new ResponseEntity(httpHeaders,HttpStatus.OK);
        } catch (MessagesNotFoundException e) {

            e.printStackTrace();
            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return new ResponseEntity(httpHeaders,HttpStatus.CONFLICT);
    }

    @CrossOrigin
    @GetMapping("/messages/by/{idSender},{idReceiver}/{startBound},{toBound}")
    public ResponseEntity<List<MessageRestDTO>> listMessagesBy(HttpServletRequest request, HttpServletResponse response,
                                               @PathVariable("idSender") Long idSender, @PathVariable("idReceiver") Long idReceiver,
                                               @PathVariable("startBound") int startBound, @PathVariable("toBound") int toBound) {
        HttpHeaders httpHeaders = getHeaders();
        try {
            List<MessageRestDTO> messageDTOList = new ArrayList<>();
            List<Message> messageList = new ArrayList<>();

            messageList.addAll(messageRepository.listMessagesBy(idSender, idReceiver, startBound, toBound));

            for (Message message : messageList) {
                messageDTOList.add(messageMapper.mapToMessageRestDTO(message));
            }


            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            return new ResponseEntity<>(messageDTOList,httpHeaders,HttpStatus.OK);

        } catch (MessagesNotFoundException e) {

            e.printStackTrace();
            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return new ResponseEntity<>(httpHeaders,HttpStatus.OK);
    }


}
