package com.supertask.chat.domain.model;

import java.time.Instant;

public class ServerLog {
    private Long id;
    private Instant dateOfLog;
    private String typeOfAction;
    private String contentOfAction;
    private Integer status;

    public ServerLog(Instant dateOfLog, String typeOfAction, String contentOfAction, Integer status) {
        this.dateOfLog = dateOfLog;
        this.typeOfAction = typeOfAction;
        this.contentOfAction = contentOfAction;
        this.status = status;
    }

    public ServerLog(Long id, Instant dateOfLog, String typeOfAction, String contentOfAction, Integer status) {
        this.id = id;
        this.dateOfLog = dateOfLog;
        this.typeOfAction = typeOfAction;
        this.contentOfAction = contentOfAction;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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


    @Override
    public String toString() {
        return "ServerLog{" +
                "id=" + id +
                ", dateOfLog=" + dateOfLog +
                ", typeOfAction='" + typeOfAction + '\'' +
                ", contentOfAction='" + contentOfAction + '\'' +
                '}';
    }
}
