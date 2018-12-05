package com.example.fortegp05.sampleandroidapps.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class ConnpassEventListItemEntity extends BaseObservable {
    @Bindable
    private String eventName;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
