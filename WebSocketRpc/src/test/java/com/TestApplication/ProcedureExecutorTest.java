package com.TestApplication;


import com.WebSocketRpc.api.ProcedureDTO;
import com.WebSocketRpc.api.ProcedureMethod;
import com.WebSocketRpc.application.services.ProcedureDTOConverter;
import com.WebSocketRpc.application.services.ProcedureExecutor;
import com.WebSocketRpc.domain.model.Procedure;
import com.WebSocketRpc.domain.model.Session;
import com.WebSocketRpc.infrastructure.ProcedureRepositoryInMemory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.socket.WebSocketSession;

import java.lang.reflect.Executable;

public class ProcedureExecutorTest {


    @Test
    public void configure() {
//callback JS
        WebSocketSession webSocketSession = Mockito.mock(WebSocketSession.class);
        Mockito.when(webSocketSession.getId()).thenReturn("1");

        ProcedureDTO procedureDTO = Mockito.mock(ProcedureDTO.class);
        Mockito.when(procedureDTO.getType()).thenReturn("LOGIN");
        Mockito.when(procedureDTO.getData()).thenReturn("igor:abcd");

        ProcedureRepositoryInMemory procedureRepository = new ProcedureRepositoryInMemory();
        ProcedureExecutor procedureExecutor = ProcedureExecutor.configure(procedureRepository);
        ProcedureDTOConverter procedureDTOConverter = new ProcedureDTOConverter();

        ProcedureMethod method = (data, session) -> {

            System.out.println(data);
            Assert.assertEquals(data, procedureDTO.getData());
            Assert.assertEquals(session.getWebSocketSession(), webSocketSession);
        };


        Procedure procedure = new Procedure("LOGIN", method);

        procedureRepository.getProcedure(procedure);

        Procedure login = procedureRepository.getProcedure("LOGIN");

        login.getMethod().execute(procedureDTO.getData(), new Session(webSocketSession, procedureDTOConverter));


        procedureExecutor.execute(procedureDTO, new Session(webSocketSession, procedureDTOConverter));

        dupa function = () -> {
            System.out.println("hello");
        };

        function.zaspiewaj();

        MyAdd add = (a, b) -> {
            return a + b;
        };
        add.foo(2, 4);


    }

    @Test
    public void execute() {
        MyAdd addTwoVariable = ((a, b) -> a + b);

        int c = addTwoVariable.foo(1, 2);

        System.out.println(c);

    }

    public interface dupa {
        void zaspiewaj();
    }

    public interface MyAdd {
        int foo(int a, int b);
    }

    @Test
    public void treningLambda() {
        //lamnba dodawanie liczb

        Dzialania dodaj = (a, b) -> {
            System.out.println(a + b);

            return a + b;
        };

        Dzialania odemij = (a, b) -> {
            System.out.println(a - b);
            return a - b;
        };
        Dzialania dzielenie = (a, b) -> {
            if (b != 0) {
                System.out.println((double)a / b);
                return (double) (a / b);
            }else {
                System.out.println("Nie dziel przez 0");
                return 0;
            }
        };

        odemij.result(1,2);

        dodaj.result(1, 2);

        dzielenie.result(1,0);
        dzielenie.result(5,2);


    }

    public interface Dzialania {
        double result(int a, int b);
    }

}
