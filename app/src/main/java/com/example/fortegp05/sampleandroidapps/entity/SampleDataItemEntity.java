package com.example.fortegp05.sampleandroidapps.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.Date;

public class SampleDataItemEntity extends BaseObservable {
    @Bindable
    public Long id;

    @Bindable
    public String name;

    @Bindable
    public String memo;

    @Bindable
    public Date create_date;
}
