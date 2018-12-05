package com.example.fortegp05.sampleandroidapps.model.api;

import com.example.fortegp05.sampleandroidapps.entity.GithubApiParameterEntity;
import com.example.fortegp05.sampleandroidapps.entity.GithubRepositoryApiResponseEntity;
import com.example.fortegp05.sampleandroidapps.env.Env;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GithubApi {

    private static boolean loading = false;

    public void getRepositoryData(GithubApiParameterEntity parameter, Observer observer) {
        final String url = Env.GITHUB_GET_REPOSITORIES_API_URL + parameter.getRepositoryParameter();

        loading = true;

        Observable.create(new ObservableOnSubscribe<GithubRepositoryApiResponseEntity>() {
            @Override
            public void subscribe(ObservableEmitter<GithubRepositoryApiResponseEntity> emitter) throws Exception {

                // 非同期の処理をする
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();

                OkHttpClient client = new OkHttpClient();
                try {
                    Response res = client.newCall(request).execute();
                    String responseBodyStr = res.body().string();
                    // GSONでjsonをオブジェクト化する
                    Gson gson = new Gson();
                    GithubRepositoryApiResponseEntity responceData = (GithubRepositoryApiResponseEntity) gson.fromJson(responseBodyStr, GithubRepositoryApiResponseEntity.class);
                    emitter.onNext(responceData);
                    emitter.onComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.onError(e);
                } finally {
                    loading = false;
                }
            }
        })
                .observeOn(Schedulers.newThread())  // observeOn以降(レスポンス)も新しいスレッドでやる
                .subscribeOn(Schedulers.newThread())       // 新しいスレッドで非同期処理でやる
                .subscribe(observer);             // 実行する
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }
}
