package com.WebSocketRpc.domain.model;

import com.WebSocketRpc.api.ProcedureMethod;

public class Procedure<LT> {
    LT procedureType;
    ProcedureMethod method;

    public Procedure(LT procedureType, ProcedureMethod method) {
        this.procedureType = procedureType;
        this.method = method;
    }

    public LT getProcedureType() {
        return procedureType;
    }

    public ProcedureMethod getMethod() {
        return method;
    }
}
