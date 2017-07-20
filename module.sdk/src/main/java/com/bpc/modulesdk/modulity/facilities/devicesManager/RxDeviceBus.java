package com.bpc.modulesdk.modulity.facilities.devicesManager;

import com.bpc.modulesdk.Event;

import rx.Observable;
import rx.subjects.PublishSubject;

public class RxDeviceBus {

    private static RxDeviceBus instance;

    private PublishSubject<Event> subject = PublishSubject.create();

    public static synchronized RxDeviceBus getInstance() {
        if (instance == null) {
            instance = new RxDeviceBus();
        }
        return instance;
    }

    /**
     * Pass any event down to event listeners.
     */
    public void setEvent(Event event) {
        subject.onNext(event);
    }

    /**
     * Subscribe to this Observable. On event, do something
     * e.g. replace a fragment
     */
    public Observable<Event> getEvents() {
        return subject;
    }
}