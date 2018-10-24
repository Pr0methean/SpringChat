package com.WebSocketRpc.application.services;

import com.WebSocketRpc.domain.model.ProcedureDTO;
import com.WebSocketRpc.domain.model.Session;
import com.WebSocketRpc.domain.ports.ProcedureRepository;
import com.WebSocketRpc.domain.ports.SessionRepository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class WSRWebSocketHandler<T, I> extends TextWebSocketHandler {


    public static <T,I> WSRWebSocketHandler<T,I> configure(SessionRepository<T,I> sessionRepository, ProcedureRepository procedureRepository){
        ProcedureExecutor procedureExecutor = ProcedureExecutor.configure(procedureRepository);
        ProcedureDTOConverter procedureDTOConverter = new ProcedureDTOConverter();
        return new WSRWebSocketHandler<>(sessionRepository,procedureExecutor,procedureDTOConverter);
    }

    private SessionRepository<T,I> sessionRepository;
    private ProcedureExecutor<T> procedureExecutor;
    private ProcedureDTOConverter<T> procedureDTOConverter;

    private WSRWebSocketHandler(SessionRepository sessionRepository, ProcedureExecutor procedureExecutor, ProcedureDTOConverter procedureDTOConverter) {
        this.sessionRepository = sessionRepository;
        this.procedureExecutor = procedureExecutor;
        this.procedureDTOConverter = procedureDTOConverter;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        sessionRepository.addSession(new Session<>(webSocketSession, procedureDTOConverter));
    }

    @Override
    public void handleTextMessage(WebSocketSession webSocketSession, TextMessage json) throws IOException {

        System.out.println(json);
        ProcedureDTO<T, ?> procedureDTO = procedureDTOConverter.toProcedureDTO(json.getPayload());
        Session<T, I> session = sessionRepository.getSession(webSocketSession);
        procedureExecutor.execute(procedureDTO,session);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) throws Exception {
        sessionRepository.removeSession(webSocketSession);
    }

    @Override
    public void handleTransportError(WebSocketSession var1, Throwable var2) throws Exception {
        // TODO: 21.10.2018 not implemented
    }


}
