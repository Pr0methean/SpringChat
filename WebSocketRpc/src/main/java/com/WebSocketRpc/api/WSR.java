package com.WebSocketRpc.api;

import com.WebSocketRpc.application.services.WSRWebSocketHandler;
import com.WebSocketRpc.domain.model.Procedure;
import com.WebSocketRpc.domain.ports.ProcedureRepository;
import com.WebSocketRpc.domain.ports.SessionRepository;
import com.WebSocketRpc.infrastructure.ProcedureRepositoryInMemory;
import com.WebSocketRpc.infrastructure.SessionRepositoryInMemory;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 *
 * @param <LT> Local type
 * @param <RT> Remote type
 * @param <I> ID type
 */
public class WSR<LT,RT,I> {

    private TextWebSocketHandler textWebSocketHandler;
    private ProcedureRepository<LT> procedureRepository;
    private SessionRepository<RT,I> sessionRepository;

    public WSR() {

        this.procedureRepository = new ProcedureRepositoryInMemory<>();
        this.sessionRepository = new SessionRepositoryInMemory<>();
        this.textWebSocketHandler = WSRWebSocketHandler.configure(sessionRepository,procedureRepository);
    }
    /**
     *
     * @param procedureType ID of procedure
     * @param dataType data type pass to procedure
     * @param method  method to execute for WebClient
     * @param <D> ID procedure type
     */
    public <D> void addProcedure(LT procedureType, Class<D> dataType, ProcedureMethod<RT,I,D> method ){

        System.out.println("Add procdure Facade");
        this.procedureRepository.addProcedure(new Procedure<>(procedureType,method));
    }

    public Session<RT,I> findSession(I id){
        return this.sessionRepository.getSession(id);
    }

    public TextWebSocketHandler getHandler(){
        return this.textWebSocketHandler;
    }

}
