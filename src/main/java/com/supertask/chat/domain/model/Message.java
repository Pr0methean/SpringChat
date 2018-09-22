package com.supertask.chat.domain.model;

import java.time.Instant;

public class Message {

    private Long id;
    private String content;
    private Instant sendDate;
    private Long msgReceiverID;
    private Long msgOwnerID;


    public Message(Long id, Long messageOwnerId, Instant sendDate, String content ){
        this.id = id;
        this.msgReceiverID = messageOwnerId;
        this.sendDate = sendDate;
        this.content =content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessageOwnerId() {
        return msgReceiverID;
    }

    public void setMessageOwnerId(Long messageOwnerId) {
        this.msgReceiverID = messageOwnerId;
    }

    public Instant getSendDate() {
        return sendDate;
    }

    public void setSendDate(Instant sendDate) {
        this.sendDate = sendDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
