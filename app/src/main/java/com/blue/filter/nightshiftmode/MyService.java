package com.blue.filter.nightshiftmode;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;

import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
import static android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;


public class MyService extends Service {
    boolean mabc;
    private boolean end;
    //   private WindowManager.LayoutParams layoutParams;
    boolean abc = false;
    public static Boolean aBoolean_Img_pink = false;
    public static Boolean aBoolean_Img_red = false;
    public static Boolean aBoolean_Img_blue = false;
    public static Boolean aBoolean_Img_yellow = false;
    public static Boolean aBoolean_Img_green = false;
    public static boolean aBoolean_progress = false;
    public static boolean aBoolean_red = false;
    public static boolean aBoolean_blue = false;
    public static boolean aBoolean_green = false;
    public static boolean aBoolean_yellow = false;
    public static boolean aBoolean_pink = false;
    int realWidth;
    int realHeight;
    int hour, minute;
    int tr;
    int notificationId = 1998;
    private Context context = this;
    private Typeface typeface_Bold;
    private NotificationCompat.Builder builder;
    private RemoteViews remoteViews;
    private NotificationManager notificationManager;
    private Notification notification;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editorSv;
    private String SHARED_PREFERENCES_NAME_SerVice;
    private WindowManager wm;
    private RelativeLayout relativeLayout;
    private int color;
    private int alpha;
    private int integer;
    private int blue, red, green;
    private BroadcastReceiver receiver;
    private IntentFilter filter;
    // private Context context;
    private WindowManager.LayoutParams params;
    private Sensor sensor;
    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;
    private View view;
    private int a,b;
    private int r=255,g=255;


    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("realHeight", String.valueOf(realHeight));
        Log.d("realWidth", String.valueOf(realWidth));
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_SerVice, MODE_PRIVATE);
        editorSv = sharedPreferences.edit();

        if (MainActivity.width == 0) {

        } else {
            editorSv.putInt("width", MainActivity.width);
            editorSv.putInt("height", MainActivity.height);
            editorSv.commit();
        }
        if (red != 0) {
            if (red != 0) {
                if (green != 0) {
                    if (MainActivity.alpha != 0) {
                        editorSv.putInt("Green", green);
                        editorSv.putInt("Red", red);
                        editorSv.putInt("Blue", blue);
                    } else {

                    }


                }
            } else {

            }
        } else {

        }


    }

    @Override
    public void onDestroy() {
        SharedPreferencesManager.setToggleAuto(this, false);
        SharedPreferencesManager.setToggleService(this, false);
        Log.e("234", SharedPreferencesManager.getToggleService(this)+"");

        if (view.getWindowToken() != null) {
            wm.removeView(view);
        }

        try {
            //  wm.removeView(relativeLayout);
//            color = Color.argb(0, 0, 0, 0);
//            relativeLayout.setBackgroundColor(color);
        } catch (Exception e) {

        }

        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, final int flags, int startId) {


        if (MainActivity.hour == 0 && MainActivity.minute == 0) {

        } else {
            editorSv.putInt("hour", MainActivity.hour);
            editorSv.putInt("minute", MainActivity.minute);
            editorSv.commit();
        }
        if (MainActivity.hour1 == 0 && MainActivity.phut1 == 0) {

        } else {
            editorSv.putInt("hour1", MainActivity.hour1);
            editorSv.putInt("phut1", MainActivity.phut1);
            editorSv.commit();
        }


        hour = sharedPreferences.getInt("hour", 0);
        minute = sharedPreferences.getInt("minute", 0);

//        if (mabc==false){
//            editorSv.putInt("red",255);
//            editorSv.putInt("green",255);
//            editorSv.putInt("blue",0);
//            editorSv.commit();
//            mabc=true;
//        }else {
//
//        }




        relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams RLlayoutParams =
                new RelativeLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT);
        relativeLayout.setLayoutParams(RLlayoutParams);
        view = relativeLayout;

        //Log.e("111", " =))))))))))))");
       // color = Color.argb(alpha, MainActivity.red, MainActivity.green, MainActivity.blue);


        params = new WindowManager.LayoutParams();
        params.width = sharedPreferences.getInt("width", WindowManager.LayoutParams.MATCH_PARENT);
        params.height = sharedPreferences.getInt("height", WindowManager.LayoutParams.MATCH_PARENT) + 200;
        Log.d("width,height", params.width + "height" + params.height + "");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | FLAG_NOT_FOCUSABLE | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | FLAG_KEEP_SCREEN_ON;
