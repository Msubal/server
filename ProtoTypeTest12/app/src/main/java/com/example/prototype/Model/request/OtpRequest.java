package com.example.prototype.Model.request;

public class OtpRequest {
    String student_id ; // 학생 id
    int building_num ; // 장소 위치
    int day ; // 요일
    int start_time ; // 시작시간
    int end_time; // 끝나는 시간

    public OtpRequest(String student_id, int building_num, int day, int start_time, int end_time) {
        this.student_id = student_id;
        this.building_num = building_num;
        this.day = day;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public String getName() {
        return student_id;
    }

    public void setName(String student_id) {
        this.student_id = student_id;
    }

    public int getNumber() {
        return building_num;
    }

    public void setNumber(int building_num) {
        this.building_num = building_num;
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
