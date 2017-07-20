package com.bpc.modulesdk.modulity.facilities.bottombar;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.View;

import com.bpc.modulesdk.BaseApp;
import com.bpc.modulesdk.SdkSupportedFunctionality;
import com.bpc.modulesdk.modulity.ModulesManager;
import com.bpc.modulesdk.modulity.facilities.BottomBarItem;
import com.bpc.modulesdk.modulity.module.BottomBarUser;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Smolyaninov on 22.06.2017.
 */

public class BottomBarConstructor {

    private Context context;
    private BottomNavigationView bottomNavigationView;

    public BottomBarConstructor(Context context, BottomNavigationView bottomNavigationView) {
        this.context = context;
        this.bottomNavigationView = bottomNavigationView;
    }

    public void initBottomBar() {
        List<BottomBarUser> users = ModulesManager.getBottomBarUsers();
        List<BottomBarUser> supportedBottomBarUsers = new LinkedList<>();

        SdkSupportedFunctionality supported = BaseApp.getApp().getSdkConfig().getSupportedFunctionality();
        for (BottomBarUser user : users) {
            if (user.isEnableNow(supported)) supportedBottomBarUsers.add(user);
        }

        if (users.size() == 0) {
            bottomNavigationView.setVisibility(View.GONE);
        } else {
            for (BottomBarUser user : supportedBottomBarUsers) {
                BottomBarItem item = user.getBottomBarItem();
                if (item != null)
                    createMenuItem(item.getTitleId(), item.getImageId(), item.getActivityClass());
            }
        }
    }

    private void createMenuItem(int titleId, int iconId, Class activityClass) {
        bottomNavigationView
                .getMenu()
                .add(Menu.NONE, Menu.NONE, Menu.NONE, titleId)
                .setChecked(true)
                .setIcon(iconId)
                .setOnMenuItemClickListener(item -> {
                    Intent intent = new Intent(context, activityClass);
                    context.startActivity(intent);
                    return true;
                });
    }

}
