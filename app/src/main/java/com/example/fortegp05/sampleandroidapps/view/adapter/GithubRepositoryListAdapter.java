package com.example.fortegp05.sampleandroidapps.view.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.fortegp05.sampleandroidapps.databinding.GithubRepositoryListItemBinding;
import com.example.fortegp05.sampleandroidapps.entity.GithubRepositoryListItemEntity;

import java.util.List;

public class GithubRepositoryListAdapter extends RecyclerView.Adapter<GithubRepositoryListAdapter.ViewHolder> {

    private List<GithubRepositoryListItemEntity> data;

    public GithubRepositoryListAdapter(List<GithubRepositoryListItemEntity> data) {
        this.data = data;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        GithubRepositoryListItemBinding binding;

        public ViewHolder(GithubRepositoryListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        GithubRepositoryListItemBinding binding = GithubRepositoryListItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        GithubRepositoryListItemEntity item = this.data.get(position);
        viewHolder.binding.setGithubRepositoryData(item);
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
