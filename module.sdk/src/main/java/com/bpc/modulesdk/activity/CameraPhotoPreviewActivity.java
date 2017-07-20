package com.bpc.modulesdk.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bpc.modulesdk.utils.BitmapUtils;
import com.bpc.modulesdk.utils.ExtraKeys;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.bpc.mobilebanksdk.R;

import static android.content.Intent.FLAG_ACTIVITY_FORWARD_RESULT;

public class CameraPhotoPreviewActivity extends Activity {
    public static final Integer REQUEST_CODE = 3;

    private ImageView imagePreview;
    private Button cancelButton, retakeButton, doneButton;
    private File imageFile;

    public static void start(Context context, File imageFile) {
        Intent intent = new Intent(context, CameraPhotoPreviewActivity.class);
        intent.setFlags(FLAG_ACTIVITY_FORWARD_RESULT);

        if (imageFile != null) {
            Bundle args = new Bundle();
            args.putSerializable(ExtraKeys.IMAGE_FILE, imageFile);
            intent.putExtras(args);
        }

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera_photo_preview);
        bindViews();
        setupImagePreview();
    }

    // Private methods

    private void bindViews() {
        imagePreview = (ImageView)findViewById(R.id.image_preview);
        cancelButton = (Button)findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(v->{
            setActivityResult(RESULT_CANCELED, null);
        });
        retakeButton = (Button)findViewById(R.id.button_retake);
        retakeButton.setOnClickListener(v->{
            setActivityResult(RESULT_OK, null);
        });
        doneButton = (Button)findViewById(R.id.button_done);
        doneButton.setOnClickListener(v->{
            if (imageFile != null) {
                Intent intent = new Intent();
                Bundle args = new Bundle();
                List<File> imageFiles = new ArrayList<>();
                imageFiles.add(imageFile);
                args.putSerializable(ExtraKeys.ITEMS, (Serializable) imageFiles);
                intent.putExtras(args);

                setActivityResult(RESULT_OK, intent);
            }
        });
    }

    private void setActivityResult(int resultCode, Intent intent) {
        setResult(resultCode, intent);
        finish();
    }

    private void setupImagePreview() {
        Bundle args = getIntent().getExtras();
        if (args != null) {
            Serializable fileSerializable = args.getSerializable(ExtraKeys.IMAGE_FILE);
            if (fileSerializable != null && fileSerializable instanceof File) {
                imageFile = (File)fileSerializable;
                if (imageFile.exists()) {
                    Bitmap myBitmap = BitmapUtils.decodeFile(imageFile);
                    imagePreview.setImageBitmap(myBitmap);
                }
            }
        }
    }
}
