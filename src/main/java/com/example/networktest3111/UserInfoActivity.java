package com.example.networktest3111;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class UserInfoActivity extends AppCompatActivity {

    TextView userinfo;

    Button logout, alter, delete;

    String DB_NAME = "userinfo.db";

    MySQLiteOpenHelper helper;

    SQLiteDatabase db;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        alter = findViewById(R.id.alter);
        logout = findViewById(R.id.logout);
        delete = findViewById(R.id.delete);
        userinfo = findViewById(R.id.userinfo);

        helper = new MySQLiteOpenHelper(this, DB_NAME, null, 1);
        db = helper.getReadableDatabase();

        UserInfo info = new UserInfo(db);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String str_userinfo = bundle.getString("userinfo");
        String str_username = bundle.getString("username");

        userinfo.setText(str_userinfo);


        alter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_user = new Intent(UserInfoActivity.this, AlterActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("username", str_username);
                intent_user.putExtras(bundle1);
                startActivity(intent_user);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_user = new Intent(UserInfoActivity.this, LoginActivity.class);
                startActivity(intent_user);
                Toast.makeText(UserInfoActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.delete(str_username);
                Intent intent_user = new Intent(UserInfoActivity.this, LoginActivity.class);
                startActivity(intent_user);
                Toast.makeText(UserInfoActivity.this, "注销成功", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
