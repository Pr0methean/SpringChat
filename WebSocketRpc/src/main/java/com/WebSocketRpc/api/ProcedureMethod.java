package com.WebSocketRpc.api;

@FunctionalInterface
public interface ProcedureMethod<T> {
    void execute(T data, Session session);
}
