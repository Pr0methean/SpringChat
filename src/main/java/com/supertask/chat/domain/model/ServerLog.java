package com.supertask.chat.domain.model;

import java.time.Instant;

public class ServerLog {
    private Long id;
    private Instant dateOfLog;
    private String typeOfAction;
    private String contentOfAction;

    public ServerLog(Long id, Instant dateOfLog, String typeOfAction, String contentOfAction) {
        this.id = id;
        this.dateOfLog = dateOfLog;
        this.typeOfAction = typeOfAction;
        this.contentOfAction = contentOfAction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateOfLog() {
        return dateOfLog;
    }

    public void setDateOfLog(Instant dateOfLog) {
        this.dateOfLog = dateOfLog;
    }

    public String getTypeOfAction() {
        return typeOfAction;
    }

    public void setTypeOfAction(String typeOfAction) {
        this.typeOfAction = typeOfAction;
    }

    public String getContentOfAction() {
        return contentOfAction;
    }

    public void setContentOfAction(String contentOfAction) {
        this.contentOfAction = contentOfAction;
    }
}
