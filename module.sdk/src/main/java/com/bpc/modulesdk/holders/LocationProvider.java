package com.bpc.modulesdk.holders;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bpc.modulesdk.BaseApp;
import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by Smolyaninov on 17.02.2017.
 */

public class LocationProvider implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private final String TAG = "LocationProvider";
    private final static int PERMISSION_REQUEST_CODE = 1;

    private Context context;

    private GoogleApiClient client;
    private LocationRequest locationRequest;

    private Location currentLocation;
    private LocationEntry locationEntry;

    public static final int REQUEST_CHECK_SETTINGS = 0x1;

    private PublishSubject<LocationEntry> subject = PublishSubject.create();
    private static ExecutorService webRequestsExecutor = Executors.newFixedThreadPool(1);
    private boolean isConnected;

    public Observable<LocationEntry> getLocationObservable() {
        return subject;
    }

    LocationProvider() {
        this.context = BaseApp.getContext();
        subject.subscribeOn(Schedulers.from(webRequestsExecutor))
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());

        createLocationRequest();
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
        convertLocationToEntry();
        subject.onNext(locationEntry);
        Log.d(TAG, "onLocationChanged: " + locationEntry.getLatitude() + " " + locationEntry.getLongitude());
    }

    // TODO: 27.04.2017 exception if value is null
    public LocationEntry getLocationEntry() {
        return locationEntry;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        isConnected = true;
        startReceivingUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "onConnectionSuspended: "+i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "onConnectionFailed: "+connectionResult.getErrorCode() );
    }

    void disconnect() {
        stopReceivingUpdates();
        if (client != null) {
            client.disconnect();
        }
    }

    private void createLocationRequest() {
        createClient();
        setUpLocationRequest();
    }

    private void createClient() {
        if (client == null) {
            client = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            client.connect();
        }
    }

    private void setUpLocationRequest() {
        if (locationRequest == null) {
            locationRequest = new LocationRequest();
        }
        locationRequest.setInterval(5 * 60 * 1000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void checkIfLocationEnabled(Activity activity) {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().
                addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.
                checkLocationSettings(client, builder.build());
        result.setResultCallback(result1 -> {
            final Status status = result1.getStatus();
            Log.d(TAG, "checkIfLocationEnabled: " + status.getStatusMessage());
            switch (status.getStatusCode()) {
                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                    try {
                        status.startResolutionForResult(activity, REQUEST_CHECK_SETTINGS);
                    } catch (IntentSender.SendIntentException e) {
                        Log.e(TAG, "checkIfLocationEnabled: ", e);
                    }
                    break;
                case LocationSettingsStatusCodes.SUCCESS:
                    if (locationEntry != null) subject.onNext(locationEntry);
                    break;
                default:
                    break;
            }
        });
    }


    private void startReceivingUpdates() throws SecurityException {
        LocationServices.FusedLocationApi.requestLocationUpdates(client
                , locationRequest, this);
    }

    private void stopReceivingUpdates() {
        if (isConnected) {
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    client, this);
            isConnected = false;
        }
    }

    private void convertLocationToEntry() {
        if (currentLocation != null) {
            locationEntry = new LocationEntry();
            locationEntry.setLatitude(currentLocation.getLatitude());
            locationEntry.setLongitude(currentLocation.getLongitude());
        }
    }
}
