package com.example.networktest3111;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;

    Button login,register;

    String DB_NAME="userinfo.db";

    MySQLiteOpenHelper helper;

    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);

        helper=new MySQLiteOpenHelper(this,DB_NAME,null,1);
        db=helper.getReadableDatabase();

        UserInfo info=new UserInfo(db);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_username=username.getText().toString();
                String str_password=password.getText().toString();
                if(str_username.equals("")||str_password.equals("")){
                    Toast.makeText(LoginActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    if(info.loginVerify(str_username,str_password)){
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("username",str_username);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
