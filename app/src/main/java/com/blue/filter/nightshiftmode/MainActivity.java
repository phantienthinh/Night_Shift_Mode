package com.blue.filter.nightshiftmode;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    boolean floatingButton = false;
    boolean abooleanProgress;
    private long timeHeThong;
    boolean demSaveTime=false;
    private long timeStart;
    private long timeStop;
    TimePickerDialog timePickerDialog;
    private Calendar calendar2;
    private PendingIntent pendingIntent1;
    private boolean isHenGio = false;
    public static boolean addView;
    boolean saveTime = false;
    private ImageButton img_btn1, img_btn2, img_btn3, img_btn4, img_btn5;
    boolean sk;
    private TextView txt_PhanTram;
    private TextView txt_on_off, txt_auto, txtopacity, txt_color;
    private Typeface typeface_Bold;
    private Typeface typeface1_Medium;
    private Typeface typeface2_Regular;
    private Typeface typeface_Semibold;
    private ProgressBar progressBar;
    public static boolean start_app = false;
    public static boolean finish = false;
    public static int alpha;
    public static int width;
    public static int height;
    public static int hour1;
    public static int phut1;
    public static int hour, minute;
    public static int red = 255;
    public static int green = 255;
    public static int blue;
    //private NotificationCompat.Builder builder;
    public NotificationManager notificationManager;
    NotificationCompat.Builder builder;
    int r;
    int position;
    private Dialog ShowDiaLog;
    private BroadcastReceiver receiver;
    private IntentFilter filter;
    private float sensorLight;
    private Calendar calendar1;
    private Context context;
    private ImageView imageView_btn_1;
    private ImageView imageView_btn_2;
    private ImageView Imv_time;
    private SharedPreferences sharedPreferences;
    private View view;
    private String SHARED_PREFERENCES_NAME;
    public static ToggleButton toggleButton_Services;
    private ToggleButton toggleButton_CountDown;
    private ToggleButton toggleButton_auto_night;
    private Toolbar toolbar;
    public static SeekBar btn_seekBar;
    private boolean aBoolean;
    private boolean aBooleanRed = false;
    private boolean aBooleanBlue = false;
    private boolean aBooleanGreen = false;
    private boolean aBooleanPink = false;
    private boolean aBooleanYellow = false;
    private boolean aBooleanToggle = false;
    private boolean aBooleanBackground;
    private boolean aBooleanDieuKien = false;
    private boolean BooleanSeekbar = false;
    private MyService myService;
    private RadioGroup radioGroup;
    private RadioButton rd_30, rd_60, rd_90, rd_120;
    private Button btn_green, btn_red, btn_yellow, btn_pink, btn_blue, btn_save_time, btn_set_time, btn_sure, btn_later;
    private EditText edt_time;
    private Dialog dialog;
    private Dialog dialog1;
    private TimePicker timePicker;
    private TextView tv_time_start, tv_time_stop;
    private SharedPreferences.Editor editor;
    private AlarmManager alarmManager;
    private AlarmManager alarmManager2;
    private PendingIntent pendingIntent;
    private Calendar calendar;
    private boolean isaBooleanBackgroundRed = false;
    private boolean isaBooleanBackgroundGreen = false;
    private boolean isaBooleanBackgroundBlue = false;
    private boolean isaBooleanBackgroundYellow = false;
    private boolean isaBooleanBackgroundPink = false;
    private boolean aBooleanEyes = false;
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;
    private int notificationId;
    private RemoteViews remoteViews;
    private ImageButton img_red;
    private Handler handler1;
    private Runnable runnable1;
    private int sumTime1;
    public static boolean akm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findRealSize(MainActivity.this);
        initView();
        khoiTaoFont();
//        setSupportActionBar(toolbar);
        // permisonAndroid();
        permisonAndroidTest();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        KhoiTaoSensor();

        calendar = Calendar.getInstance();
        calendar2 = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);

        Log.e("123456",SharedPreferencesManager.getToggleService(MainActivity.this)+"");




//        float k = sensor.getMaximumRange();
        //      Log.e("k", k + "");

        Log.e("r,g,b", "red" + red + "green" + green + "blue" + blue);

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (akm==false){
            editor.putInt("red",255);
            editor.putInt("green",255);
            editor.putInt("blue",0);
            editor.commit();
            akm=true;
        }else {

        }

        Log.d("oncreate", "created");
        if (position == 0) {
            btn_seekBar.setProgress(sharedPreferences.getInt("progress", 0));
            Log.d("position", position + "");
        } else {
            btn_seekBar.setProgress(sharedPreferences.getInt("progress", 0));
            Log.d("position1", position + "");
        }

        if (MyService.aBoolean_Img_red == true) {
            red = 255;
            green = 0;
            blue = 0;
            alpha = btn_seekBar.getProgress();
            btn_red.setBackgroundResource(R.drawable.ic_red_click);
            Intent intentProgress = new Intent("color_progress");
            intentProgress.putExtra("key_color", alpha);
            intentProgress.putExtra("key_color_red", red);
            intentProgress.putExtra("key_color_green", green);
            intentProgress.putExtra("key_color_blue", blue);
            sendBroadcast(intentProgress);
        } else {
            if (MyService.aBoolean_Img_green == true) {
                red = 0;
                green = 255;
                blue = 0;
                alpha = btn_seekBar.getProgress();
                btn_green.setBackgroundResource(R.drawable.ic_green_click);
                Intent intentProgress = new Intent("color_progress");
                intentProgress.putExtra("key_color", alpha);
                intentProgress.putExtra("key_color_red", red);
                intentProgress.putExtra("key_color_green", green);
                intentProgress.putExtra("key_color_blue", blue);
                sendBroadcast(intentProgress);
            } else {
                if (MyService.aBoolean_Img_pink == true) {
                    red = 255;
                    green = 192;
                    blue = 203;
                    alpha = btn_seekBar.getProgress();
                    btn_pink.setBackgroundResource(R.drawable.ic_pink_click);
                    Intent intentProgress = new Intent("color_progress");
                    intentProgress.putExtra("key_color", alpha);
                    intentProgress.putExtra("key_color_red", red);
                    intentProgress.putExtra("key_color_green", green);
                    intentProgress.putExtra("key_color_blue", blue);
                    sendBroadcast(intentProgress);
                } else {
                    if (MyService.aBoolean_Img_yellow == true) {
                        blue = 0;
                        red = 255;
                        green = 255;
                        alpha = btn_seekBar.getProgress();
                        btn_yellow.setBackgroundResource(R.drawable.ic_yellow_click);
                        Intent intentProgress = new Intent("color_progress");
                        intentProgress.putExtra("key_color", alpha);
                        intentProgress.putExtra("key_color_red", red);
                        intentProgress.putExtra("key_color_green", green);
                        intentProgress.putExtra("key_color_blue", blue);
                        sendBroadcast(intentProgress);
                    } else {
                        if (MyService.aBoolean_Img_blue == true) {
                            blue = 255;
                            red = 0;
                            green = 0;
                            alpha = btn_seekBar.getProgress();
                            btn_blue.setBackgroundResource(R.drawable.ic_blue_click);
                            Intent intentProgress = new Intent("color_progress");
                            intentProgress.putExtra("key_color", alpha);
                            intentProgress.putExtra("key_color_red", red);
                            intentProgress.putExtra("key_color_green", green);
                            intentProgress.putExtra("key_color_blue", blue);
                            sendBroadcast(intentProgress);
                            Log.e("red1", "alpha" + alpha + "red" + green + "blue" + blue);
                        } else {

                        }
                    }
                }
            }
        }

