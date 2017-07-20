package com.bpc.modulesdk.modulity.facilities.mainmenu;

import android.content.Context;
import android.widget.ListAdapter;

import com.bpc.modulesdk.modulity.module.MainMenuUser;

import java.util.List;


/**
 * Created by Masloed on 20.11.2015.
 */
public interface MainMenu {
    ListAdapter getAdapter(Context context);
    List<MainMenuUser> getAllSortedMainMenuUsers();
}
