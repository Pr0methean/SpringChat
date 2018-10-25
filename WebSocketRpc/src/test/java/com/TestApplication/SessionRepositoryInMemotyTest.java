package com.TestApplication;

import com.WebSocketRpc.application.services.ProcedureDTOConverter;
import com.WebSocketRpc.domain.model.Session;
import com.WebSocketRpc.domain.ports.SessionRepository;
import com.WebSocketRpc.infrastructure.SessionRepositoryInMemory;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.socket.WebSocketSession;

public class SessionRepositoryInMemotyTest {

    @Test
    public void shouldAddNewSession() {
        WebSocketSession webSocketSession = Mockito.mock(WebSocketSession.class);
        Mockito.when(webSocketSession.getId()).thenReturn("WebSocketID");
        ProcedureDTOConverter procedureDTOConverter = new ProcedureDTOConverter();
        Session session1 = new Session(webSocketSession,procedureDTOConverter);

        SessionRepository sessionRepository = new SessionRepositoryInMemory();

        session1.setID("ID SESJI");
        sessionRepository.addSession(session1);

//        sessionRepository.

    }

    public Session getSession(Object Id) {
        return null;
    }

    public Session getSession(WebSocketSession session) {
        return null;
    }

    public void removeSession(WebSocketSession session) {

    }
}
