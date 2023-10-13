package com.hafiz.githubrepositorysearch.network.retrofit.response;

import com.google.gson.annotations.SerializedName;
import com.hafiz.githubrepositorysearch.model.UserDTO;
import com.hafiz.githubrepositorysearch.network.base.BaseResponse;

import java.util.List;

public class UserListResponse extends BaseResponse {

    @SerializedName("data")
    private List<UserDTO> userList;

    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }


}
