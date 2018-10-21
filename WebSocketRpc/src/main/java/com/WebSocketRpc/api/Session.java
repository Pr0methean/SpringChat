package com.WebSocketRpc.api;

public interface Session<T, I> {

    I getID();
    void setID(I id);
    <D> void executeRemoteProcedure(T procedureType, Class<D> dataType, D data);
}
