package com.WebSocketRpc.domain.ports;

import com.WebSocketRpc.domain.model.Procedure;
import com.WebSocketRpc.domain.model.Session;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

public interface ProcedureRepository<T> {

    void addProcedure(Procedure<T> procedure);

    void removeProcedure(T type);

    Procedure<T> getProcedure(T type);
}
