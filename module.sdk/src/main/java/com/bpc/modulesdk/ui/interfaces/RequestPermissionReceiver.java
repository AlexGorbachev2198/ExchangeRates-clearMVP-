package com.bpc.modulesdk.ui.interfaces;

/**
 * Created by Siarhei Mikevich on 5/24/17.
 */

public interface RequestPermissionReceiver {

    /**
     * Need to override if you want to receive action after {@link RequestPermissionRequester#needRequestPermissions(RequestPermissionReceiver, String[], int, int)}
     */
    void permissionsAllowed();
}
