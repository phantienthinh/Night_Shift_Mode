package com.example.tienthinh.nightshiftmode;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
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

import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    public static boolean start_app=false;
    public static int alpha;
    public static int width;
    public static int height;
    public static int hour1;
    public static int phut1;
    public static int hour, minute;
    public static int red;
    public static int green;
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
    public static SharedPreferences sharedPreferences;
    private View view;
    private String SHARED_PREFERENCES_NAME;
    public static ToggleButton toggleButton_Services;
    private ToggleButton toggleButton_CountDown;
    private ToggleButton toggleButton_auto_night;
    private Toolbar toolbar;
    private SeekBar btn_seekBar;
    private boolean aBoolean;
    private boolean aBooleanRed = false;
    private boolean aBooleanBlue = false;
    private boolean aBooleanGreen = false;
    private boolean aBooleanPink = false;
    private boolean aBooleanYellow = false;
    private boolean aBooleanToggle = false;
    private boolean aBooleanBackground;
    private boolean aBooleanDieuKien = false;
    private boolean BooleanSeekbar=false;
    private MyService myService;
    private RadioGroup radioGroup;
    private RadioButton rd_30, rd_60, rd_90, rd_120;
    private Button btn_green, btn_red, btn_yellow, btn_pink, btn_blue, btn_save_time, btn_set_time;
    private EditText edt_time;
    private Dialog dialog;
    private Dialog dialog1;
    private TimePicker timePicker;
    private TextView tv_time_start, tv_time_stop;
    public static SharedPreferences.Editor editor;
    private AlarmManager alarmManager;
    private AlarmManager alarmManager1;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findRealSize(MainActivity.this);
        initView();
        setSupportActionBar(toolbar);
       // permisonAndroid();
        permisonAndroidTest();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        KhoiTaoSensor();
//        float k = sensor.getMaximumRange();
        //      Log.e("k", k + "");

        Log.e("r,g,b", "red" + red + "green" + green + "blue" + blue);

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Log.d("oncreate", "created");
        if (position == 0) {
            btn_seekBar.setProgress(sharedPreferences.getInt("progress", 0));
            Log.d("position", position + "");
        } else {
            btn_seekBar.setProgress(sharedPreferences.getInt("progress", 0));
            Log.d("position1", position + "");
        }



        toggleButton_Services.setChecked(sharedPreferences.getBoolean("Toggle_check", false));
        toggleButton_auto_night.setChecked(sharedPreferences.getBoolean("Toggle_check_auto_night", false));
        Log.e("cl", red + ":" + green + ":" + blue + "");
