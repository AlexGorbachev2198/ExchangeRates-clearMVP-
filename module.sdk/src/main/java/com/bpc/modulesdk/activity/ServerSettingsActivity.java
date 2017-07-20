package com.bpc.modulesdk.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.bpc.modulesdk.activity.common.ToolBarActivity;
import com.bpc.modulesdk.holders.ServerCapabilitiesHolder;
import com.bpc.modulesdk.rest.retrofit.RetrofitRestService;
import com.bpc.modulesdk.security.SharedPreferencesHelper;
import com.bpc.modulesdk.settings.ApplicationPropertiesProvider;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Smolyaninov on 29.12.2016.
 */

public class ServerSettingsActivity extends ToolBarActivity {

    private EditText serverAddressEt;
    private Switch demoModeSwitch;
    private View serverListButton;
    private View saveButton;
    private TextView version;

    private String[] serversList;
    private String defaultServerAddress;

    private boolean isDemoMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defaultServerAddress = SharedPreferencesHelper.getServerAddress();
        isDemoMode = SharedPreferencesHelper.isDemoMode();
        if (serversList == null) {
            serversList = getResources().getStringArray(R.array.servers_addresses);
        }
        View view = findViewById(R.id.parent);
        setupViews(view);
        setListeners();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.server_settings);
        }
    }

    @NonNull
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_server_settings;
    }

    private void setupViews(View view) {
        serverAddressEt = (EditText) view.findViewById(R.id.server_address_et);
        serverAddressEt.setEnabled(!isDemoMode);

        String startingServerAddress = SharedPreferencesHelper.getServerAddress();
        serverAddressEt.setText(startingServerAddress);

        if (serverAddressEt.getText().toString().isEmpty()) {
            serverAddressEt.setText(ApplicationPropertiesProvider.getHostSettings());
        }

        demoModeSwitch = (Switch) view.findViewById(R.id.demo_mode_switch);
        demoModeSwitch.setChecked(isDemoMode);

        serverListButton = view.findViewById(R.id.servers_list);
        if (serversList.length == 0 || serversList == null) {
            serverListButton.setVisibility(View.GONE);
        } else serverListButton.setEnabled(!isDemoMode);

        saveButton = view.findViewById(R.id.save_button);

        version = (TextView) view.findViewById(R.id.version_name);
        PackageInfo pInfo;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String appVersion = pInfo.versionName;
            version.setText(appVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setListeners() {
        demoModeSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            serverAddressEt.setEnabled(!isChecked);
            serverListButton.setEnabled(!isChecked);
            SharedPreferencesHelper.setDemoMode(isChecked);
        });

        saveButton.setOnClickListener(view -> saveChosenServerAddress(serverAddressEt.getText().toString()));
        serverListButton.setOnClickListener(view -> inflateServerList());
    }

    private void inflateServerList() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.choose_server))
                .setItems(serversList, (dialog, which) -> serverAddressEt.setText(serversList[which]))
                .setNegativeButton(getResources().getText(R.string.cancel), (dialogInterface, i) -> dialogInterface.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void saveChosenServerAddress(String address) {
        ServerCapabilitiesHolder.reset();
        String fullAddress = addProtocol(address);
        if (address != null) {
            if (!defaultServerAddress.equals(fullAddress) && isValid(fullAddress)) {
                RetrofitRestService.kill();
                SharedPreferencesHelper.setServerAddress(fullAddress);
            }
        }
        startFirstActivity();
    }

    private boolean isValid(String serverAddress) {
        return serverAddress != null && URLUtil.isValidUrl(serverAddress) && !serverAddress.isEmpty();
    }

    private String addProtocol(String url) {
        if (url != null && !url.isEmpty() && !url.startsWith("http") && !url.startsWith("https")) {
            String address =  "https://" + url;
            if (!address.endsWith("/")) address = address+"/";
            return address;
        } else return url.endsWith("/") ? url : url +"/";
    }

    private void startFirstActivity() {
        Intent restartIntent = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        restartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        restartIntent.addCategory(Intent.CATEGORY_DEFAULT);
        ServerSettingsActivity.this.startActivity(restartIntent);
    }

    @Override
    public void onBackPressed() {
    }
}
