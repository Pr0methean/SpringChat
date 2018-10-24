package com.WebSocketRpc.api;

public interface Session<RT, I> {

    I getID();
    void setID(I id);
    boolean hasID();
    <D> void executeRemoteProcedure(RT procedureType, Class<D> dataType, D data);
}
