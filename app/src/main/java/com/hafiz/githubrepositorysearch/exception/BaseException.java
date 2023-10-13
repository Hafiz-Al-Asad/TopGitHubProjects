package com.hafiz.githubrepositorysearch.exception;

public class BaseException extends Exception {

    private static final String EXCEPTION_MESSAGE = "Custom Exception!!!";

    public BaseException() {
        this(EXCEPTION_MESSAGE);
    }

    public BaseException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = EXCEPTION_MESSAGE;
        }
        return message;
    }
}
