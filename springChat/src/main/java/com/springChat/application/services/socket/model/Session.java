package com.springChat.application.services.socket.model;

import com.springChat.application.services.socket.model.procedure.Procedure;
import com.springChat.application.services.socket.model.procedure.ProcedureSender;
import com.springChat.domain.model.User;

public class Session {
    private User sessionUser;
    private Boolean isAuthorized;
    private ProcedureSender sender;


    public Session(ProcedureSender sender) {

        this.sender = sender;
    }

   public void sendProcedure(Procedure procedure){
        sender.send(procedure);
   }

    public User getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
    }
}
