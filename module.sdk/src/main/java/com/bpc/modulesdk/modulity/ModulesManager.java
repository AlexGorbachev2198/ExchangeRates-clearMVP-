package com.bpc.modulesdk.modulity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.bpc.modulesdk.BaseApp;
import com.bpc.modulesdk.modulity.facilities.UserRole;
import com.bpc.modulesdk.modulity.facilities.devicesManager.Device;
import com.bpc.modulesdk.modulity.facilities.devicesManager.DeviceConnectionType;
import com.bpc.modulesdk.modulity.facilities.devicesManager.DeviceModel;
import com.bpc.modulesdk.modulity.listeners.OnSessionCloseListener;
import com.bpc.modulesdk.modulity.listeners.OnSessionOpenListener;
import com.bpc.modulesdk.modulity.module.AccountManagementUser;
import com.bpc.modulesdk.modulity.module.AppModule;
import com.bpc.modulesdk.modulity.module.AuthUser;
import com.bpc.modulesdk.modulity.module.BillPaymentUser;
import com.bpc.modulesdk.modulity.module.BottomBarUser;
import com.bpc.modulesdk.modulity.module.MainMenuUser;
import com.bpc.modulesdk.modulity.module.OpConfirmationUser;
import com.bpc.modulesdk.modulity.module.RepaymentUser;
import com.bpc.modulesdk.modulity.module.SettingsListUser;
import com.bpc.modulesdk.modulity.module.TabMenuUser;
import com.bpc.modulesdk.modulity.module.TransferUser;
import com.bpc.modulesdk.modulity.module.WidgetUser;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;


/**
 * Created by Masloed on 20.11.2015.
 */
public class ModulesManager {

    private static final String TAG = "ModulesManager";
    public static List<OpConfirmationUser> opConfirmationUsers = new LinkedList<>();
    private static Map<String, Object> registeredModules = new HashMap<>();
    private static List<MainMenuUser> menuUsers = new LinkedList<>();
    private static List<TabMenuUser> tabUsers = new LinkedList<>();
    private static List<OnSessionOpenListener> onSessionOpenListeners = new LinkedList<>();
    private static List<OnSessionCloseListener> onSessionCloseListeners = new LinkedList<>();
    //private static List<AppPropertiesUser> appPropertiesUsers = new LinkedList<>();
    //private static List<PaymentScenarioUser> paymentScenarioUsers = new LinkedList<>();
    //private static List<CardMenuUser> cardMenuUsers = new LinkedList<>();
    //private static List<SettingsListUser> settingsUsers = new LinkedList<>();
    //private static List<SourceSelectorUser> sourceSelectorUsers = new LinkedList<>();
    private static AuthUser authUser;//TODO возможно ли использование нескольких вариантов?
    private static List<DeviceModel> supportedDevices = new LinkedList<>();
    private static List<DeviceConnectionType> deviceConnectionTypes = new LinkedList<>();
    private static List<SettingsListUser> settingsListUsers = new LinkedList<>();
    private static List<TransferUser> transfersListUsers = new LinkedList<>();
    private static List<AccountManagementUser> accountManagementUserList = new LinkedList<>();
    private static List<BillPaymentUser> billPaymentUserList = new LinkedList<>();
    private static List<RepaymentUser> repaymentUserList = new LinkedList<>();
    private static List<WidgetUser> widgetListUsers = new LinkedList<>();
    private static List<BottomBarUser> bottomBarUsers = new LinkedList<>();

    public static List<OnSessionOpenListener> getOnSessionOpenListeners() {
        return Collections.unmodifiableList(onSessionOpenListeners);
    }

    public static List<OnSessionCloseListener> getOnSessionCloseListeners() {
        return Collections.unmodifiableList(onSessionCloseListeners);
    }

    public static List<MainMenuUser> getMenuUsers() {
        return Collections.unmodifiableList(menuUsers);
    }

    public static List<TabMenuUser> getTabMenuUsers() {
        return Collections.unmodifiableList(tabUsers);
    }

    public static AuthUser getAuthUser() {
        return authUser;
    }

    @Nullable
    public static <T extends Device> List<DeviceModel> getDeviceModelsImplemented(Class<T> clazz) {
        List<DeviceModel> result = new LinkedList<>();
        for (DeviceModel deviceModel : supportedDevices)
            if (clazz.isAssignableFrom(deviceModel.getDeviceClass()))
                result.add(deviceModel);
        return result;
    }

    public static List<DeviceConnectionType> getDeviceConnectionTypes() {
        return Collections.unmodifiableList(deviceConnectionTypes);
    }

    public static List<SettingsListUser> getSettingsListUsers() {
        return Collections.unmodifiableList(settingsListUsers);
    }

    public static List<TransferUser> getTransfersListUsers(UserRole userRoleType) {
        ArrayList<TransferUser> transfers = new ArrayList<>();
        for (TransferUser transfer : transfersListUsers) {
            if (transfer.getUserRole().equals(userRoleType)) {
                transfers.add(transfer);
            }
        }
        return transfers;
    }

    public static List<AccountManagementUser> getAccountManagementUserList(UserRole userRole) {
        ArrayList<AccountManagementUser> users = new ArrayList<>();
        for (AccountManagementUser user : accountManagementUserList) {
            if (user.getUserRole().equals(userRole)) {
                users.add(user);
            }
        }
        return users;
    }

    public static List<RepaymentUser> getRepaymentUserList(UserRole userRole) {
        ArrayList<RepaymentUser> users = new ArrayList<>();
        for (RepaymentUser user : repaymentUserList) {
            if (user.getUserRole().equals(userRole)) {
                users.add(user);
            }
        }
        return users;
    }

