package com.WebSocketRpc.domain.ports;

import com.WebSocketRpc.domain.model.Procedure;

import java.util.Map;

public interface ProcedureRepository<LT> {

    void addProcedure(Procedure<LT> procedure);

    void removeProcedure(LT type);

    Procedure<LT> getProcedure(LT type);
    Map<LT, Procedure<LT>> getProcedureMap();

}
