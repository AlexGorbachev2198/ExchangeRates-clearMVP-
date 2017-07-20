package com.bpc.modulesdk.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;

import com.bpc.modulesdk.BaseApp;
import com.bpc.modulesdk.SdkConfig;
import com.bpc.modulesdk.modulity.facilities.mainmenu.MainMenuGroup;
import com.bpc.modulesdk.modulity.facilities.mainmenu.MainMenuItem;
import com.bpc.modulesdk.modulity.facilities.sessionFacility.SessionFacility;
import com.bpc.modulesdk.modulity.module.MainMenuUser;
import com.bpc.modulesdk.utils.DialogHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ru.bpc.mobilebanksdk.R;

import static com.bpc.modulesdk.BaseApp.getApp;


public abstract class BaseDrawerActivity extends ToolBarActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final int RES_ACTIVITY_BASE_DRAWER = R.layout.activity_base_drawer;
    private static final String TAG = "BaseDrawerActivity";
    protected Toolbar toolbar;
    protected DrawerLayout drawer;
    protected FloatingActionButton fab;
    protected NavigationView navigationView;
    protected View headerView;
    protected ActionBarDrawerToggle toggle;
    private SdkConfig appConfig;
    private List<MainMenuUser> mainMenuUsers;

    private Map<String, List<MainMenuUser>> itemsByGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appConfig = getApp().getSdkConfig();

        setContentView(getResActivityBaseDrawerId());
        initDrawer();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        if (getTitleId() != -1) setTitle(getTitleId());
        inflateContent();
    }

    /**
     * If RES_ACTIVITY_BASE_DRAWER overrided
     * new layout xml  must contain  views:
     * - FloatingActionButton with R.id.fab
     * - Toolbar with R.id.toolbar
     * - DrawerLayout with R.id.drawer_layout
     * - NavigationView with R.id.nav_view
     * else NPE
     */

    @LayoutRes
    protected int getResActivityBaseDrawerId() {
        return RES_ACTIVITY_BASE_DRAWER;
    }

    protected void replace(@IdRes int containerId, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(containerId, fragment)
                .commitAllowingStateLoss();
    }

    @LayoutRes
    protected abstract int getContentLayoutId();

    @StringRes
    //Should be abstract, for right title translation, when user select not system language
    protected abstract int getTitleId();

    @MenuRes
    protected int getMenuId() {
        return -1;
    }


    private void initDrawer() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);


        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setToolbarNavigationClickListener(v -> {
            onBackPressed();
        });
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        headerView = navigationView.getHeaderView(0);

        mainMenuUsers = appConfig.getMainMenu().getAllSortedMainMenuUsers();
        if (mainMenuUsers == null || mainMenuUsers.isEmpty()) return;

        formMainMenu();
    }

    private void formMainMenu() {
        itemsByGroups = getMenuItemGroups();
        Menu menu = navigationView.getMenu();
        int groupId = 0;
        for (Map.Entry<String, List<MainMenuUser>> entry : itemsByGroups.entrySet()) {
            if (!entry.getKey().equals(MainMenuGroup.TOP.toString()) &&
                    !entry.getKey().equals(MainMenuGroup.BOTTOM.toString())) {
                createSubmenu(menu, groupId, entry);
            } else {
                createMenuItem(menu, groupId, entry);
            }
            groupId++;
        }
    }

    private void createSubmenu(Menu menu, int groupId, Map.Entry<String, List<MainMenuUser>> entry) {
        String subMenuHeader = entry.getKey();
        SubMenu subMenu = menu.addSubMenu(groupId, Menu.NONE, Menu.NONE, subMenuHeader);
        List<MainMenuUser> menuUsers = entry.getValue();
        for (int i = 0; i < menuUsers.size(); i++) {
            MainMenuItem mainMenuItem = menuUsers.get(i).getMainMenuItem();
            int order = menuUsers.get(i).getMainMenuPositionOrder();
            int titleId = mainMenuItem.getTitleId();
            if (menuUsers.get(i).isEnableNow(BaseApp.getApp().getSdkConfig().getSupportedFunctionality())) {
                MenuItem item = subMenu.add(groupId, i, order, titleId);
                item.setIcon(mainMenuItem.getImageId());
            }
        }
    }

    private void createMenuItem(Menu menu, int groupId, Map.Entry<String, List<MainMenuUser>> entry) {
        List<MainMenuUser> menuUsers = entry.getValue();
        for (int i = 0; i < menuUsers.size(); i++) {
            if (menuUsers.get(i).isEnableNow(BaseApp.getApp().getSdkConfig().getSupportedFunctionality())) {
                MainMenuItem mainMenuItem = menuUsers.get(i).getMainMenuItem();
                MenuItem item = menu.add(groupId, i, Menu.NONE, mainMenuItem.getTitleId());
                item.setIcon(mainMenuItem.getImageId());
            }
        }
    }

    private List<String> getMenuGroups() {

        List<MainMenuItem> items = new ArrayList<>();
        for (MainMenuUser user : mainMenuUsers) {
            items.add(user.getMainMenuItem());
        }
        Collections.sort(items);

        List<String> groups = new ArrayList<>();

        for (MainMenuItem item : items) {
            String group = item.getGroup().toString();
            if (group != null && !groups.contains(group)) {
                groups.add(group);
            }
        }
        return groups;
    }

    private Map<String, List<MainMenuUser>> getMenuItemGroups() {
        Map<String, List<MainMenuUser>> itemsByGroups = new LinkedHashMap<>();
        List<String> menuGroups = getMenuGroups();
        for (String menuGroup : menuGroups) {
            List<MainMenuUser> itemsGroup = new ArrayList<>();
            for (MainMenuUser user : mainMenuUsers) {
                String groupName = user.getMainMenuItem().getGroup().toString();
                if (groupName.equals(menuGroup)) {
                    itemsGroup.add(user);
                }
            }
            itemsByGroups.put(menuGroup, itemsGroup);
        }
        return itemsByGroups;
    }

    private void inflateContent() {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.content);
        LayoutInflater.from(this).inflate(getContentLayoutId(), viewGroup, true);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //Class activityClass = BaseApp.getApp().getSdkConfig().getMainActivity();
            //if (this.getClass().equals(activityClass)) {
            showLogoutDialog();
            //} else super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_action_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            showLogoutDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLogoutDialog() {
        DialogHelper.showYesNoDialog(this, R.string.logout_dialog_title, -1, (dialog, which) -> logout(), null);
    }

    private void logout() {
        SessionFacility.closeSession();
    }


    private MainMenuItem findItem(MenuItem item) {
        LinkedHashMap<String, List<MainMenuUser>> linkedMap = (LinkedHashMap<String, List<MainMenuUser>>) itemsByGroups;
        List<List<MainMenuUser>> l = new ArrayList<>(linkedMap.values());
        return l.get(item.getGroupId()).get(item.getItemId()).getMainMenuItem();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        MainMenuItem menuItem = findItem(item);
        if (menuItem != null && menuItem.getImageId() > 0) {
            drawer.closeDrawer(GravityCompat.START);
            Class<?> cl = menuItem.getActivityClass();
            if (!(cl.isAssignableFrom(this.getClass()))) {
                Intent intent = new Intent(BaseDrawerActivity.this, menuItem.getActivityClass());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
            return true;
        } else
            return false;
    }
}
