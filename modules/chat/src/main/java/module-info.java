open module chat {

    //requires
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;
    requires spring.boot;
    requires spring.beans;
    requires spring.websocket;
    requires java.sql;
    requires tomcat.embed.core;
    requires spring.jdbc;

    requires authenticateService;
    requires userRepository;
    //exports
}