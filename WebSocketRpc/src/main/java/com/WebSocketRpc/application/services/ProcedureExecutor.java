//package com.WebSocketRpc.application.services;
//
//
//
//import com.WebSocketRpc.domain.model.Session;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ProcedureExecutor {
//
//    private Map<Object, ProcedureMethod> procedureMap;
//
//    public ProcedureExecutor() {
//        this.procedureMap = new HashMap<>();
//    }
//
//    public void execute(Object typ, Object data, Session session) {
//        ProcedureMethod procedureToExec = this.procedureMap.get(typ);
//
//        if (procedureToExec != null){
//            procedureToExec.execute(data,session);
//        }else{
//            throw new RuntimeException("Procedure not exist");
//        }
//    }
//
//    public <T> void add(Object typ, Class<T> dataType, ProcedureMethod<T> procedure){
//        this.procedureMap.put(typ,procedure);
//    }
//}