package com.springChat.application.services.socket.model.procedure;

import com.springChat.application.services.socket.model.Session;

import java.util.HashMap;
import java.util.Map;

public class ProcedureExecutor {

    private Map<ProcedureTyp,ProcedureMethod> procedureMap;

    public ProcedureExecutor() {
        this.procedureMap = new HashMap<>();
    }

    public void execute(ProcedureTyp typ, Object data, Session session) {
        ProcedureMethod procedureToExec = this.procedureMap.get(typ);
        if (procedureToExec != null){
            procedureToExec.execute(data,session);
        }else{
            throw new RuntimeException("Procedure not exist");
        }
    }

    public <T> void add(ProcedureTyp typ, Class<T> dataType, ProcedureMethod<T> procedure){
        this.procedureMap.put(typ,procedure);
    }
}