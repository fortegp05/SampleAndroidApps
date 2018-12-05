package com.example.fortegp05.sampleandroidapps.model.api;

import com.example.fortegp05.sampleandroidapps.entity.ConnpassEventApiParameterEntity;
import com.example.fortegp05.sampleandroidapps.entity.ConnpassEventApiResponseEntity;
import com.example.fortegp05.sampleandroidapps.env.Env;
import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConnpassEventApi {

    private static boolean loading = false;

    public void getData(ConnpassEventApiParameterEntity parameter, Observer observer) {
        final String url = Env.CONNPASS_EVENT_API_URL + parameter.getParameter();

        loading = true;

        Observable.create(new ObservableOnSubscribe<ConnpassEventApiResponseEntity>() {
            @Override
            public void subscribe(ObservableEmitter<ConnpassEventApiResponseEntity> emitter) throws Exception {

                // 非同期の処理をする
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();

                OkHttpClient client = new OkHttpClient();
                try {
                    Response res = client.newCall(request).execute();
                    // response.bodyから取り出すのが複数回できないので一度変数に入れとく
                    String responseBodyStr = res.body().string();
                    // GSONでjsonをオブジェクト化する
                    Gson gson = new Gson();
                    ConnpassEventApiResponseEntity responceData = (ConnpassEventApiResponseEntity) gson.fromJson(responseBodyStr, ConnpassEventApiResponseEntity.class);
                    emitter.onNext(responceData);
                    emitter.onComplete();
                } catch (IOException e) {
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
