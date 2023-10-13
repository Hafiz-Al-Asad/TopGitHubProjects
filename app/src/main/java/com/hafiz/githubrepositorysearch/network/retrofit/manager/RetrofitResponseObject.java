package com.hafiz.githubrepositorysearch.network.retrofit.manager;

public class RetrofitResponseObject {

    private Object object;

    private Integer requestCode;

    public RetrofitResponseObject(Object object, Integer requestCode) {
        this.object = object;
        this.requestCode = requestCode;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Integer getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(Integer requestCode) {
        this.requestCode = requestCode;
    }

}
