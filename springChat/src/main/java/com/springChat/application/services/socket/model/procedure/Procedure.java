package com.springChat.application.services.socket.model.procedure;

public class Procedure<T> {
    ProcedureTyp procedureTyp;
    T data;

    public Procedure() {
    }

    public Procedure(ProcedureTyp procedureTyp) {
        this.procedureTyp = procedureTyp;
    }

    public ProcedureTyp getProcedureTyp() {
        return procedureTyp;
    }

    public void setProcedureTyp(ProcedureTyp procedureTyp) {
        this.procedureTyp = procedureTyp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
