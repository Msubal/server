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
import com.example.prototype.Util.RetrofitService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edittext_ID)
    EditText editText_ID;
    @BindView(R.id.edittext_PSW)
    EditText editText_PSW;
    @BindView(R.id.sendserver_btn)
    Button sendserver_btn;

    String id;
    String password;
    Retrofit retrofit;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    @OnClick(R.id.sendserver_btn)
    public void onClickServerBtn(){
        id = editText_ID.getText().toString();
        password = editText_PSW.getText().toString();

        userInfo = new UserInfo(id, password);

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.sendUserLoginInfo(userInfo).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
//                    Log.e("onResponse success","id="+response.body().getId());
//                    Log.e("onResponse success","password="+response.body().getPassword());
//                    Log.e("onResponse success","name="+response.body().getName());
//                    Log.e("onResponse success","check="+response.body().getCheck());

                    if(response.body().getOtp_check().equals("x")) {
                        Toast.makeText(getApplicationContext(), response.body().getName()+"님 환영합니다.", Toast.LENGTH_LONG).show();

                        Intent sixthActivity = new Intent(MainActivity.this ,  Main6Activity.class);
                        sixthActivity.putExtra("userId",response.body().getId());
                        sixthActivity.putExtra("userPw",response.body().getPassword());
                        sixthActivity.putExtra("userName",response.body().getName());
                        MainActivity.this.startActivity(sixthActivity);
                        finish();
                    }
                    else{
                        Intent fixthActivity = new Intent(MainActivity.this, Main5Activity.class);
                        MainActivity.this.startActivity(fixthActivity);
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