//            |SYSTEM_UI_FLAG_LAYOUT_STABLE;
            //SYSTEM_UI_FLAG_IMMERSIVE_STICKY|

        } else {
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | FLAG_NOT_FOCUSABLE | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | SYSTEM_UI_FLAG_LAYOUT_STABLE | FLAG_KEEP_SCREEN_ON;


        }


        params.format = PixelFormat.TRANSLUCENT;
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);


        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "color_red":
                        aBoolean_progress = false;
                        aBoolean_red = true;
                        aBoolean_blue = false;
                        aBoolean_green = false;
                        aBoolean_yellow = false;
                        aBoolean_pink = false;
                        red = intent.getIntExtra("key_color_red", 0);
                        green = 0;
                        blue = 0;


                        editorSv.putInt("red", MainActivity.red);
                        editorSv.putInt("green", 0);
                        editorSv.putInt("blue", 0);
                        editorSv.putInt("red_alpha", MainActivity.alpha);
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.putBoolean("aboolean_progress", aBoolean_progress);
                        editorSv.commit();
                        saveColorAlpha();
                        relativeLayout.setBackgroundColor(color);

                        break;
                    case "color_yellow":

                        aBoolean_progress = false;
                        aBoolean_red = false;
                        aBoolean_blue = false;
                        aBoolean_green = false;
                        aBoolean_yellow = true;
                        aBoolean_pink = false;
                        blue = 0;
                        red = intent.getIntExtra("key_color_yellow_red", 0);
                        green = intent.getIntExtra("key_color_yellow_green", 0);
                        editorSv.putInt("yellow_red", MainActivity.red);
                        editorSv.putInt("yellow_green", MainActivity.green);
                        editorSv.putInt("yellow_blue", 0);
                        editorSv.putInt("yellow_alpha", MainActivity.alpha);
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.putBoolean("aboolean_progress", aBoolean_progress);
                        editorSv.putInt("red",255);
                        editorSv.putInt("green",255);
                        editorSv.putInt("blue",0);
                        editorSv.commit();
                        saveColorAlpha();
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "color_blue":
                        aBoolean_progress = false;
                        aBoolean_red = false;
                        aBoolean_blue = true;
                        aBoolean_green = false;
                        aBoolean_yellow = false;
                        aBoolean_pink = false;
                        red = 0;
                        green = 0;
                        blue = intent.getIntExtra("key_color_blue", 0);



                        editorSv.putInt("blue_blue", MainActivity.blue);
                        editorSv.putInt("blue_red", 0);
                        editorSv.putInt("blue_green", 0);
                        editorSv.putInt("blue_alpha", MainActivity.alpha);
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.putBoolean("aboolean_progress", aBoolean_progress);

                        editorSv.putInt("red",0);
                        editorSv.putInt("green",0);
                        editorSv.putInt("blue",255);
                        editorSv.commit();
                        saveColorAlpha();
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "color_green":
                        aBoolean_progress = false;
                        aBoolean_red = false;
                        aBoolean_blue = false;
                        aBoolean_green = true;
                        aBoolean_yellow = false;
                        aBoolean_pink = false;
                        red = 0;
                        blue = 0;
                        green = intent.getIntExtra("key_color_green", 0);

                        editorSv.putInt("green_green", MainActivity.green);
                        editorSv.putInt("green_red", 0);
                        editorSv.putInt("green_blue", 0);
                        editorSv.putInt("green_alpha", MainActivity.alpha);
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.putBoolean("aboolean_progress", aBoolean_progress);

                        editorSv.putInt("red",0);
                        editorSv.putInt("green",255);
                        editorSv.putInt("blue",0);
                        editorSv.commit();
                        saveColorAlpha();
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "color_pink":

                        aBoolean_progress = false;
                        aBoolean_red = false;
                        aBoolean_blue = false;
                        aBoolean_green = false;
                        aBoolean_yellow = false;
                        aBoolean_pink = true;
                        //alpha = intent.getIntExtra("key_color", 0);
                        blue = intent.getIntExtra("key_color_pink_blue", 0);
                        green = intent.getIntExtra("key_color_pink_green", 0);
                        red = intent.getIntExtra("key_color_pink_red", 0);



                        editorSv.putInt("pink_blue", MainActivity.blue);
                        editorSv.putInt("pink_green", MainActivity.green);
                        editorSv.putInt("pink_red", MainActivity.red);
                        editorSv.putInt("pink_alpha", MainActivity.alpha);
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.putBoolean("aboolean_progress", aBoolean_progress);

                        editorSv.putInt("red",255);
                        editorSv.putInt("green",192);
                        editorSv.putInt("blue",203);
                        editorSv.commit();

                        saveColorAlpha();
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "color_progress":
                        Log.e("???", "color_progress: ");
                        aBoolean_progress = true;
                        aBoolean_red = false;
                        aBoolean_blue = false;
                        aBoolean_green = false;
                        aBoolean_yellow = false;
                        aBoolean_pink = false;
                        blue = intent.getIntExtra("key_color_blue", 0);
                        green = intent.getIntExtra("key_color_green", 0);
                        red = intent.getIntExtra("key_color_red", 0);
                        alpha = intent.getIntExtra("key_color", 0);
