package com.WebSocketRpc.api;

public interface Session<RT, I> {

    I getId();
    void setId(I id);
    boolean hasId();
    <D> void executeRemoteProcedure(RT procedureType, Class<D> dataType, D data);
}
