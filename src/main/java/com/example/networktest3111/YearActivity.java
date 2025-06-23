package com.example.networktest3111;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class YearActivity extends AppCompatActivity {

    TextView tv_name, tv_date, tv_health, tv_love, tv_career, tv_finance, tv_info, tv_text;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);

        tv_name = findViewById(R.id.tv_name);
        tv_date = findViewById(R.id.tv_date);
        tv_health = findViewById(R.id.tv_health);
        tv_love = findViewById(R.id.tv_love);
        tv_career = findViewById(R.id.tv_career);
        tv_finance = findViewById(R.id.tv_finance);
        tv_info = findViewById(R.id.tv_info);
        tv_text = findViewById(R.id.tv_text);

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
            String career = jsonObject.optString("career");
            String finance = jsonObject.optString("finance");

            tv_name.setText(name);
            tv_date.setText(date);
            tv_health.setText(health);
            tv_love.setText(love);
            tv_career.setText(career);
            tv_finance.setText(finance);

            String mima = jsonObject.optString("mima");
            try {
                JSONObject jsonObject_mima = new JSONObject(mima);
                String info = jsonObject_mima.optString("info");
                String text = jsonObject_mima.optString("text");

                tv_info.setText(info);
                tv_text.setText(text);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