    public static List<BillPaymentUser> getBillPaymentUserList(UserRole userRole) {
        ArrayList<BillPaymentUser> users = new ArrayList<>();
        for (BillPaymentUser user : billPaymentUserList) {
            if (user.getUserRole().equals(userRole)) {
                users.add(user);
            }
        }
        return users;
    }

    public static List<WidgetUser> getWidgetListUsers() {
        return Collections.unmodifiableList(widgetListUsers);
    }

    public static List<BottomBarUser> getBottomBarUsers() {
        return Collections.unmodifiableList(bottomBarUsers);
    }

    public static <T> T getModule(String moduleKey, Class<T> clazz) {
        if (ModulesManager.registeredModules.get(moduleKey) != null) {
            T module = (T) ModulesManager.registeredModules.get(moduleKey);
            return module;
        }
        return null;
    }

    private static void registerUsers(Object module) {
        if (module instanceof MainMenuUser) menuUsers.add((MainMenuUser) module);
        if (module instanceof TabMenuUser) tabUsers.add((TabMenuUser) module);
        //if (module instanceof SettingsListUser) settingsUsers.add((SettingsListUser) module);
        //if (module instanceof SourceSelectorProvider)sourceSelectorUsers.addAll(((SourceSelectorProvider) module).getListSelectorSource());
        if (module instanceof OnSessionOpenListener) {
            onSessionOpenListeners.add((OnSessionOpenListener) module);
        }
        if (module instanceof OnSessionCloseListener) {
            onSessionCloseListeners.add((OnSessionCloseListener) module);
        }
        //if (module instanceof AppPropertiesUser) appPropertiesUsers.add((AppPropertiesUser) module);
        //if (module instanceof PaymentScenarioUser)            paymentScenarioUsers.add((PaymentScenarioUser) module);
        //if (module instanceof CardMenuUser) cardMenuUsers.add((CardMenuUser) module);
        if (module instanceof AuthUser) {
            authUser = (AuthUser) module;
        }
        if (module instanceof DeviceModel) {
            supportedDevices.add((DeviceModel) module);
        }
        if (module instanceof DeviceConnectionType) {
            deviceConnectionTypes.add((DeviceConnectionType) module);
        }
        if (module instanceof SettingsListUser) {
            settingsListUsers.add((SettingsListUser) module);
        }
        if (module instanceof TransferUser) {
            transfersListUsers.add((TransferUser) module);
        }
        if (module instanceof AccountManagementUser) {
            accountManagementUserList.add((AccountManagementUser) module);
        }
        if (module instanceof BillPaymentUser) {
            billPaymentUserList.add((BillPaymentUser) module);
        }
        if (module instanceof RepaymentUser) {
            repaymentUserList.add((RepaymentUser) module);
        }
        if (module instanceof WidgetUser) {
            widgetListUsers.add((WidgetUser) module);
        }
        if (module instanceof BottomBarUser) {
            bottomBarUsers.add((BottomBarUser) module);
        }
    }

    private static void registerModule(Class moduleClass) {
        Object module;
        try {
            module = moduleClass.getConstructor().newInstance();
            String moduleName = module.getClass().getAnnotation(AppModule.class).moduleName();
            registeredModules.put(moduleName, module);
        } catch (Exception e) {
            Log.e(TAG, "Cannot create module class from class \"" + moduleClass.getName() + "\"", e);
            return;
        }
        registerUsers(module);
    }

    public static void init(Context context) {
        for (Object user : BaseApp.getApp().getSdkConfig().getDefaultUsers()) registerUsers(user);

        List<Class> modulesClasses = getClassesAnnotatedWith(context, AppModule.class);
        String[] modulesFromProperties = BaseApp.getApp().getSdkConfig().getModuleNames();

        if (modulesClasses != null && modulesFromProperties != null)
            for (Class<?> moduleClass : modulesClasses) {
                AppModule appModule = moduleClass.getAnnotation(AppModule.class);
                String moduleName = appModule.moduleName();
                if (TextUtils.isEmpty(moduleName)) continue;

                for (String propName : modulesFromProperties) {
                    if (!TextUtils.isEmpty(propName) && moduleName.equals(propName)) {
                        registerModule(moduleClass);
                    }
                }
            }
        for (Map.Entry<String, Object> entry : registeredModules.entrySet()) {
            Log.d(TAG, "module " + entry.getKey() + " has beeen registered.");
        }
    }

    private static <T extends Annotation> List<Class> getClassesAnnotatedWith(Context context, Class<T> theAnnotation) {
        ArrayList<Class> candidates = new ArrayList<>();

        DexFile dex = null;
        try {
            dex = new DexFile(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir);
        } catch (Exception e) {
            Log.e(TAG, "Cannot get source dir", e);
        }
        if (dex == null)
            return candidates;
        Enumeration<String> entries = dex.entries();

        while (entries.hasMoreElements()) {
            String className = entries.nextElement();

            // Load the class
            Class<?> entryClass = null;
            try {
                entryClass = Class.forName(className, true, context.getClass().getClassLoader());
            } catch (Throwable e) {
            }

            if (entryClass != null
                    && checkAnnotation(theAnnotation, entryClass, className)) {
                candidates.add(entryClass);
            }
        }
        return candidates;
    }

    private static boolean checkAnnotation(Class theAnnotation, Class entryClass, String className) {
        try {
            return entryClass.getAnnotation(theAnnotation) != null;
        } catch (NoClassDefFoundError e) {
            Log.e(TAG, "checkAnnotation: " + className, e);
            return false;
        }

    }
}
