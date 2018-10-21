package com.WebSocketRpc.domain.model;


import com.WebSocketRpc.application.services.ProcedureSender;

public class Session<T,I> implements com.WebSocketRpc.api.Session<T,I> {

    @Override
    public I getID() {
        return null;
    }

    @Override
    public void setID(I id) {

    }

    @Override
    public <D> void executeRemoteProcedure(T procedureType, Class<D> dataType, D data) {

    }
}
