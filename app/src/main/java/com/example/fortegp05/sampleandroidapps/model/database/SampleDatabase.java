package com.example.fortegp05.sampleandroidapps.model.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.fortegp05.sampleandroidapps.entity.SampleTableRegisterEntity;
import com.example.fortegp05.sampleandroidapps.model.database.converter.LocalDateTimeTypeConverter;
import com.example.fortegp05.sampleandroidapps.model.database.dao.SampleDao;

@Database(entities = {SampleTableRegisterEntity.class}, version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeTypeConverter.class)
public abstract class SampleDatabase extends RoomDatabase {

    public abstract SampleDao getSampleDao();

}
