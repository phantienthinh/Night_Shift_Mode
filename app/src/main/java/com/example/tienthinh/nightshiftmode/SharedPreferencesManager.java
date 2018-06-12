package com.example.tienthinh.nightshiftmode;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Trung Tran Thanh on 4/26/2018.
 */

public class SharedPreferencesManager {
    private static final String KEY_POWER = "key_favor";
    private static final String KEY_WIDTH = "key_width";
    private static final String KEY_HEIGHT = "key_height";


    public static void setFavor(Context context, boolean favor){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean(KEY_POWER, favor).apply();
    }

    public static void set(Context context, boolean favor){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean(KEY_POWER, favor).apply();
    }

    public static void setKeyWidth(Context context, int favor){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt(KEY_WIDTH, favor).apply();
    }

    public static void setWidth(Context context,  int favor){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt(KEY_WIDTH, favor).apply();
    }

    public static void setKeyHeight(Context context, int favor){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt(KEY_HEIGHT, favor).apply();
    }

    public static void setHeight(Context context,  int favor){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt(KEY_HEIGHT, favor).apply();
    }





  

}
