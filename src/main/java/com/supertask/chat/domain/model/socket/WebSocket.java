package com.supertask.chat.domain.model.socket;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
public class WebSocket extends TextWebSocketHandler {

    List<WebSocketSession> sessions= new CopyOnWriteArrayList<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        for (WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        this.sessions.remove(session);
        System.out.println(this.sessions.size()+ "<< Web socket left open connection");
    }
}
