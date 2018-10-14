package com.authenticate.domain.model;

import java.time.Instant;

class Token {
    private String value;
    private Instant dateOfCreated;
    private Instant dateOfExpiry;


    public void UpdateDataOfExpiry(Instant newDate){
        this.dateOfExpiry = newDate;
    }

    public String getValue() {
        return value;
    }

    public Instant getDateOfCreated() {
        return dateOfCreated;
    }

    public Instant getDateOfExpiry() {
        return dateOfExpiry;
    }
}
