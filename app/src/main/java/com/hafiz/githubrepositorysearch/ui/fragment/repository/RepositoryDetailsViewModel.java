package com.hafiz.githubrepositorysearch.ui.fragment.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hafiz.githubrepositorysearch.model.RepositoryDTO;

public class RepositoryDetailsViewModel extends AndroidViewModel {

    private MutableLiveData<RepositoryDTO> repository = new MutableLiveData<>();

    public RepositoryDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<RepositoryDTO> getRepository() {
        return repository;
    }

    public void setRepository(RepositoryDTO repository) {
        this.repository.postValue(repository);
    }
}
