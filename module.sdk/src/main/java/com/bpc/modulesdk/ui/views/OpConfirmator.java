package com.bpc.modulesdk.ui.views;

import android.support.annotation.StringRes;
import android.view.View;

import com.bpc.modulesdk.utils.callback.Callback;

/**
 * Created by Samoylov on 20.09.2016.
 * <p>
 * Interface of something, that can get confirmation of operation.
 */

public interface OpConfirmator {

    /**
     * Get view for confirmation
     */
    View getView();

    /**
     * Get value for confirm operation
     *
     * @return
     */
    String getConfirmVal();

    /**
     * Set confirm value, if it is allowed
     *
     * @param confirmVal confirmation value
     */
    void setConfirmVal(String confirmVal);

    /**
     * Check that confirmVal entered correctly
     *
     * @return true, if confirmVal correct
     */
    boolean isValid();

    /**
     * Set actions for events
     *
     * @param onCompleteCallback when user completed confirmation with any result
     * @param onChangeCallback   when confirmVal changed
     */
    void setActions(Callback onCompleteCallback, Callback onChangeCallback);

    /**
     * Return error message about incorrect confirmVal
     *
     * @return string res id
     */
    @StringRes
    int getErrorMessage();

    /**
     * Confirmator can handle error by itself.
     * In this case getErrorMessage() will **not** be called.
     *
     * @return true is handled, false otherwise
     */
    boolean handleConfirmationError();
}