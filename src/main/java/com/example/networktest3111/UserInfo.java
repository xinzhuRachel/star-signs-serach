package com.example.networktest3111;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.networktest3111.User;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {

    SQLiteDatabase db;

    String table_name = "userinfo_tb";

    public UserInfo(SQLiteDatabase db) {
        this.db = db;
    }

    public void insert(User user) {

        ContentValues cv = new ContentValues();
        cv.put("password", user.getpassword());
        cv.put("username", user.getusername());
        cv.put("age", user.getAge());
        cv.put("constellation", user.getConstellation());
        db.insert(table_name, null, cv);
    }

    public void update(User user) {
        ContentValues cv = new ContentValues();
        cv.put("password", user.getpassword());
        cv.put("age", user.getAge());
        cv.put("constellation", user.getConstellation());
        db.update(table_name, cv, "username=?", new String[]{user.getusername()});
    }

    public void delete(String username) {
        db.delete(table_name, "username=?", new String[]{username});
    }

    public List<User> queryAll(String username) {
        List<User> list = new ArrayList<User>();
//        Cursor cursor=db.query(table_name,null,null,null,null,null,null);
        Cursor cursor = db.query(table_name, null, "username=?", new String[]{username}, null, null, null);
        while (cursor.moveToNext()) {
            int uid = cursor.getInt(0);
            String str_username = cursor.getString(1);
            String str_password = cursor.getString(2);
            String str_age = cursor.getString(3);
            String str_constellation = cursor.getString(4);
//            if(str_username==username) {
//                list.add(new User(uid,username,password,bookWriter));
//            }
            list.add(new User(uid, str_username, str_password, str_age, str_constellation));
        }
        return list;
    }

    public boolean loginCheck(String username) {
        Cursor cursor = db.query(table_name, null, "username=?", new String[]{username}, null, null, null);
        if (cursor.moveToNext()) {
            return true;
        }
        return false;
    }

    public boolean loginVerify(String username, String password) {
        Cursor cursor = db.query(table_name, null, "username=? and password=?", new String[]{username, password}, null, null, null);
        if (cursor.moveToNext()) {
            return true;
        }
        return false;
    }
}
