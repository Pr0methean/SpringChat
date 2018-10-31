package com.springChat.api.wsr.dto;

public class MessageDTO {
    private Long SenderId;
    private Long ReceiverId;
    private String content;

    public MessageDTO() {
    }

    public Long getReceiverId() {
        return ReceiverId;
    }

    public void setReceiverId(Long receiverId) {
        ReceiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSenderId() {
        return SenderId;
    }

    public void setSenderId(Long senderId) {
        SenderId = senderId;
    }
}
