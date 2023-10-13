package com.hafiz.githubrepositorysearch.network.retrofit.response.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseListResponseData implements Serializable {

    @SerializedName("last")
    private Boolean last;
    @SerializedName("number")
    private Integer pageNumber;
    @SerializedName("numberOfElements")
    private Integer numberOfElements;
    @SerializedName("totalElements")
    private Integer totalElements;
    @SerializedName("totalPages")
    private Integer totalPages;

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
