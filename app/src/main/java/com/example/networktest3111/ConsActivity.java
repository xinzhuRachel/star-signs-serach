package com.example.networktest3111;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class ConsActivity extends AppCompatActivity {

    TextView tv_range, tv_name, tv_zxtd, tv_sssx, tv_zggw, tv_yysx, tv_zdtz, tv_zgxx, tv_xyys, tv_jssw, tv_xyhm, tv_kyjs, tv_bx, tv_yd, tv_qd, tv_jbtz, tv_jttz, tv_xsfg, tv_gxmd, tv_zj;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons);

        tv_range = findViewById(R.id.tv_range);
        tv_name = findViewById(R.id.tv_name);
        tv_zxtd = findViewById(R.id.tv_zxtd);
        tv_sssx = findViewById(R.id.tv_sssx);
        tv_zggw = findViewById(R.id.tv_zggw);
        tv_yysx = findViewById(R.id.tv_yysx);
        tv_zdtz = findViewById(R.id.tv_zdtz);
        tv_zgxx = findViewById(R.id.tv_zgxx);
        tv_xyys = findViewById(R.id.tv_xyys);
        tv_jssw = findViewById(R.id.tv_jssw);
        tv_xyhm = findViewById(R.id.tv_xyhm);
        tv_kyjs = findViewById(R.id.tv_kyjs);
        tv_bx = findViewById(R.id.tv_bx);
        tv_yd = findViewById(R.id.tv_yd);
        tv_qd = findViewById(R.id.tv_qd);
        tv_jbtz = findViewById(R.id.tv_jbtz);
        tv_jttz = findViewById(R.id.tv_jttz);
        tv_xsfg = findViewById(R.id.tv_xsfg);
        tv_gxmd = findViewById(R.id.tv_gxmd);
        tv_zj = findViewById(R.id.tv_zj);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String data = bundle.getString("信息");
        parseJsonDataAndShow(data);
    }

    public void parseJsonDataAndShow(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            String range = jsonObject.optString("range");
            String name = jsonObject.optString("name");
            String zxtd = jsonObject.optString("zxtd");
            String sssx = jsonObject.optString("sssx");
            String zggw = jsonObject.optString("zggw");
            String yysx = jsonObject.optString("yysx");
            String zdtz = jsonObject.optString("zdtz");
            String zgxx = jsonObject.optString("zgxx");
            String xyys = jsonObject.optString("xyys");
            String jssw = jsonObject.optString("jssw");
            String xyhm = jsonObject.optString("xyhm");
            String kyjs = jsonObject.optString("kyjs");
            String bx = jsonObject.optString("bx");
            String yd = jsonObject.optString("yd");
            String qd = jsonObject.optString("qd");
            String jbtz = jsonObject.optString("jbtz");
            String jttz = jsonObject.optString("jttz");
            String xsfg = jsonObject.optString("xsfg");
            String gxmd = jsonObject.optString("gxmd");
            String zj = jsonObject.optString("zj");

            tv_range.setText(range);
            tv_name.setText(name);
            tv_zxtd.setText(zxtd);
            tv_sssx.setText(sssx);
            tv_zggw.setText(zggw);
            tv_yysx.setText(yysx);
            tv_zdtz.setText(zdtz);
            tv_zgxx.setText(zgxx);
            tv_xyys.setText(xyys);
            tv_jssw.setText(jssw);
            tv_xyhm.setText(xyhm);
            tv_kyjs.setText(kyjs);
            tv_bx.setText(bx);
            tv_yd.setText(yd);
            tv_qd.setText(qd);
            tv_jbtz.setText(jbtz);
            tv_jttz.setText(jttz);
            tv_xsfg.setText(xsfg);
            tv_gxmd.setText(gxmd);
            tv_zj.setText(zj);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
