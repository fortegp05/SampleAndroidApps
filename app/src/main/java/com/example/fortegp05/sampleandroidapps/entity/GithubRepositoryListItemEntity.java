package com.example.fortegp05.sampleandroidapps.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class GithubRepositoryListItemEntity extends BaseObservable {
    @Bindable
    String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
