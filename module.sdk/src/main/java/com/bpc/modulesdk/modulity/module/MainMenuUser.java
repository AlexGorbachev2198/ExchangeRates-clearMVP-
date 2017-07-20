package com.bpc.modulesdk.modulity.module;


import com.bpc.modulesdk.modulity.facilities.mainmenu.MainMenuItem;

/**
 * Created by Masloed on 20.11.2015.
 */
public interface MainMenuUser extends ExtraControlled {

    MainMenuItem getMainMenuItem();

    /**
     * @return - ключ для сохранения настроек отображения пункта меню
     */
    String getMenuVisibilityKey();

    int getMainMenuPositionOrder();
}
