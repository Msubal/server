package com.example.prototype.Model.response;

public class LoginResponse {
    private int num;
    private int id;
    private String password;
    private String name;
    private String check;
    private String otp_check;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getOtp_check() {
        return otp_check;
    }

    public void setOtp_check(String otp_check) {
        this.otp_check = otp_check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
