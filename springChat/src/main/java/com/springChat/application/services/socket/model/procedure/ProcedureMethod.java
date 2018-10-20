package com.springChat.application.services.socket.model.procedure;

import com.springChat.application.services.socket.model.Session;

@FunctionalInterface
public interface ProcedureMethod<T> {
    void execute(T data, Session session);
}
