package com.bpc.modulesdk.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bpc.modulesdk.utils.BitmapUtils;

import java.io.File;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by dzmitrystrupinski on 5/3/17.
 */

public class CameraButton extends LinearLayout {
    private LinearLayout rootLayout;
    private LinearLayout emptyImageLayout;
    private ImageView imageView;

    public CameraButton(Context context) {
        super(context);
        init();
    }

    public CameraButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CameraButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setupImage(File imageFile) {
        if (imageFile != null && imageFile.exists()) {
            imageView.setVisibility(View.VISIBLE);
            emptyImageLayout.setVisibility(View.GONE);

            Bitmap myBitmap = BitmapUtils.decodeFile(imageFile);
            imageView.setImageBitmap(myBitmap);
        } else {
            imageView.setVisibility(View.GONE);
            emptyImageLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        rootLayout.setOnClickListener(l);
    }

    // Private methods

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_camera_button, this);
        bindViews();
    }

    private void bindViews() {
        rootLayout = (LinearLayout)findViewById(R.id.root_layout);
        emptyImageLayout = (LinearLayout)findViewById(R.id.layout_empty_image);
        imageView = (ImageView)findViewById(R.id.image);
    }
}
