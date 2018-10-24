package com.WebSocketRpc.application.services;

import com.WebSocketRpc.domain.model.Procedure;
import com.WebSocketRpc.domain.model.ProcedureDTO;
import com.WebSocketRpc.domain.model.Session;
import com.WebSocketRpc.domain.ports.ProcedureRepository;

public class ProcedureExecutor<T> {

    public static ProcedureExecutor configure(ProcedureRepository procedureRepository){
        return new ProcedureExecutor(procedureRepository);
    }

    private ProcedureRepository<T> procedureRepository;

    private ProcedureExecutor(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;

    }

    public <I> void execute(ProcedureDTO<T,?> procedureDTO, Session<T,I> session){

        Procedure<T> procedure = procedureRepository.getProcedure(procedureDTO.getProcedureType());
        procedure.getMethod().execute(procedureDTO.getData(),session);

    }
}
