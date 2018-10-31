package com.springChat.api.wsr.dto;

public class AuthSessionDTO {
    private Long userId;

    public AuthSessionDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
