package com.example.networktest3111;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.networktest3111.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class API {
    public static String doGet(String constellation, String day_type) {
        String result = "";
        BufferedReader reader = null;
        try {
            HttpURLConnection httpURLConnection = null;
            String url = "http://web.juhe.cn/constellation/getAll?consName=" + constellation + "&type=" + day_type + "&key=0b15f0b5445ba629d6a4135d5bd94bcb";
            URL requestUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            reader = new BufferedReader((new InputStreamReader(inputStream)));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            result = builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String doGetCons(String date) {
        String result = "";
        BufferedReader reader = null;
        try {

            HttpURLConnection httpURLConnection = null;
            String url = "http://apis.juhe.cn/fapig/constellation/query?keyword=" + date + "&key=16141ed8a2b3140e04739660fabfb128";
            URL requestUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();


            InputStream inputStream = httpURLConnection.getInputStream();

            reader = new BufferedReader((new InputStreamReader(inputStream)));

            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                return null;
            }

            result = builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    public static String doGetPair(String men, String women) {
        String result = "";
        BufferedReader reader = null;
        try {

            HttpURLConnection httpURLConnection = null;
            String url = "https://apis.juhe.cn/xzpd/query?men=" + men + "&women=" + women + "&key=083970e5bbba9836bd28c34a3e23cf0d";
            URL requestUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();


            InputStream inputStream = httpURLConnection.getInputStream();

            reader = new BufferedReader((new InputStreamReader(inputStream)));

            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                return null;
            }

            result = builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
}
