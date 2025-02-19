package com.hafiz.githubrepositorysearch.ui.fragment.repository;

import android.app.Activity;
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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hafiz.githubrepositorysearch.R;
import com.hafiz.githubrepositorysearch.databinding.RepositoryListFragmentBinding;
import com.hafiz.githubrepositorysearch.model.RepositoryDTO;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitError;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitResponseListener;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitResponseObject;
import com.hafiz.githubrepositorysearch.network.retrofit.retrofitServiceImpl.RepositoryListServiceImpl;
import com.hafiz.githubrepositorysearch.util.Utils;
import com.hafiz.githubrepositorysearch.viewmodel.RepositoryListSharedViewModel;
import com.hafiz.githubrepositorysearch.viewmodel.RepositoryListViewModel;

import java.util.List;

public class RepositoryListFragment extends Fragment implements RetrofitResponseListener {

    private RepositoryListFragmentBinding mBinding;
    private RepositoryListViewModel mViewModel;

    private RepositoryListSharedViewModel mSharedViewModel;

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
        mSharedViewModel = new ViewModelProvider(requireActivity()).get(RepositoryListSharedViewModel.class);
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
        mBinding.swipeRefreshLayout.setOnRefreshListener(() -> {
            requestRepositoryList();
        });
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
            adapter.setIsLoading(false);
            adapter.setList(list);
        }
    }

    private void populateRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recyclerView.setLayoutManager(layoutManager);
        adapter = new RepositoryListFragmentAdapter(mContext);
        adapter.setOnItemClickListener(dto -> {
            // Handle item click here
            // Pass the selected product to the ViewModel
            mSharedViewModel.setRepository(dto);

            // Navigate to the DetailsFragment
            // Use a NavController or FragmentManager to navigate to the DetailsFragment
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable(BundleKeys.REPOSITORY_DETAILS_KEY, dto);

            Navigation.findNavController(((Activity) mContext).findViewById(R.id.my_nav_host_fragment))
                    .navigate(R.id.SecondFragment, new Bundle());
        });
        mBinding.recyclerView.setAdapter(adapter);
    }

    private void requestRepositoryList() {
        if (adapter != null) {
            adapter.setIsLoading(true);
        }
        RepositoryListServiceImpl service = new RepositoryListServiceImpl(mContext, this);
        service.setProgressDialogVisibility(false);
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
                mBinding.swipeRefreshLayout.setRefreshing(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}