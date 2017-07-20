package com.bpc.modulesdk.ui.views;

import android.app.Activity;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by dzmitrystrupinski on 5/3/17.
 */

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    private Camera camera;
    private Activity context;
    private int cameraId;

    public CameraPreview(Activity context, Camera camera) {
        super(context);

        this.context = context;
        this.camera = camera;

        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        startCameraPreview();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        refreshCamera(camera, cameraId);
    }

    public void refreshCamera(Camera camera, int cameraId) {
        if (holder.getSurface() == null) {
            // preview surface does not exist
            return;
        }

        stopCameraPreview();
        setCamera(camera, cameraId);
        startCameraPreview();
    }

    public void setCamera(Camera camera, int cameraId) {
        this.camera = camera;
        this.cameraId = cameraId;
    }

    // Private methods

    private void startCameraPreview() {
        setCameraDisplayOrientation();

        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (Exception exception) {
            Log.e(TAG, "Can't start camera preview due to Exception", exception);
        }
    }

    private void stopCameraPreview() {
        try {
            camera.stopPreview();
        } catch (Exception e) {
            Log.e(TAG, "Exception during stopping camera preview");
        }
    }

    private void setCameraDisplayOrientation() {
        android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);

        Camera.Parameters parameters = camera.getParameters();

        int degrees = 0;

        int rotation = context.getWindowManager().getDefaultDisplay().getRotation();
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int displayOrientation;
        int rotate;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            displayOrientation = (info.orientation + degrees) % 360;
            displayOrientation = (360 - displayOrientation) % 360;  // compensate the mirror

            rotate = (360 + info.orientation + degrees) % 360;
        } else {  // back-facing
            displayOrientation = (info.orientation - degrees + 360) % 360;
            rotate = (360 + info.orientation - degrees) % 360;
        }

        Log.v(TAG, "rotation cam / phone = displayRotation: " + info.orientation + " / " + degrees + " = "
                + displayOrientation);

        camera.setDisplayOrientation(displayOrientation);

        Log.v(TAG, "screenshot rotation: " + info.orientation + " / " + degrees + " = " + rotate);

        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
        Camera.Size optimalSize = getOptimalPreviewSize(sizes, getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        parameters.setPreviewSize(optimalSize.width, optimalSize.height);
        parameters.setRotation(rotate);
        camera.setParameters(parameters);
    }

    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.05;
        double targetRatio = (double) w/h;

        if (sizes==null) return null;

        Camera.Size optimalSize = null;

        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        // Find size
        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }
}
