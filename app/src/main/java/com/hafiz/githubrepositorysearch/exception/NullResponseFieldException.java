package com.hafiz.githubrepositorysearch.exception;

public class NullResponseFieldException extends ResponseBaseException {

    public NullResponseFieldException() {
        this("");
    }

    public NullResponseFieldException(String fieldName) {
        super("Response [" + fieldName + "]: null");
    }

    public NullResponseFieldException(String apiName, String fieldName) {
        super("Response [" + apiName + ":" + fieldName + "]: null");
    }

    public NullResponseFieldException(String apiName, String fieldName, String message) {
        super("Response [" + apiName + ":" + fieldName + "]: null" + ". " + message);
    }
}
