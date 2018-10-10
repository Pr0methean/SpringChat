package com.chat.api.restLogger;

import com.chat.domain.model.ServerLog;
import com.chat.domain.model.dto.Link;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ServerLogDTO {
    private Long id;
    private Instant dateOfLog;
    private String typeOfAction;
    private String contentOfAction;
    private Integer status;
    private List<Link> _links;


    public ServerLogDTO(ServerLog serverLog) {
        this.id = serverLog.getId();
        this.dateOfLog = serverLog.getDateOfLog();
        this.typeOfAction = serverLog.getTypeOfAction();
        this.contentOfAction = serverLog.getContentOfAction();
        this.status = serverLog.getStatus();
        this._links = new ArrayList<>();
    }
    public void addLink(Link link) {
        this._links.add(link);
    }

    public Long getId() {
        return id;
    }

    public Instant getDateOfLog() {
        return dateOfLog;
    }

    public String getTypeOfAction() {
        return typeOfAction;
    }

    public String getContentOfAction() {
        return contentOfAction;
    }

    public Integer getStatus() {
        return status;
    }

    public List<Link> get_links() {
        return _links;
    }
}
