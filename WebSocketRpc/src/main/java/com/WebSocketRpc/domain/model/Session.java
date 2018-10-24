package com.WebSocketRpc.domain.model;


import com.WebSocketRpc.application.services.ProcedureDTOConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.nio.charset.Charset;

public class Session<T, I> implements com.WebSocketRpc.api.Session<T, I> {

    private ProcedureDTOConverter<T> procedureDTOConverter;
    private WebSocketSession webSocketSession;
    private I ID;

    public Session(WebSocketSession webSocketSession, ProcedureDTOConverter procedureDTOConverter) {
        this.webSocketSession = webSocketSession;
        this.procedureDTOConverter = procedureDTOConverter;
    }

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    @Override
    public I getID() {
        return ID;
    }

    @Override
    public void setID(I id) {
        this.ID = id;
    }

    @Override
    public boolean hasID() {
        return (this.ID != null);
    }

    @Override
    public <D> void executeRemoteProcedure(T procedureType, Class<D> dataType, D data) {

        ProcedureDTO<T, D> procedureDTO = new ProcedureDTO<>(procedureType, data);
        try {
            String jsonString = procedureDTOConverter.toJsonString(procedureDTO);
            TextMessage textMessage = new TextMessage(jsonString.getBytes(Charset.forName("UTF-8")));
            webSocketSession.sendMessage(textMessage);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
