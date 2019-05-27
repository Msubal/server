package com.example.myapplication.Util;

import com.example.myapplication.Model.request.OtpRequest;
import com.example.myapplication.Model.request.UserInfo;
import com.example.myapplication.Model.response.LoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

//    String URL = "http://223.194.134.218:8080/adminSystem/";
    String URL = "http://223.194.133.171:8080/adminSystem2/";
//    String URL = "http://223.194.134.218:8080/adminSystem3/";



    @POST("loginSystem")
    Call<LoginResponse> sendUserLoginInfo(@Body UserInfo userInfo);

    @POST("logoutSystem")
    Call<LoginResponse> sendLogout(@Body UserInfo userInfo);

    @GET("schedule_api/{number}")
    Call<ResponseBody> sendPlaceNum(@Path("number") int number);

    @POST("otpSystem")
    Call<OtpRequest> sendOtpSchedule(@Body OtpRequest otpRequest);





}
