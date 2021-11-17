package com.sample.mainactivity;


import static com.sample.mainactivity.EditActivity.EDIT_OK_ADD;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sample.timetable.Schedule;
import com.sample.timetable.Time;
import com.sample.timetable.TimetableView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private Context context;
    public static final int HOME_REQUEST_ADD = 1;
    public static final int HOME_REQUEST_EDIT = 2;

    public static final int FIND_OK_CODE = 1000;

    private Button addBtn;
    private Button clearBtn;
    private Button saveBtn;
    private Button loadBtn;
    private Object TextView;
    private TimetableView timetable;

    private Schedule schedule,schedule1;




    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e("What the ?", "onActivityResult:");
                    if (result.getResultCode() == EDIT_OK_ADD) {
                        Intent data = result.getData();
                        ArrayList<Schedule> item = (ArrayList<Schedule>) data.getSerializableExtra("schedules");
                        Log.e("resultValue", String.valueOf(result));
                        timetable.add(item);
                        Log.e("add", "시간표 추가되었습니다.");
                    }

                }
            }
    );


    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("home", "resume");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = (ViewGroup) inflater.inflate(R.layout.home_fragment, container, false);
        init(view);

//        //나갔다 들어왔다하면서 추가를해야지 저장된 상태로 시간표 추가됨

        Bundle bundle = getArguments();


        if (bundle != null) {
            loadTempSavedData();

//            bundle.getSerializable("lecture");
//            Log.e("bundle_log",bundle.toString());
            String ClassName = bundle.getString("Class_Name");
            String ClassSchedule = bundle.getString("Class_Schedule");
            String ClassProf = bundle.getString("Class_Prof");
            Log.e("ClassName", ClassName.toString());
            Log.e("ClassSchedule", ClassSchedule.toString());
            Log.e("ClassProf", ClassProf.toString());


            Map<String, Integer> weekMap = new HashMap<>();
            weekMap.put("월", 0);
            weekMap.put("화", 1);
            weekMap.put("수", 2);
            weekMap.put("목", 3);
            weekMap.put("금", 4);
            weekMap.put("토", 5);
            weekMap.put("일", 6);


            String t1 = ClassSchedule.substring(ClassSchedule.indexOf("[") + 1, ClassSchedule.indexOf("]"));
            String t2 = ClassSchedule.substring(ClassSchedule.lastIndexOf("[") + 1, ClassSchedule.lastIndexOf("]"));

            String week1 = t1.substring(0, 1);
            String week2 = t2.substring(0, 1);

            Log.e("week1", week1.toString());


            String startTime1 = t1.substring(1);
            String startTime2 = t2.substring(1);

            Log.e("startTime1", startTime1.toString());

            int time1s = 0;
            int time1e = 0;
            int time2s = 0;
            int time2e = 0;


            if (startTime1.indexOf("-") > -1) {
                time1s = Integer.parseInt(startTime1.substring(0, 1)) + 8;
                time1e = Integer.parseInt(startTime1.substring(2)) + 9;
            } else {
                time1s = Integer.parseInt(startTime1.substring(0)) + 8;
                time1e = time1s + 1;
            }

            if (startTime2.indexOf("-") > -1) {
                time2s = Integer.parseInt(startTime2.substring(0, 1)) + 8;
                time2e = Integer.parseInt(startTime2.substring(2)) + 9;
            } else {
                time2s = Integer.parseInt(startTime2.substring(0)) + 8;
                time2e = time2s + 1;
            }

            //room은 ClassSchedule에서 강의실 정보
            String room = ClassSchedule.substring(0, 5);
//            Log.e("room name", room.toString());


            System.out.println(ClassName);
            System.out.println(time1s + " " + time1e);
            System.out.println(time2s + " " + time2e);
            System.out.println(ClassProf);
            System.out.println(week1);
            System.out.println(week2);
            System.out.println(room);




            //Schedule에 데이터 넣어서 timetable.add
            Log.e("sss",",,,,,,,");
            ArrayList<Schedule> item = new ArrayList<>();
            Schedule schedule1 = new Schedule();
            schedule1.setClassTitle(ClassName);
            schedule1.setClassPlace(room);
            schedule1.setDay(weekMap.get(week1));
            schedule1.setStartTime(new Time(time1s, 0));
            schedule1.setEndTime(new Time(time1e, 0));
            item.add(schedule1);

            schedule1 = new Schedule();
            schedule1.setClassTitle(ClassName);
            schedule1.setClassPlace(room);
            schedule1.setDay(weekMap.get(week2));
            schedule1.setStartTime(new Time(time2s, 0));
            schedule1.setEndTime(new Time(time2e, 0));
            item.add(schedule1);

            timetable.add(item);


            //밑에 textbox에 찍히는 코드
            android.widget.TextView className_txtView = view.findViewById(R.id.className_txtView);
            android.widget.TextView classSchedule_txtView = view.findViewById(R.id.classSchedule_txtView);
            android.widget.TextView prof_txtView = view.findViewById(R.id.prof_txtView);
            className_txtView.setText(ClassName);
            classSchedule_txtView.setText(ClassSchedule);
            prof_txtView.setText(ClassProf);
        }
