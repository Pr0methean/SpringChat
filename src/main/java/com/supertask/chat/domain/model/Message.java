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

}
