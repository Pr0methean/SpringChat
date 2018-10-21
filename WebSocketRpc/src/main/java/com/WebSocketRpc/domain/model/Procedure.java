package com.WebSocketRpc.domain.model;

public class Procedure<T,D> {
    T procedureType;
    D data;

    public Procedure() {
    }

    public Procedure(T procedureType) {
        this.procedureType = procedureType;
    }

    public T getProcedureType() {
        return procedureType;
    }

    public void setProcedureType(T procedureType) {
        this.procedureType = procedureType;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
