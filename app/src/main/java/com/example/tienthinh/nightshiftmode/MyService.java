package com.example.tienthinh.nightshiftmode;

import android.app.Notification;
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
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;

import java.util.Calendar;

import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;
import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;


public class MyService extends Service {
    //   private WindowManager.LayoutParams layoutParams;
    public static Boolean aBoolean_Img_pink =false;
    public static Boolean aBoolean_Img_red =false;
    public static Boolean aBoolean_Img_blue =false;
    public static Boolean aBoolean_Img_yellow =false;
    public static Boolean aBoolean_Img_green =false;
    private NotificationCompat.Builder builder;
    private RemoteViews remoteViews;
    private NotificationManager notificationManager;
    private Notification notification;
    int realWidth;
    int realHeight;
    int hour, minute;
    int tr;
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
    public static boolean aBoolean_progress = false;
    public static boolean aBoolean_red = false;
    public static boolean aBoolean_blue = false;
    public static boolean aBoolean_green = false;
    public static boolean aBoolean_yellow = false;
    public static boolean aBoolean_pink = false;
    private Sensor sensor;
    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;


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


//        wm=(WindowManager)getSystemService(WINDOW_SERVICE);
////        relativeLayout = new RelativeLayout(this);
////        RelativeLayout.LayoutParams RLlayoutParams = new RelativeLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
////                WindowManager.LayoutParams.MATCH_PARENT);
////        relativeLayout.setLayoutParams(RLlayoutParams);
////        color = Color.argb(alpha, red,green, blue);
////
////        //Intent  intent = new Intent(this,MyService.class);
//////        Integer so = intent.getIntExtra("key_alpha",se\);
//////        Log.d("alpha", String.valueOf(so));
////      // color = Color.argb(alpha,238 ,232 ,170);
//////        relativeLayout.setBackgroundColor(color);
////
////       // WindowManager.LayoutParams params = new WindowManager.LayoutParams();
////
////        params = new WindowManager.LayoutParams();
////        params.width=MainActivity.height;
////        params.height= MainActivity.height;
////        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
////        params.flags= WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
////        params.flags = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
////        params.format=PixelFormat.TRANSLUCENT;
//
//        receiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                switch (intent.getAction()){
//                    case "color_red":
//                        red =  intent.getExtras().getInt("key_color_red");
//                        color = Color.argb(alpha,red,0,0);
//                        relativeLayout.setBackgroundColor(color);
//                        break;
//                    case "color_yellow":
//                        red = intent.getExtras().getInt("key_color_yellow_red");
//                        green = intent.getExtras().getInt("key_color_yellow_green");
//                        color = Color.argb(alpha,red,green,0);
//                        relativeLayout.setBackgroundColor(color);
//                        break;
//                    case "color_blue":
//                        blue = intent.getExtras().getInt("key_color_blue",0);
//                        color = Color.argb(alpha,0,0,blue);
//                        relativeLayout.setBackgroundColor(color);
//                        break;
//                    case "color_green":
//                        green = intent.getExtras().getInt("key_color_green",0);
//                        color = Color.argb(alpha,0,green,0);
//                        relativeLayout.setBackgroundColor(color);
//                        break;
//                    case "color_pink":
//                        blue = intent.getExtras().getInt("key_color_pink_blue",0);
//                        green = intent.getExtras().getInt("key_color_pink_green",0);
//                        red = intent.getExtras().getInt("key_color_pink_red",0);
//                        color = Color.argb(alpha,red,green,blue);
//                        relativeLayout.setBackgroundColor(color);
//                        break;
//                    case "color_progress":
//                        blue = intent.getIntExtra("key_color_blue",0);
//                        green = intent.getIntExtra("key_color_green",0);
//                        red = intent.getIntExtra("key_color_red",0);
//                        alpha = intent.getIntExtra("key_color",0);
//                        color = Color.argb(MainActivity.alpha, red,green, blue);
//                        params.width=MainActivity.width;
//                        params.height=MainActivity.height;
//                        relativeLayout.setBackgroundColor(color);
//                        break;
//                }
//            }
//        };
//        filter = new IntentFilter();
//        filter.addAction("color_red");
//        filter.addAction("color_pink");
//        filter.addAction("color_green");
//        filter.addAction("color_blue");
//        filter.addAction("color_yellow");
//        filter.addAction("color_progress");
//        getBaseContext().registerReceiver(receiver, filter);
////        wm.addView(relativeLayout,params);
//


    }

    @Override
    public void onDestroy() {
        try {
            wm.removeView(relativeLayout);
        } catch (Exception e) {

        }

        Log.e("SERVICE", "true");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

//        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
//        sensor= sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);







        if (MainActivity.hour == 0&&MainActivity.minute==0) {

        } else {
            editorSv.putInt("hour", MainActivity.hour);
            editorSv.putInt("minute", MainActivity.minute);
            editorSv.commit();
        }
        if (MainActivity.hour1==0&&MainActivity.phut1==0){

        }else {
            editorSv.putInt("hour1",MainActivity.hour1);
            editorSv.putInt("phut1",MainActivity.phut1);
            editorSv.commit();
        }


        hour = sharedPreferences.getInt("hour", 0);
        minute = sharedPreferences.getInt("minute", 0);


        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams RLlayoutParams =
                new RelativeLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT);
        relativeLayout.setLayoutParams(RLlayoutParams);

        color = Color.argb(alpha,MainActivity.red, MainActivity.green,MainActivity.blue);


