package com.example.networktest3111;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MonthActivity extends AppCompatActivity {

    TextView tv_name, tv_date, tv_health, tv_love, tv_work, tv_money, tv_all;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        tv_name = findViewById(R.id.tv_name);
        tv_date = findViewById(R.id.tv_date);
        tv_health = findViewById(R.id.tv_health);
        tv_love = findViewById(R.id.tv_love);
        tv_work = findViewById(R.id.tv_work);
        tv_money = findViewById(R.id.tv_money);
        tv_all = findViewById(R.id.tv_all);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String data = bundle.getString("信息");
        parseJsonDataAndShow(data);
    }


    public void parseJsonDataAndShow(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            String name = jsonObject.optString("name");
            String date = jsonObject.optString("date");
            String health = jsonObject.optString("health");
            String love = jsonObject.optString("love");
            String work = jsonObject.optString("work");
            String money = jsonObject.optString("money");
            String all = jsonObject.optString("all");

            tv_name.setText(name);
            tv_date.setText(date);
            tv_health.setText(health);
            tv_love.setText(love);
            tv_work.setText(work);
            tv_money.setText(money);
            tv_all.setText(all);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
