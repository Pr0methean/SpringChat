package com.WebSocketRpc.api;


import com.WebSocketRpc.domain.model.Session;

@FunctionalInterface
public interface ProcedureMethod<T> {
    void execute(T data, Session session);
}
