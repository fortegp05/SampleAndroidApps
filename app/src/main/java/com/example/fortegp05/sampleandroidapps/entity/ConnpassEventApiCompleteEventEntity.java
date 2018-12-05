package com.example.fortegp05.sampleandroidapps.entity;

import java.util.ArrayList;

public class ConnpassEventApiCompleteEventEntity {
    ArrayList<ConnpassEventListItemEntity> eventListData;
    boolean result;

    public ArrayList<ConnpassEventListItemEntity> getEventListData() {
        return eventListData;
    }

    public boolean isResult() {
        return result;
    }

    public void setEventListData(ArrayList<ConnpassEventListItemEntity> eventListData) {
        this.eventListData = eventListData;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
