package com.example.fortegp05.sampleandroidapps.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.Date;

public class SampleDataEditEntity {
    Integer id;

    String name;

    String memo;

    Date createDate;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMemo() {
        return memo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