//                        if (abc==false){
//                            editorSv.putInt("red",MainActivity.red);
//                            editorSv.putInt("green",MainActivity.green);
//                            editorSv.putInt("blue",MainActivity.blue);
////                            editorSv.commit();
//                            saveColorAlpha();
//                            abc=true;
//                        }else {

//                        }
                        editorSv.putInt("color_r", red);
                        editorSv.putInt("color_g", green);
                        editorSv.putInt("color_b", blue);
                        editorSv.putInt("alpha", alpha);
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.putBoolean("aboolean_progress", aBoolean_progress);
                        editorSv.commit();
                        saveColorAlpha();
                        relativeLayout.setBackgroundColor(color);

                        break;
                    case "intentProgress":
                        Log.e("??", "intentProgress");
                        alpha = intent.getIntExtra("intentProgress_alpha", 0);
                        blue = intent.getIntExtra("intentProgress_blue", 0);
                        green = intent.getIntExtra("intentProgress_green", 0);
                        red = intent.getIntExtra("intentProgress_red", 0);
                        saveColorAlpha();
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction1":
                        a =150;
                        r = sharedPreferences.getInt("red", 0);
                        g = sharedPreferences.getInt("green", 0);
                        b = sharedPreferences.getInt("blue", 0);
                        color = Color.argb(a, r, g, b);
                        relativeLayout.setBackgroundColor(color);
                        Log.e("color_pink", MainActivity.alpha + "red:" + MainActivity.green + "green:" + MainActivity.blue + "blue");
                        break;
                    case "sendAction2":
                        a=120;
                        r = sharedPreferences.getInt("red", 0);
                        g = sharedPreferences.getInt("green", 0);
                        b = sharedPreferences.getInt("blue", 0);
                        color = Color.argb(a, r, g, b);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction3":
                        a=90;
                        r = sharedPreferences.getInt("red", 0);
                        g = sharedPreferences.getInt("green", 0);
                        b = sharedPreferences.getInt("blue", 0);
                        color = Color.argb(a, r, g, b);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction4":
                        a=60;
                        r = sharedPreferences.getInt("red", 0);
                        g = sharedPreferences.getInt("green", 0);
                        b = sharedPreferences.getInt("blue", 0);
                        color = Color.argb(a, r, g, b);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction5":
                        a=30;
                        r = sharedPreferences.getInt("red", 0);
                        g = sharedPreferences.getInt("green", 0);
                        b = sharedPreferences.getInt("blue", 0);
                        color = Color.argb(a, r, g, b);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "img_pink":
                        remoteViews.setImageViewResource(R.id.img_pink, R.drawable.ic_pink_click);
                        remoteViews.setImageViewResource(R.id.img_blue, R.drawable.ic_blue);
                        remoteViews.setImageViewResource(R.id.img_green, R.drawable.ic_green);
                        remoteViews.setImageViewResource(R.id.img_red, R.drawable.ic_red);
                        remoteViews.setImageViewResource(R.id.img_yellow, R.drawable.ic_yellow);
                        notificationManager.notify(notificationId, notification);

                        aBoolean_Img_pink = true;

                        editorSv.putInt("red",255);
                        editorSv.putInt("green",192);
                        editorSv.putInt("blue",203);
                        aBoolean_progress = false;
                        aBoolean_red = false;
                        aBoolean_blue = false;
                        aBoolean_green = false;
                        aBoolean_yellow = false;
                        aBoolean_pink = true;
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.putBoolean("aboolean_progress", aBoolean_progress);
                        editorSv.commit();
                        saveColorAlpha();
                        relativeLayout.setBackgroundColor(color);

                        aBoolean_Img_pink = true;
                        aBoolean_Img_red = false;
                        aBoolean_Img_blue = false;
                        aBoolean_Img_yellow = false;
                        aBoolean_Img_green = false;
                        break;
                    case "img_green":
                        remoteViews.setImageViewResource(R.id.img_pink, R.drawable.ic_pink);
                        remoteViews.setImageViewResource(R.id.img_blue, R.drawable.ic_blue);
                        remoteViews.setImageViewResource(R.id.img_green, R.drawable.ic_green_click);
                        remoteViews.setImageViewResource(R.id.img_red, R.drawable.ic_red);
                        remoteViews.setImageViewResource(R.id.img_yellow, R.drawable.ic_yellow);
                        notificationManager.notify(notificationId, notification);

                        editorSv.putInt("red",0);
                        editorSv.putInt("green",255);
                        editorSv.putInt("blue",0);
                        aBoolean_progress = false;
                        aBoolean_red = false;
                        aBoolean_blue = false;
                        aBoolean_green = true;
                        aBoolean_yellow = false;
                        aBoolean_pink = false;
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.putBoolean("aboolean_progress", aBoolean_progress);
                        editorSv.commit();
                        saveColorAlpha();
                        relativeLayout.setBackgroundColor(color);

                        aBoolean_Img_pink = false;
                        aBoolean_Img_red = false;
                        aBoolean_Img_blue = false;
                        aBoolean_Img_yellow = false;
                        aBoolean_Img_green = true;
                        break;
                    case "img_blue":
                        remoteViews.setImageViewResource(R.id.img_pink, R.drawable.ic_pink);
                        remoteViews.setImageViewResource(R.id.img_blue, R.drawable.ic_blue_click);
                        remoteViews.setImageViewResource(R.id.img_green, R.drawable.ic_green);
                        remoteViews.setImageViewResource(R.id.img_red, R.drawable.ic_red);
                        remoteViews.setImageViewResource(R.id.img_yellow, R.drawable.ic_yellow);
                        notificationManager.notify(notificationId, notification);


                        editorSv.putInt("red",0);
                        editorSv.putInt("green",0);
                        editorSv.putInt("blue",255);
                        aBoolean_progress = false;
                        aBoolean_red = false;
                        aBoolean_blue = true;
                        aBoolean_green = false;
                        aBoolean_yellow = false;
                        aBoolean_pink = false;
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.putBoolean("aboolean_progress", aBoolean_progress);
                        editorSv.commit();
                        saveColorAlpha();
                        relativeLayout.setBackgroundColor(color);

                        aBoolean_Img_pink = false;
                        aBoolean_Img_red = false;
                        aBoolean_Img_blue = true;
                        aBoolean_Img_yellow = false;
                        aBoolean_Img_green = false;
                        break;
                    case "img_yellow":
                        remoteViews.setImageViewResource(R.id.img_pink, R.drawable.ic_pink);
                        remoteViews.setImageViewResource(R.id.img_blue, R.drawable.ic_blue);
                        remoteViews.setImageViewResource(R.id.img_green, R.drawable.ic_green);
                        remoteViews.setImageViewResource(R.id.img_red, R.drawable.ic_red);
                        remoteViews.setImageViewResource(R.id.img_yellow, R.drawable.ic_yellow_click);
                        notificationManager.notify(notificationId, notification);


                        editorSv.putInt("red",255);
                        editorSv.putInt("green",255);
                        editorSv.putInt("blue",0);
                        aBoolean_progress = false;
                        aBoolean_red = false;
                        aBoolean_blue = false;
                        aBoolean_green = false;
                        aBoolean_yellow = true;
                        aBoolean_pink = false;
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.putBoolean("aboolean_progress", aBoolean_progress);
                        editorSv.commit();
                        saveColorAlpha();
                        relativeLayout.setBackgroundColor(color);

                        aBoolean_Img_pink = false;
                        aBoolean_Img_red = false;
                        aBoolean_Img_blue = false;
                        aBoolean_Img_yellow = true;
                        aBoolean_Img_green = false;
                        break;
                    case "img_red":
                        remoteViews.setImageViewResource(R.id.img_pink, R.drawable.ic_pink);
                        remoteViews.setImageViewResource(R.id.img_blue, R.drawable.ic_blue);
                        remoteViews.setImageViewResource(R.id.img_green, R.drawable.ic_green);
                        remoteViews.setImageViewResource(R.id.img_red, R.drawable.ic_red_click);
                        remoteViews.setImageViewResource(R.id.img_yellow, R.drawable.ic_yellow);
                        notificationManager.notify(notificationId, notification);

                        editorSv.putInt("red",255);
                        editorSv.putInt("green",0);
                        editorSv.putInt("blue",0);
                        aBoolean_progress = false;
                        aBoolean_red = true;
                        aBoolean_blue = false;
                        aBoolean_green = false;
                        aBoolean_yellow = false;
                        aBoolean_pink = false;
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.putBoolean("aboolean_progress", aBoolean_progress);
                        editorSv.commit();
                        saveColorAlpha();
                        relativeLayout.setBackgroundColor(color);
                        aBoolean_Img_pink = false;
                        aBoolean_Img_red = true;
                        aBoolean_Img_blue = false;
                        aBoolean_Img_yellow = false;
                        aBoolean_Img_green = false;
                        break;
                    case "setSeekBar":
                        saveColorAlpha();
                        relativeLayout.setBackgroundColor(color);
                        break;
