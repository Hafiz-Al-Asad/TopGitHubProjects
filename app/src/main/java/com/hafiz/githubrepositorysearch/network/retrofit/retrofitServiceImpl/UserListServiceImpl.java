package com.hafiz.githubrepositorysearch.network.retrofit.retrofitServiceImpl;

import android.content.Context;

import androidx.annotation.NonNull;

import com.hafiz.githubrepositorysearch.R;
import com.hafiz.githubrepositorysearch.exception.NullResponseFieldException;
import com.hafiz.githubrepositorysearch.model.UserDTO;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.BaseServiceImpl;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitCallback;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitError;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitManager;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitResponseListener;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitResponseObject;
import com.hafiz.githubrepositorysearch.network.retrofit.response.UserListResponse;
import com.hafiz.githubrepositorysearch.network.retrofit.retrofitService.UserListService;
import com.hafiz.githubrepositorysearch.util.UiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserListServiceImpl extends BaseServiceImpl {

    public static Integer TASK_ID = UserListServiceImpl.class.getName().hashCode();

    public UserListServiceImpl(Context context, RetrofitResponseListener retrofitResponseListener) {
        super(context, retrofitResponseListener);
    }

    public void request() {
        Retrofit retrofit = RetrofitManager.getInstance();

        UserListService service = retrofit.create(UserListService.class);
        Call<UserListResponse> call = service.getUserList();

        startProgressDialog();
        setApiName(call);

        call.enqueue(new RetrofitCallback<UserListResponse>(context) {
            @Override
            public void onResponse(@NonNull Response<UserListResponse> response) {
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

    private void handleResponse(UserListResponse response) {
        try {
            handleBaseResponse(response);

            List<UserDTO> list = response.getUserList();
//            if (data == null) {
//                throw new NullResponseFieldException(apiName, FIELD_DATA);
//            }

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
