package com.example.fortegp05.sampleandroidapps.viewmodel.fragment;

import com.example.fortegp05.sampleandroidapps.entity.ConnpassEventApiCompleteEventEntity;
import com.example.fortegp05.sampleandroidapps.entity.ConnpassEventApiParameterEntity;
import com.example.fortegp05.sampleandroidapps.entity.ConnpassEventApiResponseEntity;
import com.example.fortegp05.sampleandroidapps.entity.ConnpassEventListItemEntity;
import com.example.fortegp05.sampleandroidapps.env.Env;
import com.example.fortegp05.sampleandroidapps.lib.RxBus;
import com.example.fortegp05.sampleandroidapps.model.api.ConnpassEventApi;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ConnpassEventListViewModel {

    public void getEventListData() {

        ConnpassEventApiParameterEntity connpassEventApiParameter = new ConnpassEventApiParameterEntity();
        connpassEventApiParameter.setStart(1);
        connpassEventApiParameter.setCount(Env.CONNPASS_EVENT_API_DATA_COUNT);
        connpassEventApiParameter.setOrder(Env.CONNPASS_EVENT_API_ORDER_NEW);

        ConnpassEventApi connpassEventApi = new ConnpassEventApi();
        connpassEventApi.getData(connpassEventApiParameter, this.observer);
    }

    private Observer observer = new Observer<ConnpassEventApiResponseEntity>() {
        ConnpassEventApiCompleteEventEntity connpassEventApiCompleteEvent = new ConnpassEventApiCompleteEventEntity();

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(ConnpassEventApiResponseEntity response) {

            try {
                ArrayList<ConnpassEventListItemEntity> eventListData = new ArrayList<>();
                for (ConnpassEventApiResponseEntity.Event event : response.getEvents()) {
                    ConnpassEventListItemEntity connpassEventListItemEntity = new ConnpassEventListItemEntity();
                    connpassEventListItemEntity.setEventName(event.getTitle());
                    eventListData.add(connpassEventListItemEntity);
                }
                connpassEventApiCompleteEvent.setEventListData(eventListData);
                connpassEventApiCompleteEvent.setResult(true);
            } catch (Exception e) {
                e.printStackTrace();
                connpassEventApiCompleteEvent.setResult(false);
            }

            // イベントを呼び出す
            RxBus.getInstance().send(connpassEventApiCompleteEvent);
        }

        @Override
        public void onError(Throwable e) {
            // イベントを呼び出す
            RxBus.getInstance().send(connpassEventApiCompleteEvent);
        }

        @Override
        public void onComplete() {

        }
    };

}
