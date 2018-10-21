package com.WebSocketRpc.infrastructure;

import com.WebSocketRpc.domain.model.Procedure;
import com.WebSocketRpc.domain.ports.ProcedureRepository;

public class ProcedureRepositoryInMemory<T,D> implements ProcedureRepository<T,D> {
    @Override
    public void addProcedure(T type, D data) {

    }

    @Override
    public void removeProcedure(T type) {

    }

    @Override
    public Procedure<T, D> getProcedure(T type) {
        return null;
    }
}
