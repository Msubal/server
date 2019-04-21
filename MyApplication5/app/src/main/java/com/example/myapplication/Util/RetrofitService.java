package com.example.myapplication.Util;

import com.example.myapplication.Model.request.UserInfo;
import com.example.myapplication.Model.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {


    String URL = "http://223.194.130.56:8080/adminSystem2/";

    @POST("login")
    Call<LoginResponse> sendUserLoginInfo(@Body UserInfo userInfo);

}
