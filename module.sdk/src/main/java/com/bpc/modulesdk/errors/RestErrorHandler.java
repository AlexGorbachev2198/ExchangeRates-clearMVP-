package com.bpc.modulesdk.errors;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bpc.modulesdk.BaseApp;
import com.bpc.modulesdk.modulity.facilities.sessionFacility.SessionFacility;
import com.bpc.modulesdk.rest.dto.response.MainResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import ru.bpc.mobilebanksdk.R;

import static ru.bpc.mobilebanksdk.R.string.code;

public class RestErrorHandler {

    private static final String TAG = "RestError";

    public static void handle(View view, Throwable t) {
        Log.e(TAG, "handling error:", t);
        @StringRes int idRes = R.string.error_common_rest;
        int res = getStringRes(t);
        if (res == -1 && t.getCause() != null) res = getStringRes(t.getCause());
        idRes = res != -1 ? res : idRes;

        if (view != null) Snackbar.make(view, idRes, Snackbar.LENGTH_LONG).show();
        else Toast.makeText(BaseApp.getContext(), idRes, Toast.LENGTH_LONG).show();

    }

    public static void handle(View view, MainResponse response) {
        String desc = getStringRes(response);
        if (ErrorCode.INVALID_ACCESS_TOKEN == response.getResult()) {
            SessionFacility.closeSession();
            Toast.makeText(BaseApp.getContext(), desc, Toast.LENGTH_LONG).show();
        } else if (ErrorCode.INTERNAL_SERVER_ERROR == response.getResult()) {
            Toast.makeText(BaseApp.getContext(), desc, Toast.LENGTH_LONG).show();
        } else if (view != null) {
            Snackbar.make(view, desc, Snackbar.LENGTH_LONG).show();
        } else {
            Toast.makeText(BaseApp.getContext(), desc, Toast.LENGTH_LONG).show();
        }
    }

    public static void handle(Throwable t) {
        handle(null, t);
    }

    @StringRes
    private static int getStringRes(Throwable t) {
        @StringRes int idRes = -1;
        if (t instanceof ConnectException) idRes = R.string.error_no_internet;
        else if (t instanceof SocketTimeoutException) idRes = R.string.error_no_connection;
        //HttpException(в т ч 404)
        return idRes;
    }


    private static String getStringRes(MainResponse response) {
        String description = response.getDescription();
        if (response.getErrorDesc() != null && response.getErrorDesc().getUserMessage() != null) {
            return response.getErrorDesc().getUserMessage();
        }
        if (!TextUtils.isEmpty(description)) return description;
        int code = response.getResult();
        @StringRes int idRes = -1;
        switch (code) {
            case ErrorCode.UNSUPPORTED_OPERATION:
                idRes = R.string.error_unsupported_operation;
                break;
            case ErrorCode.INCORRECT_SECURITY_CARD:
                idRes = R.string.error_card_security;
                break;
            case ErrorCode.NOT_ENOUGH_MONEY:
                idRes = R.string.error_not_enough_money;
                break;
            case ErrorCode.INVALID_CARD_STATUS:
                idRes = R.string.error_invalid_card_status;
                break;
            case ErrorCode.INVALID_ACCESS_TOKEN:
                idRes = R.string.error_invalid_access_token;
                break;
            case ErrorCode.NO_LOCATION_PROVIDED:
                idRes = R.string.error_no_location_provided;
                break;
            case ErrorCode.TRANSACTION_LIMIT_EXCEEDED:
                idRes = R.string.error_transaction_limit_exceeded;
                break;
            case ErrorCode.INTERNAL_SERVER_ERROR:
                idRes = R.string.error_internal_server;
                break;
            default:
                idRes = R.string.error_common_rest;
        }
        return BaseApp.getContext().getString(idRes);
    }


}