package com.bpc.modulesdk.activity.common;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bpc.modulesdk.BaseApp;
import com.bpc.modulesdk.errors.RestErrorHandler;
import com.bpc.modulesdk.holders.AgentProfileHolder;
import com.bpc.modulesdk.holders.LocationHolder;
import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.bpc.modulesdk.utils.DialogHelper;
import com.bpc.modulesdk.utils.PermissionsHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import ru.bpc.mobilebanksdk.R;
import rx.Observable;
import rx.Subscription;

public class GEORestrictionsActivity extends AppCompatActivity {

    public static final String TAG = "GEORestrictionsActivity";

    private final static int REQUEST_CODE_PERMISSIONS = 1;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 22;
    private final String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    private Subscription locationSubscription;
    private Observable<LocationEntry> locationObservable;
    private ProgressDialog progressDialog;

    public static void start() {
        Intent intent = new Intent(BaseApp.getContext(), GEORestrictionsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApp.getContext().startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);
        progressDialog = DialogHelper.getProgressDialog(this);
        execLocationReceiving();
    }
    private boolean checkPlayServices(Activity activity) {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(activity);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(activity, result,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }

            return false;
        }

        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationSubscription != null) locationSubscription.unsubscribe();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (PermissionsHelper.isAllGranted(grantResults) && PermissionsHelper.isPermissionGranted(this, PERMISSIONS))
                execLocationReceiving();
            else {
                finish();
                AgentProfileHolder.setProfileSuccess(false);
            }
        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void execLocationReceiving() {
        Log.d(TAG, "execLocationReceiving: ");
        if(!checkPlayServices(this))return;
        if (!PermissionsHelper.isPermissionGranted(this, PERMISSIONS)) {
            PermissionsHelper.requestPermissions(GEORestrictionsActivity.this, PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS, 0, R.string.permission_location);
            return;
        }
        initLocationObservable(GEORestrictionsActivity.this);

        locationSubscription = locationObservable
                .doOnSubscribe(this::doOnStartLoading)
                .doOnUnsubscribe(this::doOnStopLoading)
                .subscribe(locationEntry -> {
                            finish();
                            AgentProfileHolder.setProfileSuccess(true);
                        },
                        this::handleError);
    }

    private void initLocationObservable(Activity activity) {
        locationObservable = LocationHolder.getLocationProvider().getLocationObservable();
        LocationHolder.getLocationProvider().checkIfLocationEnabled(activity);
    }

    private void doOnStopLoading() {
        progressDialog.dismiss();
    }

    private void doOnStartLoading() {
        progressDialog.show();
    }

    private void handleError(Throwable t) {
        RestErrorHandler.handle(t);
    }

    /*private void showEnableLocationDialog() {
        DialogHelper.showNotClosableYesNoDialog(OperationBaseActivity.this, 0, R.string.retry_enable_location, (dialog, which) -> {
                    dialog.dismiss();
                    LocationHolder.reset();
                }, (dialog, which) -> {
                    LocationHolder.reset();
                    Intent intent = new Intent(this, getApp().getSdkConfig().getMainActivity());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    this.startActivity(intent);
                }
        );
    }*/
}
