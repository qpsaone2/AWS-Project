package com.sample.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.timetable.SaveManager;
import com.sample.timetable.Schedule;
import com.sample.timetable.TimetableView;

import java.util.ArrayList;

public class LectureAdapter extends RecyclerView.Adapter<LectureAdapter.LectureViewHolder> implements Filterable {

    private ArrayList<Lecture> lectureArrayList; //Lecture 객체 가지고옴 (arraylist --> LectureArrayList)
    private Context context; //선택한 액티비티에 대한 context를 가지고올 때
    public static final int EDIT_OK_ADD = 10;

    private ArrayList<Lecture> lectureArrayListFull;
    private TimetableView timetable;


    OnClassItemClickListener listener;

    public LectureAdapter(ArrayList<Lecture> lectureArrayList, Context context) {
        this.lectureArrayList = lectureArrayList;
        this.context = context;
    }

    public LectureAdapter(ArrayList<Lecture> lectureArrayList) {
        this.lectureArrayList = lectureArrayList;
    }


//    public LectureAdapter(ArrayList<Lecture> lectureArrayList) {
//    }

    @NonNull
    @Override
    public LectureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //실제 리스트 뷰가 어뎁토로 연결되고 뷰 홀더를 최초로 만들어냄
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.course,parent,false);
        LectureViewHolder holder = new LectureViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LectureViewHolder holder, int position) {

        //각 item들을 매칭해준다
        holder.className_txtView.setText(lectureArrayList.get(position).getClass_Name());
        holder.classSchedule_txtView.setText(lectureArrayList.get(position).getClass_schedule());
        holder.prof_txtView.setText(lectureArrayList.get(position).getProf());


        //searchbtn 눌렀을 때
        holder.search_addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                        if(searchbtn != null  ){
                Log.e("search_addButton", "눌러짐");


                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("lecture", lectureArrayList.get(position));
                context.startActivity(intent);

            }
        });


    }


    @Override
    public int getItemCount() {

        //삼항 연산자 (조건식? 참:거짓)
        return (lectureArrayList != null ? lectureArrayList.size() :0);

    }

    public void setOnItemClickListener(OnClassItemClickListener listener) {
        this.listener = listener;
    }

    public void onItemClick(LectureViewHolder holder, View view, int position) {
        if(listener != null) {
            listener.onItemClick(holder,view,position); }
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword)
        {
            if(lectureArrayListFull == null) {
                lectureArrayListFull = new ArrayList<>(lectureArrayList);
            }
            Log.e("filter", "@@@@@@@@@@@@@@@@@@@@");
            ArrayList<Lecture> filteredList = new ArrayList<>();
                Log.e("filter", "@" + keyword + "@");
                Log.e("filter", lectureArrayList.toString());
                Log.e("filter", lectureArrayListFull.toString());

            if(keyword == null || keyword.length() ==0) {
                filteredList.addAll(lectureArrayListFull);
            }
            else
            {
                String filterPattern = keyword.toString().toLowerCase().trim();

                for(Lecture item : lectureArrayListFull)
                {
                    if(item.getClass_Name().toLowerCase().contains(filterPattern))
                        filteredList.add(item);

                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return  results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results)
        {
            lectureArrayList.clear();
            lectureArrayList.addAll((ArrayList<Lecture>)results.values);
            notifyDataSetChanged();
        }
    };

    public class LectureViewHolder extends RecyclerView.ViewHolder {

        TextView className_txtView;
        TextView classSchedule_txtView;
        TextView prof_txtView;
        Button search_addButton;



        public LectureViewHolder(@NonNull View itemView) {
            super(itemView);
            this.className_txtView = itemView.findViewById(R.id.className_txtView);
            this.classSchedule_txtView = itemView.findViewById(R.id.classSchedule_txtView);
            this.prof_txtView = itemView.findViewById(R.id.prof_txtView);
            this.search_addButton = itemView.findViewById(R.id.search_addButton);

//            search_addButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position = getAbsoluteAdapterPosition();
//                    if ( listener != null){
//                        listener.onItemClick(LectureViewHolder.this, view, position);
//                    }
//
//                }
//            });

        }

    }

}
