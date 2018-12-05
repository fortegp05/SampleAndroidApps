package com.example.fortegp05.sampleandroidapps.model.database.repository;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.fortegp05.sampleandroidapps.entity.SampleDataItemEntity;
import com.example.fortegp05.sampleandroidapps.entity.SampleTableRegisterEntity;
import com.example.fortegp05.sampleandroidapps.model.database.SampleDatabase;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.schedulers.Schedulers;

public class SampleRepository {

    private final SampleDatabase db;

    public SampleRepository(Context context) {

        db = Room.databaseBuilder(context, SampleDatabase.class, "sample_database")
                .allowMainThreadQueries()
                .build();

    }

    public void fetchAll(Observer observer) {
        Observable.create(new ObservableOnSubscribe<ArrayList<SampleDataItemEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<ArrayList<SampleDataItemEntity>> emitter) throws Exception {
                try {
                    ArrayList<SampleDataItemEntity> list = (ArrayList<SampleDataItemEntity>) db.getSampleDao().findAll();
                    emitter.onNext(list);
                } catch (Exception e) {
                    emitter.onNext(null);
                }
                emitter.onComplete();
            }
        })
                .observeOn(Schedulers.newThread())  // observeOn以降(レスポンス)も新しいスレッドでやる
                .subscribeOn(Schedulers.newThread())       // 新しいスレッドで非同期処理でやる
                .subscribe(observer);             // 実行する

    }

    public void save(final SampleTableRegisterEntity sampleTableRegisterEntity, Observer observer) {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                try {
                    db.getSampleDao().save(sampleTableRegisterEntity);
                    emitter.onNext(new Boolean(true));
                } catch (Exception e) {
                    emitter.onNext(new Boolean(false));
                }
                emitter.onComplete();
            }
        })
                .observeOn(Schedulers.newThread())  // observeOn以降(レスポンス)も新しいスレッドでやる
                .subscribeOn(Schedulers.newThread())       // 新しいスレッドで非同期処理でやる
                .subscribe(observer);             // 実行する
    }

}
