package com.WebSocketRpc.infrastructure;

import com.WebSocketRpc.domain.model.Session;
import com.WebSocketRpc.domain.ports.SessionNotExist;
import com.WebSocketRpc.domain.ports.SessionRepository;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.TreeMap;

public class SessionRepositoryInMemory<RT, I> implements SessionRepository<RT, I> {

    private Map<I, Session<RT, I>> authorizedSessionMap;
    private Map<String, Session<RT, I>> allSessionMap;

    public SessionRepositoryInMemory() {
        authorizedSessionMap = new TreeMap<>();
        allSessionMap = new TreeMap<>();
    }

    @Override
    public void addSession(Session<RT, I> session) {

        if (session.hasID()) {
            authorizedSessionMap.put(session.getID(), session);
            allSessionMap.put(session.getWebSocketSession().getId(), session);
        } else {
            allSessionMap.put(session.getWebSocketSession().getId(), session);
        }
    }


    @Override
    public Session<RT, I> getSession(I id) {
        if (authorizedSessionMap.containsKey(id)) {
            return authorizedSessionMap.get(id);
        } else {
            throw new SessionNotExist("Session no exist in Repository");
        }
    }

    @Override
    public Session<RT, I> getSession(WebSocketSession webSocketSession) {
        if (allSessionMap.containsKey(webSocketSession.getId())) {
            return authorizedSessionMap.get(webSocketSession.getId());
        } else {
            throw new SessionNotExist("Session no exist in Repository");
        }
    }

    @Override
    public void removeSession(WebSocketSession webSocketSession) {
        Session<RT, I> session = allSessionMap.get(webSocketSession.getId());
        if (session.hasID()) {
            authorizedSessionMap.remove(session.getID());
        }
        allSessionMap.remove(webSocketSession.getId());
    }
}
