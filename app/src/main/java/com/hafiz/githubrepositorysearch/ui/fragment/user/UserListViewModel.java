package com.hafiz.githubrepositorysearch.ui.fragment.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hafiz.githubrepositorysearch.model.UserDTO;

import java.util.List;

public class UserListViewModel extends AndroidViewModel {

    private MutableLiveData<List<UserDTO>> list = new MutableLiveData<>();

    public UserListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<UserDTO>> getList() {
        return list;
    }

    public void setList(List<UserDTO> list) {
        this.list.postValue(list);
    }
}
