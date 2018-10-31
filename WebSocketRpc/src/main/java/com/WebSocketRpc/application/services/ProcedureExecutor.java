package com.WebSocketRpc.application.services;

import com.WebSocketRpc.domain.model.Procedure;
import com.WebSocketRpc.api.ProcedureDTO;
import com.WebSocketRpc.domain.model.Session;
import com.WebSocketRpc.domain.ports.ProcedureRepository;

public class ProcedureExecutor<LT,RT> {

    public static ProcedureExecutor configure(ProcedureRepository procedureRepository){
        return new ProcedureExecutor(procedureRepository);
    }

    private ProcedureRepository<LT> procedureRepository;

    private ProcedureExecutor(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;

    }

    public <I> void execute(ProcedureDTO<LT,?> procedureDTO, Session<RT,I> session){

        try{
            Procedure<LT> procedure = procedureRepository.getProcedure(procedureDTO.getType());
            procedure.getMethod().execute(procedureDTO.getData(),session);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
