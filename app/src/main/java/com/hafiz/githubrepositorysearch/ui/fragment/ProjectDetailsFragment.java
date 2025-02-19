package com.hafiz.githubrepositorysearch.ui.fragment;

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
import com.hafiz.githubrepositorysearch.databinding.ProjectDetailsFragmentBinding;
import com.hafiz.githubrepositorysearch.model.RepositoryDTO;
import com.hafiz.githubrepositorysearch.util.DateTimeUtil;
import com.hafiz.githubrepositorysearch.util.Utils;
import com.hafiz.githubrepositorysearch.viewmodel.ProjectDetailsViewModel;
import com.hafiz.githubrepositorysearch.viewmodel.ProjectListSharedViewModel;
import com.squareup.picasso.Picasso;

public class ProjectDetailsFragment extends Fragment {

    private ProjectDetailsFragmentBinding mBinding;
    private ProjectDetailsViewModel mViewModel;

    private ProjectListSharedViewModel mSharedViewModel;

    private Context mContext;

    public static ProjectDetailsFragment newInstance() {
        return new ProjectDetailsFragment();
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
                R.layout.project_details_fragment, container, false);
        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProjectDetailsViewModel.class);
        mSharedViewModel = new ViewModelProvider(requireActivity()).get(ProjectListSharedViewModel.class);
        mBinding.setSharedViewModel(mSharedViewModel);

//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            if (bundle.containsKey(BundleKeys.REPOSITORY_DETAILS_KEY)) {
//                RepositoryDTO repository = (RepositoryDTO) bundle.getSerializable(BundleKeys.REPOSITORY_DETAILS_KEY);
//                mViewModel.setRepository(repository);
//            }
//        }

        subscribeUi();
    }

    private void subscribeUi() {
        subscribeUiToRepository(mSharedViewModel.getRepository());
    }

    private void subscribeUiToRepository(LiveData<RepositoryDTO> liveData) {
        liveData.observe(getViewLifecycleOwner(), data -> {
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