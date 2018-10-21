//package com.WebSocketRpc.application.services;
//
//import com.WebSocketRpc.application.services.s1.ProcedureConverter;
//import com.WebSocketRpc.domain.ports.SessionRepository;
//import com.springChat.application.services.socket.model.Session;
//import com.springChat.application.services.socket.model.procedure.Procedure;
//import com.springChat.application.services.socket.model.procedure.ProcedureConverter;
//import com.springChat.application.services.socket.model.procedure.ProcedureExecutor;
//import com.springChat.application.services.socket.model.procedure.ProcedureTyp;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//
//public class WebSocket extends TextWebSocketHandler {
//
//    private ProcedureExecutor executor;
//    private ProcedureConverter procedureConverter;
//
//    public static WebSocket configure() {
//        ProcedureConverter procedureConverter = new ProcedureConverter();
//        ProcedureExecutor procedureExecutor = new ProcedureExecutor();
//        SessionRepository sessionRepository = com.WebSocketRpc.infrastructure.SessionRepository.configure(procedureConverter);
//        return new WebSocket(sessionRepository, procedureExecutor, procedureConverter);
//    }
//
//
//    private List<WebSocketSession> sessions;
//    private SessionRepository sessionRepository;
//
//
//    private WebSocket(SessionRepository sessionRepository, ProcedureExecutor executor, ProcedureConverter procedureConverter) {
//        this.executor = executor;
//        this.procedureConverter = procedureConverter;
//        this.sessions = new CopyOnWriteArrayList<>();
//        this.sessionRepository = sessionRepository;
//
//
//        this.executor.add("hello", com.WebSocketRpc.infrastructure.SessionRepository.class,(data, session) -> {
//
//            session.
//        });
//
//
//
//    }
//
////    public void executProcedur(String tocken, ProcedureTyp, Object data){
////
////    }
//
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) {
//        sessionRepository.addSession(session);
//
//    }
//
//    @Override
//    public void handleTextMessage(WebSocketSession webSession, TextMessage json) throws IOException {
//        Session session = sessionRepository.getSession(webSession);
//
//        try {
//            Procedure procedure = procedureConverter.toObject(json.toString());
//            executor.execute(procedure.getProcedureTyp(), procedure.getData(), session);
//        } catch (Exception e) {
//            executor.execute(ProcedureTyp.ERROR, "Error parse procedure Json to Object", session);
//        }
//
//
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        sessionRepository.removeSession(session);
//        System.out.println(this.sessions.size() + "<< Web socket left open connection");
//    }
//}
