package com.example.networktest3111;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv_name, tv_QFriend, tv_color, test, username, tv;

    Spinner sp_stars, sp_stars_cons, sp_men, sp_women;

    DatePickerDialog.OnDateSetListener setListener;

    String DB_NAME = "userinfo.db";

    MySQLiteOpenHelper helper;

    SQLiteDatabase db;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_name = findViewById(R.id.tv_name);
        tv_QFriend = findViewById(R.id.tv_QFriend);
        tv_color = findViewById(R.id.tv_color);
        sp_stars = findViewById(R.id.sp_stars);
        sp_stars_cons = findViewById(R.id.sp_stars_cons);
        sp_men = findViewById(R.id.sp_men);
        sp_women = findViewById(R.id.sp_women);
        test = findViewById(R.id.test);
        username = findViewById(R.id.username);
        tv = findViewById(R.id.tv);

        helper = new MySQLiteOpenHelper(this, DB_NAME, null, 1);
        db = helper.getReadableDatabase();

        UserInfo info = new UserInfo(db);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String str_month = "";
                String str_day = "";
                month = month + 1;
                if (month < 10) {
                    str_month = "0" + month;
                } else {
                    str_month = "" + month;
                }
                if (dayOfMonth < 10) {
                    str_day = "0" + dayOfMonth;
                } else {
                    str_day = "" + dayOfMonth;
                }
                String date = year + "-" + str_month + "-" + str_day;
                tv.setText(date);
            }
        };
    }

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {

                String strData = (String) msg.obj;
                if (day.equals("今日") || day.equals("明日")) {
                    Intent intent = new Intent(MainActivity.this, DayActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("信息", strData);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                } else if (day.equals("一周")) {
                    Intent intent = new Intent(MainActivity.this, WeekActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("信息", strData);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                } else if (day.equals("本月")) {
                    Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("信息", strData);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, YearActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("信息", strData);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private Handler mHandler_cons = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String strData = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(strData);
                    String result = jsonObject.optString("result");
                    Intent intent = new Intent(MainActivity.this, ConsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("信息", result);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };

    private Handler mHandler_pair = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String strData = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(strData);
                    String result = jsonObject.optString("result");
                    Intent intent = new Intent(MainActivity.this, PairActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("信息", result);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };

    public void start(View view) {
        new Thread(new Runnable() {
            //            子线程
            @Override
            public void run() {
                String stringFromNet = getStringFromNet();

                Message message = new Message();
                message.what = 0;
                message.obj = stringFromNet;

                mHandler.sendMessage(message);

            }
        }).start();
    }

    public void start_cons(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String stringCons = getStringCons();

                Message message = new Message();
                message.what = 0;
                message.obj = stringCons;

                mHandler_cons.sendMessage(message);
            }
        }).start();
    }

    public void start_pair(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String stringPair = getStringPair();

                Message message = new Message();
                message.what = 0;
                message.obj = stringPair;

                mHandler_pair.sendMessage(message);
            }
        }).start();
    }

    String day;

    private String getStringFromNet() {
        String constellation = sp_stars_cons.getSelectedItem().toString();
        day = sp_stars.getSelectedItem().toString();
        String day_type = "";
        if (day.equals("今日")) {
            day_type = "today";
        } else if (day.equals("明日")) {
            day_type = "tomorrow";
        } else if (day.equals("一周")) {
            day_type = "week";
        } else if (day.equals("本月")) {
            day_type = "month";
        } else if (day.equals("今年")) {
            day_type = "year";
        }
        return API.doGet(constellation, day_type);
    }

    private String getStringCons() {
        String date = tv.getText().toString();
        return API.doGetCons(date);
    }

    private String getStringPair() {
        String men = sp_men.getSelectedItem().toString();
        String women = sp_women.getSelectedItem().toString();
        return API.doGetPair(men, women);
    }

    public void goUserInfo(View view) {
        UserInfo info = new UserInfo(db);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String str_username = bundle.getString("username");

        List<User> list = info.queryAll(str_username);
        StringBuffer sb = new StringBuffer();
        sb.append(list.get(0).toString());
        Intent intent_userinfo = new Intent(MainActivity.this, UserInfoActivity.class);
        Bundle bundle1 = new Bundle();
        bundle1.putString("username", str_username);
        bundle1.putString("userinfo", sb.toString());
        intent_userinfo.putExtras(bundle1);
        startActivity(intent_userinfo);
    }
}