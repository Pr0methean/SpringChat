package com.springChat.api.wsr.types;

public enum LocalProcedure {
    FORWARDMESSAGE,  AUTHSESSION;

    @Override
    public String toString() {
        return this.name();
    }
}
