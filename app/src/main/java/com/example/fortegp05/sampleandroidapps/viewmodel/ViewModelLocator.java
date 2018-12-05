package com.example.fortegp05.sampleandroidapps.viewmodel;

import com.example.fortegp05.sampleandroidapps.viewmodel.fragment.ConnpassEventListViewModel;
import com.example.fortegp05.sampleandroidapps.viewmodel.fragment.GithubRepositoryListViewModel;
import com.example.fortegp05.sampleandroidapps.viewmodel.fragment.SqliteSampleEditViewModel;
import com.example.fortegp05.sampleandroidapps.viewmodel.fragment.SqliteSampleListViewModel;

public class ViewModelLocator {

    private ConnpassEventListViewModel connpassEventListViewModel;
    private SqliteSampleListViewModel sqliteSampleListViewModel;
    private SqliteSampleEditViewModel sqliteSampleEditViewModel;
    private GithubRepositoryListViewModel githubRepositoryListViewModel;

    private ViewModelLocator() {
        connpassEventListViewModel = new ConnpassEventListViewModel();
        sqliteSampleListViewModel = new SqliteSampleListViewModel();
        sqliteSampleEditViewModel = new SqliteSampleEditViewModel();
        githubRepositoryListViewModel = new GithubRepositoryListViewModel();
    }

    public ConnpassEventListViewModel getConnpassEventListViewModel() {
        return connpassEventListViewModel;
    }

    public SqliteSampleListViewModel getSqliteSampleListViewModel() {
        return sqliteSampleListViewModel;
    }

    public SqliteSampleEditViewModel getSqliteSampleEditViewModel() {
        return sqliteSampleEditViewModel;
    }

    public GithubRepositoryListViewModel getGithubRepositoryListViewModel() {
        return githubRepositoryListViewModel;
    }
}
