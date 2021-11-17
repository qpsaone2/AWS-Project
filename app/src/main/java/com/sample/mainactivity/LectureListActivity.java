package com.sample.mainactivity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.sample.timetable.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LectureListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
//    private RecyclerView.Adapter adapter;
    private LectureAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Lecture> lectureArrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private androidx.appcompat.widget.SearchView searchView;
    private Schedule schedule;

//    LectureAdapter myadapter = null;

    // EditActivity와 통신
    public static final int FIND_OK_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_list);
        Log.e("RecyclerView", "넘어옴1");


        recyclerView = findViewById(R.id.recyclerview_lecture); //아이디 연결
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        lectureArrayList = new ArrayList<>(); //Lectrue 객체를 담을 ArrayList

        adapter = new LectureAdapter(lectureArrayList, this);
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어뎁터 연결

        database = FirebaseDatabase.getInstance();//파베 데이터베이스 연동

        searchView =(androidx.appcompat.widget.SearchView) findViewById(R.id.autoCompleteText);

        databaseReference = database.getReference("Android_Schedule"); //DB 테이블을 연결
//        databaseReference = database.getReference("Affiliated"); //DB 테이블을 연결

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                lectureArrayList.clear(); //기존 배열리스트가 존재하지않게 초기화
                Log.e("onDataChange", "clear");
                Log.e("onDataChange", dataSnapshot.getChildren().toString());

                GenericTypeIndicator<Map<String, Object>> genericTypeIndicator = new GenericTypeIndicator<Map<String, Object>>() {
                };
                Map<String, Object> map = dataSnapshot.getValue(genericTypeIndicator);
                Log.e("iter", map.toString());

                List<Map<String, Object>> Affiliated = (List<Map<String, Object>>) map.get("Affiliated");

                //Affiliated에 있는 데이터 가져와서 화면출력
                for (int i = 0; i <= 207; i++) {
                    Map<String, Object> Affiliate = Affiliated.get(i);
                    Lecture lecture = new Lecture();
                    lecture.setClass_Name((String) Affiliate.get("class_Name"));
                    lecture.setClass_schedule((String) Affiliate.get("class_schedule"));
                    lecture.setProf((String) Affiliate.get("prof"));

                    lectureArrayList.add(lecture); //담은 데이터들을 배열리스트에 넣고 리사이컬러뷰로 보낼 준비


                }


//
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){ //반복문으로 데이터 List를 출력해냄
//
//
//
//                    Lecture lecture = snapshot.getValue(Lecture.class); //만들어놨던 Lecture 객체 데이터를 담는다
//                    Log.e("iter", lecture.toString());
//
//                }
                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
                Log.e("adapter", adapter.getItemCount() + "");


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //DB를 가지고오던 중 에러 발생 시
                Log.e("LectureActivity", String.valueOf(databaseError.toException())); //에러문 출력

            }


//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                lectureArrayList.clear();
//                // Get Post object and use the values to update the UI
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){ //반복문으로 데이터 List를 출력해냄
//                    Lecture lecture = snapshot.getValue(Lecture.class); //만들어놨던 Lecture 객체 데이터를 담는다
//                    Log.e("iter", lecture.toString());
//                    lectureArrayList.add(lecture); //담은 데이터들을 배열리스트에 넣고 리사이컬러뷰로 보낼 준비
//                }
//                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
////                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//            }
//        };
//        databaseReference.addValueEventListener(postListener);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }



}

