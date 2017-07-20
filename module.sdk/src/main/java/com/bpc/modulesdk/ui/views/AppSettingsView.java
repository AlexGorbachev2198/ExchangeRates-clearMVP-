package com.bpc.modulesdk.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.bpc.modulesdk.BaseApp;
import com.bpc.modulesdk.activity.ServerSettingsActivity;
import com.bpc.modulesdk.settings.ApplicationPropertiesProvider;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Smolyaninov on 12.01.2017.
 */

public class AppSettingsView extends LinearLayout {
    public AppSettingsView(Context context) {
        super(context);
        init();
    }

    public AppSettingsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AppSettingsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AppSettingsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        if (isInEditMode())
            return;
        LayoutInflater.from(getContext()).inflate(R.layout.view_app_settings, this);
        String s = ApplicationPropertiesProvider.getRuntimeSettings();
        boolean isRuntimeSettingsAccessed = BaseApp.APP_SETTINGS_RUNTIME.equals(s);
        setVisibility(isRuntimeSettingsAccessed ? VISIBLE : GONE);
        setOnClickListener(v -> openSettingsActivity());
    }

    private void openSettingsActivity() {
        Intent intent = new Intent(getContext(), /*BaseBankApplication.getApp().getSdkConfig().getAppSettingsActivity()*/ServerSettingsActivity.class);
        getContext().startActivity(intent);
    }
}
