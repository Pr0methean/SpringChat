package com.supertask.chat.domain.model;

import java.time.Instant;

public class ServerLog {
    Long id;
    Instant dateOfLog;
    String typeOfAction;
    String contentOfAction;

    public ServerLog(Long id, Instant dateOfLog, String typeOfAction, String contentOfAction) {
        this.id = id;
        this.dateOfLog = dateOfLog;
        this.typeOfAction = typeOfAction;
        this.contentOfAction = contentOfAction;
    }
}
