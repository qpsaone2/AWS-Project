package com.sample.mainactivity;

import java.io.Serializable;


public class Lecture  implements Serializable {

    private String class_Name;
    private String class_schedule;
    private String prof;
    private String time;

    private long day_1;
    private long day_2;

    private String day1_start_time;
    private String day2_start_time;

    private String day1_end_time;
    private String day2_end_time;

    public Lecture() {
        this.class_Name = class_Name;
        this.class_schedule = class_schedule;
        this.prof = prof;
        this.time = time;
        this.day_1 = day_1;
        this.day_2 = day_2;
        this.day1_start_time = day1_start_time;
        this.day2_start_time = day2_start_time;
        this.day1_end_time = day1_end_time;
        this.day2_end_time = day2_end_time;
    }

    public String getClass_Name() {
        return class_Name;
    }

    public void setClass_Name(String class_Name) {
        this.class_Name = class_Name;
    }

    public String getClass_schedule() {
        return class_schedule;
    }

    public void setClass_schedule(String class_schedule) {
        this.class_schedule = class_schedule;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getDay_1() {
        return day_1;
    }

    public void setDay_1(long day_1) {
        this.day_1 = day_1;
    }

    public long getDay_2() {
        return day_2;
    }

    public void setDay_2(long day_2) {
        this.day_2 = day_2;
    }

    public String getDay1_start_time() {
        return day1_start_time;
    }

    public void setDay1_start_time(String day1_start_time) {
        this.day1_start_time = day1_start_time;
    }

    public String getDay2_start_time() {
        return day2_start_time;
    }

    public void setDay2_start_time(String day2_start_time) {
        this.day2_start_time = day2_start_time;
    }

    public String getDay1_end_time() {
        return day1_end_time;
    }

    public void setDay1_end_time(String day1_end_time) {
        this.day1_end_time = day1_end_time;
    }

    public String getDay2_end_time() {
        return day2_end_time;
    }

    public void setDay2_end_time(String day2_end_time) {
        this.day2_end_time = day2_end_time;
    }



// classTitle == class_Name
    // classPlace == class_schedule
    // prof == professorName
    // className, classTime , classroom 데이터 필요함


    @Override
    public String toString() {
        return "Lecture{" +
                "class_Name='" + class_Name + '\'' +
                ", class_schedule='" + class_schedule + '\'' +
                ", prof='" + prof + '\'' +
                '}';
    }

}
