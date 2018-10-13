package com.authenticate.infrastructure.MemoryTockenRepository.exceptions;

public class TokenNotFindException extends Exception {
    public TokenNotFindException(String message) {
        super(message);
    }
}
