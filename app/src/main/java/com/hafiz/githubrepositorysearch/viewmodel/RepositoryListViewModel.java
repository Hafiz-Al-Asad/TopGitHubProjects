package com.hafiz.githubrepositorysearch.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hafiz.githubrepositorysearch.model.RepositoryDTO;

import java.util.List;

public class RepositoryListViewModel extends AndroidViewModel {

    private MutableLiveData<List<RepositoryDTO>> list = new MutableLiveData<>();

    public RepositoryListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<RepositoryDTO>> getList() {
        return list;
    }

    public void setList(List<RepositoryDTO> list) {
        this.list.postValue(list);
    }
}