//                } else {
//
//                }
//            } else {
//
//            }
//        } else {
//
//        }
        KhoiTaoReceive();
    }

    private void khoiTaoFont() {
        typeface_Bold = Typeface.createFromAsset(getAssets(), "fonts/SanFranciscoDisplay-Bold.otf");

        typeface1_Medium = Typeface.createFromAsset(getAssets(), "fonts/SanFranciscoDisplay-Medium.otf");

        typeface2_Regular = Typeface.createFromAsset(getAssets(), "fonts/SanFranciscoDisplay-Regular.otf");

        typeface_Semibold = Typeface.createFromAsset(getAssets(), "fonts/SanFranciscoDisplay-Semibold.otf");

        txt_on_off.setTypeface(typeface2_Regular);
        txtopacity.setTypeface(typeface2_Regular);
        txt_color.setTypeface(typeface2_Regular);
        txt_auto.setTypeface(typeface2_Regular);
    }


    private void KhoiTaoReceive() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "img_pink":
                        btn_green.setBackgroundResource(R.drawable.ic_green);
                        btn_blue.setBackgroundResource(R.drawable.ic_blue);
                        btn_pink.setBackgroundResource(R.drawable.ic_pink_click);
                        btn_red.setBackgroundResource(R.drawable.ic_red);
                        btn_yellow.setBackgroundResource(R.drawable.ic_yellow);


                        Log.e("img_pink", "da vao toi day");
                        aBooleanRed = false;
                        aBooleanGreen = false;
                        aBooleanBlue = false;
                        aBooleanYellow = false;
                        aBooleanPink = true;
                        red = 255;
                        green = 192;
                        blue = 203;
                        editor.putInt("red", 255);
                        editor.putInt("green", 192);
                        editor.putInt("blue", 203);
                        editor.putBoolean("aBooleanRed", aBooleanRed);
                        editor.putBoolean("aBooleanBlue", aBooleanBlue);
                        editor.putBoolean("aBooleanGreen", aBooleanGreen);
                        editor.putBoolean("aBooleanPink", aBooleanPink);
                        editor.putBoolean("aBooleanYellow", aBooleanYellow);
                        editor.commit();
                        break;
                    case "img_green":
                        btn_green.setBackgroundResource(R.drawable.ic_green_click);
                        btn_blue.setBackgroundResource(R.drawable.ic_blue);
                        btn_pink.setBackgroundResource(R.drawable.ic_pink);
                        btn_red.setBackgroundResource(R.drawable.ic_red);
                        btn_yellow.setBackgroundResource(R.drawable.ic_yellow);
                        aBooleanRed = false;
                        aBooleanGreen = true;
                        aBooleanBlue = false;
                        aBooleanYellow = false;
                        aBooleanPink = false;
                        red = 0;
                        green = 255;
                        blue = 0;
                        editor.putInt("red", 0);
                        editor.putInt("green", 255);
                        editor.putInt("blue", 0);
                        editor.putBoolean("aBooleanRed", aBooleanRed);
                        editor.putBoolean("aBooleanBlue", aBooleanBlue);
                        editor.putBoolean("aBooleanGreen", aBooleanGreen);
                        editor.putBoolean("aBooleanPink", aBooleanPink);
                        editor.putBoolean("aBooleanYellow", aBooleanYellow);
                        editor.commit();
                        break;
                    case "img_blue":
                        btn_green.setBackgroundResource(R.drawable.ic_green);
                        btn_blue.setBackgroundResource(R.drawable.ic_blue_click);
                        btn_pink.setBackgroundResource(R.drawable.ic_pink);
                        btn_red.setBackgroundResource(R.drawable.ic_red);
                        btn_yellow.setBackgroundResource(R.drawable.ic_yellow);
                        aBooleanRed = false;
                        aBooleanGreen = false;
                        aBooleanBlue = true;
                        aBooleanYellow = false;
                        aBooleanPink = false;
                        red = 0;
                        green = 0;
                        blue = 255;
                        editor.putInt("red", 0);
                        editor.putInt("green", 0);
                        editor.putInt("blue", 255);
                        editor.putBoolean("aBooleanRed", aBooleanRed);
                        editor.putBoolean("aBooleanBlue", aBooleanBlue);
                        editor.putBoolean("aBooleanGreen", aBooleanGreen);
                        editor.putBoolean("aBooleanPink", aBooleanPink);
                        editor.putBoolean("aBooleanYellow", aBooleanYellow);
                        editor.commit();
                        break;
                    case "img_red":
                        btn_green.setBackgroundResource(R.drawable.ic_green);
                        btn_blue.setBackgroundResource(R.drawable.ic_blue);
                        btn_pink.setBackgroundResource(R.drawable.ic_pink);
                        btn_red.setBackgroundResource(R.drawable.ic_red_click);
                        btn_yellow.setBackgroundResource(R.drawable.ic_yellow);
                        aBooleanRed = true;
                        aBooleanGreen = false;
                        aBooleanBlue = false;
                        aBooleanYellow = false;
                        aBooleanPink = false;
                        red = 255;
                        green = 0;
                        blue = 0;
                        editor.putInt("red", 255);
                        editor.putInt("green", 0);
                        editor.putInt("blue", 0);
                        editor.putBoolean("aBooleanRed", aBooleanRed);
                        editor.putBoolean("aBooleanBlue", aBooleanBlue);
                        editor.putBoolean("aBooleanGreen", aBooleanGreen);
                        editor.putBoolean("aBooleanPink", aBooleanPink);
                        editor.putBoolean("aBooleanYellow", aBooleanYellow);
                        editor.commit();
                        break;
                    case "img_yellow":
                        btn_green.setBackgroundResource(R.drawable.ic_green);
                        btn_blue.setBackgroundResource(R.drawable.ic_blue);
                        btn_pink.setBackgroundResource(R.drawable.ic_pink);
                        btn_red.setBackgroundResource(R.drawable.ic_red);
                        btn_yellow.setBackgroundResource(R.drawable.ic_yellow_click);
                        aBooleanRed = false;
                        aBooleanGreen = false;
                        aBooleanBlue = false;
                        aBooleanYellow = true;
                        aBooleanPink = false;
                        red = 255;
                        green = 255;
                        blue = 0;
                        editor.putInt("red", 255);
                        editor.putInt("green", 255);
                        editor.putInt("blue", 0);
                        editor.putBoolean("aBooleanRed", aBooleanRed);
                        editor.putBoolean("aBooleanBlue", aBooleanBlue);
                        editor.putBoolean("aBooleanGreen", aBooleanGreen);
                        editor.putBoolean("aBooleanPink", aBooleanPink);
                        editor.putBoolean("aBooleanYellow", aBooleanYellow);
                        editor.commit();
                        break;
                    case "turnOff":
                        toggleButton_Services.setChecked(false);
                        imageView_btn_1.setVisibility(View.GONE);
                        toggleButton_auto_night.setChecked(false);
                        btn_seekBar.setEnabled(false);
                        btn_seekBar.setClickable(false);
                        BooleanSeekbar = false;
                        aBooleanDieuKien = false;
                        sensorManager.unregisterListener(sensorEventListener);
                        sensorLight = 0.0f;
                        editor.putBoolean("Toggle_check", false);
                        SharedPreferencesManager.setToggleService(context, false);
                        SharedPreferencesManager.setToggleAuto(context, false);
                        editor.commit();
                        Log.e("off", "onReceive: ");
                        break;
                    case "turnOn":
                        imageView_btn_1.setVisibility(View.VISIBLE);
                        SharedPreferencesManager.setToggleService(context, true);
                        toggleButton_Services.setChecked(true);
                        editor.putBoolean("Toggle_check", true);
                        editor.commit();
                        btn_seekBar.setEnabled(true);
                        btn_seekBar.setClickable(true);
                        break;
                }
            }
        };
        filter = new IntentFilter();
        filter.addAction("img_pink");
        filter.addAction("img_green");
        filter.addAction("img_red");
        filter.addAction("img_blue");
        filter.addAction("img_yellow");
        filter.addAction("turnOff");
        filter.addAction("turnOn");
        getBaseContext().registerReceiver(receiver, filter);
    }

    private void DiaLogShow() {
        ShowDiaLog = new Dialog(this);
        ShowDiaLog.setCancelable(false);
        ShowDiaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ShowDiaLog.setContentView(R.layout.dialog_progressbar);
        ShowDiaLog.show();
    }


    private void permisonAndroidTest() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1234);
            }
        } else {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 12) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    toggleButton_Services.setChecked(false);
                    editor.putBoolean("Toggle_check", toggleButton_Services.isChecked());
                    SharedPreferencesManager.setToggleService(this, toggleButton_Services.isChecked());
                    editor.commit();
                }
            }
        } else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void findRealSize(Activity activity) {
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//
//        if (Build.VERSION.SDK_INT >= 17) {
//            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
//        } else {
//            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        }
//
//        int realWidth = displayMetrics.widthPixels;
//        int realHeight = displayMetrics.heightPixels;
//        width = realWidth;
////        height = realHeight;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = getWindowManager().getDefaultDisplay();
            Point point = new Point();
            display.getRealSize(point);
            int realWidth = point.x;
            int realHeight = point.y;
            width = realWidth;
            height = realHeight;
            Log.i("LOG_TAG", "realWidth: " + realWidth + " realHeight: " + realHeight);
        }


    }

    private void initView() {
        txt_PhanTram = findViewById(R.id.phanTram);
        txt_auto = findViewById(R.id.auto);
        txt_color = findViewById(R.id.color);
        txt_on_off = findViewById(R.id.on_off);
        txtopacity = findViewById(R.id.textView);
        btn_blue = findViewById(R.id.btn_blue);
        btn_green = findViewById(R.id.btn_green);
        btn_red = findViewById(R.id.btn_red);
        btn_pink = findViewById(R.id.btn_pink);
        btn_yellow = findViewById(R.id.btn_yellow);
        btn_seekBar = (SeekBar) findViewById(R.id.seekBar);
        toggleButton_Services = (ToggleButton) findViewById(R.id.toggleButton);
        imageView_btn_1 = (ImageView) findViewById(R.id.Imv_time_1);
        imageView_btn_2 = (ImageView) findViewById(R.id.Imv_time_2);
        Imv_time = findViewById(R.id.Imv_time);
        imageView_btn_1.setVisibility(View.GONE);
        imageView_btn_2.setVisibility(View.GONE);
        toggleButton_auto_night = (ToggleButton) findViewById(R.id.toggleButton_auto_night);
        onClickToggleButtonStart();
        //btn_seekBar.setVisibility(View.GONE);
        onclickSeekBar();
        onClickToggleButtonAutoStart();
        onclickButtonGreen();
        onclickButtonRed();
        onclickButtonYellow();
        onclickButtonBlue();
        onclickButtonPink();
        onClickImvTime();
        Imv_time.setOnClickListener(this);


    }

    private void onClickToggleButtonAutoStart() {
        toggleButton_auto_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean boleanAutoStart = toggleButton_auto_night.isChecked();
                Log.e("boleanAutoStart", String.valueOf(boleanAutoStart));
                if (boleanAutoStart == true) {
                    btn_seekBar.setEnabled(false);

                    if (toggleButton_Services.isChecked() == true) {

                    } else {
                        Toast.makeText(MainActivity.this, R.string.PleaseTurnOn, Toast.LENGTH_SHORT).show();
                        toggleButton_auto_night.setChecked(false);
                        aBooleanDieuKien = false;
                    }
                    if (toggleButton_auto_night.isChecked() == false) {

                    } else {
                        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
                            BooleanSeekbar = true;
                            aBooleanDieuKien = true;
                            // openSensor();
                            sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), sensorManager.SENSOR_DELAY_FASTEST);
                            DiaLogShow();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    ShowDiaLog.dismiss();
                                }
                            }, 3000);

                        } else {
                            toggleButton_auto_night.setChecked(false);
                            btn_seekBar.setClickable(true);
                            btn_seekBar.setEnabled(true);
                            Toast.makeText(MainActivity.this, R.string.Sorry,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    btn_seekBar.setEnabled(true);
                    BooleanSeekbar = false;
                    aBooleanDieuKien = false;
                    sensorManager.unregisterListener(sensorEventListener);
                    sensorLight = 0.0f;
                    alpha = btn_seekBar.getProgress();
                    Intent intent = new Intent("setSeekBar");
                    sendBroadcast(intent);

                    Toast.makeText(MainActivity.this, R.string.Turnoff, Toast.LENGTH_SHORT).show();
                }
                editor.putBoolean("Toggle_check_auto_night", toggleButton_auto_night.isChecked());
                SharedPreferencesManager.setToggleAuto(MainActivity.this, toggleButton_auto_night.isChecked());
                editor.commit();
            }

        });

    }

    private void KhoiTaoSensor() {
        Log.e("ok", "KhoiTaoSensor: ");
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Log.e("sensorLight", sensorLight + "");
                float[] value = event.values;
                sensorLight = value[0];
                UpdateUI();


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    private void UpdateUI() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        public void run() {
                            setBright();

                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }

    private void setBright() {
        if (sensorLight > 0 && sensorLight <= 200) {
            Intent intent = new Intent("sendAction1");
            sendBroadcast(intent);
        } else {

        }
        if (sensorLight > 200 && sensorLight <= 1000) {
            Intent intent = new Intent("sendAction2");
            sendBroadcast(intent);
        } else {

        }
        if (sensorLight > 1000 && sensorLight <= 5000) {
            Intent intent = new Intent("sendAction3");
            sendBroadcast(intent);
        } else {

        }
        if (sensorLight > 5000 && sensorLight <= 13000) {
            Intent intent = new Intent("sendAction4");
            sendBroadcast(intent);
        } else {

        }
        if (sensorLight > 13000) {
            Intent intent = new Intent("sendAction5");
            sendBroadcast(intent);
        }
    }

    private void onClickImvTime() {
        imageView_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickIMv_btn_1();
            }
        });
        imageView_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClickImv_btn_2();


            }
        });
    }

    private void onClickImv_btn_2() {

        dialog1 = new Dialog(MainActivity.this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setCancelable(true);
        dialog1.setContentView(R.layout.custom_dialog_calendar);
        dialog1.show();
        int hourstart = sharedPreferences.getInt("hour", 0);
        int minutestart = sharedPreferences.getInt("minute", 0);
        int hourstop = sharedPreferences.getInt("hour1", 0);
        int minutestop = sharedPreferences.getInt("phut1", 0);

        timePicker = (TimePicker) dialog1.findViewById(R.id.time_Picker);
        tv_time_start = (TextView) dialog1.findViewById(R.id.tv_time_start);
        tv_time_stop = (TextView) dialog1.findViewById(R.id.tv_time_stop);
        btn_set_time = (Button) dialog1.findViewById(R.id.btn_set_time);

        if (hourstart == 0 && minutestart == 0) {

        } else {
            if (minutestart < 10) {
                tv_time_start.setText(hourstart + ":0" + minutestart);
            } else {
                tv_time_start.setText(hourstart + ":" + minutestart);
            }
        }

        if (hourstop == 0 && minutestop == 0) {

        } else {
            if (minutestop < 10) {
                tv_time_stop.setText(hourstop + ":0" + minutestop);
            } else {
                tv_time_stop.setText(hourstop + ":" + minutestop);
            }
        }


        Calendar calendarTImePicker = Calendar.getInstance();
        int hourTimePicker = calendarTImePicker.get(Calendar.HOUR_OF_DAY);
        int minuteTimePicker = calendarTImePicker.get(Calendar.MINUTE);

        timePicker.setCurrentHour(hourTimePicker);
        timePicker.setCurrentMinute(minuteTimePicker);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int i) {
                hour = hourOfDay;
                minute = i;
                if (minute < 10) {
                    String phut = "0" + minute;
                    tv_time_start.setText(hour + ":" + "0" + minute);
                } else {
                    tv_time_start.setText(hour + ":" + minute);
                }

                editor.putInt("hour", hour);
                editor.putInt("minute", minute);
                editor.commit();

            }
        });
