package com.supertask.chat.domain.model;

import java.time.Instant;

public class Message {

    private Long id;
    private Long messageOwnerId;

    private Instant sendDate;
    private String content;

    public Message(Long id, Long messageOwnerId, Instant sendDate, String content ){
        this.id = id;
        this.messageOwnerId = messageOwnerId;
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
        return messageOwnerId;
    }

    public void setMessageOwnerId(Long messageOwnerId) {
        this.messageOwnerId = messageOwnerId;
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
