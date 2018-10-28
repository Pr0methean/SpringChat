package com.WebSocketRpc.api;

import org.springframework.web.socket.WebSocketSession;

public interface Session<RT, I> {

    I getID();
    void setID(I id);
    boolean hasID();
    <D> void executeRemoteProcedure(RT procedureType, Class<D> dataType, D data);
    WebSocketSession getWebSocketSession();
}
