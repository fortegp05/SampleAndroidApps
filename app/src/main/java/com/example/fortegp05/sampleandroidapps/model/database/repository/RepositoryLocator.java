package com.example.fortegp05.sampleandroidapps.model.database.repository;

import android.content.Context;

public class RepositoryLocator {
    private SampleRepository sampleRepository;

    private RepositoryLocator(Context context) {
        this.sampleRepository = new SampleRepository(context);
    }

    public SampleRepository getSampleRepository() {
        return sampleRepository;
    }
}
