package com.bpc.modulesdk.ui.interfaces;

import android.content.Intent;

/**
 * Created by Samoylov on 16.11.2016.
 */

public interface ActivityResultRequester {
    /**
     * Request to start activity for result
     *
     * @param receiver - who ask. Need to return result to it.
     * @param intent   - data for startActivityForResult
     * @see ActivityResultReceiver#receive(Intent)
     */
    void startActivityForResult(ActivityResultReceiver receiver, Intent intent);
}