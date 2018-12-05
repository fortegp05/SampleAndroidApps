package com.example.fortegp05.sampleandroidapps.entity;

import java.util.ArrayList;

public class GithubRepositoryApiCompleteEventEntity {
    ArrayList<GithubRepositoryListItemEntity> listItems;
    boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ArrayList<GithubRepositoryListItemEntity> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<GithubRepositoryListItemEntity> listItems) {
        this.listItems = listItems;
    }
}
