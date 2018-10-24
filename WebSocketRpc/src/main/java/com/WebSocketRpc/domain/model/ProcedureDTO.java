package com.WebSocketRpc.domain.model;

public class ProcedureDTO<T,D> {
    T procedureType;
    D data;

    public ProcedureDTO(T procedureType, D data) {
        this.procedureType = procedureType;
        this.data = data;
    }

    public ProcedureDTO() {
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
