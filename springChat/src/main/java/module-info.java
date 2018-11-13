open module chat {

    //requires
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;
    requires spring.boot;
    requires spring.beans;
    requires spring.websocket;
    requires java.sql;

    requires spring.jdbc;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.naming;

    requires WebSocketRpc;

    requires authenticateService;
    requires userRepository;
    requires spring.hateoas;
    requires messageRepository;

    //exports
}