//        else
//            {
//                //처음 들어갔을때 저장했던 데이터 나옴
//                loadSavedData();
//            }
            return view;
    }




    private void init(View v) {
        addBtn = (Button) v.findViewById(R.id.add_btn);
        clearBtn = (Button) v.findViewById(R.id.clear_btn);
        saveBtn = (Button) v.findViewById(R.id.save_btn);
        loadBtn = (Button) v.findViewById(R.id.load_btn);

        timetable = (TimetableView) v.findViewById(R.id.timetable);
//        timetable.setHeaderHighlight(2);

        initView();
    }

    private void initView() {
        Log.e("initView", "initView 실행됨");

        addBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        loadBtn.setOnClickListener(this);

        timetable.setOnStickerSelectEventListener(new TimetableView.OnStickerSelectedListener() {
            @Override
            public void OnStickerSelected(int idx, ArrayList<Schedule> schedules) {
                Intent i = new Intent(getView().getContext(), EditActivity.class);
                i.putExtra("mode", HOME_REQUEST_EDIT);
                i.putExtra("idx", idx);
                i.putExtra("schedules", schedules);
//                startActivityForResult(i, HOME_REQUEST_EDIT);
                startActivity(i);
                Log.e("Home_timetable", "HomeFragment에서 timetable 클릭됨");
            }
        });

//        timetable.setOnStickerSelectEventListener(new TimetableView.OnStickerSelectedListener() {
//            @Override
//            public void OnStickerSelected(int idx, ArrayList<Schedule> schedules) {
//                Intent i = new Intent(getView().getContext(), EditActivity.class);
//                i.putExtra("mode",HOME_REQUEST_EDIT);
//                i.putExtra("idx", idx);
//                i.putExtra("schedules", schedules);
//                startActivityForResult(i, HOME_REQUEST_EDIT);
//                Log.e("Schedule_timetable", "ScheduelFragment에서 timetable 클릭됨");
//            }
//        });

//        timetable.setOnStickerSelectEventListener((idx, schedules) -> {
//            Intent i = new Intent(context, EditActivity.class);
//            i.putExtra("mode",REQUEST_EDIT);
//            i.putExtra("idx", idx);
//            i.putExtra("schedules", schedules);
//            startActivity(i);
//        });

//        timetable.setOnStickerSelectEventListener((idx, schedules) -> {
//            Intent j = new Intent(getView().getContext(), FindClassActivity.class);
//            j.putExtra("mode",REQUEST_EDIT);
//            j.putExtra("idx", idx);
//            j.putExtra("schedules", schedules);
//            startActivityForResult(j,REQUEST_EDIT);
//        });
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_btn:
                Intent i = new Intent(getActivity(), EditActivity.class);
                i.putExtra("mode", HOME_REQUEST_ADD);
                resultLauncher.launch(i);
                saveTempByPreference(timetable.createSaveData());
                Log.e("add_btn","add버튼 누름");
                // 11/17 추가
//                Intent j = new Intent(getActivity(),FindClassActivity.class);
//                j.putExtra("mode",REQUEST_ADD);
//                startActivityForResult(j,REQUEST_ADD);
                break;
            case R.id.clear_btn:
                timetable.removeAll();
                break;
            case R.id.save_btn:
                saveByPreference(timetable.createSaveData());
                break;
            case R.id.load_btn:
                loadSavedData();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }


//                 EditActivity에서 처리된 결과를 받는 메소드
//                 처리된 결과 (resultCode)가 EditActivity.RESULT_OK_ADD이면 requestCode를 판별해 결과 처리를 진행
//                 처리 결과를 item으로 선언해서 timetable에 더한다.
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.e("Schedule", "onActivityResult 실행됨");
//        switch (requestCode){
//            case HOME_REQUEST_ADD:
//                if(resultCode == EditActivity.EDIT_OK_ADD){
//                    ArrayList<Schedule> item = (ArrayList<Schedule>)data.getSerializableExtra("schedules");
//                    Log.e("ScheduleFrament", String.valueOf(resultCode));
//                    timetable.add(item);
//                    Log.e("add" , "시간표 추가되었습니다.");
//                }
//                break;
//            case HOME_REQUEST_EDIT:
//                /** Edit -> Submit */
//                if(resultCode == EditActivity.EDIT_OK_EDIT){
//                    int idx = data.getIntExtra("idx",-1);
//                    ArrayList<Schedule> item = (ArrayList<Schedule>)data.getSerializableExtra("schedules");
//                    timetable.edit(idx,item);
//                    Log.e("edit" , "변경되었습니다.");
//                }
//                /** Edit -> Delete */
//                else if(resultCode == EditActivity.EDIT_OK_DELETE){
//                    int idx = data.getIntExtra("idx",-1);
//                    timetable.remove(idx);
//                    Log.e("delete" , "삭제되었습니다.");
//                }
//                break;
//        }
//    }


    /**
                 * save timetableView's data to SharedPreferences in json format
                 */
                private void saveByPreference(String data) {
                    SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = mPref.edit();
                    editor.putString("timetable_demo", data);
                    editor.commit();
                    Toast.makeText(getActivity(), "saved!", Toast.LENGTH_SHORT).show();
                }

                /**
                 * get json data from SharedPreferences and then restore the timetable
                 */
                public void loadSavedData() {
                    timetable.removeAll();
                    SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    String savedData = mPref.getString("timetable_demo", "");
                    if (savedData == null || savedData.equals("")) return;
                    timetable.load(savedData);
                    Toast.makeText(getActivity(), "loaded!", Toast.LENGTH_SHORT).show();
                }

                private void saveTempByPreference(String data) {
                    SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = mPref.edit();
                    editor.putString("timetable_demo_temp", data);
                    editor.commit();
                }

                /**
                 * get json data from SharedPreferences and then restore the timetable
                 */
                public void loadTempSavedData() {
                    timetable.removeAll();
                    SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    String savedData = mPref.getString("timetable_demo_temp", "");
                    if (savedData == null || savedData.equals("")) return;
                    timetable.load(savedData);
                }




            }
