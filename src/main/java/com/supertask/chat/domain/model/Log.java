package com.supertask.chat.domain.model;

import java.time.Instant;

public class Log {
    Long id;
    Instant dateOfLog;
    String typeOfAction;
    String contentOfAction;

    public Log(Long id, Instant dateOfLog, String typeOfAction, String contentOfAction) {
        this.id = id;
        this.dateOfLog = dateOfLog;
        this.typeOfAction = typeOfAction;
        this.contentOfAction = contentOfAction;
    }
}