//        hour = sharedPreferences.getInt("hour", hour);
//        minute = sharedPreferences.getInt("minute", minute);


        tv_time_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar1 = Calendar.getInstance();
                int gio = calendar1.get(Calendar.HOUR_OF_DAY);
                int phut = calendar1.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour1 = hourOfDay;
                        phut1 = minute;
                        if (minute < 10) {
                            tv_time_stop.setText(hourOfDay + ":" + "0" + minute);
                        } else {
                            tv_time_stop.setText(hourOfDay + ":" + minute);
                        }
                        Log.e("calendar1", hourOfDay + "");
                        editor.putInt("hour1", hour1);
                        editor.putInt("phut1", phut1);
                        editor.commit();

                        Log.e("gioKT", hour1+"");
                        Log.e("PhutKT", phut1+"" );
                    }
                }, gio, phut, false);

                timePickerDialog.show();

//                hour1 = sharedPreferences.getInt("hour1", hour1);
//                phut1 = sharedPreferences.getInt("phut1", phut1);

            }
        });
        btn_set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSetTime();

            }
        });
    }

    private void onClickSetTime() {

                hour1 = sharedPreferences.getInt("hour1", hour1);
                phut1 = sharedPreferences.getInt("phut1", phut1);
                hour = sharedPreferences.getInt("hour",hour);
                minute = sharedPreferences.getInt("minute",minute);




        if (tv_time_start.getText().length() == 0) {
            Toast.makeText(MainActivity.this, R.string.TimeStart, Toast.LENGTH_SHORT).show();
        } else {
            if (tv_time_stop.getText().length() == 0) {
                Toast.makeText(MainActivity.this, R.string.TimeEnd, Toast.LENGTH_SHORT).show();
            } else {
                if (tv_time_stop.getText().equals(tv_time_start.getText()) == true) {
                    Toast.makeText(MainActivity.this, R.string.erroData, Toast.LENGTH_SHORT).show();
                } else {

                    calendar.set(Calendar.HOUR_OF_DAY,hour);
                    calendar.set(Calendar.MINUTE,minute);
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                    timeStart = calendar.getTimeInMillis();

                    calendar2.set(Calendar.HOUR_OF_DAY,hour1);
                    calendar2.set(Calendar.MINUTE,phut1);
                    calendar2.set(Calendar.SECOND,0);
                    calendar2.set(Calendar.MILLISECOND,0);
                    timeStop = calendar2.getTimeInMillis();

                    timeHeThong=System.currentTimeMillis();

                    if (timeStart>timeStop){
                        Toast.makeText(MainActivity.this, R.string.nhohon, Toast.LENGTH_SHORT).show();
                    }else {
                        if (demSaveTime==false){

                            demSaveTime=true;
                            cancelBaoThuc();
                            bamLan1();
                            dialog1.cancel();


                        }else {
                            demSaveTime=false;
                            cancelBaoThuc();
                            bamLan2();
                            dialog1.cancel();

                        }

                    }




                }

            }

        }

    }

    private void bamLan2() {
        //thời gian start

        if (timeHeThong<=timeStart){
            Intent intent = new Intent(MainActivity.this, AlamRecever.class);
            intent.putExtra("start", true);
            intent.putExtra("timeStart", timeStart);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeStart, pendingIntent);
        }else {
            long timeStartTomorow = timeStart+86400000;
            Intent intent = new Intent(MainActivity.this,AlamRecever.class);
            intent.putExtra("start", true);
            intent.putExtra("timeStart", timeStartTomorow);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeStartTomorow, pendingIntent);

            if (SharedPreferencesManager.getToggleService(MainActivity.this)==true){
            }else {
                if (timeHeThong>timeStart && timeHeThong<timeStop){
                    startService(new Intent(MainActivity.this,MyService.class));
                }else {

                }
            }
            UpdateUiStart();
        }

        //thời gian stop

        if (timeHeThong<=timeStop){
            Intent intent1 = new Intent(MainActivity.this,AlamRecever.class);
            intent1.putExtra("stop", true);
            intent1.putExtra("timeStop", timeStop);
            pendingIntent1 = PendingIntent.getBroadcast(MainActivity.this, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager2.setExact(AlarmManager.RTC_WAKEUP, timeStop, pendingIntent1);
        }else {

            long timeStopTomorow = timeStop+86400000;
            Intent intent = new Intent(MainActivity.this,AlamRecever.class);
            intent.putExtra("start", true);
            intent.putExtra("timeStart", timeStopTomorow);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeStopTomorow, pendingIntent);

            if (SharedPreferencesManager.getToggleService(MainActivity.this)==true){
                stopService(new Intent(MainActivity.this,MyService.class));
            }else {}

            upDateUiStop();

        }

    }

    private void upDateUiStop() {
        BooleanSeekbar = false;
        aBooleanDieuKien = false;
        sensorManager.unregisterListener(sensorEventListener);
        sensorLight = 0.0f;

        toggleButton_auto_night.setChecked(false);
        toggleButton_Services.setChecked(false);
        SharedPreferencesManager.setToggleService(MainActivity.this,false);
        SharedPreferencesManager.setToggleAuto(MainActivity.this,false);
        imageView_btn_1.setVisibility(View.GONE);
        btn_seekBar.setClickable(false);
        btn_seekBar.setEnabled(false);
        editor.putBoolean("Toggle_check",false);
        editor.putBoolean("Toggle_check_auto_night",false);
        editor.commit();
    }


    private void bamLan1() {

        if (timeHeThong<=timeStart){
            Intent intent = new Intent(MainActivity.this, AlamRecever.class);
            intent.putExtra("start", true);
            intent.putExtra("timeStart", timeStart);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeStart, pendingIntent);
        }else {
            long timeStartTomorow = timeStart+86400000;
            Intent intent = new Intent(MainActivity.this,AlamRecever.class);
            intent.putExtra("start", true);
            intent.putExtra("timeStart", timeStartTomorow);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeStartTomorow, pendingIntent);

            if (SharedPreferencesManager.getToggleService(MainActivity.this)==true){
            }else {
                if (timeHeThong>timeStart && timeHeThong<timeStop){
                    startService(new Intent(MainActivity.this,MyService.class));
                }else {

                }
            }
          UpdateUiStart();
        }

        if (timeHeThong<=timeStop){
            Intent intent1 = new Intent(MainActivity.this,AlamRecever.class);
            intent1.putExtra("stop", true);
            intent1.putExtra("timeStop", timeStop);
            pendingIntent1 = PendingIntent.getBroadcast(MainActivity.this, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager2.setExact(AlarmManager.RTC_WAKEUP, timeStop, pendingIntent1);
        }else {
            long timeStopTomorow = timeStop+86400000;
            Intent intent = new Intent(MainActivity.this,AlamRecever.class);
            intent.putExtra("start", true);
            intent.putExtra("timeStart", timeStopTomorow);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeStopTomorow, pendingIntent);

            if (SharedPreferencesManager.getToggleService(MainActivity.this)==true){
                stopService(new Intent(MainActivity.this,MyService.class));
            }else {}
            upDateUiStop();
        }

    }
    private void cancelBaoThuc() {
        try {
            alarmManager.cancel(pendingIntent);
            alarmManager2.cancel(pendingIntent1);
        }catch (Exception e){}
    }
    private void xuLyLogic() {
        Calendar calendar3 = Calendar.getInstance();
        int gioHt = calendar3.get(Calendar.HOUR_OF_DAY);
        int phutHT = calendar3.get(Calendar.MINUTE);
        int gioluuBD = sharedPreferences.getInt("hour", 0);
        int phutLuuBD = sharedPreferences.getInt("minute", 0);

        if (minute == sharedPreferences.getInt("minute", 0) && hour == sharedPreferences.getInt("hour", 0)) {

        } else {
            if (gioHt < gioluuBD) {

            } else {
                if (gioHt == gioluuBD) {
                    if (phutHT < phutLuuBD) {

                    } else {
                        if (phutHT > phutLuuBD) {
                            if (toggleButton_Services.isChecked() == true) {
                            } else {
                                Log.e("humm", "xuLyLogic: trên");
                                startService(new Intent(MainActivity.this, MyService.class));
                                toggleButton_Services.setChecked(true);
                                SharedPreferencesManager.setToggleService(this, true);
                                btn_seekBar.setClickable(true);
                                btn_seekBar.setEnabled(true);
                            }
                        }
                        if (phutHT == phutLuuBD) {

                        } else {
                        }
                    }
                } else {
                    if (gioHt > gioluuBD) {
                        if (toggleButton_Services.isChecked() == true) {
                        } else {
                            Log.e("humm", "xuLyLogic: ");
                            startService(new Intent(MainActivity.this, MyService.class));
                            toggleButton_Services.setChecked(true);
                            SharedPreferencesManager.setToggleService(this, true);
                            btn_seekBar.setClickable(true);
                            btn_seekBar.setEnabled(true);
                        }
                    }

                }
            }
        }


    }
