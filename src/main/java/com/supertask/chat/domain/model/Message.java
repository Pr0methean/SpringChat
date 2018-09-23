package com.supertask.chat.domain.model;

import java.time.Instant;

public class Message {

    private Long id;
    private String content;
    private Instant sendDate;
    private Long idSender;
    private Long idReceiver;

    public Message() {
    }

    public Message(Long id, String content, Instant sendDate, Long idSender, Long idReceiver) {
        this.id = id;
        this.content = content;
        this.sendDate = sendDate;
        this.idSender = idSender;
        this.idReceiver = idReceiver;
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

    public Long getIdSender() {
        return idSender;
    }

    public void setIdSender(Long idSender) {
        this.idSender = idSender;
    }

    public Long getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(Long idReceiver) {
        this.idReceiver = idReceiver;
    }
}

