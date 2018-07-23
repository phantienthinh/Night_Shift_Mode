package com.blue.filter.nightshiftmode;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;

public class IntentService extends android.app.IntentService {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private boolean start,stop;
    private long timeStart,timeStop;

    public IntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        preferences = getApplicationContext().getSharedPreferences("", getApplicationContext().MODE_PRIVATE);
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
                getApplicationContext().stopService(new Intent(getApplicationContext(),MyService.class));
                MainActivity.toggleButton_Services.setChecked(true);
            } catch (Exception e) {
            }
            SharedPreferencesManager.setToggleService(getApplicationContext(), true);
            editor.putBoolean("Toggle_check", true);
            editor.commit();
            Intent intentStart = new Intent(getApplicationContext(), MyService.class);
            getApplicationContext().startService(intentStart);
            // SharedPreferencesManager.setToggleService(context,true);
            Intent intent1 = new Intent("turnOn");
            getApplicationContext().sendBroadcast(intent1);
            start = false;

            AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
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
            SharedPreferencesManager.setToggleAuto(getApplicationContext(), false);
            SharedPreferencesManager.setToggleService(getApplicationContext(), false);
            Intent intent1 = new Intent("turnOff");
            getApplicationContext().sendBroadcast(intent1);
            Intent intentStop = new Intent(getApplicationContext(), MyService.class);
            getApplicationContext().stopService(intentStop);

            AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            intent = new Intent("stop");
            intent.putExtra("stop", true);
            intent.putExtra("timeStop", timeStop + 86400000);
            alarmManager.set(AlarmManager.RTC_WAKEUP, timeStop + 86400000, pendingIntent);

        } else {
        }


    }



}
