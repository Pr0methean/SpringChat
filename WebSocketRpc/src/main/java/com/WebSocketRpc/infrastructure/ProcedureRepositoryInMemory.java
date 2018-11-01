package com.WebSocketRpc.infrastructure;

import com.WebSocketRpc.domain.model.Procedure;
import com.WebSocketRpc.domain.ports.ProcedureNotExist;
import com.WebSocketRpc.domain.ports.ProcedureRepository;

import java.util.HashMap;
import java.util.Map;

public class ProcedureRepositoryInMemory<LT> implements ProcedureRepository<LT> {

    private Map<String,Procedure<LT>> procedureMap;
    private Class<?> procedureTypeClass;

    // TODO: 2018-10-31 Add
    public Class<?> getProcedureTypeClass() {
        return procedureTypeClass;
    }

    public ProcedureRepositoryInMemory() {
        procedureMap = new HashMap<>();
    }

    @Override
    public void addProcedure(Procedure<LT> procedure) {
        this.procedureTypeClass = procedure.getProcedureType().getClass();
        System.out.println("ADD procedure to repot : "+procedure.getProcedureType().toString());
        procedureMap.put(procedure.getProcedureType().toString(),procedure);
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
        if (procedureMap.containsKey(type.toString())){
            return procedureMap.get(type.toString());
        }else {
            throw new ProcedureNotExist("Procedure not Exist in Repository. Type: "+type);
        }
    }
}
