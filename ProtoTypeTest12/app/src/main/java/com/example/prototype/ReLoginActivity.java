package com.example.prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prototype.Model.request.UserInfo;
import com.example.prototype.Model.response.LoginResponse;
import com.example.prototype.Model.response.OtpResponse;
import com.example.prototype.Util.RetrofitService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReLoginActivity extends AppCompatActivity {

    @BindView(R.id.edittext_ID)
    EditText editText_ID;
    @BindView(R.id.edittext_PSW)
    EditText editText_PSW;
    @BindView(R.id.sendserver_btn)
    Button sendserver_btn;

    String id;
    String password;
    String name;
    Retrofit retrofit;
    UserInfo userInfo;

    OtpResponse otpResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        otpResponse = new OtpResponse();

        Intent intent = getIntent();
        editText_ID.setText(intent.getExtras().getInt("userId")+"");
        editText_PSW.setText(intent.getExtras().getString("userPw"));

    }



    @OnClick(R.id.sendserver_btn)
    public void onClickServerBtn(){
        id = editText_ID.getText().toString();
        password = editText_PSW.getText().toString();

        userInfo = new UserInfo(id, password);

        final RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.sendUserLoginInfo(userInfo).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    name = response.body().getName();
                    id = response.body().getId()+"";
                    password = response.body().getPassword();
                    //otp 정보가 없는사람 --> 엑티비티4
                    if(response.body().getOtp_check().equals("x")) {
                        Toast.makeText(getApplicationContext(), response.body().getName()+"님 환영합니다.", Toast.LENGTH_SHORT).show();

                        Intent sixthActivity = new Intent(ReLoginActivity.this ,  Main6Activity.class);
                        sixthActivity.putExtra("userId",response.body().getId());
                        sixthActivity.putExtra("userPw",response.body().getPassword());
                        sixthActivity.putExtra("userName",response.body().getName());
                        ReLoginActivity.this.startActivity(sixthActivity);
                        finish();
                    }
                    else{
                        // otp를 가지고있는 정보를 바로 파싱한다. --> 액티비티 5
                        Toast.makeText(getApplicationContext(), response.body().getName()+"님 환영합니다.", Toast.LENGTH_SHORT).show();
                        retrofitService.sendIdCheckOtp(userInfo).enqueue(new Callback<OtpResponse>() {
                            @Override
                            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {

                                if(response.isSuccessful()){
                                    Intent fixthActivity = new Intent(ReLoginActivity.this,Main5Activity.class);
                                    fixthActivity.putExtra("userId", Integer.parseInt(id));
                                    fixthActivity.putExtra("userPw",password);
                                    fixthActivity.putExtra("userName",name);
                                    fixthActivity.putExtra("unity_pw",response.body().getOtp_pw());
                                    fixthActivity.putExtra("start_time",response.body().getStart_time());
                                    fixthActivity.putExtra("end_time",response.body().getEnd_time());
                                    fixthActivity.putExtra("day",response.body().getDay());
                                    fixthActivity.putExtra("building_num",response.body().getBuilding_num());
                                    ReLoginActivity.this.startActivity(fixthActivity);
                                }
                            }

                            @Override
                            public void onFailure(Call<OtpResponse> call, Throwable t) {
                                Log.e("로그인 실패","로그인 실패: "+t.toString());
                            }
                        });


                    }

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("onFailure",""+t.toString());

                Toast.makeText(getApplicationContext(), "아이디 비번을 확인해주세요, 중복 로그인입니다.", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
