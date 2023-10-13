package com.hafiz.githubrepositorysearch.network.retrofit.retrofitService;

import com.hafiz.githubrepositorysearch.network.retrofit.response.RepositorySearchResponse;
import com.hafiz.githubrepositorysearch.network.retrofit.response.UserListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RepositoryService {
    @GET("search/repositories")
    Call<RepositorySearchResponse> search(@Query("q") String queryKeyword,
                                          @Query("sort") String sortedBy,
                                          @Query("order") String orderBy);
}
