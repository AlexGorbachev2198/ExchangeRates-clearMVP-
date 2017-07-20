package com.bpc.modulesdk.ui.interfaces;

import android.content.Intent;

/**
 * Created by Samoylov on 16.11.2016.
 *
 * Interface of receiver that want to receive result from onActivityResult
 */

public interface ActivityResultReceiver {
    /**
     * Need to override if you want to receive Intent after ActivityResultRequester#startActivityForResult(ActivityResultReceiver, Intent)}
     *
     * @param data - data that received from result
     */
    void receive(Intent data);
}