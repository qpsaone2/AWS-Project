package com.sample.mainactivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private ArrayAdapter subjectAdapter;
    private Spinner subjectSpinner;
    private ArrayAdapter sortAdapter;
    private Spinner sortSpinner;
    private ArrayAdapter studentAdapter;
    private Spinner studentSpinner;
    private ArrayAdapter majorAdapter;
    private Spinner majorSpinner;


    private String courseUniversity="";
    private String courseStudent="";
    private String courseSort="";
    private String courseSubject="";




    @Override
    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);

        final RadioGroup courseUniversityGroup = (RadioGroup) getView().findViewById(R.id.courseUniversityGroup);
        subjectSpinner = (Spinner) getView().findViewById(R.id.subjectSpinner);
        sortSpinner = (Spinner) getView().findViewById(R.id.sortSpinner);
        studentSpinner = (Spinner) getView().findViewById(R.id.studentSpinner);
        majorSpinner = (Spinner) getView().findViewById(R.id.majorSpinner);


        courseUniversityGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton courseButton = (RadioButton) getView().findViewById(i);
                courseUniversity = courseButton.getText().toString();

                subjectAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.subject, android.R.layout.simple_spinner_dropdown_item);
                subjectSpinner.setAdapter(subjectAdapter);

                sortAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.sort, android.R.layout.simple_spinner_dropdown_item);
                sortSpinner.setAdapter(sortAdapter);

                studentAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.student, android.R.layout.simple_spinner_dropdown_item);
                studentSpinner.setAdapter(studentAdapter);

                majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.major, android.R.layout.simple_spinner_dropdown_item);
                majorSpinner.setAdapter(majorAdapter);

//                if(courseUniversity.equals("학생"))
//                {
//
//                }
//                else if(courseUniversity.equals("교수"))
//                {
//
//                }


            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false);
    }
}