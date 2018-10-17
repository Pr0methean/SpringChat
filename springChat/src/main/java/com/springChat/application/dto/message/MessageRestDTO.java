package com.springChat.application.dto.message;

import org.springframework.hateoas.ResourceSupport;

import java.time.Instant;

public class MessageRestDTO extends ResourceSupport {

    private Long idMessage;
    private String content;
    private Instant sentDate;
    private Long idSender;
    private Long idReceiver;

    public MessageRestDTO() {
    }

    public MessageRestDTO(Long idMessage, String content, Instant sentDate, Long idSender, Long idReceiver) {
        this.idMessage = idMessage;
        this.content = content;
        this.sentDate = sentDate;
        this.idSender = idSender;
        this.idReceiver = idReceiver;
    }


    public Long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Long idMessage) {
        this.idMessage = idMessage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getSentDate() {
        return sentDate;
    }

    public void setSentDate(Instant sentDate) {
        this.sentDate = sentDate;
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

