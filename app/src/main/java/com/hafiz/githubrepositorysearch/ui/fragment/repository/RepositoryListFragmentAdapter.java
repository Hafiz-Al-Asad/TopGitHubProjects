package com.hafiz.githubrepositorysearch.ui.fragment.repository;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.hafiz.githubrepositorysearch.R;
import com.hafiz.githubrepositorysearch.constant.BundleKeys;
import com.hafiz.githubrepositorysearch.databinding.RepositoryListItemBinding;
import com.hafiz.githubrepositorysearch.model.RepositoryDTO;
import com.hafiz.githubrepositorysearch.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class RepositoryListFragmentAdapter extends RecyclerView.Adapter<RepositoryListFragmentAdapter.MyViewHolder>
        implements Filterable {

    private Context mContext;
    private List<RepositoryDTO> list = new ArrayList<>();
    private List<RepositoryDTO> fullList = new ArrayList<>();

    private SearchFilter searchFilter;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        RepositoryListItemBinding binding;

        public MyViewHolder(RepositoryListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RepositoryListItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.repository_list_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RepositoryDTO dto = list.get(position);
        holder.binding.setViewModel(dto);
        holder.binding.executePendingBindings();

        holder.itemView.setOnClickListener(v -> {
            if (mContext instanceof Activity) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(BundleKeys.REPOSITORY_DETAILS_KEY, dto);

                Navigation.findNavController(((Activity) mContext).findViewById(R.id.my_nav_host_fragment))
                        .navigate(R.id.SecondFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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
