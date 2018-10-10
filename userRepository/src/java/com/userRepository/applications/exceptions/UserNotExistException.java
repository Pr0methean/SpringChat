package com.userRepository.applications.exceptions;

public class UserNotExistException extends Exception {
    public String dueTo;
    public UserNotExistException(String message) {
        super(message);
    }
}
