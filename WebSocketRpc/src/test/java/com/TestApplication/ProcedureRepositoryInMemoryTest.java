package com.TestApplication;

import com.WebSocketRpc.domain.model.Procedure;
import com.WebSocketRpc.domain.model.Session;
import com.WebSocketRpc.domain.ports.ProcedureRepository;
import com.WebSocketRpc.infrastructure.ProcedureRepositoryInMemory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ProcedureRepositoryInMemoryTest<LT> {


    @Test
    public void shouldAddProcedure() {
        //given
        ProcedureRepositoryInMemory procedureRepository = new ProcedureRepositoryInMemory();

       // Procedure<String> procedureStart = new Procedure<String>("Login", ((data, session) -> {}));

       //when
      //  procedureRepository.addProcedure(procedureStart);

        //then
        Procedure fetchProcedure = procedureRepository.getProcedure("Login");
        Assert.assertNotNull(fetchProcedure);

    }
}
