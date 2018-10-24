package com.WebSocketRpc.infrastructure;

import com.WebSocketRpc.domain.model.Session;
import com.WebSocketRpc.domain.ports.SessionNotExist;
import com.WebSocketRpc.domain.ports.SessionRepository;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.TreeMap;

public class SessionRepositoryInMemory<T,I>  implements SessionRepository<T,I>  {

    private Map<I,Session<T,I>> authorizedSessionMap;
    private Map<WebSocketSession,Session<T,I>> allSessionMap;

    public SessionRepositoryInMemory() {
        authorizedSessionMap = new TreeMap<>();
    }

    @Override
    public void addSession(Session<T, I> session) {

        if(session.hasID()){
            authorizedSessionMap.put(session.getID(),session);
            allSessionMap.put(session.getWebSocketSession(),session);
        }else {
            allSessionMap.put(session.getWebSocketSession(),session);
        }
    }


    @Override
    public Session<T, I> getSession(I id) {
        if (authorizedSessionMap.containsKey(id)){
            return authorizedSessionMap.get(id);
        }else {
            throw new SessionNotExist("Session no exist in Repository");
        }
    }

    @Override
    public Session<T, I> getSession(WebSocketSession webSocketSession) {
        if (allSessionMap.containsKey(webSocketSession)){
            return authorizedSessionMap.get(webSocketSession.getId());
        }else {
            throw new SessionNotExist("Session no exist in Repository");
        }
    }

    @Override
    public void removeSession(WebSocketSession webSocketSession) {
        Session<T, I> session = allSessionMap.get(webSocketSession);
        if (session.hasID()){
            authorizedSessionMap.remove(session.getID());
        }
        allSessionMap.remove(webSocketSession);
    }
}
