package com.example.capstone_ui_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    RecyclerView lecture_list;

    FragmentManager fragmentManager = getSupportFragmentManager();

    BottomNavigationView bottomNavigationView;
    NavigationFragment navigationFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView();

    }

    private void mBottomNavigationView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        navigationFragment = new NavigationFragment();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_navi_navigation: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, navigationFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.bottom_navi_qr: {
                        Intent Select = new Intent(MainActivity.this, CameraActivity.class);
                        startActivity(Select);
                        break;
                    }
                    case R.id.bottom_navi_weather: {
                        Intent Select = new Intent(MainActivity.this, com.example.capstone_ui_1.ui.main.MainActivity.class);
                        startActivity(Select);
                        break;
                    }

                }
                return true;
            }
            });
        }
}