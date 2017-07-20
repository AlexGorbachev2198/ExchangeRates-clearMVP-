package com.bpc.modulesdk.security;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bpc.modulesdk.BaseApp;

public class SharedPreferencesHelper {
    private static final String LOGIN_PREFS = "login_prefs";
    private static final String KEY_USER_LOGIN = "last_login";
    private static final String KEY_IS_USER_JOINED = "save_login";
    private static final String KEY_CHECK_SERVER_ADDRESS = "server_address";

    private static final String APP_PREFS = "app";
    private static final String KEY_CHECK_DEMO_MODE = "demo_mode";
    private static final String KEY_LOCALE = "locale";
    private static final String KEY_SYSTEM_LOCALE = "system_locale";
    private static final String SECURITY_KEY_PREFS = "key_prefs";

    private static final String KEY_PREFERRED_CONFIRMATION_TYPE = "preferred_confirmation_type";

    /*  private static final String APP_PREFS = "app";
      private static final String KEY_HOST = "host";
      private static final String KEY_FAKE_MODE = "fake_mode";
      private static final String KEY_APPLICATION_LANGUAGE = "application_language";
      private static final String KEY_MENU_ITEM_PREFIX = "menuitem_";
      private static final String KEY_NEWS_DELETED = "news_deleted";
      private static final String KEY_NEWS_READ = "news_read";
      private static final String KEY_CHECK_EMAIL = "check_email";

      private static final String CARD_ORDER_PREFS = "cards_order_prefs";
      private static final String KEY_CARD_PREFIX_ORDER = "CARD_ORDER";

      private static final String TEMPLATE_ORDER_PREFS = "template_order_prefs";
      private static final String KEY_TEMPLATE_PREFIX_ORDER = "TEMPLATE_ORDER";

      private static final String KEY_IS_HIDE_BALANCE = "is_hide_balance";*/

