package com.WebSocketRpc.domain.ports;

import com.WebSocketRpc.domain.model.Session;
import org.springframework.web.socket.WebSocketSession;

public interface SessionRepository<T,I> {

    void addSession(Session<T,I> session);

    void removeSession(I Id);

    Session<T,I> getSession(I Id);
}
