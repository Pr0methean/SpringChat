package com.springChat.api.wsr.controlers;

import com.WebSocketRpc.api.Session;
import com.WebSocketRpc.api.WSR;
import com.springChat.api.wsr.types.LocalProcedure;
import com.springChat.api.wsr.types.RemoteProcedure;
import com.springChat.api.wsr.dto.AuthSessionDTO;
import com.springChat.api.wsr.dto.ErrorDTO;
import com.springChat.api.wsr.dto.MessageDTO;
import com.springChat.domain.model.Message;
import com.springChat.domain.ports.MessageRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class MessageControllerWSR implements InitializingBean {

    private WSR<LocalProcedure, RemoteProcedure,Long> wsr;
    private MessageRepository messageRepository;

    @Autowired
    public MessageControllerWSR(WSR<LocalProcedure, RemoteProcedure, Long> wsr, MessageRepository messageRepository) {
        this.wsr = wsr;
        this.messageRepository = messageRepository;

    }

    @Override
    public void afterPropertiesSet() {

        System.out.println(" WSR Message controller start");
        wsr.addProcedure(LocalProcedure.FORWARDMESSAGE, MessageDTO.class,(data, session) -> {

            if(session.hasId()){

                Message message = new Message();
                message.setContent(data.getContent());
                message.setIdReceiver(data.getReceiverId());
                message.setIdSender(session.getId());
                message.setSentDate(Instant.now());

                messageRepository.saveMessage(message);


                try{
                    Session<RemoteProcedure, Long> receiverSession = wsr.findSession(data.getReceiverId());
                    receiverSession.executeRemoteProcedure(RemoteProcedure.ADDMESSAGE,MessageDTO.class,data);
                }catch (Exception e){


                }

            }else {
                ErrorDTO errorDTO = new ErrorDTO();
                errorDTO.setStatus("403");
                errorDTO.setMessage("Unauthorized");
                session.executeRemoteProcedure(RemoteProcedure.ERROR, ErrorDTO.class,errorDTO);
            }


        });

        wsr.addProcedure(LocalProcedure.AUTHSESSION, AuthSessionDTO.class,(data, session) -> {

            Long userId = data.getUserId();
            session.setId(userId);

            System.out.println("Authorized User by id: "+data.getUserId());
            final ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setMessage("Authorized succeed");
            errorDTO.setStatus("OK");
            session.executeRemoteProcedure(RemoteProcedure.ERROR,ErrorDTO.class,errorDTO);

        });

    }
}
