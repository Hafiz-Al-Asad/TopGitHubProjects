package com.hafiz.githubrepositorysearch.network.base;

import com.google.gson.annotations.SerializedName;

/**
 * @author Hafiz
 * @since 12/10/23 12:01 PM
 */
public class BaseResponse {

    @SerializedName("StatusCode")
    private int statusCode;
    @SerializedName("StatusMessage")
    private String statusMessage;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

}
