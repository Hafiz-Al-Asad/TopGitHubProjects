package com.hafiz.githubrepositorysearch.ui.fragment.repository;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.hafiz.githubrepositorysearch.R;
import com.hafiz.githubrepositorysearch.constant.BundleKeys;
import com.hafiz.githubrepositorysearch.databinding.RepositoryDetailsFragmentBinding;
import com.hafiz.githubrepositorysearch.model.RepositoryDTO;
import com.hafiz.githubrepositorysearch.util.DateTimeUtil;
import com.hafiz.githubrepositorysearch.util.Utils;
import com.hafiz.githubrepositorysearch.viewmodel.RepositoryDetailsViewModel;
import com.squareup.picasso.Picasso;

public class RepositoryDetailsFragment extends Fragment {

    private RepositoryDetailsFragmentBinding mBinding;
    private RepositoryDetailsViewModel mViewModel;

    private Context mContext;

    public static RepositoryDetailsFragment newInstance() {
        return new RepositoryDetailsFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.repository_details_fragment, container, false);
        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RepositoryDetailsViewModel.class);

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey(BundleKeys.REPOSITORY_DETAILS_KEY)) {
                RepositoryDTO repository = (RepositoryDTO) bundle.getSerializable(BundleKeys.REPOSITORY_DETAILS_KEY);
                mViewModel.setRepository(repository);
            }
        }

        subscribeUi();
    }

    private void subscribeUi() {
        subscribeUiToRepository(mViewModel.getRepository());
    }

    private void subscribeUiToRepository(LiveData<RepositoryDTO> liveData) {
        liveData.observe(getViewLifecycleOwner(), data -> {
            mBinding.setViewModel(data);
            populateImageThumbnailSectionUi(data);
            if (data != null) {
                String date = DateTimeUtil.formatDateTimeGithub(data.getUpdatedAt());
                mBinding.tvLastUpdated.setText(date);
            }
        });
    }

    private void populateImageThumbnailSectionUi(RepositoryDTO repositoryDTO) {
        if (repositoryDTO != null && repositoryDTO.getOwner() != null) {
            String url = repositoryDTO.getOwner().getAvatarUrl();
            if (Utils.isNotBlankString(url)) {
                Picasso.get()
                        .load(Utils.trimToNull(url))
                        .placeholder(R.drawable.ic_product)
                        .error(R.drawable.error_loading)
                        .into(mBinding.ivProduct);
            }
        }

    }

}