package com.TestApplication;

import com.WebSocketRpc.application.services.ProcedureDTOConverter;
import com.WebSocketRpc.domain.model.Session;
import com.WebSocketRpc.domain.ports.SessionRepository;
import com.WebSocketRpc.infrastructure.SessionRepositoryInMemory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.socket.WebSocketSession;

public class SessionRepositoryInMemoryTest {


    @Test
    public void shouldReturnSessionByIdAfterSetIdToSession() {

        //given
        WebSocketSession webSocketSession = Mockito.mock(WebSocketSession.class);
        Mockito.when(webSocketSession.getId()).thenReturn("1");
        ProcedureDTOConverter procedureDTOConverter = new ProcedureDTOConverter();
        SessionRepository sessionRepository = new SessionRepositoryInMemory();
        Session session = new Session(webSocketSession,procedureDTOConverter,sessionRepository);
        sessionRepository.addSession(session);

        //when
        session.setId("ID");
        Session sessionWithID = sessionRepository.getSession("ID");

        //then
        Assert.assertEquals(session,sessionWithID);

    }
}
