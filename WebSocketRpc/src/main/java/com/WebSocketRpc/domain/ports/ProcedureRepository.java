package com.WebSocketRpc.domain.ports;

import com.WebSocketRpc.domain.model.Procedure;
import com.WebSocketRpc.domain.model.Session;
import org.springframework.web.socket.WebSocketSession;

public interface ProcedureRepository<T,D> {

    void addProcedure(T type, D data);

    void removeProcedure(T type);

    Procedure<T,D> getProcedure(T type);
}