//    private void hamcu(){
//        if (minute == sharedPreferences.getInt("minute", 0) && hour == sharedPreferences.getInt("hour", 0)) {
//            Log.e("==", "= nhau: ");
//        } else {
//            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
//            calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
//            calendar.set(Calendar.SECOND, 0);
//            calendar.set(Calendar.MILLISECOND, 0);
//            timeStart = calendar.getTimeInMillis();
//
//            calendar2.set(Calendar.HOUR_OF_DAY, hour1);
//            calendar2.set(Calendar.MINUTE, phut1);
//            calendar2.set(Calendar.SECOND, 0);
//            calendar2.set(Calendar.MILLISECOND, 0);
//            timeStop = calendar2.getTimeInMillis();
//
//            editor.putLong("Key_Time_Start", timeStart);
//            editor.commit();
//            Log.e("vao", "vao");
//
//            Log.e("system", System.currentTimeMillis() + "");
//            Log.e("system1", timeStart + "");
//            Log.e("system2", timeStop + "");
//
//
//            if (System.currentTimeMillis() <= timeStart) {
//
//                Log.e("nhu", "vao");
//                Intent intent = new Intent(MainActivity.this, AlamRecever.class);
//                intent.putExtra("start", true);
//                intent.putExtra("timeStart", timeStart);
//                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeStart, pendingIntent);
//            } else {
//                if (System.currentTimeMillis() >= timeStart && System.currentTimeMillis() <= timeStop) {
//                    if (toggleButton_Services.isChecked() == true) {
//
//                    } else {
//                        Log.e("vao", "dieuKien");
//                        startService(new Intent(MainActivity.this, MyService.class));
//                        toggleButton_Services.setChecked(true);
//                        btn_seekBar.setClickable(true);
//                        btn_seekBar.setEnabled(true);
//                        imageView_btn_1.setVisibility(View.VISIBLE);
//                        SharedPreferencesManager.setToggleService(this, true);
//                        editor.putBoolean("Toggle_check", true);
//                        editor.commit();
//
//                    }
//                } else {
//
//                }
//                timeStart = timeStart + 86400000;
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    Intent intent = new Intent(this, AlamRecever.class);
//                    intent.putExtra("start", true);
//                    intent.putExtra("timeStart", timeStart);
//                    pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeStart, pendingIntent);
//
//                } else {
//                    Intent intent = new Intent("start");
//                    intent.putExtra("start", true);
//                    intent.putExtra("timeStart", timeStart);
//                    pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeStart, pendingIntent);
//                }
//
//            }
//
//        }
//
//
//        if (System.currentTimeMillis() <= timeStop) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                Intent intent1 = new Intent(this, AlamRecever.class);
//                intent1.putExtra("stop", true);
//                intent1.putExtra("timeStop", timeStop);
//                pendingIntent1 = PendingIntent.getBroadcast(MainActivity.this, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//                alarmManager2.set(AlarmManager.RTC_WAKEUP, timeStop, pendingIntent1);
//            } else {
//                Intent intent1 = new Intent("stop");
//                intent1.putExtra("stop", true);
//                intent1.putExtra("timeStop", timeStop);
//                pendingIntent1 = PendingIntent.getBroadcast(MainActivity.this, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//                alarmManager2.set(AlarmManager.RTC_WAKEUP, timeStop, pendingIntent1);
//            }
//
//        } else {
//            if (SharedPreferencesManager.getToggleService(this) == true) {
//                SharedPreferencesManager.setToggleService(MainActivity.this, false);
//                SharedPreferencesManager.setToggleAuto(MainActivity.this, false);
//                toggleButton_Services.setChecked(false);
//                btn_seekBar.setEnabled(false);
//                btn_seekBar.setClickable(false);
//                imageView_btn_1.setVisibility(View.GONE);
//                editor.putBoolean("Toggle_check", false);
//                editor.putBoolean("Toggle_check_auto_night", false);
//                editor.commit();
//                sensorLight = 0.0f;
//                sensorManager.unregisterListener(sensorEventListener);
//                stopService(new Intent(MainActivity.this, MyService.class));
//            } else {
//
//            }
//            timeStop = timeStop + 86400000;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                Intent intent1 = new Intent(this, AlamRecever.class);
//                intent1.putExtra("stop", true);
//                intent1.putExtra("timeStop", timeStop);
//                pendingIntent1 = PendingIntent.getBroadcast(MainActivity.this, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//                alarmManager2.set(AlarmManager.RTC_WAKEUP, timeStop, pendingIntent1);
//            }
//            Intent intent1 = new Intent("stop");
//            intent1.putExtra("stop", true);
//            intent1.putExtra("timeStop", timeStop);
//            pendingIntent1 = PendingIntent.getBroadcast(MainActivity.this, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//            alarmManager2.set(AlarmManager.RTC_WAKEUP, timeStop, pendingIntent1);
//        }
//
//
//        dialog1.cancel();
//        Log.e("hourstart", hour+"" );
//        Log.e("hourEnd", hour1+"" );
//        Log.e("minuteStart", minute+"" );
//        Log.e("minuteEnd", phut1+"" );
//    }
    private void UpdateUiStart(){
        toggleButton_Services.setChecked(true);
        SharedPreferencesManager.setToggleService(MainActivity.this,true);
        imageView_btn_1.setVisibility(View.VISIBLE);
        btn_seekBar.setClickable(true);
        btn_seekBar.setEnabled(true);
        editor.putBoolean("Toggle_check",true);
        editor.commit();
    }


    private void onClickIMv_btn_1() {
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCancelable(true);
        dialog.show();

        TextView txtdialog = dialog.findViewById(R.id.txt_Title_dialog);
        txtdialog.setTypeface(typeface_Semibold);

        toggleButton_CountDown = (ToggleButton) dialog.findViewById(R.id.toggleButton_CountDown);
        edt_time = (EditText) dialog.findViewById(R.id.edt_time);
        rd_30 = (RadioButton) dialog.findViewById(R.id.rb_30);
        rd_60 = (RadioButton) dialog.findViewById(R.id.rb_60);
        rd_90 = (RadioButton) dialog.findViewById(R.id.rb_90);
        rd_120 = (RadioButton) dialog.findViewById(R.id.rb_120);
        btn_save_time = (Button) dialog.findViewById(R.id.btn_save_time);

        edt_time.setInputType(0);

        edt_time.setEnabled(false);
        edt_time.setClickable(false);


        rd_30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRadioButton30();
            }
        });
        rd_60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRadioButton60();

            }
        });
        rd_90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRadioButton90();
            }
        });
        rd_120.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRadioButton120();
            }
        });

        rd_60.setClickable(false);
        rd_30.setClickable(false);
        rd_120.setClickable(false);
        rd_90.setClickable(false);

        toggleButton_CountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggleButton_CountDown.isChecked() == false) {
                    rd_60.setClickable(false);
                    rd_30.setClickable(false);
                    rd_120.setClickable(false);
                    rd_90.setClickable(false);
                    edt_time.setEnabled(false);
                } else {
                    edt_time.setInputType(InputType.TYPE_CLASS_NUMBER);
                    edt_time.setEnabled(true);
                    edt_time.setClickable(true);
                    rd_60.setClickable(true);
                    rd_30.setClickable(true);
                    rd_120.setClickable(true);
                    rd_90.setClickable(true);
                }
            }
        });


        btn_save_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSaveTime();
            }
        });
        toggleButton_CountDown.setChecked(false);

    }

    private void onClickSaveTime() {


        if (edt_time.getText().toString().length() == 0) {
            Toast.makeText(MainActivity.this, R.string.enterTime, Toast.LENGTH_SHORT).show();
        } else {
            if (saveTime == false) {
                saveTime = true;
                if (edt_time.getText().toString().length() == 0 || edt_time.getText().toString().contains(".")) {
                    Toast.makeText(MainActivity.this, R.string.data, Toast.LENGTH_SHORT).show();
                } else {
                    int sumTime, counDown;
                    String chuoi = edt_time.getText().toString();
                    chuoi.trim();
                    counDown = 1000;
                    sumTime = Integer.parseInt(chuoi);
                    sumTime1 = (sumTime * 60000);

                    if (toggleButton_CountDown.isChecked() == true) {

                        try {
                            handler1.removeCallbacksAndMessages(null);
                            handler1 = null;
                        } catch (Exception e) {

                        }


                        handler1 = new Handler();
                        runnable1 = new Runnable() {
                            @Override
                            public void run() {
                                stopService(new Intent(MainActivity.this, MyService.class));
                                toggleButton_Services.setChecked(false);
                                toggleButton_auto_night.setChecked(false);
                                editor.putBoolean("Toggle_check", false);
                                SharedPreferencesManager.setToggleService(MainActivity.this, false);
                                editor.commit();
                                btn_seekBar.setEnabled(false);
                                btn_seekBar.setClickable(false);
                                sensorManager.unregisterListener(sensorEventListener);
                                sensorLight = 0.0f;
                            }
                        };
                        handler1.postDelayed(runnable1, sumTime1);

                        dialog.cancel();
//                    imageView_btn_1.setVisibility(View.GONE);
//                        imageView_btn_2.setVisibility(View.VISIBLE);
                        Imv_time.setVisibility(View.VISIBLE);


                    } else {
                        Toast.makeText(MainActivity.this, R.string.pleaseTurnOn, Toast.LENGTH_SHORT).show();

                    }
                }
            } else {
                saveTime = false;
                handler1.removeCallbacksAndMessages(null);
                handler1 = null;

                handler1 = new Handler();
                runnable1 = new Runnable() {
                    @Override
                    public void run() {
                        stopService(new Intent(MainActivity.this, MyService.class));
                        toggleButton_Services.setChecked(false);
                        toggleButton_auto_night.setChecked(false);
                        editor.putBoolean("Toggle_check", false);
                        SharedPreferencesManager.setToggleService(MainActivity.this, false);
                        SharedPreferencesManager.setToggleAuto(MainActivity.this, false);
                        editor.commit();
                        btn_seekBar.setEnabled(false);
                        btn_seekBar.setClickable(false);
                        sensorManager.unregisterListener(sensorEventListener);
                        sensorLight = 0.0f;
                    }
                };
                handler1.postDelayed(runnable1, sumTime1);


                dialog.cancel();


            }
        }


    }

    private void onClickRadioButton120() {
        rd_60.setChecked(false);
        rd_90.setChecked(false);
        rd_30.setChecked(false);
        edt_time.setText("120");

    }

    private void onClickRadioButton90() {
        rd_60.setChecked(false);
        rd_30.setChecked(false);
        rd_120.setChecked(false);
        edt_time.setText("90");

    }

    private void onClickRadioButton60() {
        rd_30.setChecked(false);
        rd_90.setChecked(false);
        rd_120.setChecked(false);
        edt_time.setText("60");
    }

    private void onClickRadioButton30() {
        rd_60.setChecked(false);
        rd_90.setChecked(false);
        rd_120.setChecked(false);
        edt_time.setText("30");
    }

    private void onclickButtonPink() {
        btn_pink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_green.setBackgroundResource(R.drawable.ic_green);
                btn_blue.setBackgroundResource(R.drawable.ic_blue);
                btn_pink.setBackgroundResource(R.drawable.ic_pink_click);
                btn_red.setBackgroundResource(R.drawable.ic_red);
                btn_yellow.setBackgroundResource(R.drawable.ic_yellow);


                aBooleanRed = false;
                aBooleanGreen = false;
                aBooleanBlue = false;
                aBooleanYellow = false;
                aBooleanPink = true;
                abooleanProgress=false;
                editor.putBoolean("aboolean_progress",abooleanProgress);
                red = 255;
                green = 192;
                blue = 203;
                editor.putInt("red", 255);
                editor.putInt("green", 192);
                editor.putInt("blue", 203);
                editor.putBoolean("aBooleanRed", aBooleanRed);
                editor.putBoolean("aBooleanBlue", aBooleanBlue);
                editor.putBoolean("aBooleanGreen", aBooleanGreen);
                editor.putBoolean("aBooleanPink", aBooleanPink);
                editor.putBoolean("aBooleanYellow", aBooleanYellow);
                editor.commit();
                Log.e("boolean", String.valueOf(aBooleanPink));


                Intent intentPink = new Intent("color_pink");
                intentPink.putExtra("key_color_pink_blue", blue);
                intentPink.putExtra("key_color_pink_green", green);
                intentPink.putExtra("key_color_pink_red", red);
                sendBroadcast(intentPink);
                Intent intent = new Intent("img_pink");
                sendBroadcast(intent);
            }


        });
    }

    private void onclickButtonBlue() {

        btn_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_green.setBackgroundResource(R.drawable.ic_green);
                btn_blue.setBackgroundResource(R.drawable.ic_blue_click);
                btn_pink.setBackgroundResource(R.drawable.ic_pink);
                btn_red.setBackgroundResource(R.drawable.ic_red);
                btn_yellow.setBackgroundResource(R.drawable.ic_yellow);
                aBooleanRed = false;
                aBooleanGreen = false;
                aBooleanBlue = true;
                aBooleanPink = false;
                aBooleanYellow = false;
                red = 0;
                green = 0;
                blue = 255;
                abooleanProgress=false;
                editor.putBoolean("aboolean_progress",abooleanProgress);

                editor.putInt("red", 0);
                editor.putInt("green", 0);
                editor.putInt("blue", 255);
                editor.putBoolean("aBooleanRed", aBooleanRed);
                editor.putBoolean("aBooleanBlue", aBooleanBlue);
                editor.putBoolean("aBooleanGreen", aBooleanGreen);
                editor.putBoolean("aBooleanPink", aBooleanPink);
                editor.putBoolean("aBooleanYellow", aBooleanYellow);
                editor.commit();

                Intent intentBlue = new Intent("color_blue");
                intentBlue.putExtra("key_color_blue", blue);
                sendBroadcast(intentBlue);
                Intent intent = new Intent("img_blue");
                sendBroadcast(intent);
            }

        });

    }

    private void onclickButtonYellow() {
        btn_yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_green.setBackgroundResource(R.drawable.ic_green);
                btn_blue.setBackgroundResource(R.drawable.ic_blue);
                btn_pink.setBackgroundResource(R.drawable.ic_pink);
                btn_red.setBackgroundResource(R.drawable.ic_red);
                btn_yellow.setBackgroundResource(R.drawable.ic_yellow);
                aBooleanYellow = true;
                aBooleanBlue = false;
                aBooleanGreen = false;
                aBooleanPink = false;
                aBooleanRed = false;
                blue = 0;
                red = 255;
                green = 255;
                abooleanProgress=false;
                editor.putBoolean("aboolean_progress",abooleanProgress);

                editor.putInt("red", 255);
                editor.putInt("green", 255);
                editor.putInt("blue", 0);
                editor.putBoolean("aBooleanRed", aBooleanRed);
                editor.putBoolean("aBooleanBlue", aBooleanBlue);
                editor.putBoolean("aBooleanGreen", aBooleanGreen);
                editor.putBoolean("aBooleanPink", aBooleanPink);
                editor.putBoolean("aBooleanYellow", aBooleanYellow);
                editor.commit();

                Intent intentYellow = new Intent("color_yellow");
                intentYellow.putExtra("key_color_yellow_red", red);
                intentYellow.putExtra("key_color_yellow_green", green);
                sendBroadcast(intentYellow);
                Intent intent = new Intent("img_yellow");
                sendBroadcast(intent);

            }
        });

    }

    private void onclickButtonRed() {
        btn_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_green.setBackgroundResource(R.drawable.ic_green);
                btn_blue.setBackgroundResource(R.drawable.ic_blue);
                btn_pink.setBackgroundResource(R.drawable.ic_pink);
                btn_red.setBackgroundResource(R.drawable.ic_red_click);
                btn_yellow.setBackgroundResource(R.drawable.ic_yellow);
                aBooleanRed = true;
                aBooleanGreen = false;
                aBooleanPink = false;
                aBooleanBlue = false;
                aBooleanYellow = false;
                abooleanProgress=false;

                red = 255;
                green = 0;
                blue = 0;

                editor.putInt("red", 255);
                editor.putInt("green", 0);
                editor.putInt("blue", 0);
                editor.putBoolean("aboolean_progress",abooleanProgress);
                editor.putBoolean("aBooleanRed", aBooleanRed);
                editor.putBoolean("aBooleanBlue", aBooleanBlue);
                editor.putBoolean("aBooleanGreen", aBooleanGreen);
                editor.putBoolean("aBooleanPink", aBooleanPink);
                editor.putBoolean("aBooleanYellow", aBooleanYellow);
                editor.commit();
                Intent intentRed = new Intent("color_red");
                intentRed.putExtra("key_color_red", red);
//                startService(intentRed);
                sendBroadcast(intentRed);
                Intent intent = new Intent("img_red");
                sendBroadcast(intent);


            }
        });
    }

    private void onclickButtonGreen() {
        btn_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_green.setBackgroundResource(R.drawable.ic_green_click);
                btn_blue.setBackgroundResource(R.drawable.ic_blue);
                btn_pink.setBackgroundResource(R.drawable.ic_pink);
                btn_red.setBackgroundResource(R.drawable.ic_red);
                btn_yellow.setBackgroundResource(R.drawable.ic_yellow);
                aBooleanGreen = true;
                aBooleanRed = false;
                aBooleanPink = false;
                aBooleanBlue = false;
                aBooleanYellow = false;
                abooleanProgress=false;
                editor.putBoolean("aboolean_progress",abooleanProgress);
                red = 0;
                green = 255;
                blue = 0;

//                int green =255;
//                Intent intentGreen = new Intent(MainActivity.this,MyService.class);
//                intentGreen.putExtra("key_color_green",green);
//                startService(intentGreen);
                editor.putInt("red", 0);
                editor.putInt("green", 255);
                editor.putInt("blue", 0);
                editor.putBoolean("aBooleanRed", aBooleanRed);
                editor.putBoolean("aBooleanBlue", aBooleanBlue);
                editor.putBoolean("aBooleanGreen", aBooleanGreen);
                editor.putBoolean("aBooleanPink", aBooleanPink);
                editor.putBoolean("aBooleanYellow", aBooleanYellow);
                editor.commit();
                Intent intent = new Intent("img_green");
                sendBroadcast(intent);
                Intent intentGreen = new Intent("color_green");
                intentGreen.putExtra("key_color_red", red);
                intentGreen.putExtra("key_color_green", green);
                intentGreen.putExtra("key_color_blue", blue);
                sendBroadcast(intentGreen);

            }
        });
    }

    private void onclickSeekBar() {
        btn_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int phanTram = (int) (progress * 100) / 200;
                txt_PhanTram.setText(phanTram + "%");
                editor.putInt("phanTram", phanTram);
                editor.commit();

                if (BooleanSeekbar == true) {

                } else {
                    if (aBoolean == true) {
                        Intent intent1 = new Intent(MainActivity.this, MyService.class);
                        intent1.putExtra("key_color", seekBar.getProgress());
                        intent1.putExtra("key_color_red", red);
                        intent1.putExtra("key_color_green", green);
                        intent1.putExtra("key_color_blue", blue);
                        Log.e("r,g,b", "r" + red + "g" + green + "b" + blue);
                        startService(intent1);
                    } else {


                        alpha = seekBar.getProgress();
                        Intent intentProgress = new Intent("color_progress");
                        intentProgress.putExtra("key_color", alpha);
                        intentProgress.putExtra("key_color_red", red);
                        intentProgress.putExtra("key_color_green", green);
                        intentProgress.putExtra("key_color_blue", blue);
                        sendBroadcast(intentProgress);
                        if (alpha == 0) {
                            editor.putInt("progress", btn_seekBar.getProgress());
                            editor.commit();
                            sharedPreferences.getInt("progress", 0);
                        } else {
                            editor.putInt("progress", btn_seekBar.getProgress());
                            editor.commit();
                            sharedPreferences.getInt("progress", 0);

                        }

                    }
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void onClickToggleButtonStart() {
        toggleButton_Services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean aBoolean = ((ToggleButton) view).isChecked();
                if (aBoolean == false) {

                    floatingButton=false;

                    sensorManager.unregisterListener(sensorEventListener);
                    sensorLight = 0.0f;
                    toggleButton_auto_night.setChecked(false);
                    btn_seekBar.setEnabled(false);
                    btn_seekBar.setClickable(false);
                    imageView_btn_1.setVisibility(View.GONE);
                    imageView_btn_2.setVisibility(View.GONE);
                    Imv_time.setVisibility(View.VISIBLE);
                    Intent intentToggle_off = new Intent("toggleButton_off");
                    sendBroadcast(intentToggle_off);

                    Intent intent = new Intent("delete_windows");
                    sendBroadcast(intent);
                    stopService(new Intent(MainActivity.this, MyService.class));
                    isHenGio = false;
                    editor.putBoolean("windowManager", isHenGio);
                    SharedPreferencesManager.setToggleService(MainActivity.this, toggleButton_Services.isChecked());
                    SharedPreferencesManager.setToggleAuto(MainActivity.this, toggleButton_auto_night.isChecked());
                    editor.putBoolean("Toggle_check", toggleButton_Services.isChecked());
                    editor.putBoolean("Toggle_check_auto_night", toggleButton_auto_night.isChecked());
                    editor.commit();


                } else {
                    Imv_time.setVisibility(View.VISIBLE);
                    if (floatingButton==true){
                        imageView_btn_1.setVisibility(View.VISIBLE);
                        imageView_btn_2.setVisibility(View.VISIBLE);
                    }else {
                        imageView_btn_1.setVisibility(View.GONE);
                        imageView_btn_2.setVisibility(View.GONE);
                    }
                    BooleanSeekbar = false;
                    aBooleanDieuKien = false;
                    aBoolean = true;
                    aBooleanRed = false;
                    aBooleanBlue = false;
                    aBooleanGreen = false;
                    aBooleanYellow = false;
                    Intent intent = new Intent(MainActivity.this, MyService.class);
                    intent.putExtra("key_color", MainActivity.alpha);
                    intent.putExtra("key_color_red", red);
                    intent.putExtra("key_color_green", green);
                    intent.putExtra("aaa", false);
                    startService(intent);
                    startService(new Intent(MainActivity.this, IntentService.class));
                    btn_seekBar.setClickable(true);
                    btn_seekBar.setEnabled(true);
                }
                SharedPreferencesManager.setToggleService(MainActivity.this, toggleButton_Services.isChecked());
                editor.putBoolean("Toggle_check", toggleButton_Services.isChecked());
                Log.e("put", toggleButton_Services.isChecked() + "");
                editor.commit();


            }
        });
    }


    @Override
    protected void onResume() {
        Log.e("bo", MyService.aBoolean_Img_pink + "");
        Log.e("start_app", start_app + "");

        Log.e("zzz", SharedPreferencesManager.getToggleService(this) + "--");

        txt_PhanTram.setText(sharedPreferences.getInt("phanTram", 0) + "%");



        if (SharedPreferencesManager.getToggleAuto(MainActivity.this) == true) {
            editor.putBoolean("Toggle_check_auto_night", true);
            editor.commit();
        } else {
            editor.putBoolean("Toggle_check_auto_night", false);
            editor.commit();
        }

        if (sharedPreferences.getBoolean("Toggle_check_auto_night", false) == true) {
            toggleButton_auto_night.setChecked(true);
            editor.putBoolean("Toggle_check", true);
            editor.commit();
        } else {
            toggleButton_auto_night.setChecked(false);
            editor.putBoolean("Toggle_check", false);
            editor.commit();
        }
        if (SharedPreferencesManager.getToggleService(MainActivity.this) == true) {
            Log.e("1111", "true");
            btn_seekBar.setClickable(true);
            btn_seekBar.setEnabled(true);
            toggleButton_Services.setChecked(true);

        } else {
            Log.e("1111", "false");
            toggleButton_Services.setChecked(false);
            btn_seekBar.setClickable(false);
            btn_seekBar.setEnabled(false);
        }


        if (toggleButton_Services.isChecked() == true) {
            imageView_btn_1.setVisibility(View.VISIBLE);
            btn_seekBar.setEnabled(true);
            btn_seekBar.setClickable(true);
             Imv_time.setVisibility(View.VISIBLE);
        } else {
            btn_seekBar.setEnabled(false);
            btn_seekBar.setClickable(false);
             Imv_time.setVisibility(View.VISIBLE);
            imageView_btn_1.setVisibility(View.GONE);
        }

        if (MyService.aBoolean_Img_pink == true) {
            red = 255;
            green = 192;
            blue = 203;
            alpha = btn_seekBar.getProgress();
            //btn_pink.setBackgroundResource(R.drawable.custom_button_1);
            Intent intentProgress = new Intent("color_progress");
            intentProgress.putExtra("key_color", alpha);
            intentProgress.putExtra("key_color_red", red);
            intentProgress.putExtra("key_color_green", green);
            intentProgress.putExtra("key_color_blue", blue);
            sendBroadcast(intentProgress);
            btn_green.setBackgroundResource(R.drawable.ic_green);
            btn_blue.setBackgroundResource(R.drawable.ic_blue);
            btn_pink.setBackgroundResource(R.drawable.ic_pink_click);
            btn_red.setBackgroundResource(R.drawable.ic_red);
            btn_yellow.setBackgroundResource(R.drawable.ic_yellow);
            aBooleanRed = false;
            aBooleanGreen = false;
            aBooleanBlue = false;
            aBooleanYellow = false;
            aBooleanPink = true;
            red = 255;
            green = 192;
            blue = 203;
            editor.putInt("pink_red", 255);
            editor.putInt("pink_green", 192);
            editor.putInt("pink_blue", 203);
            editor.putBoolean("aBooleanRed", aBooleanRed);
            editor.putBoolean("aBooleanBlue", aBooleanBlue);
            editor.putBoolean("aBooleanGreen", aBooleanGreen);
            editor.putBoolean("aBooleanPink", aBooleanPink);
            editor.putBoolean("aBooleanYellow", aBooleanYellow);
            editor.commit();
        } else {
            if (MyService.aBoolean_Img_blue == true) {
                red = 0;
                green = 0;
                blue = 255;
                alpha = btn_seekBar.getProgress();
                btn_blue.setBackgroundResource(R.drawable.ic_blue_click);
                Intent intentProgress = new Intent("color_progress");
                intentProgress.putExtra("key_color", alpha);
                intentProgress.putExtra("key_color_red", red);
                intentProgress.putExtra("key_color_green", green);
                intentProgress.putExtra("key_color_blue", blue);
                sendBroadcast(intentProgress);
                btn_green.setBackgroundResource(R.drawable.ic_green);
                btn_pink.setBackgroundResource(R.drawable.ic_pink);
                btn_red.setBackgroundResource(R.drawable.ic_red);
                btn_yellow.setBackgroundResource(R.drawable.ic_yellow);
                aBooleanRed = false;
                aBooleanGreen = false;
                aBooleanBlue = true;
                aBooleanYellow = false;
                aBooleanPink = false;
                red = 0;
                green = 0;
                blue = 255;
                editor.putInt("pink_red", 0);
                editor.putInt("pink_green", 0);
                editor.putInt("pink_blue", 255);
                editor.putBoolean("aBooleanRed", aBooleanRed);
                editor.putBoolean("aBooleanBlue", aBooleanBlue);
                editor.putBoolean("aBooleanGreen", aBooleanGreen);
                editor.putBoolean("aBooleanPink", aBooleanPink);
                editor.putBoolean("aBooleanYellow", aBooleanYellow);
                editor.commit();
            } else {
                if (MyService.aBoolean_Img_green == true) {

                    red = 0;
                    green = 255;
                    blue = 0;
                    alpha = btn_seekBar.getProgress();
                    Intent intentProgress = new Intent("color_progress");
                    intentProgress.putExtra("key_color", alpha);
                    intentProgress.putExtra("key_color_red", red);
                    intentProgress.putExtra("key_color_green", green);
                    intentProgress.putExtra("key_color_blue", blue);
                    sendBroadcast(intentProgress);
                    btn_green.setBackgroundResource(R.drawable.ic_green_click);
                    btn_blue.setBackgroundResource(R.drawable.ic_blue);
                    btn_pink.setBackgroundResource(R.drawable.ic_pink);
                    btn_red.setBackgroundResource(R.drawable.ic_red);
                    btn_yellow.setBackgroundResource(R.drawable.ic_yellow);
                    aBooleanRed = false;
                    aBooleanGreen = true;
                    aBooleanBlue = false;
                    aBooleanPink = false;
                    aBooleanYellow = false;
                    editor.putInt("blue_red", 0);
                    editor.putInt("blue_green", 0);
                    editor.putInt("blue_blue", 255);
                    editor.putBoolean("aBooleanRed", aBooleanRed);
                    editor.putBoolean("aBooleanBlue", aBooleanBlue);
                    editor.putBoolean("aBooleanGreen", aBooleanGreen);
                    editor.putBoolean("aBooleanPink", aBooleanPink);
                    editor.putBoolean("aBooleanYellow", aBooleanYellow);
                    editor.commit();
                } else {
                    if (MyService.aBoolean_Img_red == true) {
                        red = 255;
                        green = 0;
                        blue = 0;
                        alpha = btn_seekBar.getProgress();
                        Intent intentProgress = new Intent("color_progress");
                        intentProgress.putExtra("key_color", alpha);
                        intentProgress.putExtra("key_color_red", red);
                        intentProgress.putExtra("key_color_green", green);
                        intentProgress.putExtra("key_color_blue", blue);
                        sendBroadcast(intentProgress);
                        btn_green.setBackgroundResource(R.drawable.ic_green);
                        btn_blue.setBackgroundResource(R.drawable.ic_blue);
                        btn_pink.setBackgroundResource(R.drawable.ic_pink);
                        btn_red.setBackgroundResource(R.drawable.ic_red_click);
                        btn_yellow.setBackgroundResource(R.drawable.ic_yellow);
                        aBooleanRed = true;
                        aBooleanGreen = false;
                        aBooleanPink = false;
                        aBooleanBlue = false;
                        aBooleanYellow = false;
                        red = 255;
                        green = 0;
                        blue = 0;
                        editor.putBoolean("aBooleanRed", aBooleanRed);
                        editor.putBoolean("aBooleanBlue", aBooleanBlue);
                        editor.putBoolean("aBooleanGreen", aBooleanGreen);
                        editor.putBoolean("aBooleanPink", aBooleanPink);
                        editor.putBoolean("aBooleanYellow", aBooleanYellow);
                        editor.commit();
                    } else {
                        if (MyService.aBoolean_Img_yellow == true) {
                            blue = 0;
                            red = 255;
                            green = 255;
                            alpha = btn_seekBar.getProgress();
                            btn_yellow.setBackgroundResource(R.drawable.custom_button_1);
                            Intent intentProgress = new Intent("color_progress");
                            intentProgress.putExtra("key_color", alpha);
                            intentProgress.putExtra("key_color_red", red);
                            intentProgress.putExtra("key_color_green", green);
                            intentProgress.putExtra("key_color_blue", blue);
                            sendBroadcast(intentProgress);

                            btn_green.setBackgroundResource(R.drawable.ic_green);
                            btn_blue.setBackgroundResource(R.drawable.ic_blue);
                            btn_pink.setBackgroundResource(R.drawable.ic_pink);
                            btn_red.setBackgroundResource(R.drawable.ic_red);
                            btn_yellow.setBackgroundResource(R.drawable.ic_yellow_click);
                            aBooleanYellow = true;
                            aBooleanBlue = false;
                            aBooleanGreen = false;
                            aBooleanPink = false;
                            aBooleanRed = false;
                            blue = 0;
                            red = 255;
                            green = 255;
                            editor.putInt("yellow_red", 255);
                            editor.putInt("yellow_green", 255);
                            editor.putInt("yellow_blue", 0);
                            editor.putBoolean("aBooleanRed", aBooleanRed);
                            editor.putBoolean("aBooleanBlue", aBooleanBlue);
                            editor.putBoolean("aBooleanGreen", aBooleanGreen);
                            editor.putBoolean("aBooleanPink", aBooleanPink);
                            editor.putBoolean("aBooleanYellow", aBooleanYellow);
                            editor.commit();

                        } else {

                        }
                    }
                }
            }
        }

        super.onResume();


        // DieuKien();

    }

    private void DieuKien() {
        if (sharedPreferences.getBoolean("aBooleanRed", false) == true) {
            red = 255;
            green = 0;
            blue = 0;
            Intent intent = new Intent("aBooleanRedTrue");
            intent.putExtra("aBooleanRedTrue_red", red);
            intent.putExtra("aBooleanRedTrue_green", green);
            intent.putExtra("aBooleanRedTrue_blue", blue);
            intent.putExtra("aBooleanRedTrue_alpha", MainActivity.alpha);
            sendBroadcast(intent);

        } else {
            if (sharedPreferences.getBoolean("aBooleanGreen", false) == true) {
                red = 0;
                green = 255;
                blue = 0;

            } else {
                if (sharedPreferences.getBoolean("aBooleanPink", false) == true) {
                    red = 255;
                    green = 192;
                    blue = 203;

                } else {
                    if (sharedPreferences.getBoolean("aBooleanYellow", false) == true) {
                        blue = 0;
                        red = 255;
                        green = 255;

                    } else {
                        if (sharedPreferences.getBoolean("aBooleanBlue", false) == true) {
                            red = 0;
                            green = 0;
                            blue = 255;

                        } else {

                        }
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (finish == true) {
            finish();
        } else {
            Dialog dialogfinish = new Dialog(this);
            dialogfinish.setCancelable(true);
            dialogfinish.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogfinish.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialogfinish.setContentView(R.layout.custom_dialog_rate_start);
            dialogfinish.show();
            img_btn1 = dialogfinish.findViewById(R.id.start1);
            img_btn2 = dialogfinish.findViewById(R.id.start2);
            img_btn3 = dialogfinish.findViewById(R.id.start3);
            img_btn4 = dialogfinish.findViewById(R.id.start4);
            img_btn5 = dialogfinish.findViewById(R.id.start5);
            btn_later = dialogfinish.findViewById(R.id.btn_later);
            btn_sure = dialogfinish.findViewById(R.id.btn_sure);

            img_btn1.setOnClickListener(this);
            img_btn2.setOnClickListener(this);
            img_btn3.setOnClickListener(this);
            img_btn4.setOnClickListener(this);
            img_btn5.setOnClickListener(this);
            btn_sure.setOnClickListener(this);
            btn_later.setOnClickListener(this);

        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start1:
                img_btn1.setBackgroundResource(R.drawable.star_select);
                img_btn2.setBackgroundResource(R.drawable.star);
                img_btn3.setBackgroundResource(R.drawable.star);
                img_btn4.setBackgroundResource(R.drawable.star);
                img_btn5.setBackgroundResource(R.drawable.star);
                break;
            case R.id.start2:
                img_btn1.setBackgroundResource(R.drawable.star_select);
                img_btn2.setBackgroundResource(R.drawable.star_select);
                img_btn3.setBackgroundResource(R.drawable.star);
                img_btn4.setBackgroundResource(R.drawable.star);
                img_btn5.setBackgroundResource(R.drawable.star);
                break;
            case R.id.start3:
                img_btn1.setBackgroundResource(R.drawable.star_select);
                img_btn2.setBackgroundResource(R.drawable.star_select);
                img_btn3.setBackgroundResource(R.drawable.star_select);
                img_btn4.setBackgroundResource(R.drawable.star);
                img_btn5.setBackgroundResource(R.drawable.star);
                break;
            case R.id.start4:
                img_btn1.setBackgroundResource(R.drawable.star_select);
                img_btn2.setBackgroundResource(R.drawable.star_select);
                img_btn3.setBackgroundResource(R.drawable.star_select);
                img_btn4.setBackgroundResource(R.drawable.star_select);
                img_btn5.setBackgroundResource(R.drawable.star);
                break;
            case R.id.start5:
                img_btn1.setBackgroundResource(R.drawable.star_select);
                img_btn2.setBackgroundResource(R.drawable.star_select);
                img_btn3.setBackgroundResource(R.drawable.star_select);
                img_btn4.setBackgroundResource(R.drawable.star_select);
                img_btn5.setBackgroundResource(R.drawable.star_select);
                break;
            case R.id.btn_sure:
                finish = true;
                Uri uri = Uri.parse("market://details?id=" + MainActivity.this.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName())));
                    finish();
                }
                break;
            case R.id.btn_later:
                finish();
                break;
            case R.id.Imv_time:
             if (floatingButton==false){
                 floatingButton=true;
                 imageView_btn_2.setVisibility(View.VISIBLE);
                 if (toggleButton_Services.isChecked()==true){
                     imageView_btn_1.setVisibility(View.VISIBLE);
                 }else {
                     imageView_btn_1.setVisibility(View.GONE);
                 }


             }else {
                 floatingButton=false;
                 imageView_btn_2.setVisibility(View.GONE);
                 imageView_btn_1.setVisibility(View.GONE);

             }

                break;

        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}