//                    case "delete_windows":
//                        try {
//                          //  wm.removeView(relativeLayout);
//                        } catch (Exception e) {
//
//                        }
//                        /// color = Color.argb(0, 0, 0, 0);
//                        //relativeLayout.setBackgroundColor(color);
//                        Log.e("test1", "onReceive: ");
//
//                        break;
//                    case "addWindow":
//                        try {
//                            wm.removeView(relativeLayout);
//                        }catch (Exception e){
//
//                        }
//
//                        try {
//
//                        } catch (Exception e) {
//
//                        }
//                        break;

                }
            }
        };
        filter = new IntentFilter();
        filter.addAction("color_red");
        filter.addAction("color_pink");
        filter.addAction("color_green");
        filter.addAction("color_blue");
        filter.addAction("color_yellow");
        filter.addAction("color_progress");
        filter.addAction("intentProgress");
        filter.addAction("sendAction1");
        filter.addAction("sendAction2");
        filter.addAction("sendAction3");
        filter.addAction("sendAction4");
        filter.addAction("sendAction5");
        filter.addAction("sendAction6");
        filter.addAction("sendAction7");
        filter.addAction("sendAction8");
        filter.addAction("sendAction9");
        filter.addAction("sendAction10");
        filter.addAction("sendAction11");
        filter.addAction("sendAction12");
        filter.addAction("img_pink");
        filter.addAction("img_red");
        filter.addAction("img_green");
        filter.addAction("img_yellow");
        filter.addAction("img_blue");
        filter.addAction("setSeekBar");
        filter.addAction("delete_windows");
        filter.addAction("addWindow");
        //   filter.addAction("toggleButton_on");
        // filter.addAction("toggleButton_off");
        getBaseContext().registerReceiver(receiver, filter);
        KhoiTaoNoification();
        startForeground(1998, notification);

