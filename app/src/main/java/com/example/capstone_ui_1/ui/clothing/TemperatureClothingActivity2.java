package com.example.capstone_ui_1.ui.clothing;
/* 5°C ~ 9°C 이하 일 경우 추천해주는 액티비티 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone_ui_1.R;

public class TemperatureClothingActivity2 extends AppCompatActivity {
    TextView tx9;
    ImageView btnToHome;
    ViewGroup tokboki, shoes, thickCoat, neat, scarf, leather;
    String url;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_clothing2);

        btnToHome = (ImageView) findViewById(R.id.btnToHome);
        btnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tx9 = (TextView)findViewById(R.id.textView9);
        intent = getIntent(); /*데이터 수신*/

        String name = intent.getStringExtra("temperature");

        tx9.setText(name + "°C");
        tokboki = (ViewGroup) findViewById(R.id.layout_tokboki);
        shoes = (ViewGroup) findViewById(R.id.layout_winterShoes);
        thickCoat = (ViewGroup) findViewById(R.id.layout_thickCoat);
        neat = (ViewGroup) findViewById(R.id.layout_neat);
        scarf = (ViewGroup) findViewById(R.id.layout_scarf);
        leather = (ViewGroup) findViewById(R.id.layout_leather);

    }
}