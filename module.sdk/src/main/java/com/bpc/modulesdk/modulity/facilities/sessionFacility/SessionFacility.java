package com.bpc.modulesdk.modulity.facilities.sessionFacility;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.bpc.modulesdk.holders.AgentProfileHolder;
import com.bpc.modulesdk.modulity.ModulesManager;
import com.bpc.modulesdk.modulity.listeners.OnSessionCloseListener;
import com.bpc.modulesdk.modulity.listeners.OnSessionOpenListener;
import com.bpc.modulesdk.net.CookieHolder;
import com.bpc.modulesdk.rest.RestServiceFactory;
import com.bpc.modulesdk.rest.dto.response.MainResponse;
import com.bpc.modulesdk.rest.dto.response.ProfileResponse;
import com.bpc.modulesdk.security.SharedPreferencesHelper;

import java.util.Date;

import rx.Observable;


/**
 * Created by Masloed on 08.12.2016.
 */

public class SessionFacility {

    private static final String TAG = "SessionFacility";
    private static SessionFacility mInstance = null;

    private boolean isEndSessionDeferred;

    private static SessionFacility getInstance() {
        if (mInstance == null) {
            mInstance = new SessionFacility();
        }
        return mInstance;
    }

    public static void saveLogin(String login) {
        getInstance().setLogin(login);
        SharedPreferencesHelper.saveLogin(login);
    }

    public static void openSession(ProfileResponse profileResponse) {
        AgentProfileHolder.set(profileResponse, () -> getInstance().startSession(), null);
    }

    public static void closeSession() {
        getInstance().endSession();
    }

    /**
     * Close session not immediately, only when application come to foreground or any {@link com.bpc.modulesdk.activity.common.CommonActivity} start.
     */
    public static void closeSessionDeferred() {
        getInstance().endSessionDeferred();
    }

    public static boolean isCloseSessionDeferred() {
        return getInstance().isEndSessionDeferred();
    }

    @Nullable
    public static String getStoredLogin() {
        return SharedPreferencesHelper.getLogin();
    }

    //==============================================================
    private String login;
    private Date loginTime;
    private boolean isAuthorized = false;

    private SessionFacility() {
    }

    private void setLogin(String login) {
        this.login = login;
    }

    private void startSession() {
        //TODO something when session already exits
        if (TextUtils.isEmpty(login)) ;//TODO exception
        else {
            loginTime = new Date();
            isAuthorized = true;
            isEndSessionDeferred = false;
        }
        //trySendToken();//todo if push is required
        for (OnSessionOpenListener listener : ModulesManager.getOnSessionOpenListeners())
            listener.onSessionOpen();
    }

    private void endSession() {
        Observable<MainResponse> o = RestServiceFactory.get().logout();
        o.subscribe((mainResponse) -> Log.d(TAG, "endSession: success"),
                e -> Log.e(TAG, "endSession: ", e));
        login = null;
        loginTime = null;
        isAuthorized = false;
        isEndSessionDeferred = false;
        CookieHolder.get().reset();//ToDO проверить
        for (OnSessionCloseListener listener : ModulesManager.getOnSessionCloseListeners())
            listener.onSessionClose();
    }

    private void endSessionDeferred() {
        if (isAuthorized)
            isEndSessionDeferred = true;
    }

    private boolean isEndSessionDeferred() {
        return isEndSessionDeferred;
    }

}

