package com.example.tienthinh.nightshiftmode;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    public static int alpha;
    public static int width;
    public static int height;
    int r;
    private ImageView Imv_time;
    int position;
    private SharedPreferences sharedPreferences;
    private View view;
    private String SHARED_PREFERENCES_NAME;
    private ToggleButton toggleButton;
    private Toolbar toolbar;
    private SeekBar btn_seekBar;
    private boolean aBoolean;
    private boolean aBooleanRed;
    private boolean aBooleanBlue;
    private boolean aBooleanGreen;
    private boolean aBooleanPink;
    private boolean aBooleanYellow = false;
    private MyService myService;
    private int red = 238;
    private int green = 232;
    private int blue = 170;
    private RadioGroup radioGroup;
    private RadioButton rd_30,rd_60,rd_90,rd_120;
    private Button btn_green, btn_red, btn_yellow, btn_pink, btn_blue;

    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findRealSize(MainActivity.this);
        initView();
        setSupportActionBar(toolbar);
        // sharedPreferences = getSharedPreferences("key_color",Context.MODE_PRIVATE);
        //int r = sharedPreferences.getInt("color_red",0);
        //   editor = sharedPreferences.edit();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 12);
            }
        }

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
        toggleButton.setChecked(sharedPreferences.getBoolean("Toggle_check",false));

