package com.supertask.chat.api.rest;

import com.supertask.chat.domain.model.dto.Link;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MessageDTO {
    private Long id;
    private String content;
    private Instant sendDate;
    private Long msgReceiverID;
    private Long msgOwnerID;
    private List<Link> _links = new ArrayList();


    public void addLik(Link link) {
        this._links.add(link);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getSendDate() {
        return sendDate;
    }

    public void setSendDate(Instant sendDate) {
        this.sendDate = sendDate;
    }

    public Long getMsgReceiverID() {
        return msgReceiverID;
    }

    public void setMsgReceiverID(Long msgReceiverID) {
        this.msgReceiverID = msgReceiverID;
    }

    public Long getMsgOwnerID() {
        return msgOwnerID;
    }

    public void setMsgOwnerID(Long msgOwnerID) {
        this.msgOwnerID = msgOwnerID;
    }

    public List<Link> get_links() {
        return _links;
    }

    public void set_links(List<Link> _links) {
        this._links = _links;
    }
}
