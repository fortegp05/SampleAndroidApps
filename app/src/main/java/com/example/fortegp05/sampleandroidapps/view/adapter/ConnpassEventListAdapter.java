package com.example.fortegp05.sampleandroidapps.view.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.fortegp05.sampleandroidapps.databinding.ConnpassEventListItemBinding;
import com.example.fortegp05.sampleandroidapps.entity.ConnpassEventListItemEntity;

import java.util.ArrayList;

public class ConnpassEventListAdapter extends RecyclerView.Adapter<ConnpassEventListAdapter.ViewHolder> {

    private ArrayList<ConnpassEventListItemEntity> data;

    public ConnpassEventListAdapter(ArrayList<ConnpassEventListItemEntity> data) {
        this.data = data;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ConnpassEventListItemBinding binding;

        public ViewHolder(ConnpassEventListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        ConnpassEventListItemBinding binding = ConnpassEventListItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        ConnpassEventListItemEntity connpassEventListItem = this.data.get(position);
        viewHolder.binding.setConnpassvEventListItemData(connpassEventListItem);
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
