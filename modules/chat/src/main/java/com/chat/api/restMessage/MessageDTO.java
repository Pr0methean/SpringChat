package com.chat.api.restMessage;

import com.chat.domain.model.Message;
import com.chat.domain.model.dto.Link;

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
        this.sendDate = message.getSentDate();
        this.idSender = message.getIdSender();
        this.idReceiver = message.getIdReceiver();
        this._links = new ArrayList<>();
    }

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

    public List<Link> get_links() {
        return _links;
    }

    public void set_links(List<Link> _links) {
        this._links = _links;
    }
}