//        if (red == 0) {
//            if (green == 0) {
//                if (blue == 0) {
        if (MyService.aBoolean_Img_red == true) {
            red = 255;
            green = 0;
            blue = 0;
            alpha = btn_seekBar.getProgress();
            btn_red.setBackgroundResource(R.drawable.custom_button_1);
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
                btn_green.setBackgroundResource(R.drawable.custom_button_1);
                Intent intentProgress = new Intent("color_progress");
                intentProgress.putExtra("key_color", alpha);
                intentProgress.putExtra("key_color_red", red);
                intentProgress.putExtra("key_color_green", green);
                intentProgress.putExtra("key_color_blue", blue);
                sendBroadcast(intentProgress);
            } else {
                if (MyService.aBoolean_Img_pink==true) {
                    red = 255;
                    green = 192;
                    blue = 203;
                    alpha = btn_seekBar.getProgress();
                    btn_pink.setBackgroundResource(R.drawable.custom_button_1);
                    Intent intentProgress = new Intent("color_progress");
                    intentProgress.putExtra("key_color", alpha);
                    intentProgress.putExtra("key_color_red", red);
                    intentProgress.putExtra("key_color_green", green);
                    intentProgress.putExtra("key_color_blue", blue);
                    sendBroadcast(intentProgress);
                } else {
                    if (MyService.aBoolean_Img_yellow==true) {
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
                    } else {
                        if (MyService.aBoolean_Img_blue==true) {
                            blue = 255;
                            red = 0;
                            green = 0;
                            alpha = btn_seekBar.getProgress();
                            btn_blue.setBackgroundResource(R.drawable.custom_button_1);
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

    private void KhoiTaoReceive() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "img_pink":
                        btn_green.setBackgroundResource(R.drawable.custom_button);
                        btn_blue.setBackgroundResource(R.drawable.custom_button);
                        btn_pink.setBackgroundResource(R.drawable.custom_button_1);
                        btn_red.setBackgroundResource(R.drawable.custom_button);
                        btn_yellow.setBackgroundResource(R.drawable.custom_button);
                        Log.e("img_pink", "da vao toi day");
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
                        break;
                    case "img_green":
                        btn_pink.setBackgroundResource(R.drawable.custom_button);
                        btn_blue.setBackgroundResource(R.drawable.custom_button);
                        btn_green.setBackgroundResource(R.drawable.custom_button_1);
                        btn_red.setBackgroundResource(R.drawable.custom_button);
                        btn_yellow.setBackgroundResource(R.drawable.custom_button);
                        aBooleanRed = false;
                        aBooleanGreen = true;
                        aBooleanBlue = false;
                        aBooleanYellow = false;
                        aBooleanPink = false;
                        red = 0;
                        green = 255;
                        blue = 0;
                        editor.putInt("pink_red", 0);
                        editor.putInt("pink_green", 255);
                        editor.putInt("pink_blue", 0);
                        editor.putBoolean("aBooleanRed", aBooleanRed);
                        editor.putBoolean("aBooleanBlue", aBooleanBlue);
                        editor.putBoolean("aBooleanGreen", aBooleanGreen);
                        editor.putBoolean("aBooleanPink", aBooleanPink);
                        editor.putBoolean("aBooleanYellow", aBooleanYellow);
                        editor.commit();
                        break;
                    case "img_blue":
                        btn_pink.setBackgroundResource(R.drawable.custom_button);
                        btn_green.setBackgroundResource(R.drawable.custom_button);
                        btn_blue.setBackgroundResource(R.drawable.custom_button_1);
                        btn_red.setBackgroundResource(R.drawable.custom_button);
                        btn_yellow.setBackgroundResource(R.drawable.custom_button);
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
                        break;
                    case "img_red":
                        btn_pink.setBackgroundResource(R.drawable.custom_button);
                        btn_green.setBackgroundResource(R.drawable.custom_button);
                        btn_blue.setBackgroundResource(R.drawable.custom_button);
                        btn_red.setBackgroundResource(R.drawable.custom_button_1);
                        btn_yellow.setBackgroundResource(R.drawable.custom_button);
                        aBooleanRed = true;
                        aBooleanGreen = false;
                        aBooleanBlue = false;
                        aBooleanYellow = false;
                        aBooleanPink = false;
                        red = 255;
                        green = 0;
                        blue = 0;
                        editor.putInt("pink_red", 255);
                        editor.putInt("pink_green", 0);
                        editor.putInt("pink_blue", 0);
                        editor.putBoolean("aBooleanRed", aBooleanRed);
                        editor.putBoolean("aBooleanBlue", aBooleanBlue);
                        editor.putBoolean("aBooleanGreen", aBooleanGreen);
                        editor.putBoolean("aBooleanPink", aBooleanPink);
                        editor.putBoolean("aBooleanYellow", aBooleanYellow);
                        editor.commit();
                        break;
                    case "img_yellow":
                        btn_pink.setBackgroundResource(R.drawable.custom_button);
                        btn_green.setBackgroundResource(R.drawable.custom_button);
                        btn_blue.setBackgroundResource(R.drawable.custom_button);
                        btn_red.setBackgroundResource(R.drawable.custom_button);
                        btn_yellow.setBackgroundResource(R.drawable.custom_button_1);
                        aBooleanRed = false;
                        aBooleanGreen = false;
                        aBooleanBlue = false;
                        aBooleanYellow = true;
                        aBooleanPink = false;
                        red = 255;
                        green = 255;
                        blue = 0;
                        editor.putInt("pink_red", 255);
                        editor.putInt("pink_green", 255);
                        editor.putInt("pink_blue", 0);
                        editor.putBoolean("aBooleanRed", aBooleanRed);
                        editor.putBoolean("aBooleanBlue", aBooleanBlue);
                        editor.putBoolean("aBooleanGreen", aBooleanGreen);
                        editor.putBoolean("aBooleanPink", aBooleanPink);
                        editor.putBoolean("aBooleanYellow", aBooleanYellow);
                        editor.commit();
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
        getBaseContext().registerReceiver(receiver, filter);
    }

    private  void DiaLogShow(){
        ShowDiaLog = new Dialog(this);
        ShowDiaLog.setCancelable(false);
        ShowDiaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ShowDiaLog.setContentView(R.layout.dialog_progressbar);
        ShowDiaLog.show();
    }
    private void openSensor(){
        new AsyncTask<Void,Void,Void>(){


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                DiaLogShow();
            }


            @Override
            protected Void doInBackground(Void... voids) {
                    sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), sensorManager.SENSOR_DELAY_FASTEST);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                Toast.makeText(MainActivity.this, "Chức năng đã được bật", Toast.LENGTH_SHORT).show();
                ShowDiaLog.cancel();
            }
        }.execute();
    }


    private void permisonAndroid() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 12);
            }
        }
    }

    private void permisonAndroidTest(){
        if(Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1234);
            }
        }
        else
        {
            Toast.makeText(context, "Vui lòng caaos quyền cho ứng dụng", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==12){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)){
                    toggleButton_Services.setChecked(false);
                    editor.putBoolean("Toggle_check", toggleButton_Services.isChecked());
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
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        Imv_time = (ImageView) findViewById(R.id.Imv_time);
        btn_blue = findViewById(R.id.btn_blue);
        btn_green = findViewById(R.id.btn_green);
        btn_red = findViewById(R.id.btn_red);
        btn_pink = findViewById(R.id.btn_pink);
        btn_yellow = findViewById(R.id.btn_yellow);
        btn_seekBar = (SeekBar) findViewById(R.id.seekBar);
        toggleButton_Services = (ToggleButton) findViewById(R.id.toggleButton);
        imageView_btn_1 = (ImageView) findViewById(R.id.Imv_time_1);
        imageView_btn_2 = (ImageView) findViewById(R.id.Imv_time_2);
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


    }

    private void onClickToggleButtonAutoStart() {
        toggleButton_auto_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean boleanAutoStart = toggleButton_auto_night.isChecked();
                Log.e("boleanAutoStart", String.valueOf(boleanAutoStart));
                if (boleanAutoStart == true) {


                    if (toggleButton_Services.isChecked() == true) {

                    } else {
                        Toast.makeText(MainActivity.this, "Vui lòng bật ứng dụng", Toast.LENGTH_SHORT).show();
                        toggleButton_auto_night.setChecked(false);
                        aBooleanDieuKien = false;
                    }
                    if (toggleButton_auto_night.isChecked() == false) {

                    } else {
                   if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null){
                       BooleanSeekbar = true;
                       aBooleanDieuKien = true;
                       openSensor();

                        } else {
                            toggleButton_auto_night.setChecked(false);
                            Toast.makeText(MainActivity.this, "Xin lỗi!!! điện thoại của bạn không thể sử dụng được chức năng này ",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    BooleanSeekbar =false;
                    aBooleanDieuKien = false;
                    sensorManager.unregisterListener(sensorEventListener);
                    sensorLight = 0;
                    alpha = btn_seekBar.getProgress();
                    Intent intent = new Intent("setSeekBar");
                    sendBroadcast(intent);

                    //c  sensorManager.unregisterListener((SensorEventListener) MainActivity.this,sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY));
                    Toast.makeText(MainActivity.this, "Bạn đã tắt chức năng", Toast.LENGTH_SHORT).show();
                }
                editor.putBoolean("Toggle_check_auto_night", toggleButton_auto_night.isChecked());
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
        Imv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aBooleanBackground == false) {
                    aBooleanBackground = true;
                    imageView_btn_1.setVisibility(View.VISIBLE);
                    imageView_btn_2.setVisibility(View.VISIBLE);
                } else {
                    aBooleanBackground = false;
                    imageView_btn_1.setVisibility(View.GONE);
                    imageView_btn_2.setVisibility(View.GONE);
                }

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
                        if (sharedPreferences.getInt("hour", 0) == 0 && sharedPreferences.getInt("minute", 0) == 0) {

                        } else {
                            tv_time_start.setText(sharedPreferences.getInt("hour", 0) + ":"
                                    + sharedPreferences.getInt("minute", 0));
                        }

                        if (sharedPreferences.getInt("hour1", 0) == 0 && sharedPreferences.getInt("phut1", 0) == 0) {

                        } else {
                            tv_time_stop.setText(sharedPreferences.getInt("hour1", 0) + ":" + sharedPreferences.getInt("phut1", 0));
                        }

                    }
                });

            }
        });
    }

    private void onClickImv_btn_2() {

        dialog1 = new Dialog(MainActivity.this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setCancelable(true);
        dialog1.setContentView(R.layout.custom_dialog_calendar);
        dialog1.show();
        timePicker = (TimePicker) dialog1.findViewById(R.id.time_Picker);
        tv_time_start = (TextView) dialog1.findViewById(R.id.tv_time_start);
        tv_time_stop = (TextView) dialog1.findViewById(R.id.tv_time_stop);
        btn_set_time = (Button) dialog1.findViewById(R.id.btn_set_time);
//        if ()


        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int i) {
                hour = hourOfDay;
                minute = i;
                if (minute < 10) {
                    String phut = "0" + minute;
                    tv_time_start.setText(hour + ":" + phut);
                } else {
                    tv_time_start.setText(hour + ":" + minute);
                }
                editor.putInt("hour", hour);
                editor.putInt("minute", i);
                editor.commit();

            }
        });
        tv_time_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tv_time_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar1 = Calendar.getInstance();
                int gio = calendar1.get(Calendar.HOUR);
                int phut = calendar1.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour1 = hourOfDay;
                        phut1 = minute;
                        tv_time_stop.setText(hourOfDay + ":" + minute);

                        Log.e("calendar1", hourOfDay + "");
                        editor.putInt("hour1", hour1);
                        editor.putInt("phut1", phut1);
                        editor.commit();

                    }
                }, gio, phut, false);

                timePickerDialog.show();
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
        if (tv_time_start.getText().length() == 0) {
            Toast.makeText(MainActivity.this, "Vui lòng chọn thời gian bắt đầu", Toast.LENGTH_SHORT).show();

        } else {
            if (tv_time_stop.getText().length() == 0) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập thời gian kết thúc", Toast.LENGTH_SHORT).show();
            } else {
                if (tv_time_stop.getText().equals(tv_time_start.getText()) == true) {
                    Toast.makeText(MainActivity.this, "Sai dữ liệu", Toast.LENGTH_SHORT).show();
                } else {
                    calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR, hour);
                    calendar.set(Calendar.MINUTE, minute);
                    alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    imageView_btn_1.setVisibility(View.GONE);
                    imageView_btn_2.setVisibility(View.GONE);
                    dialog1.cancel();
                    editor.putInt("hour", hour);
                    editor.putInt("minute", minute);
                    editor.putInt("hour1", hour1);
                    editor.putInt("hour1", phut1);
                    editor.commit();
                    Log.e("xl", "hour: " + hour + "minute: " + minute + "hour1: " + hour1 + "minute1: " + phut1);
                }

            }

        }

    }


    private void onClickIMv_btn_1() {
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCancelable(true);
        dialog.show();

        toggleButton_CountDown = (ToggleButton) dialog.findViewById(R.id.toggleButton_CountDown);
        edt_time = (EditText) dialog.findViewById(R.id.edt_time);
        radioGroup = (RadioGroup) dialog.findViewById(R.id.rd_group);
        rd_30 = (RadioButton) dialog.findViewById(R.id.rb_30);
        rd_60 = (RadioButton) dialog.findViewById(R.id.rb_60);
        rd_90 = (RadioButton) dialog.findViewById(R.id.rb_90);
        rd_120 = (RadioButton) dialog.findViewById(R.id.rb_120);
        btn_save_time = (Button) dialog.findViewById(R.id.btn_save_time);
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
        btn_save_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSaveTime();
            }
        });
        toggleButton_CountDown.setChecked(false);

    }

    private void onClickSaveTime() {
        Log.e("button_service", toggleButton_Services.isChecked()+"");
        if (edt_time.getText().toString().length() == 0) {
            Toast.makeText(MainActivity.this, "Không được để trống thời gian", Toast.LENGTH_SHORT).show();
        } else {
            int sumTime, counDown;
            String chuoi = edt_time.getText().toString();
            chuoi.trim();
            counDown = 1000;
            sumTime = Integer.parseInt(chuoi);
            int sumTime1 = (sumTime * 60000);

            CountDownTimer countDownTimer = new CountDownTimer(sumTime1, counDown) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.d("sumtime1", millisUntilFinished + "");
                }

                @Override
                public void onFinish() {
                    // Toast.makeText(MainActivity.this,"end",Toast.LENGTH_SHORT).show();
                    Log.d("sumTime1", "onFinish: ");
                    // toggleButton_Services.setChecked(false);
                    stopService(new Intent(MainActivity.this, MyService.class));
                    toggleButton_Services.setChecked(false);
                }

            };
            if (toggleButton_CountDown.isChecked() == true) {
                countDownTimer.start();
                dialog.cancel();
                imageView_btn_1.setVisibility(View.GONE);
                imageView_btn_2.setVisibility(View.GONE);

            } else {
                Toast.makeText(MainActivity.this, "Vui lòng bật chức năng", Toast.LENGTH_SHORT).show();
                countDownTimer.cancel();
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
                btn_green.setBackgroundResource(R.drawable.custom_button);
                btn_blue.setBackgroundResource(R.drawable.custom_button);
                btn_pink.setBackgroundResource(R.drawable.custom_button_1);
                btn_red.setBackgroundResource(R.drawable.custom_button);
                btn_yellow.setBackgroundResource(R.drawable.custom_button);


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

                btn_green.setBackgroundResource(R.drawable.custom_button);
                btn_blue.setBackgroundResource(R.drawable.custom_button_1);
                btn_pink.setBackgroundResource(R.drawable.custom_button);
                btn_red.setBackgroundResource(R.drawable.custom_button);
                btn_yellow.setBackgroundResource(R.drawable.custom_button);
                aBooleanRed = false;
                aBooleanGreen = false;
                aBooleanBlue = true;
                aBooleanPink = false;
                aBooleanYellow = false;
                red = 0;
                green = 0;
                blue = 255;
                Intent intentBlue = new Intent("color_blue");
                intentBlue.putExtra("key_color_blue", blue);
                sendBroadcast(intentBlue);
                editor.putInt("blue_red", 0);
                editor.putInt("blue_green", 0);
                editor.putInt("blue_blue", 255);
                editor.putBoolean("aBooleanRed", aBooleanRed);
                editor.putBoolean("aBooleanBlue", aBooleanBlue);
                editor.putBoolean("aBooleanGreen", aBooleanGreen);
                editor.putBoolean("aBooleanPink", aBooleanPink);
                editor.putBoolean("aBooleanYellow", aBooleanYellow);
                editor.commit();
                Intent intent = new Intent("img_blue");
                sendBroadcast(intent);
            }
        });

    }

    private void onclickButtonYellow() {
        btn_yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_green.setBackgroundResource(R.drawable.custom_button);
                btn_blue.setBackgroundResource(R.drawable.custom_button);
                btn_pink.setBackgroundResource(R.drawable.custom_button);
                btn_red.setBackgroundResource(R.drawable.custom_button);
                btn_yellow.setBackgroundResource(R.drawable.custom_button_1);
                aBooleanYellow = true;
                aBooleanBlue = false;
                aBooleanGreen = false;
                aBooleanPink = false;
                aBooleanRed = false;
                blue = 0;
                red = 255;
                green = 255;
                Intent intentYellow = new Intent("color_yellow");
                intentYellow.putExtra("key_color_yellow_red", red);
                intentYellow.putExtra("key_color_yellow_green", green);
                sendBroadcast(intentYellow);
                editor.putInt("yellow_red", 255);
                editor.putInt("yellow_green", 255);
                editor.putInt("yellow_blue", 0);
                editor.putBoolean("aBooleanRed", aBooleanRed);
                editor.putBoolean("aBooleanBlue", aBooleanBlue);
                editor.putBoolean("aBooleanGreen", aBooleanGreen);
                editor.putBoolean("aBooleanPink", aBooleanPink);
                editor.putBoolean("aBooleanYellow", aBooleanYellow);
                editor.commit();
                Intent intent = new Intent("img_yellow");
                sendBroadcast(intent);

            }
        });

    }

    private void onclickButtonRed() {
        btn_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_green.setBackgroundResource(R.drawable.custom_button);
                btn_blue.setBackgroundResource(R.drawable.custom_button);
                btn_pink.setBackgroundResource(R.drawable.custom_button);
                btn_red.setBackgroundResource(R.drawable.custom_button_1);
                btn_yellow.setBackgroundResource(R.drawable.custom_button);
                aBooleanRed = true;
                aBooleanGreen = false;
                aBooleanPink = false;
                aBooleanBlue = false;
                aBooleanYellow = false;
                red = 255;
                green = 0;
                blue = 0;
                Intent intentRed = new Intent("color_red");
                intentRed.putExtra("key_color_red", red);
//                startService(intentRed);
                sendBroadcast(intentRed);
                editor.putBoolean("aBooleanRed", aBooleanRed);
                editor.putBoolean("aBooleanBlue", aBooleanBlue);
                editor.putBoolean("aBooleanGreen", aBooleanGreen);
                editor.putBoolean("aBooleanPink", aBooleanPink);
                editor.putBoolean("aBooleanYellow", aBooleanYellow);
                editor.commit();
                Intent intent = new Intent("img_red");
                sendBroadcast(intent);
            }
        });
    }

    private void onclickButtonGreen() {
        btn_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_green.setBackgroundResource(R.drawable.custom_button_1);
                btn_blue.setBackgroundResource(R.drawable.custom_button);
                btn_pink.setBackgroundResource(R.drawable.custom_button);
                btn_red.setBackgroundResource(R.drawable.custom_button);
                btn_yellow.setBackgroundResource(R.drawable.custom_button);
                aBooleanGreen = true;
                aBooleanRed = false;
                aBooleanPink = false;
                aBooleanBlue = false;
                aBooleanYellow = false;
                red = 0;
                green = 255;
                blue = 0;
                Intent intentGreen = new Intent("color_green");
                intentGreen.putExtra("key_color_red", red);
                intentGreen.putExtra("key_color_green", green);
                intentGreen.putExtra("key_color_blue", blue);
                sendBroadcast(intentGreen);
//                int green =255;
//                Intent intentGreen = new Intent(MainActivity.this,MyService.class);
//                intentGreen.putExtra("key_color_green",green);
//                startService(intentGreen);
                editor.putInt("green_red", 0);
                editor.putInt("green_green", 255);
                editor.putInt("green_blue", 0);
                editor.putBoolean("aBooleanRed", aBooleanRed);
                editor.putBoolean("aBooleanBlue", aBooleanBlue);
                editor.putBoolean("aBooleanGreen", aBooleanGreen);
                editor.putBoolean("aBooleanPink", aBooleanPink);
                editor.putBoolean("aBooleanYellow", aBooleanYellow);
                editor.commit();
                Intent intent = new Intent("img_green");
                sendBroadcast(intent);
            }
        });
    }

    private void onclickSeekBar() {
        btn_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (BooleanSeekbar==true){

                }else {
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
                    Imv_time.setVisibility(View.GONE);
                    imageView_btn_1.setVisibility(View.GONE);
                    imageView_btn_2.setVisibility(View.GONE);
                    Intent intentToggle_off = new Intent("toggleButton_off");
                    sendBroadcast(intentToggle_off);
                    stopService(new Intent(MainActivity.this, MyService.class));
                    Intent intent = new Intent("delete_windows");
                    sendBroadcast(intent);
//                    try {
//                        notificationManager.cancel(1998);
//                    } catch (Exception e) {
//
//                    }


                } else {
                    aBoolean = true;
                    aBooleanRed = false;
                    aBooleanBlue = false;
                    aBooleanGreen = false;
                    aBooleanYellow = false;
                    Imv_time.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(MainActivity.this, MyService.class);
                    intent.putExtra("key_color", MainActivity.alpha);
                    intent.putExtra("key_color_red", red);
                    // intent.putExtra();
                    intent.putExtra("key_color_green", green);
                    startService(intent);
                    Toast.makeText(MainActivity.this, "on", Toast.LENGTH_SHORT).show();

                   // KhoiTaoNoification();
                }
                editor.putBoolean("Toggle_check", toggleButton_Services.isChecked());
                Log.e("put", toggleButton_Services.isChecked()+"");
                editor.commit();


            }
        });
    }




    @Override
    protected void onResume() {
        Log.e("bo", MyService.aBoolean_Img_pink + "");
        Log.e("start_app", start_app+"" );
       // toggleButton_Services.setChecked(sharedPreferences.getBoolean("Toggle_check", false));

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
            btn_green.setBackgroundResource(R.drawable.custom_button);
            btn_blue.setBackgroundResource(R.drawable.custom_button);
            btn_pink.setBackgroundResource(R.drawable.custom_button_1);
            btn_red.setBackgroundResource(R.drawable.custom_button);
            btn_yellow.setBackgroundResource(R.drawable.custom_button);
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
                btn_blue.setBackgroundResource(R.drawable.custom_button_1);
                Intent intentProgress = new Intent("color_progress");
                intentProgress.putExtra("key_color", alpha);
                intentProgress.putExtra("key_color_red", red);
                intentProgress.putExtra("key_color_green", green);
                intentProgress.putExtra("key_color_blue", blue);
                sendBroadcast(intentProgress);
                btn_green.setBackgroundResource(R.drawable.custom_button);
                // btn_blue.setBackgroundResource(R.drawable.custom_button);
                btn_pink.setBackgroundResource(R.drawable.custom_button);
                btn_red.setBackgroundResource(R.drawable.custom_button);
                btn_yellow.setBackgroundResource(R.drawable.custom_button);
                aBooleanRed = false;
                aBooleanGreen = false;
                aBooleanBlue = true;
                aBooleanYellow = false;
                aBooleanPink = false;
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
                if (MyService.aBoolean_Img_green == true) {

                    red = 0;
                    green = 255;
                    blue = 0;
                    alpha = btn_seekBar.getProgress();
                    btn_green.setBackgroundResource(R.drawable.custom_button_1);
                    Intent intentProgress = new Intent("color_progress");
                    intentProgress.putExtra("key_color", alpha);
                    intentProgress.putExtra("key_color_red", red);
                    intentProgress.putExtra("key_color_green", green);
                    intentProgress.putExtra("key_color_blue", blue);
                    sendBroadcast(intentProgress);
                    //  btn_green.setBackgroundResource(R.drawable.custom_button);
                    btn_blue.setBackgroundResource(R.drawable.custom_button);
                    btn_pink.setBackgroundResource(R.drawable.custom_button);
                    btn_red.setBackgroundResource(R.drawable.custom_button);
                    btn_yellow.setBackgroundResource(R.drawable.custom_button);
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
                        btn_green.setBackgroundResource(R.drawable.custom_button);
                        btn_blue.setBackgroundResource(R.drawable.custom_button);
                        btn_pink.setBackgroundResource(R.drawable.custom_button);
                        btn_red.setBackgroundResource(R.drawable.custom_button_1);
                        btn_yellow.setBackgroundResource(R.drawable.custom_button);
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

                            btn_green.setBackgroundResource(R.drawable.custom_button);
                            btn_blue.setBackgroundResource(R.drawable.custom_button);
                            btn_pink.setBackgroundResource(R.drawable.custom_button);
                            btn_red.setBackgroundResource(R.drawable.custom_button);
                            btn_yellow.setBackgroundResource(R.drawable.custom_button_1);
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
    protected void onRestart() {
        super.onRestart();

    }

}


