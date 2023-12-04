package com.hafiz.githubrepositorysearch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hafiz.githubrepositorysearch.model.RepositoryDTO;

public class RepositoryListSharedViewModel extends ViewModel {
    private MutableLiveData<RepositoryDTO> repository = new MutableLiveData<>();

    public LiveData<RepositoryDTO> getRepository() {
        return repository;
    }

    public void setRepository(RepositoryDTO repository) {
        this.repository.postValue(repository);
    }
}
