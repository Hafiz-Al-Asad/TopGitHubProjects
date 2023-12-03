package com.hafiz.githubrepositorysearch.ui.fragment.repository;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.hafiz.githubrepositorysearch.R;
import com.hafiz.githubrepositorysearch.constant.BundleKeys;
import com.hafiz.githubrepositorysearch.databinding.RepositoryListItemBinding;
import com.hafiz.githubrepositorysearch.databinding.RepositoryListItemShimmerBinding;
import com.hafiz.githubrepositorysearch.model.RepositoryDTO;
import com.hafiz.githubrepositorysearch.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RepositoryListFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements Filterable {

    private Context mContext;
    private List<RepositoryDTO> list = new ArrayList<>();
    private List<RepositoryDTO> fullList = new ArrayList<>();

    private SearchFilter searchFilter;

    private boolean isLoading = true;

    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_SHIMMER = 2;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        RepositoryListItemBinding binding;

        public MyViewHolder(RepositoryListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class ShimmerViewHolder extends RecyclerView.ViewHolder {
        ShimmerFrameLayout shimmerLayout;

        public ShimmerViewHolder(View itemView) {
            super(itemView);
            shimmerLayout = itemView.findViewById(R.id.shimmer_layout);
        }
    }

    public RepositoryListFragmentAdapter(Context context) {
        this.mContext = context;
    }

    public void setList(List<RepositoryDTO> list) {
        this.fullList = list;

        if (this.fullList == null) {
            this.fullList = new ArrayList<>();
        }

        isLoading = false;

        setVisibleList(list);
    }

    private void setVisibleList(List<RepositoryDTO> list) {
        this.list = list;

        if (this.list == null) {
            this.list = new ArrayList<>();
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_SHIMMER) {
            RepositoryListItemShimmerBinding binding = DataBindingUtil.inflate(
                    inflater, R.layout.repository_list_item_shimmer, parent, false);
            return new ShimmerViewHolder(binding.getRoot());
        } else {
            RepositoryListItemBinding binding = DataBindingUtil.inflate(
                    inflater, R.layout.repository_list_item, parent, false);
            return new MyViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            RepositoryDTO dto = list.get(position);
            MyViewHolder myViewHolder = (MyViewHolder) holder;

            myViewHolder.binding.setViewModel(dto);
            populateImageThumbnailSectionUi(dto, myViewHolder.binding.ivProduct);
            myViewHolder.binding.executePendingBindings();

            myViewHolder.itemView.setOnClickListener(v -> {
                if (mContext instanceof Activity) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleKeys.REPOSITORY_DETAILS_KEY, dto);

                    Navigation.findNavController(((Activity) mContext).findViewById(R.id.my_nav_host_fragment))
                            .navigate(R.id.SecondFragment, bundle);
                }
            });
        } else if (holder instanceof ShimmerViewHolder) {
            // Handle shimmer animation for shimmer items
            ((ShimmerViewHolder) holder).shimmerLayout.startShimmer();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return isLoading ? VIEW_TYPE_SHIMMER : VIEW_TYPE_ITEM;
    }

    private void populateImageThumbnailSectionUi(RepositoryDTO repositoryDTO, ImageView imageView) {
        if (repositoryDTO != null && repositoryDTO.getOwner() != null) {
            String url = repositoryDTO.getOwner().getAvatarUrl();
            if (Utils.isNotBlankString(url)) {
                Picasso.get()
                        .load(Utils.trimToNull(url))
                        .placeholder(R.drawable.ic_product)
                        .error(R.drawable.error_loading)
                        .into(imageView);
            }
        }

    }

    @Override
    public int getItemCount() {
        return isLoading ? 5 : list.size();
    }

    @Override
    public Filter getFilter() {
        if (searchFilter == null) {
            searchFilter = new SearchFilter();
        }
        return searchFilter;
    }

    public class SearchFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = fullList;
                results.count = fullList.size();
            } else {
                List<RepositoryDTO> filteredList = new ArrayList<>();
                String searchKey = constraint.toString();
                for (RepositoryDTO element : fullList) {
                    if (match(element, searchKey)) {
                        filteredList.add(element);
                    }
                }

                results.values = filteredList;
                results.count = filteredList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            //noinspection unchecked
            setVisibleList((List<RepositoryDTO>) results.values);
        }

        private boolean match(RepositoryDTO element, String search) {
            if (element == null) {
                return false;
            }
            if (element.getName() != null && Utils.containsIgnoreCase(element.getName(), search)) {
                return true;
            }
            if (element.getFullName() != null && Utils.containsIgnoreCase(element.getFullName(), search)) {
                return true;
            }

            return false;
        }
    }
}
