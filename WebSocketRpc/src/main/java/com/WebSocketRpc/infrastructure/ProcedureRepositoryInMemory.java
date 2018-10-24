package com.WebSocketRpc.infrastructure;

import com.WebSocketRpc.domain.model.Procedure;
import com.WebSocketRpc.domain.ports.ProcedureNotExist;
import com.WebSocketRpc.domain.ports.ProcedureRepository;

import java.util.Map;
import java.util.TreeMap;

public class ProcedureRepositoryInMemory<T> implements ProcedureRepository<T> {

    private Map<T,Procedure<T>> procedureMap;

    public ProcedureRepositoryInMemory() {
        procedureMap = new TreeMap<>();
    }

    @Override
    public void addProcedure(Procedure<T> procedure) {
        procedureMap.put(procedure.getProcedureType(),procedure);
    }

    @Override
    public void removeProcedure(T type) {
        if (procedureMap.containsKey(type)){
        procedureMap.remove(type);
        }else {
            throw new ProcedureNotExist("Procedure not Exist in Repository");
        }
    }

    @Override
    public Procedure<T> getProcedure(T type) {
        if (procedureMap.containsKey(type)){
            return procedureMap.get(type);
        }else {
            throw new ProcedureNotExist("Procedure not Exist in Repository");
        }
    }
}
