package com.supertask.chat.vendors.AuthenticateServiceModule.api;

import java.time.Instant;

public class TokenDTO {

    private String value;
    private Instant dateOfCreated;
    private Instant dateOfExpiry;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Instant getDateOfCreated() {
        return dateOfCreated;
    }

    public void setDateOfCreated(Instant dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }

    public Instant getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(Instant dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }
}
