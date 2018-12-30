package com.WebSocketRpc.api;

@FunctionalInterface
public interface ProcedureMethod<RT,I,T> {
    void execute(T data, Session<RT,I> session);
}
