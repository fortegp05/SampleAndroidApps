package com.example.fortegp05.sampleandroidapps.model.database.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

import io.reactivex.annotations.Nullable;

public class LocalDateTimeTypeConverter {

    @TypeConverter
    public static Date timeToDate(@Nullable Long time) {
        return time == null ? null : new Date(time);
    }

    @TypeConverter
    public static Long dateToTime(@Nullable Date date) {
        return date == null ? null : date.getTime();
    }

}
