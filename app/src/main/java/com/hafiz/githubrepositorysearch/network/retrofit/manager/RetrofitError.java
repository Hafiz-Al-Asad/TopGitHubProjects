package com.hafiz.githubrepositorysearch.network.retrofit.manager;

public class RetrofitError {

    private Integer code;
    private String message;

    public RetrofitError() {
        this(null, null);
    }

    public RetrofitError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
