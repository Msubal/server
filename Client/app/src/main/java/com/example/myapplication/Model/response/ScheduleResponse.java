package com.example.myapplication.Model.response;

import java.io.Serializable;

public class ScheduleResponse implements Serializable {
    int num ;
    String name ;
    int number ;
    int day ;
    int time ;

    public ScheduleResponse(int num, String name, int number, int day, int time) {
        this.num = num;
        this.name = name;
        this.number = number;
        this.day = day;
        this.time = time;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
