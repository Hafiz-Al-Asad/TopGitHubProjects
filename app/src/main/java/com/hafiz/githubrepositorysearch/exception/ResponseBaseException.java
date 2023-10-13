package com.hafiz.githubrepositorysearch.exception;

public class ResponseBaseException extends BaseException {

    public ResponseBaseException() {
        this("Response Exception");
    }

    public ResponseBaseException(String message) {
        super(message);
    }
}
