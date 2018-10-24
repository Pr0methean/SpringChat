package com.WebSocketRpc.domain.ports;

import com.WebSocketRpc.domain.model.Session;
import org.springframework.web.socket.WebSocketSession;

/**
 *
 * @param <T>
 * @param <I>
 */
public interface SessionRepository<T,I> {

    void addSession(Session<T,I> session);

    Session<T,I> getSession(I Id);

    Session<T,I> getSession(WebSocketSession session);

    void removeSession(WebSocketSession session);
}
