package com.sample.mainactivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Main", "넘어옴1");

        mBottomNavigationView();

        Intent intent = getIntent();
        Lecture lecture = (Lecture) intent.getSerializableExtra("lecture");
        if(lecture != null)
            Log.e("main lecture", lecture.toString());

        homeFragment = new HomeFragment();


        getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, homeFragment).commit();

        //클래스이름,스케쥴,교수이름 넘김
        Bundle bundle = new Bundle();
        if (lecture != null) {
//            bundle.putSerializable("lecture",lecture);

            bundle.putString("Class_Name", lecture.getClass_Name());
            bundle.putString("Class_Schedule", lecture.getClass_schedule());
            bundle.putString("Class_Prof", lecture.getProf());
            homeFragment.setArguments(bundle);
        }


    }

    private void mBottomNavigationView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        homeFragment = new HomeFragment();
//        navigationFragment = new NavigationFragment();


        getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, homeFragment).commitAllowingStateLoss();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_navi_home: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, homeFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.bottom_navi_navigation: {
                        Intent Select = new Intent(MainActivity.this, NavActivity.class);
                        startActivity(Select);
                        break;
                    }
                    case R.id.bottom_navi_qr: {
                        Intent Select = new Intent(MainActivity.this, CameraActivity.class);
                        startActivity(Select);
                        break;
                    }
                    case R.id.bottom_navi_weather: {
                        Intent Select = new Intent(MainActivity.this, com.sample.mainactivity.ui.main.MainActivity.class);
                        startActivity(Select);
                        break;
                    }

                }
                return true;
            }
        });
    }
}