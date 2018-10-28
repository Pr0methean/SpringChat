//package com.TestApplication;
//
//import com.WebSocketRpc.application.services.ProcedureDTOConverter;
//import com.WebSocketRpc.domain.model.Session;
//import com.WebSocketRpc.domain.ports.SessionNotExist;
//import com.WebSocketRpc.domain.ports.SessionRepository;
//import com.WebSocketRpc.infrastructure.SessionRepositoryInMemory;
//import org.junit.Assert;
//import org.junit.Test;
//import org.mockito.Mockito;
//import org.springframework.web.socket.WebSocketSession;
//
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
//
//
//public class SessionRepositoryInMemotyTest {
//
//
//    private Logger log = LogManager.getLogger(String.valueOf(SessionRepositoryInMemotyTest.class));
//    @Test
//    public void shouldAddNewSessionAndCheckIsItExist() {
//        //given
//        WebSocketSession webSocketSession = Mockito.mock(WebSocketSession.class);
//        Mockito.when(webSocketSession.getId()).thenReturn("1");
//
//        ProcedureDTOConverter procedureDTOConverter = new ProcedureDTOConverter();
//        Session sessionStart = new Session(webSocketSession, procedureDTOConverter,);
//
//        //then
//        SessionRepository sessionRepository = new SessionRepositoryInMemory();
//        sessionRepository.addSession(sessionStart);
//
//        //when
//        Session sesssionGot = sessionRepository.getSession(webSocketSession);
//
//        Assert.assertEquals(sesssionGot, sessionStart);
//
//    }
//    @Test
//    public void shouldReturnSessionByIdAfterSetIdToSession() {
//
//        //given
//        WebSocketSession webSocketSession = Mockito.mock(WebSocketSession.class);
//        Mockito.when(webSocketSession.getId()).thenReturn("1");
//        ProcedureDTOConverter procedureDTOConverter = new ProcedureDTOConverter();
//        SessionRepository sessionRepository = new SessionRepositoryInMemory();
//        Session session = new Session(webSocketSession,procedureDTOConverter,sessionRepository);
//        sessionRepository.addSession(session);
//
//        //when
//        session.setID("ID");
//        Session sessionWithID = sessionRepository.getSession("ID");
//
//        //then
//        Assert.assertEquals(session,sessionWithID);
//
//    }
//
//    @Test
//    public void removeSession() {
//        //given
//        WebSocketSession webSocketSession = Mockito.mock(WebSocketSession.class);
//        Mockito.when(webSocketSession.getId()).thenReturn("1");
//        ProcedureDTOConverter procedureDTOConverter = new ProcedureDTOConverter();
//        Session sessionStart = new Session(webSocketSession, procedureDTOConverter);
//
//        //then
//        SessionRepository sessionRepository = new SessionRepositoryInMemory();
//        sessionRepository.addSession(sessionStart);
//
//        //when
//        sessionRepository.removeSession(webSocketSession);
//
//        try {
//            sessionRepository.getSession(webSocketSession);
//            Assert.assertTrue(false);
//        }catch (SessionNotExist e){
//            log.info("Test true with exception : " + e.getMessage());
//            System.out.println("Test true with exception : " + e.getMessage());
//            Assert.assertTrue(true);
//        }
//    }
//    @Test
//    public void shouldCompareAuthorizedAndNotAuthorizesSession() {
//        //given
//        WebSocketSession webSocketSession = Mockito.mock(WebSocketSession.class);
//        Mockito.when(webSocketSession.getId()).thenReturn("1");
//        ProcedureDTOConverter procedureDTOConverter = new ProcedureDTOConverter();
//        Session sessionStart = new Session(webSocketSession, procedureDTOConverter);
//
//
//        //then
//        sessionStart.setID("1_authorized");
//        SessionRepositoryInMemory sessionRepository = new SessionRepositoryInMemory();
//        sessionRepository.addSession(sessionStart);
//
//        //when
//        Session sessionByWebSocker = sessionRepository.getSession(webSocketSession);
//        Session sessionByID = sessionRepository.getSession("1_authorized");
//
//
//        Assert.assertEquals(sessionByWebSocker,sessionByID);
//
//        Object allSession = sessionRepository.getAllSessionMap().get("1");
//        Object authorizedSession = sessionRepository.getAuthorizedSessionMap().get("1_authorized");
//
//        Assert.assertEquals(allSession,authorizedSession);
//
//    }
//    @Test
//    public void shouldNotEqualAuthorizetAndNotAuthorizet() {
//        //given
//        WebSocketSession webSocketSession = Mockito.mock(WebSocketSession.class);
//        Mockito.when(webSocketSession.getId()).thenReturn("1");
//        ProcedureDTOConverter procedureDTOConverter = new ProcedureDTOConverter();
//        Session sessionStart = new Session(webSocketSession, procedureDTOConverter);
//
//
//        //then
//        SessionRepositoryInMemory sessionRepository = new SessionRepositoryInMemory();
//        sessionRepository.addSession(sessionStart);
//
//        //when
//
//
//        Object allSession = sessionRepository.getAllSessionMap().get("1");
//        Object authorizedSession = sessionRepository.getAuthorizedSessionMap().get("1");
//
//        Assert.assertNotSame(allSession,authorizedSession);
//
//    }
//
//}
