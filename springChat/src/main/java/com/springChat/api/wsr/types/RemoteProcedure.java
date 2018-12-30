package com.springChat.api.wsr.types;

public enum RemoteProcedure{
    ADDMESSAGE, ERROR, ADDMYMESSAGE;

    @Override
    public String toString() {
        return this.name();
    }
}