//        int flags1 = View.SYSTEM_UI_FLAG_IMMERSIVE
//                // Set the content to appear under the system bars so that the
//                // content doesn't resize when the system bars hide and show.
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                // Hide the nav bar and status bar
//                //| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN;

        //Intent  intent = new Intent(this,MyService.class);
//        Integer so = intent.getIntExtra("key_alpha",se\);
//        Log.d("alpha", String.valueOf(so));
        // color = Color.argb(alpha,238 ,232 ,170);
//        relativeLayout.setBackgroundColor(color);

        // WindowManager.LayoutParams params = new WindowManager.LayoutParams();

        //   SharedPreferencesManager.getFavor(getBaseContext());


        params = new WindowManager.LayoutParams();
        params.width = sharedPreferences.getInt("width", WindowManager.LayoutParams.MATCH_PARENT);
        params.height = sharedPreferences.getInt("height", WindowManager.LayoutParams.MATCH_PARENT)+200;
        Log.d("width,height", params.width + "height" + params.height + "");

        if (Build.VERSION.SDK_INT >= 26){
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

            //params.flags =WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
//            params.flags =WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
//            params.flags =WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
            params.flags =WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            params.flags =WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
           // params.flags =WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
//                    | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        } else {
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        }
//        params.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
//        params.flags = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
//        params.flags = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
//        params.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
//        params.flags = SYSTEM_UI_FLAG_LAYOUT_STABLE | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_IMMERSIVE
//                | SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        params.flags =getSystemUiVisibility();
       // params.flags =WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        params.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION |
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        params.format = PixelFormat.TRANSLUCENT;

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
                        red = intent.getIntExtra("key_color_red",0);
                        green=0;
                        blue=0;
                        color = Color.argb(MainActivity.alpha, red, 0, 0);
                        relativeLayout.setBackgroundColor(color);
                        editorSv.putInt("red",MainActivity.red);
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

                        break;
                    case "color_yellow":
                        aBoolean_progress = false;
                        aBoolean_red = false;
                        aBoolean_blue = false;
                        aBoolean_green = false;
                        aBoolean_yellow = true;
                        aBoolean_pink = false;
                        blue=0;
                        red = intent.getIntExtra("key_color_yellow_red",0);
                        green = intent.getIntExtra("key_color_yellow_green",0);
                        color = Color.argb(MainActivity.alpha, red, green, 0);
                        relativeLayout.setBackgroundColor(color);

                        editorSv.putInt("yellow_red", MainActivity.red);
                        editorSv.putInt("yellow_green",MainActivity.green);
                        editorSv.putInt("yellow_blue", 0);
                        editorSv.putInt("yellow_alpha", MainActivity.alpha);
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.putBoolean("aboolean_progress", aBoolean_progress);
                        editorSv.commit();
                        break;
                    case "color_blue":
                        aBoolean_progress = false;
                        aBoolean_red = false;
                        aBoolean_blue = true;
                        aBoolean_green = false;
                        aBoolean_yellow = false;
                        aBoolean_pink = false;
                        red= 0;
                        green=0;
                        blue = intent.getIntExtra("key_color_blue", 0);
                        color = Color.argb(MainActivity.alpha, 0, 0, blue);
                        relativeLayout.setBackgroundColor(color);
                        editorSv.putInt("blue_blue",MainActivity.blue);
                        editorSv.putInt("blue_red", 0);
                        editorSv.putInt("blue_green", 0);
                        editorSv.putInt("blue_alpha", MainActivity.alpha);
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.putBoolean("aboolean_progress", aBoolean_progress);
                        editorSv.commit();
                        break;
                    case "color_green":
                        aBoolean_progress = false;
                        aBoolean_red = false;
                        aBoolean_blue = false;
                        aBoolean_green = true;
                        aBoolean_yellow = false;
                        aBoolean_pink = false;
                        red = 0;
                        blue=0;
                       // green = intent.getIntExtra("key_color_green", 0);
                      //  green = intent.getIntExtra("key_color_green", 0);
                        green = intent.getIntExtra("key_color_green", 0);
                        color = Color.argb(MainActivity.alpha, 0, green, 0);
                        relativeLayout.setBackgroundColor(color);
                        editorSv.putInt("green_green",MainActivity.green);
                        editorSv.putInt("green_red", 0);
                        editorSv.putInt("green_blue", 0);
                        editorSv.putInt("green_alpha", MainActivity.alpha);
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.putBoolean("aboolean_progress", aBoolean_progress);
                        editorSv.commit();
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
                        //SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCES_NAME,MODE_PRIVATE);
                        color = Color.argb(MainActivity.alpha, red, green, blue);
                        relativeLayout.setBackgroundColor(color);
                        editorSv.putInt("pink_blue",MainActivity.blue);
                        editorSv.putInt("pink_green",MainActivity.green);
                        editorSv.putInt("pink_red",MainActivity.red);
                        editorSv.putInt("pink_alpha", MainActivity.alpha);
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.putBoolean("aboolean_progress", aBoolean_progress);
                        editorSv.commit();
                        break;
                    case "color_progress":
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
                        color = Color.argb(MainActivity.alpha, red, green, blue);
