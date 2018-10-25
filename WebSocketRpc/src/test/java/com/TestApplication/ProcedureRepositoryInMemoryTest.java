package com.TestApplication;

import com.WebSocketRpc.domain.model.Procedure;
import com.WebSocketRpc.domain.model.Session;
import com.WebSocketRpc.domain.ports.ProcedureRepository;
import com.WebSocketRpc.infrastructure.ProcedureRepositoryInMemory;
import org.junit.Test;

import java.util.Map;

public class ProcedureRepositoryInMemoryTest<LT> {


    @Test
    public void shouldAddProcedure() {
        //given
        ProcedureRepository<LT> procedureRepository = new ProcedureRepositoryInMemory();
//        Session
//
        Procedure<String> procedure1 = new Procedure<String>("Login", ((data, session) -> {

        }));
        //then



    }


    public void removeProcedure(Object type) {

    }


    public Procedure getProcedure(Object type) {
        return null;
    }


    public Map getProcedureMap() {
        return null;
    }
}