//        Log.d("width", width + "");

    }

    private void findRealSize(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();

        if (Build.VERSION.SDK_INT >= 17) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }

        int realWidth = displayMetrics.widthPixels;
        int realHeight = displayMetrics.heightPixels;
        width = realWidth;
        height = realHeight;

        Log.i("LOG_TAG", "realWidth: " + realWidth + " realHeight: " + realHeight);
    }

    private void initView() {
        Imv_time = (ImageView)findViewById(R.id.Imv_time);
        btn_blue = findViewById(R.id.btn_blue);
        btn_green = findViewById(R.id.btn_green);
        btn_red = findViewById(R.id.btn_red);
        btn_pink = findViewById(R.id.btn_pink);
        btn_yellow = findViewById(R.id.btn_yellow);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btn_seekBar = (SeekBar) findViewById(R.id.seekBar);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        onClickToggleButton();
        //btn_seekBar.setVisibility(View.GONE);
        onclickSeekBar();

        onclickButtonGreen();
        onclickButtonRed();
        onclickButtonYellow();
        onclickButtonBlue();
        onclickButtonPink();
        onClickImvTime();


    }

    private void onClickImvTime() {
        Imv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setCancelable(true);
                dialog.show();
                radioGroup = (RadioGroup)dialog.findViewById(R.id.rd_group);
                rd_30 = (RadioButton)dialog.findViewById(R.id.rb_30);
                rd_60 = (RadioButton)dialog.findViewById(R.id.rb_60);
                rd_90 = (RadioButton)dialog.findViewById(R.id.rb_90);
                rd_120 = (RadioButton)dialog.findViewById(R.id.rb_120);

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

            }
        });
    }

    private void onClickRadioButton120() {
        rd_60.setChecked(false);
        rd_90.setChecked(false);
        rd_30.setChecked(false);
    }

    private void onClickRadioButton90() {
        rd_60.setChecked(false);
        rd_30.setChecked(false);
        rd_120.setChecked(false);
    }

    private void onClickRadioButton60() {
        rd_30.setChecked(false);
        rd_90.setChecked(false);
        rd_120.setChecked(false);
    }

    private void onClickRadioButton30() {
       rd_60.setChecked(false);
       rd_90.setChecked(false);
       rd_120.setChecked(false);
    }

    private void onclickButtonPink() {
        btn_pink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBooleanRed = false;
                aBooleanGreen = false;
                aBooleanBlue = false;
                aBooleanYellow = false;
                aBooleanPink = false;
                red = 255;
                green = 192;
                blue = 203;
                editor.putInt("pink_red", 255);
                editor.putInt("pink_green", 192);
                editor.putInt("pink_blue", 203);
                editor.commit();

                Intent intentPink = new Intent("color_pink");
                intentPink.putExtra("key_color_pink_blue", blue);
                intentPink.putExtra("key_color_pink_green", green);
                intentPink.putExtra("key_color_pink_red", red);
                sendBroadcast(intentPink);
            }
        });
    }

    private void onclickButtonBlue() {

        btn_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBooleanRed = false;
                aBooleanGreen = false;
                aBooleanBlue = false;
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
                editor.commit();
            }
        });

    }

    private void onclickButtonYellow() {
//        int yellow =255;
//        int yellow1 = 255;

        btn_yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBooleanYellow = false;
                aBooleanBlue = false;
                aBooleanGreen = false;
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
                editor.commit();


            }
        });

    }

    private void onclickButtonRed() {
        btn_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBooleanRed = false;
                aBooleanGreen = false;
                aBooleanBlue = false;
                aBooleanYellow = false;
                red = 255;
                green = 0;
                blue = 0;
                Intent intentRed = new Intent("color_red");
                intentRed.putExtra("key_color_red", red);
//                startService(intentRed);

                sendBroadcast(intentRed);
                editor.putInt("red_red", 255);
                editor.putInt("red_green", 0);
                editor.putInt("red_blue", 0);
                editor.commit();
            }
        });
    }

    private void onclickButtonGreen() {
        btn_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBooleanGreen = false;
                aBooleanRed = false;
                aBooleanBlue = false;
                aBooleanYellow = false;
                red = 0;
                green = 255;
                blue = 0;
                Intent intentGreen = new Intent("color_green");
                intentGreen.putExtra("key_color_green", green);
                sendBroadcast(intentGreen);
//                int green =255;
//                Intent intentGreen = new Intent(MainActivity.this,MyService.class);
//                intentGreen.putExtra("key_color_green",green);
//                startService(intentGreen);
                editor.putInt("green_red", 0);
                editor.putInt("green_green", 255);
                editor.putInt("green_blue", 0);
                editor.commit();
            }
        });
    }

    private void onclickSeekBar() {
        btn_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (aBoolean == true) {

                    Intent intent1 = new Intent(MainActivity.this, MyService.class);
                    intent1.putExtra("key_color", seekBar.getProgress());
                    intent1.putExtra("key_color_red", red);
                    intent1.putExtra("key_color_green", green);
                    intent1.putExtra("key_color_blue", blue);
                    startService(intent1);

                } else {

                    // switch ()


                    if (aBooleanRed == true) {
                        red = 255;
                        green = 0;
                        blue = 0;
                        aBooleanBlue = false;
                        aBooleanGreen = false;
                        aBooleanYellow = false;
                        aBooleanPink = false;
                    } else {
                        if (aBooleanGreen == true) {
                            aBooleanBlue = false;
                            aBooleanRed = false;
                            aBooleanPink = false;
                            aBooleanYellow = false;
                            red = 0;
                            green = 255;
                            blue = 0;

                        } else {
                            if (aBooleanYellow == true) {
                                aBooleanBlue = false;
                                aBooleanRed = false;
                                aBooleanPink = false;
                                aBooleanGreen = false;
                                red = 255;
                                green = 255;
                                blue = 0;

                            } else {
                                if (aBooleanBlue == true) {
                                    aBooleanYellow = false;
                                    aBooleanPink = false;
                                    aBooleanRed = false;
                                    aBooleanGreen = false;
                                    red = 0;
                                    green = 0;
                                    blue = 255;
                                } else {
                                    if (aBooleanPink == true) {
                                        aBooleanYellow = false;
                                        aBooleanRed = false;
                                        aBooleanGreen = false;
                                        aBooleanBlue = false;
                                        red = 255;
                                        green = 192;
                                        blue = 203;
                                    }
                                }
                            }

                        }

                    }
                }

                alpha = seekBar.getProgress();
                //  btn_seekBar.setVisibility(View.VISIBLE);
                // Intent intent1 = new Intent(MainActivity.this, MyService.class);
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


                //   intent1.putExtra("key_color", seekBar.getProgress());
                // intent1.putExtra("key_color_red", red);
                // intent1.putExtra("key_color_green", green);
//                intent1.putExtra("width",width);
//                intent1.putExtra("height",height);
                //intent1.putExtra("key_color_blue", blue);
                //startService(intent1);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void onClickToggleButton() {
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean aBoolean = ((ToggleButton) view).isChecked();
                if (aBoolean == false) {
                    //btn_seekBar.setVisibility(View.GONE);
                    stopService(new Intent(MainActivity.this, MyService.class));
                    Toast.makeText(MainActivity.this, "off", Toast.LENGTH_SHORT).show();
//                    btn_seekBar.setVisibility(View.GONE);
//                    editor.putInt("ToggleButton_off",View.GONE);
//                    editor.commit();
                } else {
                    //  btn_seekBar.setVisibility(View.VISIBLE);
                    aBoolean = true;
                    aBooleanRed = false;
                    aBooleanBlue = false;
                    aBooleanGreen = false;
                    aBooleanYellow = false;
                    Intent intent = new Intent(MainActivity.this, MyService.class);
//                    intent.putExtra("width",width);
//                    intent.putExtra("height",height);
//                    intent.putExtra("key_color", MainActivity.alpha);
//                intent.putExtra("key_color_red", red);
//                intent.putExtra("key_color_green", green);
                    startService(intent);
                    Toast.makeText(MainActivity.this, "on", Toast.LENGTH_SHORT).show();
//                    editor.putInt("ToggleButton_on",View.VISIBLE);
//                    editor.putBoolean("toggleButton", toggleButton.isChecked());
//                    editor.commit();
//                    sharedPreferences.getBoolean("toggleButton",true);

                }
                editor.putBoolean("Toggle_check",toggleButton.isChecked());
                editor.commit();


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        boolean b = sharedPreferences.getBoolean("toggleButton", true);
//        toggleButton.setChecked(b);
//        Log.d("1", "onRestart: ");

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}

