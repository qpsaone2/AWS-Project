package com.example.capstone_ui_1.ui.clothing;
/* 28°C 이상 일 경우 추천해주는 액티비티 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone_ui_1.R;

public class TemperatureClothingActivity8 extends AppCompatActivity {
    TextView tx9;
    ImageView btnToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_clothing8);

        btnToHome = (ImageView) findViewById(R.id.btnToHome);
        btnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1);
        tx9 = (TextView)findViewById(R.id.textView9);
        Intent intent = getIntent(); /* 데이터 수신 */

        String name = intent.getStringExtra("temperature");

        tx9.setText(name + "°C");

    }
}