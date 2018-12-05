package com.example.fortegp05.sampleandroidapps.viewmodel.fragment;

import com.example.fortegp05.sampleandroidapps.entity.SampleDataSaveResultEventEntity;
import com.example.fortegp05.sampleandroidapps.entity.SampleTableRegisterEntity;
import com.example.fortegp05.sampleandroidapps.lib.ExpApplication;
import com.example.fortegp05.sampleandroidapps.lib.RxBus;
import com.example.fortegp05.sampleandroidapps.model.database.repository.SampleRepository;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SqliteSampleEditViewModel {
    private boolean isProcessing = false;

    public void saveData(SampleTableRegisterEntity tableData) {
        SampleRepository sampleRep = ExpApplication.getInstance().getRepositoryLocator().getSampleRepository();
        sampleRep.save(tableData, this.observer);
    }

    private Observer observer = new Observer<Boolean>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Boolean result) {
            try {
                SampleDataSaveResultEventEntity sampleDataSaveResultEventEntity = new SampleDataSaveResultEventEntity();
                sampleDataSaveResultEventEntity.setResult(result);

                // イベントを呼び出す
                RxBus.getInstance().send(sampleDataSaveResultEventEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    public boolean isProcessing() {
        return isProcessing;
    }

    public void setProcessing(boolean processing) {
        isProcessing = processing;
    }
}
