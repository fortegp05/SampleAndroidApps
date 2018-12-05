package com.example.fortegp05.sampleandroidapps.lib;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBus {
    private final PublishSubject<Object> bus = PublishSubject.create();
    private static RxBus rxBus;

    private RxBus() {

    }

    public static RxBus getInstance() {
        if (rxBus == null) {
            rxBus = new RxBus();
        }
        return rxBus;
    }

    public void send(final Object event) {
        bus.onNext(event);
    }

    public Observable<Object> toObservable() {
        return bus;
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }
}
