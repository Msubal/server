package com.example.prototype.Util;

import com.example.prototype.Model.request.OtpRequest;
import com.example.prototype.Model.request.UserInfo;
import com.example.prototype.Model.response.LoginResponse;
import com.example.prototype.Model.response.OtpResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

//    String URL = "http://223.194.128.56:8080/adminSystem2/";
    String URL = "http://223.194.131.54:8080/adminSystem2/";




    @POST("loginSystem")
    Call<LoginResponse> sendUserLoginInfo(@Body UserInfo userInfo);

    @POST("logoutSystem")
    Call<LoginResponse> sendLogout(@Body UserInfo userInfo);

    @GET("schedule_api/{number}")
    Call<ResponseBody> sendPlaceNum(@Path("number") int number);

    @POST("otpSystem")
    Call<OtpResponse> sendOtpSchedule(@Body OtpRequest otpRequest);

    @POST("otpCheck")
    Call <OtpResponse> sendIdCheckOtp(@Body UserInfo userInfo);

    @POST("otpDelete")
    Call <OtpResponse> sendOtpDelete(@Body OtpRequest otpRequest);




}
