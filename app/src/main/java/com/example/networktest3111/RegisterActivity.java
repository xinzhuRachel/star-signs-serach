package com.example.networktest3111;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText username,password,age,constellation;

    Button register;

    String DB_NAME="userinfo.db";

    MySQLiteOpenHelper helper;

    SQLiteDatabase db;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        age=findViewById(R.id.age);
        constellation=findViewById(R.id.constellation);
        register=findViewById(R.id.register);

        helper=new MySQLiteOpenHelper(this,DB_NAME,null,1);
        db=helper.getReadableDatabase();

        UserInfo info=new UserInfo(db);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_username=username.getText().toString();
                String str_password=password.getText().toString();
                String str_age=age.getText().toString();
                String str_constellation=constellation.getText().toString();
                if(str_username.equals("")||str_password.equals("")||str_age.equals("")||str_constellation.equals("")){
                    Toast.makeText(RegisterActivity.this,"用户信息不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    if(info.loginCheck(str_username)){
                        Toast.makeText(RegisterActivity.this,"用户名已存在，请重新输入",Toast.LENGTH_SHORT).show();
                    }else{
                        info.insert(new User(str_username,str_password,str_age,str_constellation));
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
