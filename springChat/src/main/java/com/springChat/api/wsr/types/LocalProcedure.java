package com.springChat.api.wsr.types;

public enum LocalProcedure {
    SENDMESSAGE, AUTHSESSION;

    @Override
    public String toString() {
        return this.name();
    }
}
