package com.bpc.modulesdk.utils;

import android.widget.ImageView;

import com.bpc.modulesdk.net.OkHttpClientBuilder;
import com.bpc.modulesdk.security.SharedPreferencesHelper;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Siarhei Mikevich on 5/25/17.
 */

public class ImageUtils {

    private static OkHttp3Downloader sDownloader;

    public static void loadImage(ImageView imageView, String imageId) {
        new Picasso.Builder(imageView.getContext())
                .downloader(getDownloader())
                .build()
                .load(generateImageUrlById(imageId))
                .error(R.drawable.ic_camera_alt_black_24dp)
                .into(imageView);
    }

    private static OkHttp3Downloader getDownloader() {
        OkHttp3Downloader downloader = sDownloader;
        if (downloader == null) {
            synchronized (ImageUtils.class) {
                downloader = sDownloader;
                if (downloader == null) {
                    downloader = sDownloader = new OkHttp3Downloader(OkHttpClientBuilder.getClient());
                }
            }
        }
        return downloader;
    }

    private static String generateImageUrlById(String imageId) {
        return SharedPreferencesHelper.getServerAddress() + "data/image?imgid=" + imageId;
    }

}
