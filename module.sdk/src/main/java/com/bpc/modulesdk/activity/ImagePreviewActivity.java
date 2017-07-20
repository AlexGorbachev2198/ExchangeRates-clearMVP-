package com.bpc.modulesdk.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bpc.modulesdk.utils.ExtraKeys;

import java.io.File;
import java.io.Serializable;

import ru.bpc.mobilebanksdk.R;

public class ImagePreviewActivity extends Activity {
    private ImageView imagePreview;
    private Button doneButton;

    public static void start(Context context, File imageFile) {
        Intent intent = new Intent(context, ImagePreviewActivity.class);

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
        setContentView(R.layout.activity_image_preview);
        bindViews();
        setupImagePreview();
    }

    // Private methods

    private void bindViews() {
        imagePreview = (ImageView)findViewById(R.id.image_preview);
        doneButton = (Button) findViewById(R.id.button_done);
        doneButton.setOnClickListener(v->{
            finish();
        });
    }

    private void setupImagePreview() {
        Bundle args = getIntent().getExtras();
        if (args != null) {
            Serializable fileSerializable = args.getSerializable(ExtraKeys.IMAGE_FILE);
            if (fileSerializable != null && fileSerializable instanceof File) {
                File imageFile = (File)fileSerializable;
                if (imageFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                    imagePreview.setImageBitmap(myBitmap);
                }
            }
        }
    }
}
