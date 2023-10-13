package com.hafiz.githubrepositorysearch.ui.fragment;

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
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hafiz.githubrepositorysearch.R;
import com.hafiz.githubrepositorysearch.databinding.FragmentFirstBinding;
import com.hafiz.githubrepositorysearch.model.UserDTO;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitError;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitResponseListener;
import com.hafiz.githubrepositorysearch.network.retrofit.manager.RetrofitResponseObject;
import com.hafiz.githubrepositorysearch.network.retrofit.retrofitServiceImpl.UserListServiceImpl;
import com.hafiz.githubrepositorysearch.ui.fragment.user.UserListFragmentAdapter;
import com.hafiz.githubrepositorysearch.ui.fragment.user.UserListViewModel;
import com.hafiz.githubrepositorysearch.util.Utils;

import java.util.List;

public class FirstFragment extends Fragment implements RetrofitResponseListener {

    private FragmentFirstBinding mBinding;
    private UserListViewModel mViewModel;

    private Context mContext;

    private UserListFragmentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_first, container, false);
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

        mViewModel = new ViewModelProvider(this).get(UserListViewModel.class);
        mBinding.setViewModel(mViewModel);

        initListener();
        subscribeUi();
        populateRecyclerView();
//        subscribeUi();
        //initRequest();
//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    private void initListener() {
        mBinding.buttonFirst.setOnClickListener(v -> requestUserList());
    }

    private void subscribeUi() {
        subscribeUiToList(mViewModel.getList());
    }

    private void subscribeUiToList(LiveData<List<UserDTO>> liveData) {
        liveData.observe(getViewLifecycleOwner(), this::updateRecyclerViewData);
    }

    private void updateRecyclerViewData(List<UserDTO> list) {
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

        adapter = new UserListFragmentAdapter(mContext);
        mBinding.recyclerView.setAdapter(adapter);
    }

    private void requestUserList() {
        UserListServiceImpl service = new UserListServiceImpl(mContext, this);
        service.request();
    }

    @Override
    public void onRetrofitResponse(RetrofitResponseObject retrofitResponseObject) {
        if (retrofitResponseObject == null) {
            return;
        }

        if (Utils.equals(retrofitResponseObject.getRequestCode(), UserListServiceImpl.TASK_ID)) {
            try {
                if (retrofitResponseObject.getObject() instanceof RetrofitError) {
                    throw new Exception();
                }

                //noinspection unchecked
                List<UserDTO> list = (List<UserDTO>) retrofitResponseObject.getObject();
                mViewModel.setList(list);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}