package com.bpc.modulesdk.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.bpc.modulesdk.activity.common.BaseDrawerActivity;
import com.bpc.modulesdk.modulity.ModulesManager;
import com.bpc.modulesdk.modulity.facilities.devicesManager.DevicesHolder;
import com.bpc.modulesdk.modulity.facilities.devicesManager.Device;
import com.bpc.modulesdk.modulity.facilities.devicesManager.DeviceConnectionType;
import com.bpc.modulesdk.modulity.facilities.devicesManager.DeviceAdapter;
import com.bpc.modulesdk.utils.ExtraKeys;
import com.bpc.modulesdk.ui.widgets.CustomRecyclerView;

import java.util.List;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Samoylov on 17.01.2017.
 * Activity with list of device, where user can select any.
 */

public class SelectConnectedDeviceActivity extends BaseDrawerActivity {

    private final String TAG = "SelectConnectedDevice";
    private static final String EXTRA_KEY_TITLE = "title";
    private static final int REQUEST_CODE_CONNECT_DEVICE = 191;

    private View viewEmpty;
    private CustomRecyclerView recycler;
    private FloatingActionButton floatingButton;

    private DeviceAdapter deviceAdapter;
    private Class deviceInterface = null;

    public static <T extends Device> Intent getIntent(Context context, Class<T> deviceInterface, String title) {
        Intent starter = new Intent(context, SelectConnectedDeviceActivity.class);
        starter.putExtra(ExtraKeys.DEVICE_INTERFACE, deviceInterface.getName());
        starter.putExtra(EXTRA_KEY_TITLE, title);
        return starter;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_select_device;
    }

    @Override
    protected int getTitleId() {
        return R.string.selecting_device;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView();

        String title = getIntent().getStringExtra(EXTRA_KEY_TITLE);
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
        try {
            deviceInterface = Class.forName(getIntent().getStringExtra(ExtraKeys.DEVICE_INTERFACE));
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Cannot convert device interface name to Class", e);
            finish();
        }

        recycler.setEmptyView(viewEmpty);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        deviceAdapter = new DeviceAdapter(DevicesHolder.getConnectedDevices(deviceInterface));
        deviceAdapter.setOnItemClickListener((view, item) -> onDeviceSelected(item));
        recycler.setAdapter(deviceAdapter);

        floatingButton.setOnClickListener((view) -> onConnectClick());
    }

    private void bindView() {
        viewEmpty = findViewById(R.id.view_empty);
        recycler = (CustomRecyclerView) findViewById(R.id.recycler);
        floatingButton = (FloatingActionButton) findViewById(R.id.floating_button);
    }

    private void onDeviceSelected(Device device) {
        DevicesHolder.setActiveDevice(device.getId());
        Intent intent = new Intent();
        intent.putExtra(ExtraKeys.DEVICE_ID, device.getId());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private void onConnectClick() {
        List<DeviceConnectionType> types = ModulesManager.getDeviceConnectionTypes();
        if (types.isEmpty())
            return;

        types.get(0).startConnection(this, REQUEST_CODE_CONNECT_DEVICE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CONNECT_DEVICE) {
            if (resultCode == RESULT_OK && data != null)
                deviceAdapter.setDevices(DevicesHolder.getConnectedDevices(deviceInterface));
        } else
            super.onActivityResult(requestCode, resultCode, data);
    }

}
