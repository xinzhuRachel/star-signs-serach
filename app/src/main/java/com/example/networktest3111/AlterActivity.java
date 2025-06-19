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

public class AlterActivity extends AppCompatActivity {

    EditText password,age,constellation;

    Button alter;

    String DB_NAME="userinfo.db";

    MySQLiteOpenHelper helper;

    SQLiteDatabase db;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter);

        password=findViewById(R.id.password);
        age=findViewById(R.id.age);
        constellation=findViewById(R.id.constellation);
        alter=findViewById(R.id.alter);

        helper=new MySQLiteOpenHelper(this,DB_NAME,null,1);
        db=helper.getReadableDatabase();

        UserInfo info=new UserInfo(db);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String str_username=bundle.getString("username");

        alter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_password=password.getText().toString();
                String str_age=age.getText().toString();
                String str_constellation=constellation.getText().toString();
                info.update(new User(str_username,str_password,str_age,str_constellation));

                Intent intent=new Intent(AlterActivity.this,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(AlterActivity.this,"修改成功,请重新登录",Toast.LENGTH_SHORT).show();
            }
        });


    }

}
