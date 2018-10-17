package com.messageRepository.applications.dto;

import java.time.Instant;

public class MessageDTOout {

    private Long id;
    private String content;
    private Instant sentDate;
    private Long idSender;
    private Long idReceiver;

    public MessageDTOout() {
    }

    public MessageDTOout(Long id, String content, Instant sentDate, Long idSender, Long idReceiver) {
        this.id = id;
        this.content = content;
        this.sentDate = sentDate;
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
