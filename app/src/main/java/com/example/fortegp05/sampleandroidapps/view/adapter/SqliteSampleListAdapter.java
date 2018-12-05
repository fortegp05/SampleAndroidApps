package com.example.fortegp05.sampleandroidapps.view.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.fortegp05.sampleandroidapps.databinding.SqliteSampleListItemBinding;
import com.example.fortegp05.sampleandroidapps.entity.SampleDataItemEntity;

import java.util.ArrayList;

public class SqliteSampleListAdapter extends RecyclerView.Adapter<SqliteSampleListAdapter.ViewHolder> {

    private ArrayList<SampleDataItemEntity> data;

    public SqliteSampleListAdapter(ArrayList<SampleDataItemEntity> data) {
        this.data = data;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        SqliteSampleListItemBinding binding;

        public ViewHolder(SqliteSampleListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        SqliteSampleListItemBinding binding = SqliteSampleListItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        SampleDataItemEntity sampleDataItemEntity = this.data.get(position);
        viewHolder.binding.setSqlliteSampleListData(sampleDataItemEntity);
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