//                        params.width = MainActivity.width;
//                        params.height = MainActivity.height;

                        editorSv.putInt("color_r",red);
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
                        Log.d("456", "r,g,b" + red + green + blue);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "intentProgress":
                        alpha = intent.getIntExtra("intentProgress_alpha", 0);
                        blue = intent.getIntExtra("intentProgress_blue", 0);
                        green = intent.getIntExtra("intentProgress_green", 0);
                        red = intent.getIntExtra("intentProgress_red", 0);
                        color = Color.argb(MainActivity.alpha, red, green, blue);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction1":
                        MainActivity.alpha = 200;
                       // color = Color.argb(MainActivity.alpha,red,green,blue);
                        color = Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction2":
                        MainActivity.alpha = 180;
//                        color = Color.argb(MainActivity.alpha,red,green,blue);
                        color = Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction3":
                        MainActivity.alpha = 160;
//                        color = Color.argb(MainActivity.alpha,red,green,blue);
                        color = Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction4":
                        MainActivity.alpha = 140;
//                        color = Color.argb(MainActivity.alpha,red,green,blue);
                        color = Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction5":
                        MainActivity.alpha = 120;
//                        color = Color.argb(MainActivity.alpha,red,green,blue);
                        color = Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction6":
                        MainActivity.alpha = 100;
//                        color = Color.argb(MainActivity.alpha,red,green,blue);
                        color = Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction7":
                        MainActivity.alpha = 80;
//                        color = Color.argb(MainActivity.alpha,red,green,blue);
                        color = Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction8":
                        MainActivity.alpha = 60;
//                        color = Color.argb(MainActivity.alpha,red,green,blue);
                        color = Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction9":
                        MainActivity.alpha = 40;
//                        color = Color.argb(MainActivity.alpha,red,green,blue);
                        color = Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction10":
                        MainActivity.alpha = 20;
//                        color = Color.argb(MainActivity.alpha,red,green,blue);
                        color = Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction11":
                        MainActivity.alpha = 10;
//                        color = Color.argb(MainActivity.alpha,red,green,blue);
                        color = Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "sendAction12":
                        MainActivity.alpha = 0;
