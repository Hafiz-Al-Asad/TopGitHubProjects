package com.hafiz.githubrepositorysearch.ui.fragment.user;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hafiz.githubrepositorysearch.R;
import com.hafiz.githubrepositorysearch.databinding.UserListItemBinding;
import com.hafiz.githubrepositorysearch.model.UserDTO;
import com.hafiz.githubrepositorysearch.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class UserListFragmentAdapter extends RecyclerView.Adapter<UserListFragmentAdapter.MyViewHolder>
        implements Filterable {

    private Context mContext;
    private List<UserDTO> list = new ArrayList<>();
    private List<UserDTO> fullList = new ArrayList<>();

    private SearchFilter searchFilter;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        UserListItemBinding binding;

        public MyViewHolder(UserListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public UserListFragmentAdapter(Context context) {
        this.mContext = context;
    }

    public void setList(List<UserDTO> list) {
        this.fullList = list;

        if (this.fullList == null) {
            this.fullList = new ArrayList<>();
        }

        setVisibleList(list);
    }

    private void setVisibleList(List<UserDTO> list) {
        this.list = list;

        if (this.list == null) {
            this.list = new ArrayList<>();
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserListItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.user_list_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserDTO dto = list.get(position);
        holder.binding.setViewModel(dto);
        holder.binding.executePendingBindings();

        holder.itemView.setOnClickListener(v -> {
            if (mContext instanceof Activity) {
                Bundle bundle = new Bundle();
//                bundle.putSerializable(BundleKeys.WEEKEND_REQUEST_DETAILS, dto);

//                Navigation.findNavController(((Activity) mContext).findViewById(R.id.my_nav_host_fragment))
//                        .navigate(R.id.dest_weekend_details, bundle);
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
                List<UserDTO> filteredList = new ArrayList<>();
                String searchKey = constraint.toString();
                for (UserDTO element : fullList) {
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
            setVisibleList((List<UserDTO>) results.values);
        }

        private boolean match(UserDTO element, String search) {
            if (element == null) {
                return false;
            }
            if (element.getFirstName() != null && Utils.containsIgnoreCase(element.getFirstName(), search)) {
                return true;
            }
            if (element.getLastName() != null && Utils.containsIgnoreCase(element.getLastName(), search)) {
                return true;
            }

            return false;
        }
    }
}
