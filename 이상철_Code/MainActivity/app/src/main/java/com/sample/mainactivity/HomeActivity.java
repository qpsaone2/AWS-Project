package com.sample.mainactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
        noticeList.add(new Notice("공지사항입니다","Zr's","2021-10-16"));
        noticeList.add(new Notice("공지사항입니다","Zr's","2021-10-16"));
        noticeList.add(new Notice("공지사항입니다","Zr's","2021-10-16"));
        noticeList.add(new Notice("공지사항입니다","Zr's","2021-10-16"));
        noticeList.add(new Notice("공지사항입니다","Zr's","2021-10-16"));
        noticeList.add(new Notice("공지사항입니다","Zr's","2021-10-16"));
        noticeList.add(new Notice("공지사항입니다","Zr's","2021-10-16"));

        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);

        final Button courseButton = (Button) findViewById(R.id.courseButton);
        final Button statisticsButton = (Button) findViewById(R.id.statisticsButton);
        final Button scheduleButton = (Button) findViewById(R.id.scheduleButton);
        final LinearLayout notice = (LinearLayout) findViewById(R.id.notice);

        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notice.setVisibility(View.GONE);
                courseButton.setBackground(getResources().getDrawable(R.color.design_default_color_primary_dark));
                statisticsButton.setBackground(getResources().getDrawable(R.color.purple_500));
                scheduleButton.setBackground(getResources().getDrawable(R.color.purple_500));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new CourseFragment());
                fragmentTransaction.commit();
            }
        });

        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notice.setVisibility(View.GONE);
                courseButton.setBackground(getResources().getDrawable(R.color.purple_500));
                statisticsButton.setBackground(getResources().getDrawable(R.color.design_default_color_primary_dark));
                scheduleButton.setBackground(getResources().getDrawable(R.color.purple_500));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new StatisticsFragment());
                fragmentTransaction.commit();
            }
        });


        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notice.setVisibility(View.GONE);
                courseButton.setBackground(getResources().getDrawable(R.color.purple_500));
                statisticsButton.setBackground(getResources().getDrawable(R.color.purple_500));
                scheduleButton.setBackground(getResources().getDrawable(R.color.design_default_color_primary_dark));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new ScheduleFragment());
                fragmentTransaction.commit();
            }
        });

    }
        private long lastTimeBackPressed;

        @Override
        public void onBackPressed(){
            if(System.currentTimeMillis() - lastTimeBackPressed <1500)
            {
                finish();
                return;

            }
            Toast.makeText(this,"'뒤로' 버튼을 한 번 더 눌러 종료 합니다.", Toast.LENGTH_SHORT);
            lastTimeBackPressed = System.currentTimeMillis();
        }

    }