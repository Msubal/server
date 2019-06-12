package com.example.prototype;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prototype.Model.request.OtpRequest;
import com.example.prototype.Model.request.UserInfo;
import com.example.prototype.Model.response.LoginResponse;
import com.example.prototype.Model.response.OtpResponse;
import com.example.prototype.Util.RetrofitService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Set;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main5Activity extends AppCompatActivity {

    @BindView(R.id.place_num)
    TextView place_num;
    @BindView(R.id.start_time)
    TextView start_timeText;
    @BindView(R.id.end_time)
    TextView end_timeText;
    @BindView(R.id.textOTPNumber)
    TextView textOTP;
    @BindView(R.id.daytext)
    TextView daytext;
    @BindView(R.id.userTextView)
    TextView userTextView;
    @BindView(R.id.otpProposeButton)
    Button otpProposeButton;


    Retrofit retrofit;

    private int id;
    private String password;
    private String name;
    int week;
    int building_num;
    int start_time;
    int end_time;
    String strweek;


    public Activity currentActivity;

    public static int inputedpassward;
    public static int unity_pw ;

    int unity;

    public Main5Activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_view);
        ButterKnife.bind(this);

        Calendar cal = Calendar.getInstance();
        strweek = null;


        Intent intent = getIntent();
        id = intent.getExtras().getInt("userId");
        password = intent.getExtras().getString("userPw");
//        Log.i("유니티 액티비티 받아온 값!", "id : "+id+"\n");
//        Log.i("유니티 액티비티 받아온 값!", "passward : " + password+"\n");
        name = intent.getExtras().getString("userName");
        unity_pw = intent.getExtras().getInt("unity_pw");
        textOTP.setText(unity_pw+"");
        start_time = intent.getExtras().getInt("start_time");
        start_timeText.setText(start_time+"교시");
        end_time = intent.getExtras().getInt("end_time");
        end_timeText.setText(end_time+"교시");
        week = intent.getExtras().getInt("day");
        if(week == 1){
            strweek = "일요일";
        }else if(week == 2){
            strweek = "월요일";
        }else if(week ==3){
            strweek = "화요일";
        }else if(week ==4){
            strweek = "수요일";
        }else if(week ==5){
            strweek = "목요일";
        }else if(week ==6){
            strweek = "금요일";
        }else if(week ==7){
            strweek = "토요일";
        }

        daytext.setText(strweek);
        building_num = intent.getExtras().getInt("building_num");
        place_num.setText(building_num+"호");

        Button otpAccessButton = (Button)findViewById(R.id.otpAccessButton);

        otpProposeButton.setText("OTP 취소하기");


        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();





        otpAccessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "OTP 비밀번호  :"+unity_pw,
                            Toast.LENGTH_LONG).show();

               // Log.i(this.getClass().getName(),"유니티 실행합니다 전");
                Intent unityintent=new Intent(Main5Activity.this,UnityPlayerActivity.class);
                unityintent.putExtra("userId",id);
                unityintent.putExtra("userPw",password);
                unityintent.putExtra("userName",name);
                unityintent.putExtra("unity_pw",unity_pw);
                unityintent.putExtra("start_time",start_time);
                unityintent.putExtra("end_time",end_time);
                unityintent.putExtra("day",week);
                unityintent.putExtra("building_num",building_num);
                startActivity(unityintent);
              //  Log.i(this.getClass().getName(),"유니티 실행합니다 후 ");
                finishAfterTransition();
            }
        });
    }

    @OnClick(R.id.otpProposeButton)
    public void onClickOtpoutBtn(){
        // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("OTP를 취소하시겠습니까?");

        // "예" 버튼을 누르면 실행되는 리스너
        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                OtpRequest otpRequest = new OtpRequest(id+"",building_num,week,start_time,end_time);
                RetrofitService retrofitService = retrofit.create(RetrofitService.class);
                retrofitService.sendOtpDelete(otpRequest).enqueue(new Callback<OtpResponse>() {
                    @Override
                    public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                        Toast.makeText(getApplicationContext(), "OTP 취소 완료.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<OtpResponse> call, Throwable t) {

                    }
                });
                Intent loginIntent = new Intent(Main5Activity.this,Main6Activity.class);
                loginIntent.putExtra("userId",id);
                loginIntent.putExtra("userPw",password);
                loginIntent.putExtra("userName",name);
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
        alBuilder.setTitle("OTP 취소");
        alBuilder.show(); // AlertDialog.Bulider로 만든 AlertDialog를 보여준다.
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
                Intent loginIntent = new Intent(Main5Activity.this,ReLoginActivity.class);
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


        public static int getPassward(){
            Log.i("유니티에서 호출했습니다", "유니티에서 호출했습니다  getPassward() "+"\n");
            return unity_pw;
        }
        public static int openDoor(){
            Log.i("유니티에서 호출했습니다", "유니티에서 호출했습니다  OPEN DOOR! "+"\n");
            HomeActivity.write("a");
            return 1;
        }

        public static int getInputedPassward(int passward){
            Log.i("유니티에서 호출했습니다","사용자가 입력한 passward : "+passward);
            inputedpassward=passward;
            Log.i("InputedPassward :"," "+inputedpassward);
            return 1;
        }

        public static int senddata(int otpmun) {


        return  0;
        }


}