    private static SharedPreferences getSP(String name) {
        return BaseApp.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    ///LOGIN==============================================
    public static boolean isUserJoined() {
        boolean isSaveLogin = getSP(LOGIN_PREFS).getBoolean(KEY_IS_USER_JOINED, false);
        return isSaveLogin;
    }

    public static void setUserJoined() {
        SharedPreferences.Editor edit = getSP(LOGIN_PREFS).edit();
        edit.putBoolean(KEY_IS_USER_JOINED, true);
        edit.apply();
    }

    public static void saveLogin(String login) {
        SharedPreferences.Editor edit = getSP(LOGIN_PREFS).edit();
        edit.putString(KEY_USER_LOGIN, login);
        edit.apply();
    }

    public static void deleteLogin() {
        SharedPreferences.Editor edit = getSP(LOGIN_PREFS).edit();
        edit.clear();
        edit.apply();
    }

    public static String getLogin() {
        return getSP(LOGIN_PREFS).getString(KEY_USER_LOGIN, null);
    }

    ///////////APP//////////////////////////////////

    public static boolean isDemoMode() {
        return getSP(APP_PREFS).getBoolean(KEY_CHECK_DEMO_MODE, false);
    }

    public static void setDemoMode(boolean value) {
        SharedPreferences.Editor edit = getSP(APP_PREFS).edit();
        edit.putBoolean(KEY_CHECK_DEMO_MODE, value);
        edit.apply();
    }

    public static String getPreferredConfirmationType() {
        return getSP(APP_PREFS).getString(KEY_PREFERRED_CONFIRMATION_TYPE, null);
    }
    public static void setPreferredConfirmationType(String value) {
        getSP(APP_PREFS).edit()
                .putString(KEY_PREFERRED_CONFIRMATION_TYPE, value)
                .apply();
    }




    /*
    public static void setHost(@NonNull String host) {
        SharedPreferences.Editor edit = getSP(APP_PREFS).edit();
        edit.putString(KEY_HOST, host);
        edit.apply();
    }


    public static String getHost() {
        return getSP(APP_PREFS).getString(KEY_HOST, "");
    }

    public static void setFakeMode(boolean enable) {
        SharedPreferences.Editor edit = getSP(APP_PREFS).edit();
        edit.putBoolean(KEY_FAKE_MODE, enable);
        edit.apply();
    }

    public static boolean isFakeModeEnabled() {
        return getSP(APP_PREFS).getBoolean(KEY_FAKE_MODE, false);
    }

    public static String getLang() {
        return getSP(APP_PREFS).getString(KEY_APPLICATION_LANGUAGE, "");
    }

    public static void setLang(String lang) {
        SharedPreferences.Editor edit = getSP(APP_PREFS).edit();
        edit.putString(KEY_APPLICATION_LANGUAGE, lang);
        edit.apply();
    }

    public static boolean isMenuItemVisibile(String itemKey) {
        return getSP(APP_PREFS).getBoolean(KEY_MENU_ITEM_PREFIX + itemKey, true);
    }

    public static void setMenuItemVisibility(String itemKey, boolean visibility) {
        SharedPreferences.Editor edit = getSP(APP_PREFS).edit();
        edit.putBoolean(KEY_MENU_ITEM_PREFIX + itemKey, visibility);
        edit.apply();
    }

    public static Set<String> getReadNews() {
        return getSP(APP_PREFS).getStringSet(KEY_NEWS_READ+getLogin(), Collections.<String>emptySet());
    }

    public static void setReadNews(Set<String> readNews) {
        SharedPreferences.Editor edit = getSP(APP_PREFS).edit();
        edit.putStringSet(KEY_NEWS_READ + getLogin(), readNews);
        edit.apply();
    }

    public static Set<String> getDeletedNews() {
        return getSP(APP_PREFS).getStringSet(KEY_NEWS_DELETED + getLogin(), Collections.<String>emptySet());
    }

    public static void setDeletedNews(Set<String> readNews) {
        SharedPreferences.Editor edit = getSP(APP_PREFS).edit();
        edit.putStringSet(KEY_NEWS_DELETED +getLogin(), readNews);
        edit.apply();
    }


////CARDS ORDER////////////////////////////////////////////

    public static void saveCardsOrder(Set<String> order, String login) {
        SharedPreferences.Editor edit = getSP(CARD_ORDER_PREFS).edit();
        if (!order.isEmpty()) {
            edit.remove(KEY_CARD_PREFIX_ORDER + login);
            edit.putStringSet(KEY_CARD_PREFIX_ORDER + login, order);
        }
        edit.apply();
    }


    public static Set<String> getCardsOrder(String login) {
        return getSP(CARD_ORDER_PREFS).getStringSet(KEY_CARD_PREFIX_ORDER + login, null);
    }
////TEMPLATES ORDER////////////////////////////////////////////

    public static void saveTemplatesOrder(Set<String> order, String login) {
        SharedPreferences.Editor edit = getSP(TEMPLATE_ORDER_PREFS).edit();
        if (!order.isEmpty()) {
            edit.remove(KEY_TEMPLATE_PREFIX_ORDER + login);
            edit.putStringSet(KEY_TEMPLATE_PREFIX_ORDER + login, order);
        }
        edit.apply();
    }

    public static Set<String> getTemplatesOrder(String login) {
        return getSP(TEMPLATE_ORDER_PREFS).getStringSet(KEY_TEMPLATE_PREFIX_ORDER + login, null);
    }

    ////IS_HIDE_BALANCE ////////////////////////////////////////////
    public static boolean isHideBalance(String login) {
        return getSP(login).getBoolean(KEY_IS_HIDE_BALANCE, true);
    }

    public static void setIsHideBalance(String login, boolean isHideBalance) {
        SharedPreferences.Editor edit = getSP(login).edit();
        edit.putBoolean(KEY_IS_HIDE_BALANCE, isHideBalance);
        edit.apply();
    }*/

    ////SECURITY KEY ////////////////////////////////////////////
    public static String getSecurityKey(String tag) {
        return getSP(SECURITY_KEY_PREFS).getString(tag, "");
    }

    public static void setSecurityKey(String tag, String value) {
        SharedPreferences.Editor edit = getSP(SECURITY_KEY_PREFS).edit();
        edit.putString(tag, value);
        edit.apply();
    }
/*
    ////CHECK EMAIL ////////////////////////////////////////////
    public static String getCheckEmail() {
        return getSP(APP_PREFS).getString(KEY_CHECK_EMAIL, "");
    }

    public static void setCheckEmail(String value) {
        SharedPreferences.Editor edit = getSP(APP_PREFS).edit();
        edit.putString(KEY_CHECK_EMAIL, value);
        edit.apply();
    }*/


    ///Server settings///////////////////////////////////////////
    @NonNull
    public static String getServerAddress() {
        return getSP(APP_PREFS).getString(KEY_CHECK_SERVER_ADDRESS, "");
    }

    public static void setServerAddress(String value) {
        SharedPreferences.Editor edit = getSP(APP_PREFS).edit();
        edit.putString(KEY_CHECK_SERVER_ADDRESS, value);
        edit.apply();
    }

    /**
     * Get {@link java.util.Locale} language for language setting
     *
     * @return language or null if user select OS locale
     */
    @Nullable
    public static String getLocale() {
        return getSP(APP_PREFS).getString(KEY_LOCALE, null);
    }

    /**
     * Save user Locale choice.
     *
     * @param locale language or null if user select OS locale
     */
    public static void setLocale(@Nullable String locale) {
        SharedPreferences.Editor edit = getSP(APP_PREFS).edit();
        edit.putString(KEY_LOCALE, locale);
        edit.apply();
    }

}