package com.example.prototype;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prototype.Model.request.UserInfo;
import com.example.prototype.Model.response.LoginResponse;
import com.example.prototype.Util.RetrofitService;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main6Activity extends AppCompatActivity {

    @BindView(R.id.place_num)
    TextView place_num;
    @BindView(R.id.start_time)
    TextView start_time;
    @BindView(R.id.end_time)
    TextView end_time;
    @BindView(R.id.textOTPNumber)
    TextView textOTP;
    @BindView(R.id.userTextView)
    TextView userTextView;

    private int id;
    private String password;
    private String name;

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_view);
        ButterKnife.bind(this);

        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        textOTP.setText("OTP Number");

        Intent intent = getIntent();
        id = intent.getExtras().getInt("userId");
        password = intent.getExtras().getString("userPw");
        name = intent.getExtras().getString("userName");

        Button otpAccessButton = (Button)findViewById(R.id.otpAccessButton);
        Button otpProposeButton = (Button)findViewById(R.id.otpProposeButton);

        otpAccessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "OTP 비밀번호를 신청해주세요",
                        Toast.LENGTH_SHORT).show();

//                Log.i(this.getClass().getName(),"유니티 실행합니다 전");
//                Intent unityintent=new Intent(Main5Activity.this,UnityPlayerActivity.class);
//                startActivity(unityintent);
//                Log.i(this.getClass().getName(),"유니티 실행합니다 후 ");
//                finish();
            }
        });

        otpProposeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otpProposeIntent = new Intent(Main6Activity.this,Main2Activity.class);
                otpProposeIntent.putExtra("userId",id);
                otpProposeIntent.putExtra("userPw", password);
                otpProposeIntent.putExtra("userName", name);
                Toast.makeText(getApplicationContext(), "id: "+id,
                        Toast.LENGTH_SHORT).show();
                Main6Activity.this.startActivity(otpProposeIntent);
                finish();
            }
        });
        userTextView = findViewById(R.id.userTextView);
        userTextView.setText("환영합니다. "+name+"님");

    }


    @OnClick(R.id.backButton1)
    public void onClickLogoutBtn(){
        show();
    }

    void show(){
        // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("로그아웃 하시겠습니까?");

        // "예" 버튼을 누르면 실행되는 리스너
        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                UserInfo userInfo = new UserInfo(id+"", password);
                RetrofitService retrofitService = retrofit.create(RetrofitService.class);
                retrofitService.sendLogout(userInfo).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        Toast.makeText(getApplicationContext(), "로그아웃 완료.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
                Intent loginIntent = new Intent(Main6Activity.this,ReLoginActivity.class);
                loginIntent.putExtra("userId",id);
                loginIntent.putExtra("userPw",password);
                startActivity(loginIntent);
                finish(); // 현재 액티비티를 종료한다.
            }
        });
        // "아니오" 버튼을 누르면 실행되는 리스너
        alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return; // 아무런 작업도 하지 않고 돌아간다
            }
        });
        alBuilder.setTitle("로그 아웃");
        alBuilder.show(); // AlertDialog.Bulider로 만든 AlertDialog를 보여준다.
    }


    @Override
    public void onBackPressed() {
        show();
    }
}
