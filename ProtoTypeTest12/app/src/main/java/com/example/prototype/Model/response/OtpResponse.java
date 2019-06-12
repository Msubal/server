package com.example.prototype.Model.response;

import java.io.Serializable;

public class OtpResponse {
    private int num;
    private String student_id;
    private int building_num;
    private int day;
    private int start_time;
    private int end_time;
    private int otp_pw;
    private String current;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public int getBuilding_num() {
        return building_num;
    }

    public void setBuilding_num(int building_num) {
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

    public int getOtp_pw() {
        return otp_pw;
    }

    public void setOtp_pw(int otp_pw) {
        this.otp_pw = otp_pw;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }
}
