package com.WebSocketRpc.infrastructure;

import com.WebSocketRpc.domain.model.Procedure;
import com.WebSocketRpc.domain.ports.ProcedureNotExist;
import com.WebSocketRpc.domain.ports.ProcedureRepository;

import java.util.Map;
import java.util.TreeMap;

public class ProcedureRepositoryInMemory<LT> implements ProcedureRepository<LT> {

    private Map<LT,Procedure<LT>> procedureMap;

    public ProcedureRepositoryInMemory() {
        procedureMap = new TreeMap<>();
    }

    @Override
    public void addProcedure(Procedure<LT> procedure) {
        procedureMap.put(procedure.getProcedureType(),procedure);
    }

    @Override
    public void removeProcedure(LT type) {
        if (procedureMap.containsKey(type)){
        procedureMap.remove(type);
        }else {
            throw new ProcedureNotExist("Procedure not Exist in Repository");
        }
    }

    @Override
    public Procedure<LT> getProcedure(LT type) {
        if (procedureMap.containsKey(type)){
            return procedureMap.get(type);
        }else {
            throw new ProcedureNotExist("Procedure not Exist in Repository");
        }
    }

    public Map<LT, Procedure<LT>> getProcedureMap() {
        return procedureMap;
    }
}
