package com.ichef.android.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefrence {

    public static final String APP_PREF = "iChef";
    public static  String KEY_FIRST_NAME = "FirstName";
    public static  String KEY_LAST_NAME = "lastName";
    public static  String KEY_PASSWORD = "pass";
    public static  String KEY_USERTYPE = "usertype";
    public static  String KEY_EMAIL_ID = "email";
    public static  String KEY_MANAGER_ID = "pass";
    public static SharedPreferences sp;

    public static String KEY_USER_ID = "key_user_id";
    public static String KEY_MOBILE_NO = "key_mobile";
    public static String KEY_STATE_ID = "state_id";
    public static String KEY_IS_INTRO = "key_intro";
    public static String KEY_TOKEN = "KEY_TOKEN";
    public static String KEY_LATITUDE = "KEY_LATITUDE";
    public static String KEY_LONGITUDE = "KEY_LONGITUDE";
    public static String CITYNAME = "CITY";


    public static void save(Context context, String key, String value) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static String get(Context context, String key) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        String userId = sp.getString(key, "");
        return userId;
    }

    public static void saveInt(Context context, String key, int value) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static int getInt(Context context, String key) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        int userId = sp.getInt(key, 0);
        return userId;
    }

    public static void saveBool(Context context, String key, Boolean value) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public static Boolean getBool(Context context, String key) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        return sp.getBoolean(key, false);
    }

    public static void clearPreference(Context context) {
        sp = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.apply();
    }
}