//        if (end==true){
//
//        }else {

        if (sharedPreferences.getBoolean("Toggle_check", false) == true) {
            if (view.getWindowToken() == null) {
                wm.addView(view, params);
            }else {

            }
        } else {
            if (view.getWindowToken() == null) {
                wm.addView(view, params);
            } else {

            }

        }

        Log.d("q", color + "");
        Log.d("create", "khoi tao");

        if (sharedPreferences.getBoolean("aboolean_progress", false) == true) {
            Log.e("?", "conditionProgress: ");
            conditionProgress();
        } else {
            if (sharedPreferences.getBoolean("aBooleanRed", false) == true) {
                conditionRed();
                Log.e("?", "conditionRed: ");
            } else {
                if (sharedPreferences.getBoolean("aBooleanGreen", false) == true) {
                    conditionGreen();
                    Log.e("?", "conditionGreen: ");
                } else {
                    if (sharedPreferences.getBoolean("aBooleanBlue", false) == true) {
                        conditionBlue();
                        Log.e("?", "conditionBlue: ");
                    } else {
                        if (sharedPreferences.getBoolean("aBooleanPink", false) == true) {
                            conditionPink();
                            Log.e("?", "conditionPink: ");
                        } else {
                            if (sharedPreferences.getBoolean("aBooleanYellow", false) == true) {
                                conditionYellow();
                                Log.e("?", "conditionYellow: ");
                            } else {
                            }
                        }
                    }
                }
            }
        }


        //  color = Color.argb(alpha, red, green, blue);
        //Color.parseColor("#000000");

        // int color1 = Color.argb(alpha, 255, 0, 0);
