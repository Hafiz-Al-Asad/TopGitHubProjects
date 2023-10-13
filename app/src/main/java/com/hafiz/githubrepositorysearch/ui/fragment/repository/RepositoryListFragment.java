package com.hafiz.githubrepositorysearch.ui.fragment.repository;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hafiz.githubrepositorysearch.R;
import com.hafiz.githubrepositorysearch.databinding.RepositoryListFragmentBinding;
import com.hafiz.githubrepositorysearch.model.RepositoryDTO;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitError;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitResponseListener;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitResponseObject;
import com.hafiz.githubrepositorysearch.network.retrofit.retrofitServiceImpl.RepositoryListServiceImpl;
import com.hafiz.githubrepositorysearch.util.Utils;

import java.util.List;

public class RepositoryListFragment extends Fragment implements RetrofitResponseListener {

    private RepositoryListFragmentBinding mBinding;
    private RepositoryListViewModel mViewModel;

    private Context mContext;

    private RepositoryListFragmentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.repository_list_fragment, container, false);
        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(RepositoryListViewModel.class);
        mBinding.setViewModel(mViewModel);

        initRequest();
        initListener();
        subscribeUi();
        populateRecyclerView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    private void initListener() {
//        mBinding.buttonFirst.setOnClickListener(v -> requestUserList());
    }

    private void initRequest() {
        requestRepositoryList();
    }

    private void subscribeUi() {
        subscribeUiToList(mViewModel.getList());
    }

    private void subscribeUiToList(LiveData<List<RepositoryDTO>> liveData) {
        liveData.observe(getViewLifecycleOwner(), this::updateRecyclerViewData);
    }

    private void updateRecyclerViewData(List<RepositoryDTO> list) {
        if (adapter != null) {
            adapter.setList(list);
        }
    }

    private void populateRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mContext,
                layoutManager.getOrientation());

        mBinding.recyclerView.setLayoutManager(layoutManager);
        mBinding.recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new RepositoryListFragmentAdapter(mContext);
        mBinding.recyclerView.setAdapter(adapter);
    }

    private void requestRepositoryList() {
        RepositoryListServiceImpl service = new RepositoryListServiceImpl(mContext, this);
        service.request("android", "stars", "desc");
    }

    @Override
    public void onRetrofitResponse(RetrofitResponseObject retrofitResponseObject) {
        if (retrofitResponseObject == null) {
            return;
        }

        if (Utils.equals(retrofitResponseObject.getRequestCode(), RepositoryListServiceImpl.TASK_ID)) {
            try {
                if (retrofitResponseObject.getObject() instanceof RetrofitError) {
                    throw new Exception();
                }

                //noinspection unchecked
                List<RepositoryDTO> list = (List<RepositoryDTO>) retrofitResponseObject.getObject();
                mViewModel.setList(list);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}