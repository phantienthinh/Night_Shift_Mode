package com.blue.filter.nightshiftmode;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesManager {

    public static void setToggleService(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("Toggle_check", is).apply();
    }

    public static boolean getToggleService(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("Toggle_check", false);
    }

    public static void setToggleAuto(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("Toggle_check_auto_night", is).apply();
    }

    public static boolean getToggleAuto(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("Toggle_check_auto_night", false);
    }
}