package com.example.fortegp05.sampleandroidapps.lib;

import android.app.Application;
import android.content.Context;

import com.example.fortegp05.sampleandroidapps.model.database.repository.RepositoryLocator;
import com.example.fortegp05.sampleandroidapps.viewmodel.ViewModelLocator;

import java.lang.reflect.Constructor;

public class ExpApplication extends Application {

    private ViewModelLocator viewModelLocator;
    private static ExpApplication instance;

    private RepositoryLocator repositoryLocator;

    public void onCreate() {
        super.onCreate();
        this.instance = this;

        try {
            Constructor constructor = ViewModelLocator.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            this.viewModelLocator = (ViewModelLocator) constructor.newInstance();

            Constructor crl = RepositoryLocator.class.getDeclaredConstructor(Context.class);
            crl.setAccessible(true);
            this.repositoryLocator = (RepositoryLocator) crl.newInstance(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ExpApplication getInstance() {
        return instance;
    }

    public ViewModelLocator getViewModelLocator() {
        return viewModelLocator;
    }

    public RepositoryLocator getRepositoryLocator() {
        return repositoryLocator;
    }
}
