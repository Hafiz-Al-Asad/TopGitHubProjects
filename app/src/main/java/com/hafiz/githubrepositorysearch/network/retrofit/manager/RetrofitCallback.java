package com.hafiz.githubrepositorysearch.network.retrofit.manager;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hafiz.githubrepositorysearch.R;
import com.hafiz.githubrepositorysearch.network.base.BaseResponse;
import com.hafiz.githubrepositorysearch.util.UiUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RetrofitCallback<T> implements Callback<T> {

    private Context context;

    public RetrofitCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (!response.isSuccessful() && response.body() == null) {
            try {
                String message = null;
                ResponseBody errorBody = response.errorBody();
                if (errorBody != null) {
                    String errorBodyString = errorBody.string();
                    ObjectMapper mapper = new ObjectMapper();
                    BaseResponse baseResponse = null;
                    try {
                        baseResponse = mapper.readValue(errorBodyString, BaseResponse.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (baseResponse != null) {
                        message = baseResponse.getStatusMessage();
                    }
                }
//                if (Utils.isBlankString(message)) {
//                    message = context.getResources().getString(R.string.common_internal_server_error);
//                }
                onFailure(call, new Throwable(message));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            onResponse(response, call);
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        UiUtils.showAlertDialog(context,
                R.string.dialog_title_error, t.getMessage());
        onFailure(t, call);
    }

    public void onResponse(@NonNull Response<T> response, @NonNull Call<T> call) {
        onResponse(response);
    }

    public void onFailure(@NonNull Throwable t, @NonNull Call<T> call) {
        onFailure(t);
    }

    public abstract void onResponse(@NonNull Response<T> response);

    public abstract void onFailure(@NonNull Throwable t);
}
