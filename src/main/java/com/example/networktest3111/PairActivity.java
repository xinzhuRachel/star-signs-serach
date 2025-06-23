package com.example.networktest3111;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class PairActivity extends AppCompatActivity {

    TextView tv_men, tv_women, tv_zhishu, tv_bizhong, tv_xiangyue, tv_tcdj, tv_jieguo, tv_lianai, tv_zhuyi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair);

        tv_men = findViewById(R.id.tv_men);
        tv_women = findViewById(R.id.tv_women);
        tv_zhishu = findViewById(R.id.tv_zhishu);
        tv_bizhong = findViewById(R.id.tv_bizhong);
        tv_xiangyue = findViewById(R.id.tv_xiangyue);
        tv_tcdj = findViewById(R.id.tv_tcdj);
        tv_jieguo = findViewById(R.id.tv_jieguo);
        tv_lianai = findViewById(R.id.tv_lianai);
        tv_zhuyi = findViewById(R.id.tv_zhuyi);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String data = bundle.getString("信息");
        parseJsonDataAndShow(data);
    }

    public void parseJsonDataAndShow(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            String  men= jsonObject.optString("men");
            String  women= jsonObject.optString("women");
            String  zhishu= jsonObject.optString("zhishu");
            String  bizhong= jsonObject.optString("bizhong");
            String  xiangyue= jsonObject.optString("xiangyue");
            String  tcdj= jsonObject.optString("tcdj");
            String  jieguo= jsonObject.optString("jieguo");
            String  lianai= jsonObject.optString("lianai");
            String  zhuyi= jsonObject.optString("zhuyi");


            tv_men.setText(men);
            tv_women.setText(women);
            tv_zhishu.setText(zhishu);
            tv_bizhong.setText(bizhong);
            tv_xiangyue.setText(xiangyue);
            tv_tcdj.setText(tcdj);
            tv_jieguo.setText(jieguo);
            tv_lianai.setText(lianai);
            tv_zhuyi.setText(zhuyi);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
