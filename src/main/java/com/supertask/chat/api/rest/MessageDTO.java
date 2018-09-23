package com.supertask.chat.api.rest;

import com.supertask.chat.domain.model.Message;
import com.supertask.chat.domain.model.dto.Link;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MessageDTO {
    private Long id;
    private String content;
    private Instant sendDate;
    private Long idSender;
    private Long idReceiver;
    private List<Link> _links;

    public MessageDTO(Message message) {
        this.id = message.getId();
        this.content = message.getContent();
        this.sendDate = message.getSendDate();
        this.idSender = message.getIdSender();
        this.idReceiver = message.getIdReceiver();
        this._links = new ArrayList<>();
    }

    public void addLik(Link link) {
        this._links.add(link);
    }

}