//                        color = Color.argb(MainActivity.alpha,red,green,blue);
                        color = Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "img_pink":
                        aBoolean_Img_pink =true;
                        MainActivity.red = 255;
                        MainActivity.green = 192;
                        MainActivity.blue=203;
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
                        color=Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        aBoolean_Img_pink =true;
                        aBoolean_Img_red =false;
                        aBoolean_Img_blue =false;
                        aBoolean_Img_yellow =false;
                        aBoolean_Img_green =false;
                        break;
                    case "img_green":
                        MainActivity.red = 0;
                        MainActivity.green = 255;
                        MainActivity.blue=0;
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
                        color=Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        aBoolean_Img_pink =false;
                        aBoolean_Img_red =false;
                        aBoolean_Img_blue =false;
                        aBoolean_Img_yellow =false;
                        aBoolean_Img_green =true;
                        break;
                    case "img_blue":
                        MainActivity.red = 0;
                        MainActivity.green = 0;
                        MainActivity.blue=255;
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
                        color=Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        aBoolean_Img_pink =false;
                        aBoolean_Img_red =false;
                        aBoolean_Img_blue =true;
                        aBoolean_Img_yellow =false;
                        aBoolean_Img_green =false;
                        break;
                    case "img_yellow":
                        MainActivity.red = 255;
                        MainActivity.green = 255;
                        MainActivity.blue=0;
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
                        color=Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        aBoolean_Img_pink =false;
                        aBoolean_Img_red =false;
                        aBoolean_Img_blue =false;
                        aBoolean_Img_yellow =true;
                        aBoolean_Img_green =false;
                        break;
                    case "img_red":
                        MainActivity.red = 255;
                        MainActivity.green = 0;
                        MainActivity.blue=0;
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
                        color=Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        aBoolean_Img_pink =false;
                        aBoolean_Img_red =true;
                        aBoolean_Img_blue =false;
                        aBoolean_Img_yellow =false;
                        aBoolean_Img_green =false;
                        break;
                    case "setSeekBar":
                        color = Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
                        relativeLayout.setBackgroundColor(color);
                        break;
                    case "delete_windows":
                        try {
                            wm.removeView(relativeLayout);
                            Log.e("test1", "onReceive: " );
                        }catch (Exception e){

                        }
                        break;
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
        //   filter.addAction("toggleButton_on");
        // filter.addAction("toggleButton_off");
        getBaseContext().registerReceiver(receiver, filter);
        KhoiTaoNoification();
        startForeground(1998,notification);
        startTimerThread();

            wm.addView(relativeLayout, params);


        Log.d("q", color + "");
        Log.d("create", "khoi tao");

        if (sharedPreferences.getBoolean("aboolean_progress", false) == true) {
            conditionProgress();
        } else {
            if (sharedPreferences.getBoolean("aBooleanRed", false) == true) {
                conditionRed();
            } else {
                if (sharedPreferences.getBoolean("aBooleanGreen", false) == true) {
                    conditionGreen();
                } else {
                    if (sharedPreferences.getBoolean("aBooleanBlue", false) == true) {
                        conditionBlue();
                    } else {
                        if (sharedPreferences.getBoolean("aBooleanPink", false) == true) {
                            conditionPink();
                        } else {
                            if (sharedPreferences.getBoolean("aBooleanYellow", false) == true) {
                                conditionYellow();
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

    private void startTimerThread() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        public void run() {
                            if (MainActivity.toggleButton_Services.isChecked()==false){
                                try {
                                    wm.removeView(relativeLayout);
                                }
                                catch (Exception e){

                                }
                            }
                            else {

                            }

                            Calendar calendar = Calendar.getInstance();
                            int h = calendar.get(Calendar.HOUR);
                            int p = calendar.get(Calendar.MINUTE);
                            int s = calendar.get(Calendar.SECOND);
                            int giay =5;
                            Log.e("hour", "h" + h + "p" + p + "hour" + MainActivity.hour + "minute" + MainActivity.minute);

                            if (MainActivity.hour>=12){
                                MainActivity.hour = MainActivity.hour-12;
                            }else {

                            }
                            if (h == MainActivity.hour && p ==MainActivity.minute &&s == giay) {
                                try {
                                    wm.removeView(relativeLayout);
                                    Log.e("test", "run: " );
                                }catch (Exception e){

                                }
                                try {
                                    Log.e("qq", "vao roi");
                                    wm.addView(relativeLayout, params);
                                   editorSv.putBoolean("Toggle_check",true);
                                   MainActivity.toggleButton_Services.setChecked(true);
                                   editorSv.commit();
                                } catch (Exception e) {

                                }

                            } else {

                            }
                            if (MainActivity.hour1>=12){
                                MainActivity.hour1 =MainActivity.hour1-12;
                            }
                            else {

                            }
                            Log.e("q", MainActivity.hour1+"giờ"+MainActivity.phut1+"phút");
                            if (h == MainActivity.hour1 && p == MainActivity.phut1 && s ==giay) {
                                try {
                                    wm.removeView(relativeLayout);
                                    editorSv.putBoolean("Toggle_check",false);
                                    MainActivity.toggleButton_Services.setChecked(false);
                                    editorSv.commit();
                                } catch (Exception e) {

                                }

                            } else {

                            }


                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }
//    private void startThread(){
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Calendar calendar = Calendar.getInstance();
//                 int h = calendar.get(Calendar.HOUR);
//               int p =  calendar.get(Calendar.MINUTE);
//
////               editorSv.putInt("h",h);
////               editorSv.putInt("p",p);
//
//
//
//                Log.e("hour","hour"+h+"minute"+p );
//                if (hour==h&&minute==p){
//
////                    Toast.makeText(MyService.this,"Ok",Toast.LENGTH_SHORT).show();
//                    try {
//
//                    }catch (Exception ex){
//
//                    }
//                }else {
//
//                }
//
//
//            }
//        });
//        thread.start();
//
//
//
//    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void conditionProgress() {
                    try {
                        wm.removeView(relativeLayout);
                    } catch (Exception e) {
                    }
                    try {
                        wm.addView(relativeLayout, params);
                    } catch (Exception e) {
                    }
                    color = Color.argb(
                            sharedPreferences.getInt("alpha", 0),
                            sharedPreferences.getInt("color_r", 0),
                            sharedPreferences.getInt("color_g", 0),
                            sharedPreferences.getInt("color_b", 0)
                    );
                    Log.d("color", color + "");
                    relativeLayout.setBackgroundColor(color);


                }



    public void conditionGreen() {
                    try {
                        wm.addView(relativeLayout, params);
                    } catch (Exception e) {
                    }
                    color = Color.argb(
                            sharedPreferences.getInt("green_alpha", 0),
                            sharedPreferences.getInt("green_red", 0),
                            sharedPreferences.getInt("green_green", 0),
                            sharedPreferences.getInt("green_blue", 0)
                    );
                    Log.d("color", color + "");
                    relativeLayout.setBackgroundColor(color);


    }

    public void conditionYellow() {

                    try {
                        wm.addView(relativeLayout, params);
                    } catch (Exception e) {
                    }

                    color = Color.argb(
                            sharedPreferences.getInt("alpha", 0),
                            sharedPreferences.getInt("yellow_red", 0),
                            sharedPreferences.getInt("yellow_green", 0),
                            sharedPreferences.getInt("yellow_blue", 0)
                    );
                    relativeLayout.setBackgroundColor(color);



    }

    public void conditionPink() {


                    try {
                        wm.addView(relativeLayout, params);
                    } catch (Exception e) {
                    }

                    color = Color.argb(
                            sharedPreferences.getInt("pink_alpha", 0),
                            sharedPreferences.getInt("pink_red", 0),
                            sharedPreferences.getInt("pink_green", 0),
                            sharedPreferences.getInt("pink_blue", 0)
                    );
                    relativeLayout.setBackgroundColor(color);


    }

    public void conditionBlue() {

                    try {
                        wm.addView(relativeLayout, params);
                    } catch (Exception e) {
                    }

                    color = Color.argb(
                            sharedPreferences.getInt("blue_alpha", 0),
                            sharedPreferences.getInt("blue_red", 0),
                            sharedPreferences.getInt("blue_green", 0),
                            sharedPreferences.getInt("blue_blue", 0)
                    );
                    relativeLayout.setBackgroundColor(color);


    }

    public void conditionRed() {

                    try {
                        wm.addView(relativeLayout, params);
                    } catch (Exception e) {
                    }
                    color = Color.argb(MainActivity.alpha,MainActivity.red,MainActivity.green,MainActivity.blue);
        Log.e("conditionRed","vào đến red");

        relativeLayout.setBackgroundColor(color);



    }

    public int getSystemUiVisibility() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
   //             | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }
    private void KhoiTaoNoification() {
        builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.moon1)
                .setVisibility(VISIBILITY_PUBLIC)
                .setContentTitle("Ứng dụng đã được bật");


         notification = builder.build();
        remoteViews = new RemoteViews(getPackageName(), R.layout.custom_noification);
        int notificationId = 1998;

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
        PendingIntent settingPendingIntent = PendingIntent.getActivity(this, 5, settingIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(R.id.img_go_app, setting, settingPendingIntent);
        builder.setContentIntent(greenPendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.img_go_app, settingPendingIntent);


        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);

//        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        builder.setSound(uri);

//        Intent intent = new Intent("img_pink");
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
//        builder.addAction(R.id.img_pink,"img_pink",pendingIntent);
//        builder.setContentIntent(pendingIntent);
//        remoteViews.setOnClickPendingIntent(R.id.img_pink,pendingIntent);


//


//
//
//
//        //notification.contentView = remoteViews;
//        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//      //  notificationId++;

    }
}