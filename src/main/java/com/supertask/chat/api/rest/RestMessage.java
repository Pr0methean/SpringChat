package com.supertask.chat.api.rest;

import com.supertask.chat.domain.model.User;
import com.supertask.chat.domain.ports.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class RestMessage {

    @Autowired
    private MessageRepository messageRepository;


    @ResponseBody
    @GetMapping("/messages")
    public List<MessageDTO> getMessages(HttpServletRequest request, HttpServletResponse response){
        messageRepository.listMessages();

        return null;
    }

    @ResponseBody
    @GetMapping("/messages/{id}")
    public MessageDTO getMessageById(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long idMessage){

        messageRepository.fetchMessageBy(idMessage);
        return null;
    }

    @ResponseBody
    @GetMapping("/messages/{phrase}")
    public List<MessageDTO> getMessageContain(HttpServletRequest request, HttpServletResponse response, @PathVariable("phrase") String phrase){
        messageRepository.listMessagesContainPhrase(phrase);
        return null;
    }

    @ResponseBody
    @GetMapping("/messages/{date}")
    public List<MessageDTO> getMessageContain(HttpServletRequest request, HttpServletResponse response, @PathVariable("date") Integer date){
        messageRepository.listMessagesInDate(date);

        return null;
    }

    @ResponseBody
    @GetMapping("/messages/sender/{idUserSent}")
    public List<MessageDTO> getMessageSent(HttpServletRequest request, HttpServletResponse response, @PathVariable("idUserSent") Long idUserSent){
        messageRepository.listMessagesSent(idUserSent);
        return null;
    }

    @ResponseBody
    @GetMapping("/messages/receiver/{idUserReceive}")
    public List<MessageDTO> getMessageReceived(HttpServletRequest request, HttpServletResponse response, @PathVariable("idUserReceive") Long idUserReceive){
        messageRepository.listMessagesReceived(idUserReceive);
        return null;
    }

    @ResponseBody
    @PostMapping("/messages/{idSender},{idReceiver}")
    public void postMassageFromTo (HttpServletRequest request, HttpServletResponse response, @PathVariable("idSender") Long idSender, @PathVariable("idReceiver") int idReceiver){

        messageRepository.saveMessage(null);
    }

    @ResponseBody
    @DeleteMapping("/messages/{id}")
    public void deleteMassage(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long idMessage){
        messageRepository.deleteMessageBy(idMessage);
    }

    @ResponseBody
    @DeleteMapping("/messages/by/{idSender},{idReceiver}/{startBound},{toBound}")
    public List<MessageDTO> listMessagesBy (HttpServletRequest request, HttpServletResponse response,
                                            @PathVariable("idSender") Long idSender, @PathVariable("idReceiver") Long idReceiver,
                                            @PathVariable("startBound") int startBound,@PathVariable("toBound") int toBound){
        messageRepository.listMessagesBy(idSender,idReceiver,startBound,toBound);
        return null;
    }
    

}
