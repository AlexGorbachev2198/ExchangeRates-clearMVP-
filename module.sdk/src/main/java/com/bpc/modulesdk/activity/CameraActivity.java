package com.bpc.modulesdk.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.bpc.modulesdk.ui.views.CameraPreview;
import com.bpc.modulesdk.utils.BitmapUtils;
import com.bpc.modulesdk.utils.DialogHelper;
import com.bpc.modulesdk.utils.ExtraKeys;
import com.bpc.modulesdk.utils.PermissionsHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ru.bpc.mobilebanksdk.R;

import static android.R.attr.rotation;

public class CameraActivity extends Activity {
    private static final String PHOTOS_FOLDER = "Agency";
    private static final String PHOTO_DATE_FORMAT = "yyyyMMdd_HHmmss";
    private static final String PHOTO_FILE_PREFIX = "IMG_";
    private static final String PHOTO_FILE_EXTENSION = ".png";

    private static final int PICTURE_SIZE_LIMIT_KB = 1024;

    private static final int REQUEST_CODE_PERMISSIONS = 1;

    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private Camera camera;
    private CameraPreview preview;
    private Camera.PictureCallback pictureCallback;
    private ImageButton capture;
    private FrameLayout cameraPreview;
    private Context context;
    private boolean cameraFront = false;
    private int cameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Bundle args = getIntent().getExtras();
        if (args != null) {
            cameraFront = args.getBoolean(ExtraKeys.CAMERA_FRONT, true);
        }

        this.context = this;

        if (!PermissionsHelper.isPermissionGranted(this, PERMISSIONS)) {
            PermissionsHelper.requestPermissions(this, PERMISSIONS, REQUEST_CODE_PERMISSIONS,
                    R.string.camera_permissions_title, R.string.camera_permissions_message);
            return;
        }

        tryToOpenCamera();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (PermissionsHelper.isAllGranted(grantResults) && PermissionsHelper.isPermissionGranted(this, PERMISSIONS)) {
                tryToOpenCamera();
            } else {
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraPreview = null;
        releaseCamera();
    }

    // Private methods

    private void initialize() {
        cameraPreview = (FrameLayout) findViewById(R.id.camera_preview);
        preview = new CameraPreview(this, camera);
        cameraPreview.addView(preview);

        capture = (ImageButton) findViewById(R.id.button_capture);
        capture.setOnClickListener(captrureListener);
    }

    private void tryToOpenCamera() {
        initialize();

        if (!hasCamera(context)) {
            showInfoMessage(getString(R.string.error_no_camera));
            finish();
        } else if (camera == null) {
            openCamera();
        }
    }

    private void showInfoMessage(String message) {
        DialogHelper.showOkDialog(this, null, message, (dialog, which) -> {
            dialog.dismiss();
            finish();
        });
    }

    private void openCamera() {
        if (cameraFront) {
            //if the front facing camera does not exist
            cameraId = findFrontFacingCamera();
            if (cameraId < 0) {
                showInfoMessage(getString(R.string.error_no_front_camera_found));
            }
        } else {
            cameraId = findBackFacingCamera();
            if (cameraId < 0) {
                showInfoMessage(getString(R.string.error_no_back_camera_found));
            }
        }
        camera = Camera.open(cameraId);
        pictureCallback = getPictureCallback();
        preview.refreshCamera(camera, cameraId);
    }

    // Action listeners

    View.OnClickListener captrureListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setCameraPictureSize();
            camera.takePicture(null, null, getPictureCallback());
        }
    };

    private void setCameraPictureSize() {
        Camera.Parameters params = camera.getParameters();
        List<Camera.Size> sizes = params.getSupportedPictureSizes();
        Camera.Size chosenSize = choosePictureSize(sizes);
        params.setPictureSize(chosenSize.width, chosenSize.height);
        camera.setParameters(params);
    }

    private Camera.Size choosePictureSize(List<Camera.Size> sizes) {
        Camera.Size result = null;
        for (Camera.Size size : sizes) {
            if (size.width < 1500 && size.height < 1000) {
                result = size;
                break;
            }
        }

        if (result == null) {
            try {
                int lastItem = sizes.size() - 1;
                int middleItem = lastItem - (lastItem / 2);
                result = sizes.get(middleItem);
            } catch (IndexOutOfBoundsException e) {
                result = sizes.get(0);
            }
        }
        return result;
    }

    private boolean isApproximatePictureSizeFits(int reqWidth, int height) {
        int res = 0;

        return PICTURE_SIZE_LIMIT_KB > res;
    }

    // Camera checks and search

    private boolean hasCamera(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    private int findFrontFacingCamera() {
        return findCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
    }

    private int findBackFacingCamera() {
        return findCamera(Camera.CameraInfo.CAMERA_FACING_BACK);
    }

    private int findCamera(int facing) {
        int cameraId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == facing) {
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    // Image processing and picture handling

    private Camera.PictureCallback getPictureCallback() {
        Camera.PictureCallback picture = (data, camera1) -> {
            File pictureFile = getOutputImageFile();
            if (pictureFile != null) {
                try {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    Bitmap realBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                    ExifInterface exif = new ExifInterface(pictureFile.getPath());
                    int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                    Bitmap orientedBitmap = BitmapUtils.rotateBitmap(realBitmap, rotation, 320, 480);
                    Bitmap resizedBitmap = getResizedBitmap(orientedBitmap, PICTURE_SIZE_LIMIT_KB);

                    if (orientedBitmap != null) {
                        resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

                        fos.close();
                        showPhotoPreview(pictureFile);
                    } else {
                        fos.close();
                        showInfoMessage(getString(R.string.error_common_rest));
                    }

                } catch (FileNotFoundException e) {
                } catch (IOException e) {
                }
            }
        };

        return picture;
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private static File getOutputImageFile() {
        String root = Environment.getExternalStorageDirectory().toString();
        File mediaStorageDir = new File(root, PHOTOS_FOLDER);
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat(PHOTO_DATE_FORMAT).format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + PHOTO_FILE_PREFIX + timeStamp + PHOTO_FILE_EXTENSION);

        return mediaFile;
    }

    private void showPhotoPreview(File pictureFile) {
        CameraPhotoPreviewActivity.start(this, pictureFile);
        finish();
    }

    // Release resources

    private void releaseCamera() {
        // stop and release camera
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }
}
