package com.chat.domain.model.dto;

public class Link {
    private String rel;
    private String uri;

    public Link(String rel, String uri) {
        this.rel = rel;
        this.uri = uri;
    }

    public String getRel() {
        return rel;
    }

    public String getUri() {
        return uri;
    }
}
