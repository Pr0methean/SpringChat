package com.springChat.application.services.socket.model.procedure;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class ProcedureSender {

    private final WebSocketSession webSocketSession;
    private final ProcedureConverter procedureConverter;

    public ProcedureSender(WebSocketSession webSocketSession, ProcedureConverter procedureConverter) {

        this.webSocketSession = webSocketSession;
        this.procedureConverter = procedureConverter;
    }

    public void send(Procedure procedure) {
        try {
            String jsonString = procedureConverter.toJsonString(procedure);
            webSocketSession.sendMessage(new TextMessage(jsonString));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