//        if (aBoolean==true){
//            if (green == 0) {
//                if (blue == 0) {
//                    if (red == 0) {
//
//                        int color_r =sharedPreferences.getInt("color_r",0);
//                        int color_g =sharedPreferences.getInt("color_g",0);
//                        int color_b =sharedPreferences.getInt("color_b",0);
//                        int color_alpha =sharedPreferences.getInt("color_alpha",0);
//                        color = Color.argb(color_alpha,color_r,color_g,color_b);
//                        Log.d("123", color_r+color_g+color_b+color_alpha+"");
//                        relativeLayout.setBackgroundColor(color);
//                    } else {
//
//                    }
//                } else {
//
//                }
//            } else {
//
//            }
//        }else {
//
//        }


        return START_NOT_STICKY;

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void conditionProgress() {
        saveColorAlpha();

        Log.d("color", color + "");
        relativeLayout.setBackgroundColor(color);


    }

    private void saveColorAlpha() {
        a = sharedPreferences.getInt("progress", 0);
        r = sharedPreferences.getInt("red", 0);
        g = sharedPreferences.getInt("green", 0);
        b = sharedPreferences.getInt("blue", 0);
        color = Color.argb(a, r, g, b);
    }


    public void conditionGreen() {
        editorSv.putInt("red", 0);
        editorSv.putInt("green", 255);
        editorSv.putInt("blue", 0);
        editorSv.commit();

        saveColorAlpha();

        Log.d("color", color + "");
        relativeLayout.setBackgroundColor(color);


    }

    public void conditionYellow() {
        editorSv.putInt("red", 255);
        editorSv.putInt("green", 255);
        editorSv.putInt("blue", 0);
        editorSv.commit();


        saveColorAlpha();
        relativeLayout.setBackgroundColor(color);


    }

    public void conditionPink() {
        editorSv.putInt("red", 255);
        editorSv.putInt("green", 192);
        editorSv.putInt("blue", 203);
        editorSv.commit();

        saveColorAlpha();
        relativeLayout.setBackgroundColor(color);


    }

    public void conditionBlue() {
        editorSv.putInt("red", 0);
        editorSv.putInt("green", 0);
        editorSv.putInt("blue", 255);
        editorSv.commit();

        saveColorAlpha();
        relativeLayout.setBackgroundColor(color);


    }

    public void conditionRed() {
        editorSv.putInt("red", 255);
        editorSv.putInt("green", 0);
        editorSv.putInt("blue", 0);
        editorSv.commit();

        saveColorAlpha();
        Log.e("conditionRed", "vào đến red");

        relativeLayout.setBackgroundColor(color);


    }

    //    public int getSystemUiVisibility() {
