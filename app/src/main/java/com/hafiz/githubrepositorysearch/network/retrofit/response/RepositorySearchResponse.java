package com.hafiz.githubrepositorysearch.network.retrofit.response;

import com.google.gson.annotations.SerializedName;
import com.hafiz.githubrepositorysearch.model.RepositoryDTO;
import com.hafiz.githubrepositorysearch.network.base.BaseResponse;

import java.util.List;

public class RepositorySearchResponse extends BaseResponse {

    @SerializedName("items")
    private List<RepositoryDTO> repositoryList;

    public List<RepositoryDTO> getRepositoryList() {
        return repositoryList;
    }

    public void setRepositoryList(List<RepositoryDTO> repositoryList) {
        this.repositoryList = repositoryList;
    }
}
