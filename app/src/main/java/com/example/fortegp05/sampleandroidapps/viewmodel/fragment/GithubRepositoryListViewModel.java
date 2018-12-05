package com.example.fortegp05.sampleandroidapps.viewmodel.fragment;

import com.example.fortegp05.sampleandroidapps.entity.GithubApiParameterEntity;
import com.example.fortegp05.sampleandroidapps.entity.GithubRepositoryApiCompleteEventEntity;
import com.example.fortegp05.sampleandroidapps.entity.GithubRepositoryApiResponseEntity;
import com.example.fortegp05.sampleandroidapps.entity.GithubRepositoryListItemEntity;
import com.example.fortegp05.sampleandroidapps.lib.RxBus;
import com.example.fortegp05.sampleandroidapps.model.api.GithubApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GithubRepositoryListViewModel {

    public void getRepositoryListData() {

        GithubApiParameterEntity param = new GithubApiParameterEntity();
        param.setQ("Android");

        GithubApi api = new GithubApi();
        api.getRepositoryData(param, this.observer);
    }

    private Observer observer = new Observer<GithubRepositoryApiResponseEntity>() {
        GithubRepositoryApiCompleteEventEntity eventResult = new GithubRepositoryApiCompleteEventEntity();

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(GithubRepositoryApiResponseEntity response) {
            eventResult.setResult(false);

            List<GithubRepositoryApiResponseEntity.Repository> repositoryList = response.getItems();
            ArrayList<GithubRepositoryListItemEntity> data = new ArrayList<>();
            for (GithubRepositoryApiResponseEntity.Repository repository : repositoryList) {
                GithubRepositoryListItemEntity listItem = new GithubRepositoryListItemEntity();
                listItem.setFullName(repository.getFull_name());
                data.add(listItem);
            }

            eventResult.setListItems(data);
            if (response.getItems() != null && response.getItems().size() > 0) {
                eventResult.setResult(true);
            }

            // イベントを呼び出す
            RxBus.getInstance().send(eventResult);
        }

        @Override
        public void onError(Throwable e) {
            // イベントを呼び出す
            RxBus.getInstance().send(eventResult);
        }

        @Override
        public void onComplete() {

        }
    };

}
