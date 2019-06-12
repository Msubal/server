package com.example.prototype.Model.request;

public class OtpRequest {
    String name ; // 학생 id
    int number ; // 장소 위치
    int day ; // 요일
    int start_time ; // 시작시간
    int end_time; // 끝나는 시간

    public OtpRequest(String name, int number, int day, int start_time, int end_time) {
        this.name = name;
        this.number = number;
        this.day = day;
        this.start_time = start_time;
        this.end_time = end_time;
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

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }
}
