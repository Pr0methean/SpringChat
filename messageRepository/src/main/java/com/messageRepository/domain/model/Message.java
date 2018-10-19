package com.messageRepository.domain.model;

import java.time.Instant;

public class Message {

    private Long id;
    private String content;
    private Instant sentDate;
    private Long idSender;
    private Long idReceiver;

    public Message() {
    }

    public Message(Long id, String content, Instant sentDate, Long idSender, Long idReceiver) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (content != null ? !content.equals(message.content) : message.content != null) return false;
        if (idSender != null ? !idSender.equals(message.idSender) : message.idSender != null) return false;
        return idReceiver != null ? idReceiver.equals(message.idReceiver) : message.idReceiver == null;
    }

    @Override
    public int hashCode() {
        int result = content != null ? content.hashCode() : 0;
        result = 31 * result + (sentDate != null ? sentDate.hashCode() : 0);
        result = 31 * result + (idSender != null ? idSender.hashCode() : 0);
        result = 31 * result + (idReceiver != null ? idReceiver.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sentDate=" + sentDate +
                ", idSender=" + idSender +
                ", idReceiver=" + idReceiver +
                '}';
    }
}



