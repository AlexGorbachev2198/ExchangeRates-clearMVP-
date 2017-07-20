package com.bpc.modulesdk.holders;

import android.support.annotation.NonNull;

import com.bpc.modulesdk.activity.common.GEORestrictionsActivity;
import com.bpc.modulesdk.rest.dto.response.ProfileResponse;

import rx.functions.Action0;

public class AgentProfileHolder {

    private static AgentProfileHolder instance;

    private ProfileResponse profileResponse;
    private Action0 geoPermissionSuccessAction;
    private Action0 geoPermissionErrorAction;


    private AgentProfileHolder() {
    }

    public static AgentProfileHolder getInstance() {
        if (instance == null) {
            instance = new AgentProfileHolder();
        }
        return instance;
    }

    public static void reset() {
        instance = null;
    }

    public static void set(ProfileResponse response, Action0 s, Action0 e) {
        getInstance().setProfileResponse(response);
        getInstance().processProfile(response, s, e);
    }

    public static ProfileResponse get() {
        return getInstance().getProfileResponse();
    }

    //===private====

    private void setProfileResponse(ProfileResponse profileResponse) {
        this.profileResponse = profileResponse;
    }

    // TODO: 26.04.2017  exception если нулевое значение
    private ProfileResponse getProfileResponse() {
        return profileResponse;
    }

    private void processProfile(@NonNull ProfileResponse response, Action0 s, Action0 e) {
        this.geoPermissionSuccessAction = s;
        this.geoPermissionErrorAction = e;
        if (response.getGeoRestrictionEnabled() == 1) {
            GEORestrictionsActivity.start();
        } else setProfileSuccess(true);
    }

    public static void setProfileSuccess(boolean p) {
        if (p) {
            if (getInstance().geoPermissionSuccessAction != null)
                getInstance().geoPermissionSuccessAction.call();
        } else {
            if (getInstance().geoPermissionErrorAction != null)
                getInstance().geoPermissionErrorAction.call();
        }
    }
}