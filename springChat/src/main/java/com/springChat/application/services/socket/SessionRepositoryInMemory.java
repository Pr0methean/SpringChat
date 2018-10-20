package com.springChat.application.services.socket;

import com.springChat.application.services.socket.model.Session;
import com.springChat.application.services.socket.model.procedure.ProcedureConverter;
import com.springChat.application.services.socket.model.procedure.ProcedureSender;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

public class SessionRepositoryInMemory {

    private Map<String, Session> sessionMap;
    private ProcedureConverter procedureConverter;

    public static SessionRepositoryInMemory configure(ProcedureConverter procedureConverter){

        return new SessionRepositoryInMemory(procedureConverter);
    }


    private SessionRepositoryInMemory(ProcedureConverter procedureConverter) {
        this.procedureConverter = procedureConverter;
        this.sessionMap = new HashMap<>();
    }

    public void addSession(WebSocketSession webSocketSession){
        ProcedureSender procedureSender = new ProcedureSender(webSocketSession, procedureConverter);
        this.sessionMap.put(webSocketSession.getId(),new Session(procedureSender));
    }
    public void removeSession(WebSocketSession webSocketSession){
        this.sessionMap.remove(webSocketSession.getId());
    }

    public Session getSession(WebSocketSession webSocketSession){
       return this.sessionMap.get(webSocketSession.getId());
    }
}
