package com.example.fortegp05.sampleandroidapps.model.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.fortegp05.sampleandroidapps.entity.SampleDataItemEntity;
import com.example.fortegp05.sampleandroidapps.entity.SampleTableRegisterEntity;

import java.util.List;

@Dao
public interface SampleDao {

    @Query("select * from sample order by id desc")
    List<SampleDataItemEntity> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(SampleTableRegisterEntity sampleEntity);

}
