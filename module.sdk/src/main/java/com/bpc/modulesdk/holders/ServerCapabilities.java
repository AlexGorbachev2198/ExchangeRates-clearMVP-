package com.bpc.modulesdk.holders;

import android.os.Handler;
import android.util.Log;

import com.bpc.modulesdk.BaseApp;
import com.bpc.modulesdk.rest.RestServiceFactory;
import com.bpc.modulesdk.rest.dto.response.CapabilitiesResponse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by Masloed on 16.02.2017.
 */

public class ServerCapabilities {

    public static final String TAG = "ServerCapabilities";
    private static final int DELAY_MILLIS = 5000;
    private static ExecutorService webRequestsExecutor = Executors.newFixedThreadPool(1);

    private BehaviorSubject<Object> subject = BehaviorSubject.create();
    private Observable<CapabilitiesResponse> netObservable;
    private Handler handler = new Handler();
    private boolean isLoadingFromNetStarted = false;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            execGetCapabilities();
            handler.postDelayed(runnable, DELAY_MILLIS);
        }
    };

    ServerCapabilities() {
        subject.subscribeOn(Schedulers.from(webRequestsExecutor))
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
        netObservable = RestServiceFactory.get().getServerCapabilities();
        execGetCapabilities();
    }

    public Observable<Object> get() {
        return subject;
    }

    private void execGetCapabilities() {
        netObservable.subscribe(this::handleResponse, this::handleError, this::handleComplete);
    }

    private void handleComplete() {
        Log.d(TAG, "handleComplete: ");
    }

    private void handleResponse(CapabilitiesResponse response) {
        Log.d(TAG, "handleResponse: ");
        if (isLoadingFromNetStarted) {
            handler.removeCallbacks(runnable);
            isLoadingFromNetStarted = false;
        }
        BaseApp.getApp().getSdkConfig().getSupportedFunctionality().setSupportedFunctions(response.getSupportedFunctions());
        subject.onNext(response);
    }

    private void handleError(Throwable t) {
        Log.d(TAG, "handleError: ");
        subject.onNext(t);//TODO корректно обработать ошибку
        doPeriodicRequests();
    }

    private void doPeriodicRequests() {
        if (!isLoadingFromNetStarted) {
            handler.postDelayed(runnable, DELAY_MILLIS);
            isLoadingFromNetStarted = true;
        }
    }

    void stopPeriodicRequests() {
        handler.removeCallbacks(runnable);
    }
}
