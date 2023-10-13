package com.hafiz.githubrepositorysearch.network.retrofit.retrofitService;


import com.hafiz.githubrepositorysearch.network.retrofit.response.UserListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserListService {

    @GET("data/v1/user?limit=10")
    Call<UserListResponse> getUserList();
}
