package com.WebSocketRpc.domain.model;

import com.WebSocketRpc.api.ProcedureMethod;

public class Procedure<T> {
    T procedureType;
    ProcedureMethod method;

    public T getProcedureType() {
        return procedureType;
    }

    public ProcedureMethod getMethod() {
        return method;
    }
}
