package com.hafiz.githubrepositorysearch.network.retrofit.retrofitServiceImpl;

import android.content.Context;

import androidx.annotation.NonNull;

import com.hafiz.githubrepositorysearch.R;
import com.hafiz.githubrepositorysearch.exception.NullResponseFieldException;
import com.hafiz.githubrepositorysearch.model.RepositoryDTO;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.BaseServiceImpl;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitCallback;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitError;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitManager;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitResponseListener;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitResponseObject;
import com.hafiz.githubrepositorysearch.network.retrofit.response.RepositorySearchResponse;
import com.hafiz.githubrepositorysearch.network.retrofit.retrofitService.RepositoryService;
import com.hafiz.githubrepositorysearch.util.UiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RepositoryListServiceImpl extends BaseServiceImpl {

    public static Integer TASK_ID = RepositoryListServiceImpl.class.getName().hashCode();

    public RepositoryListServiceImpl(Context context, RetrofitResponseListener retrofitResponseListener) {
        super(context, retrofitResponseListener);
    }

    public void request(String queryKeyword, String sortedBy, String orderBy) {
        Retrofit retrofit = RetrofitManager.getInstance();

        RepositoryService service = retrofit.create(RepositoryService.class);
        Call<RepositorySearchResponse> call = service.search("search/repositories", queryKeyword, sortedBy, orderBy);

        startProgressDialog();
        setApiName(call);

        call.enqueue(new RetrofitCallback<RepositorySearchResponse>(context) {
            @Override
            public void onResponse(@NonNull Response<RepositorySearchResponse> response) {
                stopProgressDialog();
                handleResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Throwable t) {
                stopProgressDialog();
                if (retrofitResponseListener != null) {
                    retrofitResponseListener.onRetrofitResponse(
                            new RetrofitResponseObject(new RetrofitError(), TASK_ID));
                }
            }
        });
    }

    private void handleResponse(RepositorySearchResponse response) {
        try {
            handleBaseResponse(response);

            List<RepositoryDTO> list = response.getRepositoryList();

            if (list == null) {
                throw new NullResponseFieldException(apiName, FIELD_DETAILS);
            }

            if (retrofitResponseListener != null) {
                retrofitResponseListener.onRetrofitResponse(
                        new RetrofitResponseObject(list, TASK_ID));
            }

        } catch (Exception e) {
            UiUtils.showAlertDialog(context, R.string.dialog_title_error, e.getMessage());
            if (retrofitResponseListener != null) {
                retrofitResponseListener.onRetrofitResponse(
                        new RetrofitResponseObject(new RetrofitError(), TASK_ID));
            }
        }
    }
}
