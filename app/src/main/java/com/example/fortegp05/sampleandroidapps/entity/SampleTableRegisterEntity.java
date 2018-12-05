package com.example.fortegp05.sampleandroidapps.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "sample", indices = {@Index(value = {"name"}, unique = true)})
public class SampleTableRegisterEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public Long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "memo")
    public String memo;

    @ColumnInfo(name = "create_date")
    public Date createDate;

}
