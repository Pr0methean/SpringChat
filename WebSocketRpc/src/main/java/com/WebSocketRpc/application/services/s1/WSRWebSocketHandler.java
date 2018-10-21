package com.WebSocketRpc.application.services.s1;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class WSRWebSocketHandler extends TextWebSocketHandler {


    public static WSRWebSocketHandler configure(){

        return new WSRWebSocketHandler();
    }

    public WSRWebSocketHandler() { }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {

    }

    @Override
    public void handleTextMessage(WebSocketSession webSession, TextMessage json) throws IOException {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession var1, Throwable var2) throws Exception {
        // TODO: 21.10.2018 not implemented
    }


}
