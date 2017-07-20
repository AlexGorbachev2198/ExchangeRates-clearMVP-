package com.bpc.modulesdk.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Samoylov on 16.01.2017.
 */
public class PermissionsHelper {

    private static final String TAG = PermissionsHelper.class.getSimpleName();

    /**
     * Request permission for all in permissions array.
     *
     * @param activity                   that receive the result
     * @param permissions
     * @param requestCode
     * @param permissionRationaleTitle   title of dialog that describe permission benefits
     * @param permissionRationaleMessage message of dialog that describe permission benefits
     * @return true, if all permissions granted
     */
    public static boolean requestPermissions(final Activity activity, String[] permissions, final int requestCode, int permissionRationaleTitle, int permissionRationaleMessage) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        final String[] notGrantedPermissions = checkSelfPermission(activity, permissions);
        if (notGrantedPermissions == null)
            return true;

        if (activity.shouldShowRequestPermissionRationale(notGrantedPermissions[0]) && permissionRationaleMessage != 0) {
            DialogHelper.showOkDialog(activity, permissionRationaleTitle, permissionRationaleMessage, new DialogInterface.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.M)
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    activity.requestPermissions(notGrantedPermissions, requestCode);
                }
            });
        } else {
            Log.i(TAG, "Request permissions");
            activity.requestPermissions(notGrantedPermissions, requestCode);
        }
        return false;
    }

    /**
     * Request permission for all in permissions array.
     *
     * @param fragment                   that receive the result
     * @param permissions
     * @param requestCode
     * @param permissionRationaleTitle   title of dialog that describe permission benefits
     * @param permissionRationaleMessage message of dialog that describe permission benefits
     * @return true, if all permissions granted
     */
    public static boolean requestPermissions(final Fragment fragment, String[] permissions, final int requestCode, int permissionRationaleTitle, int permissionRationaleMessage) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        final String[] notGrantedPermissions = checkSelfPermission(fragment.getContext(), permissions);
        if (notGrantedPermissions == null)
            return true;

        if (fragment.shouldShowRequestPermissionRationale(notGrantedPermissions[0]) && permissionRationaleMessage != 0) {
            DialogHelper.showOkDialog(fragment.getContext(), permissionRationaleTitle, permissionRationaleMessage, new DialogInterface.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.M)
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    fragment.requestPermissions(notGrantedPermissions, requestCode);
                }
            });
        } else {
            Log.i(TAG, "Request permissions");
            fragment.requestPermissions(notGrantedPermissions, requestCode);
        }
        return false;
    }

    /**
     * Find permissions that not granted
     *
     * @param context     context
     * @param permissions array of permissions
     * @return null, if all permissions granted
     */
    private static String[] checkSelfPermission(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || permissions == null || permissions.length == 0)
            return null;
        List<String> notGrantedPermissions = new LinkedList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                notGrantedPermissions.add(permission);
                Log.i(TAG, permission + " NOT granted");
            } else
                Log.i(TAG, permission + " granted");
        }

        if (notGrantedPermissions.isEmpty())
            return null;
        else
            return notGrantedPermissions.toArray(new String[notGrantedPermissions.size()]);
    }

    public static boolean isPermissionGranted(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        return checkSelfPermission(context, permissions) == null;
    }

    public static boolean isAllGranted(int[] grantResults) {
        boolean isAllGranted = true;
        for (int result : grantResults)
            if (result != PackageManager.PERMISSION_GRANTED) {
                isAllGranted = false;
                break;
            }
        return isAllGranted;
    }

}