//
//        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|View.SYSTEM.FLAG_NOT_TOUCH_MODAL;
//   //             | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//    }
    private void KhoiTaoNoification() {
        String channelId = "channel-01";
        builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.moon1)
                .setVisibility(VISIBILITY_PUBLIC)
                .setChannelId(channelId)
                .setContentTitle("Ứng dụng đã được bật");


        notification = builder.build();

        String mau = "#5c5c5c";

        remoteViews = new RemoteViews(getPackageName(), R.layout.custom_noification);


        notification.contentView = remoteViews;
        notification.flags = Notification.FLAG_NO_CLEAR;
        notification.flags |= Notification.FLAG_ONGOING_EVENT;


        //sự kiện bấm vào pink trên notification
        String pink = "img_pink";
        Intent pinkIntent = new Intent(pink);
        PendingIntent pinkPendingIntent = PendingIntent.getBroadcast(this, 0, pinkIntent, 0);
        builder.addAction(R.id.img_pink, pink, pinkPendingIntent);
        builder.setContentIntent(pinkPendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.img_pink, pinkPendingIntent);


        //sự kiện bấm vào green trên notification
        String green = "img_green";
        Intent greenIntent = new Intent(green);
        PendingIntent greenPendingIntent = PendingIntent.getBroadcast(this, 1, greenIntent, 0);
        builder.addAction(R.id.img_green, green, greenPendingIntent);
        builder.setContentIntent(greenPendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.img_green, greenPendingIntent);

        //sự kiện bấm vào blue trên notification
        String blue = "img_blue";
        Intent blueIntent = new Intent(blue);
        PendingIntent bluePendingIntent = PendingIntent.getBroadcast(this, 2, blueIntent, 0);
        builder.addAction(R.id.img_blue, blue, bluePendingIntent);
        builder.setContentIntent(bluePendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.img_blue, bluePendingIntent);

        //sự kiện bấm vào red trên notification
        String red = "img_red";
        Intent redIntent = new Intent(red);
        PendingIntent redPendingIntent = PendingIntent.getBroadcast(this, 3, redIntent, 0);
        builder.addAction(R.id.img_red, red, redPendingIntent);
        builder.setContentIntent(redPendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.img_red, redPendingIntent);

        //sự kiện bấm vào yellow trên notification
        String yellow = "img_yellow";
        Intent yellowIntent = new Intent(yellow);
        PendingIntent yellowPendingIntent = PendingIntent.getBroadcast(this, 4, yellowIntent, 0);
        builder.addAction(R.id.img_yellow, yellow, yellowPendingIntent);
        builder.setContentIntent(yellowPendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.img_yellow, yellowPendingIntent);

        //sự kiện bấm vào setting trên notification
        String setting = "img_setting";
        Intent settingIntent = new Intent(this, MainActivity.class);
        settingIntent.setFlags(settingIntent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent settingPendingIntent = PendingIntent.getActivity(this, 5, settingIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(R.id.img_go_app, setting, settingPendingIntent);
        builder.setContentIntent(greenPendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.img_go_app, settingPendingIntent);


        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }


        notificationManager.notify(notificationId, notification);


    }
}