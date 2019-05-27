package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.request.UserInfo;
import com.example.myapplication.Model.response.LoginResponse;
import com.example.myapplication.Model.response.ScheduleResponse;
import com.example.myapplication.Util.RetrofitService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private Spinner spinner;
    private TextView userInfo;

    Retrofit retrofit;

    JsonParser jsonParser;

    private int id;
    private String password;
    private String name;


    ArrayList<ScheduleResponse> array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        id = intent.getExtras().getInt("userId");
        password = intent.getExtras().getString("userPw");
        name = intent.getExtras().getString("userName");
        userInfo = findViewById(R.id.userInfo);
        userInfo.setText("id : [" + id + "] name : [" + name +"]");

        //place 스피너
        spinner = (Spinner) findViewById(R.id.select_place);
        adapter = ArrayAdapter.createFromResource(this,R.array.place,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Override
    public void onBackPressed() {
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


    @OnClick(R.id.sendserver_btn2)
    public void onClickServerBtn(){
        //스피너 정보 저장
        String selectPlace = spinner.getSelectedItem().toString();
        //Log.e("spinner",selectPlace);
        jsonParser = new JsonParser();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.sendPlaceNum(Integer.parseInt(selectPlace)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    //Log.e("onResponse success", "성공 ㅎ");
                    try {
                        JsonArray jsonArray = (JsonArray) jsonParser.parse(response.body().string());
                        array = new ArrayList<ScheduleResponse>();

                        // 가져온 json 정보 파싱하기
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JsonObject object = (JsonObject) jsonArray.get(i);
                            int num = object.get("num").getAsInt();
                            String name = object.get("name").getAsString();
                            int number = object.get("number").getAsInt();
                            int day = object.get("day").getAsInt();
                            int time = object.get("time").getAsInt();

//                            Log.d("num","num: "+num);
//                            Log.d("name", "name : " + name);
//                            Log.d("number", "number : " + number);
//                            Log.d("day", "day : " + day);
//                            Log.d("time", "time : " + time);

                            array.add(new ScheduleResponse(num,name,number,day,time));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Intent ThirdIntent = new Intent(Main2Activity.this,Main3Activity.class);
                    ThirdIntent.putExtra("array", array);
                    Main2Activity.this.startActivity(ThirdIntent);
              }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("2 activity error","" + t.toString());
            }
        });

       // Log.d("response successful","클릭2");
    }
}
