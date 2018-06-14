package com.example.tienthinh.nightshiftmode;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.Calendar;


public class MyService extends Service {
    //   private WindowManager.LayoutParams layoutParams;
    int realWidth;
    int realHeight;
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
    private boolean aBoolean_progress = false;
    private boolean aBoolean_red = false;
    private boolean aBoolean_blue = false;
    private boolean aBoolean_green = false;
    private boolean aBoolean_yellow = false;
    private boolean aBoolean_pink = false;
    int hour,minute;


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
        }catch (Exception e){

        }

        Log.e("SERVICE", "true");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (MainActivity.hour==0){

        }else {
            editorSv.putInt("hour", MainActivity.hour);
            editorSv.putInt("minute", MainActivity.minute);
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

        color = Color.argb(alpha, red, green, blue);



        //Intent  intent = new Intent(this,MyService.class);
//        Integer so = intent.getIntExtra("key_alpha",se\);
//        Log.d("alpha", String.valueOf(so));
        // color = Color.argb(alpha,238 ,232 ,170);
//        relativeLayout.setBackgroundColor(color);

        // WindowManager.LayoutParams params = new WindowManager.LayoutParams();

        //   SharedPreferencesManager.getFavor(getBaseContext());

        params = new WindowManager.LayoutParams();
        params.width = sharedPreferences.getInt("width", WindowManager.LayoutParams.MATCH_PARENT);
        params.height = sharedPreferences.getInt("height", WindowManager.LayoutParams.MATCH_PARENT);
        Log.d("width,height", params.width + "height" + params.height + "");
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        params.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        params.flags = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
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
                        red = intent.getExtras().getInt("key_color_red");
                        color = Color.argb(MainActivity.alpha, red, 0, 0);
                        relativeLayout.setBackgroundColor(color);
                        editorSv.putInt("red", red);
                        editorSv.putInt("green", 0);
                        editorSv.putInt("blue", 0);
                        editorSv.putInt("red_alpha", MainActivity.alpha);
                        editorSv.putBoolean("aboolean_red", aBoolean_red);
                        editorSv.commit();
                        break;
                    case "color_yellow":
                        aBoolean_progress = false;
                        aBoolean_red = false;
                        aBoolean_blue = false;
                        aBoolean_green = false;
                        aBoolean_yellow = true;
                        aBoolean_pink = false;
                        red = intent.getExtras().getInt("key_color_yellow_red");
                        green = intent.getExtras().getInt("key_color_yellow_green");
                        color = Color.argb(MainActivity.alpha, red, green, 0);
                        relativeLayout.setBackgroundColor(color);

                        editorSv.putInt("yellow_red", red);
                        editorSv.putInt("yellow_green", green);
                        editorSv.putInt("yellow_blue", 0);
                        editorSv.putInt("yellow_alpha", MainActivity.alpha);
                        editorSv.putBoolean("aboolean_yellow", aBoolean_yellow);
                        editorSv.commit();
                        break;
                    case "color_blue":
                        aBoolean_progress = false;
                        aBoolean_red = false;
                        aBoolean_blue = true;
                        aBoolean_green = false;
                        aBoolean_yellow = false;
                        aBoolean_pink = false;
                        blue = intent.getExtras().getInt("key_color_blue", 0);
                        color = Color.argb(MainActivity.alpha, 0, 0, blue);
                        relativeLayout.setBackgroundColor(color);
                        editorSv.putInt("blue_blue", blue);
                        editorSv.putInt("blue_red", 0);
                        editorSv.putInt("blue_green", 0);
                        editorSv.putInt("blue_alpha", MainActivity.alpha);
                        editorSv.putBoolean("aboolean_blue", aBoolean_blue);
                        editorSv.commit();
                        break;
                    case "color_green":
                        aBoolean_progress = false;
                        aBoolean_red = false;
                        aBoolean_blue = false;
                        aBoolean_green = true;
                        aBoolean_yellow = false;
                        aBoolean_pink = false;
                        green = intent.getExtras().getInt("key_color_green", 0);
                        color = Color.argb(MainActivity.alpha, 0, green, 0);
                        relativeLayout.setBackgroundColor(color);
                        editorSv.putInt("green_green", green);
                        editorSv.putInt("green_red", 0);
                        editorSv.putInt("green_blue", 0);
                        editorSv.putInt("green_alpha", MainActivity.alpha);
                        editorSv.putBoolean("aboolean_green", aBoolean_green);
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
                        blue = intent.getExtras().getInt("key_color_pink_blue", 0);
                        green = intent.getExtras().getInt("key_color_pink_green", 0);
                        red = intent.getExtras().getInt("key_color_pink_red", 0);
                        //SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCES_NAME,MODE_PRIVATE);
                        color = Color.argb(MainActivity.alpha, red, green, blue);
                        relativeLayout.setBackgroundColor(color);
                        editorSv.putInt("pink_blue", blue);
                        editorSv.putInt("pink_green", green);
                        editorSv.putInt("pink_red", red);
                        editorSv.putInt("pink_alpha", MainActivity.alpha);
                        editorSv.putBoolean("aboolean_pink", aBoolean_pink);
                        editorSv.commit();
                        break;
                    case "color_progress":
                        aBoolean_progress = true;
                        aBoolean_red = false;
                        aBoolean_blue = false;
                        aBoolean_green = false;
                        aBoolean_yellow = true;
                        aBoolean_pink = false;
                        blue = intent.getIntExtra("key_color_blue", 0);
                        green = intent.getIntExtra("key_color_green", 0);
                        red = intent.getIntExtra("key_color_red", 0);
                        alpha = intent.getIntExtra("key_color", 0);
                        color = Color.argb(MainActivity.alpha, red, green, blue);
//                        params.width = MainActivity.width;
//                        params.height = MainActivity.height;

                        editorSv.putInt("color_r", red);
                        editorSv.putInt("color_g", green);
                        editorSv.putInt("color_b", blue);
                        editorSv.putInt("alpha", alpha);
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
        //   filter.addAction("toggleButton_on");
        // filter.addAction("toggleButton_off");
        getBaseContext().registerReceiver(receiver, filter);

        startTimerThread();
        wm.addView(relativeLayout, params);
        Log.d("q", color + "");
        Log.d("create", "khoi tao");

        if (sharedPreferences.getBoolean("aboolean_progress", false) == true) {
            conditionProgress();
        } else {
            if (sharedPreferences.getBoolean("aboolean_red", false) == true) {
                conditionRed();
            } else {
                if (sharedPreferences.getBoolean("aboolean_green", false) == true) {
                    conditionGreen();
                } else {
                    if (sharedPreferences.getBoolean("aboolean_blue", false) == true) {
                        conditionBlue();
                    } else {
                        if (sharedPreferences.getBoolean("aboolean_pink", false) == true) {
                            conditionPink();
                        } else {
                            if (sharedPreferences.getBoolean("aboolean_yellow", false) == true) {
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


        return START_STICKY;

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

                            Calendar calendar = Calendar.getInstance();
                            int h = calendar.get(Calendar.HOUR);
                            int p = calendar.get(Calendar.MINUTE);
                            Log.e("hour","h"+h+"p"+p+"hour"+hour+"minute"+minute );

                            if (h==MainActivity.hour && p == MainActivity.minute){
                                try {
                                    Log.e("qq","vao roi");
                                    wm.addView(relativeLayout,params);
                                }catch (Exception e){

                                }

                            }else {

                            }
                            if (h==MainActivity.hour1&&p==MainActivity.phut1){
                                try {
                                    wm.removeView(relativeLayout);
                                }catch (Exception e){

                                }

                            }else {

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
        if (red == 0) {
            if (green == 0) {
                if (blue == 0) {
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
            } else {

            }
        } else {

        }
    }

    public void conditionGreen() {
        if (red == 0) {
            if (green == 0) {
                if (blue == 0) {
                    wm.removeView(relativeLayout);
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
            } else {

            }
        } else {

        }
    }

    public void conditionYellow() {
        if (red == 0) {
            if (green == 0) {
                if (blue == 0) {
                    wm.removeView(relativeLayout);
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
            } else {

            }
        } else {

        }
    }

    public void conditionPink() {
        if (red == 0) {
            if (green == 0) {
                if (blue == 0) {
                    wm.removeView(relativeLayout);
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
            } else {

            }
        } else {

        }
    }

    public void conditionBlue() {
        if (red == 0) {
            if (green == 0) {
                if (blue == 0) {
                    wm.removeView(relativeLayout);
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
            } else {

            }
        } else {

        }
    }

    public void conditionRed() {
        if (red == 0) {
            if (green == 0) {
                if (blue == 0) {
                    wm.removeView(relativeLayout);
                    try {
                        wm.addView(relativeLayout, params);
                    } catch (Exception e) {
                    }

                    color = Color.argb(
                            sharedPreferences.getInt("red_alpha", 0),
                            sharedPreferences.getInt("red", 0),
                            sharedPreferences.getInt("green", 0),
                            sharedPreferences.getInt("blue", 0)
                    );
                    relativeLayout.setBackgroundColor(color);


                }
            } else {

            }
        } else {

        }
    }
}
