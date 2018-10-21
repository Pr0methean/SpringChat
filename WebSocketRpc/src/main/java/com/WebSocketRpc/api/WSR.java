package com.WebSocketRpc.api;

import com.WebSocketRpc.application.services.s1.WSRWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 *
 * @param <T> procedure Type
 * @param <I> ID type
 */
public class WSR<T,I> {

    private TextWebSocketHandler textWebSocketHandler;

    public WSR() {

        this.textWebSocketHandler = new WSRWebSocketHandler();

    }

    /**
     *
     * @param procedureType ID of procedure
     * @param dataType data type pass to procedure
     * @param method  method to execute for WebClient
     * @param <D> ID procedure type
     */
    public <D> void addProcedure(T procedureType, Class<D> dataType, ProcedureMethod<D> method ){

    }

    public <D> void executeRemoteProcedure(T procedureType, Class<D> dataType, D data){

    }

    public Session<T,I> findSession(I ID){

        return null;
    }

    public TextWebSocketHandler getHandler(){
        return this.textWebSocketHandler;
    }



    public static void main(String[] args) {

        WSR<String,Integer> WSR = new WSR<>();

        WSR.addProcedure("AUTH",String.class,(id, session) -> {
            session.setID(1);
        });

        WSR.addProcedure("ForwardMessage",String.class,(message, session) -> {

            Session<String, Integer> sesion = WSR.findSession(1);
            sesion.executeRemoteProcedure("Login",String.class,"Message");
        });


    }
}
