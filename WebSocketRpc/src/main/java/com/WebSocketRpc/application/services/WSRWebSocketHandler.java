package com.WebSocketRpc.application.services;

import com.WebSocketRpc.api.ProcedureDTO;
import com.WebSocketRpc.domain.model.Session;
import com.WebSocketRpc.domain.ports.ProcedureRepository;
import com.WebSocketRpc.domain.ports.SessionRepository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
public class WSRWebSocketHandler<LT extends Enum<LT>, RT extends Enum<RT>, I extends Comparable<I>> extends TextWebSocketHandler {


    public static <LT extends Enum<LT>, RT extends Enum<RT>, I extends Comparable<I>> WSRWebSocketHandler<LT, RT, I> configure(SessionRepository<RT, I> sessionRepository, ProcedureDTOConverter<LT> procedureDTOConverter, ProcedureExecutor<LT,RT> procedureExecutor) {

        return new WSRWebSocketHandler<>(sessionRepository, procedureExecutor, procedureDTOConverter);
    }

    private SessionRepository<RT, I> sessionRepository;
    private ProcedureExecutor<LT, RT> procedureExecutor;
    private ProcedureDTOConverter<LT> procedureDTOConverter;

    private WSRWebSocketHandler(SessionRepository sessionRepository, ProcedureExecutor procedureExecutor, ProcedureDTOConverter procedureDTOConverter) {
        this.sessionRepository = sessionRepository;
        this.procedureExecutor = procedureExecutor;
        this.procedureDTOConverter = procedureDTOConverter;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        sessionRepository.addSession(new Session<>(webSocketSession, procedureDTOConverter,sessionRepository));
    }
    @Override
    public void handleTextMessage(WebSocketSession webSocketSession, TextMessage json) {

        try {
            ProcedureDTO<LT, ?> procedureDTO = procedureDTOConverter.toProcedureDTO(json.getPayload());
            Session<RT, I> session = sessionRepository.getSession(webSocketSession);
            procedureExecutor.execute(procedureDTO, session);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error " + e.getMessage());
        }

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
