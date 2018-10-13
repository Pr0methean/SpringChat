package com.authenticate.appliacation.dto;

import java.time.Instant;
import java.util.Objects;

public class TokenDTO {

    private String value;
    private Instant dateOfCreated;
    private Instant dateOfExpiry;

    public TokenDTO() {
    }

    public TokenDTO(String value, Instant dateOfCreated, Instant dateOfExpiry) {
        this.value = value;
        this.dateOfCreated = dateOfCreated;
        this.dateOfExpiry = dateOfExpiry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenDTO tokenDTO = (TokenDTO) o;
        return Objects.equals(value, tokenDTO.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

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

    public Instant getDateOfExpire() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(Instant dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }
}
