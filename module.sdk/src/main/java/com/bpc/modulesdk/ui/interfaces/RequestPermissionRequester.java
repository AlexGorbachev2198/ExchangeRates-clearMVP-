package com.bpc.modulesdk.ui.interfaces;

/**
 * Created by Siarhei Mikevich on 5/24/17.
 */

public interface RequestPermissionRequester {

    /**
     * @param requester
     * @param permissions
     * @param permissionRationaleTitle
     * @param permissionRationaleMessage
     * @see RequestPermissionReceiver#permissionsAllowed()
     */
    void needRequestPermissions(RequestPermissionReceiver requester, String[] permissions, int permissionRationaleTitle, int permissionRationaleMessage);
    
}
