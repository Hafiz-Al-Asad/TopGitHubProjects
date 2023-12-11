package com.hafiz.githubrepositorysearch.network.retrofit.retrofitService;

import com.hafiz.githubrepositorysearch.network.retrofit.response.RepositorySearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RepositoryService {
    @GET
    Call<RepositorySearchResponse> search(@Url String endpoint,
                                          @Query("q") String queryKeyword,
                                          @Query("sort") String sortedBy,
                                          @Query("order") String orderBy);
}
