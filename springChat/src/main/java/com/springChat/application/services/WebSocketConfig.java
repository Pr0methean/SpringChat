package com.springChat.application.services;


import com.WebSocketRpc.api.WSR;
import com.springChat.api.wsr.types.LocalProcedure;
import com.springChat.api.wsr.types.RemoteProcedure;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(WSR().getHandler(),"/socket").setAllowedOrigins("*");
    }

    @Bean
    public WSR WSR() {
        return new WSR(LocalProcedure.class,RemoteProcedure.class);
    }
}
