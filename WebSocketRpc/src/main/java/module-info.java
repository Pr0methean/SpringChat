module WebSocketRpc {
    requires com.fasterxml.jackson.core;
    requires spring.websocket;
    requires com.fasterxml.jackson.databind;

    exports com.WebSocketRpc.api;
}