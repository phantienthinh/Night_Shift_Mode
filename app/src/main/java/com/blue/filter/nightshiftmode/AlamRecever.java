package com.blue.filter.nightshiftmode;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class AlamRecever extends BroadcastReceiver {
    private boolean start = false;
    private boolean stop = false;
    private long timeStart;
    private long timeStop;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    public void onReceive(Context context, Intent intent) {


        preferences = context.getSharedPreferences("", context.MODE_PRIVATE);
        editor = preferences.edit();
        start = intent.getBooleanExtra("start", false);
        stop = intent.getBooleanExtra("stop", false);
        timeStart = intent.getLongExtra("timeStart", 0);
        timeStop = intent.getLongExtra("timeStop", 0);
        Log.e("start1", start + "");
        Log.e("stop1", stop + "");


        if (start == true) {
            Log.e("start", start + "");
            try {
                context.stopService(new Intent(context,MyService.class));
                MainActivity.toggleButton_Services.setChecked(true);
            } catch (Exception e) {
            }
            SharedPreferencesManager.setToggleService(context, true);
            editor.putBoolean("Toggle_check", true);
            editor.commit();
            Intent intentStart = new Intent(context, MyService.class);
            context.startService(intentStart);
            // SharedPreferencesManager.setToggleService(context,true);
            Intent intent1 = new Intent("turnOn");
            context.sendBroadcast(intent1);
            start = false;

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            intent = new Intent("start");
            intent.putExtra("start", true);
            intent.putExtra("timeStart", timeStart + 86400000);
            alarmManager.set(AlarmManager.RTC_WAKEUP, timeStart + 86400000, pendingIntent);

        } else {
        }

        if (stop == true) {
            stop = false;
            try {
                MainActivity.toggleButton_Services.setChecked(false);
            } catch (Exception e) {
            }
            Log.e("stop", stop + "");
            //MainActivity.toggleButton_Services.setChecked(false);
            SharedPreferencesManager.setToggleAuto(context, false);
            SharedPreferencesManager.setToggleService(context, false);
            Intent intent1 = new Intent("turnOff");
            context.sendBroadcast(intent1);
            Intent intentStop = new Intent(context, MyService.class);
            context.stopService(intentStop);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            intent = new Intent("stop");
            intent.putExtra("stop", true);
            intent.putExtra("timeStop", timeStop + 86400000);
            alarmManager.set(AlarmManager.RTC_WAKEUP, timeStop + 86400000, pendingIntent);

        } else {
        }


    }
}
