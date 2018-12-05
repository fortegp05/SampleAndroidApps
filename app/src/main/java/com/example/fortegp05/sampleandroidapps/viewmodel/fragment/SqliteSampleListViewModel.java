package com.example.fortegp05.sampleandroidapps.viewmodel.fragment;

import com.example.fortegp05.sampleandroidapps.entity.SampleDataGetAllResultEventEntity;
import com.example.fortegp05.sampleandroidapps.entity.SampleDataItemEntity;
import com.example.fortegp05.sampleandroidapps.lib.ExpApplication;
import com.example.fortegp05.sampleandroidapps.lib.RxBus;
import com.example.fortegp05.sampleandroidapps.model.database.repository.SampleRepository;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SqliteSampleListViewModel {

    public void getData() {
        SampleRepository sampleRep = ExpApplication.getInstance().getRepositoryLocator().getSampleRepository();
        sampleRep.fetchAll(this.observer);
    }

    private Observer observer = new Observer<ArrayList<SampleDataItemEntity>>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(ArrayList<SampleDataItemEntity> result) {
            SampleDataGetAllResultEventEntity sampleDataGetAllResultEventEntity = new SampleDataGetAllResultEventEntity();
            try {
                // イベントを呼び出す
                sampleDataGetAllResultEventEntity.setList(result);
            } catch (Exception e) {
                e.printStackTrace();
                sampleDataGetAllResultEventEntity.setList(null);
            }
            RxBus.getInstance().send(sampleDataGetAllResultEventEntity);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

}
