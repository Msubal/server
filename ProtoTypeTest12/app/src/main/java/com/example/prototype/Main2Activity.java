package com.example.prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.prototype.Model.response.ScheduleResponse;
import com.example.prototype.Util.RetrofitService;
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
        setContentView(R.layout.otp_propose);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        id = intent.getExtras().getInt("userId");
        password = intent.getExtras().getString("userPw");
        name = intent.getExtras().getString("userName");

        //place 스피너
        spinner = (Spinner) findViewById(R.id.select_place);
        adapter = ArrayAdapter.createFromResource(this,R.array.Place,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Override
    public void onBackPressed() {
        Intent sixthActivity = new Intent(Main2Activity.this ,  Main6Activity.class);
        sixthActivity.putExtra("userId",id);
        sixthActivity.putExtra("userPw",password);
        sixthActivity.putExtra("userName",name);
        Main2Activity.this.startActivity(sixthActivity);
        finish();
    }


    @OnClick(R.id.backButton2)
    public void onClickLogoutBtn(){
        Intent sixthActivity = new Intent(Main2Activity.this ,  Main6Activity.class);
        sixthActivity.putExtra("userId",id);
        sixthActivity.putExtra("userPw",password);
        sixthActivity.putExtra("userName",name);
        Main2Activity.this.startActivity(sixthActivity);
        finish();
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
                    ThirdIntent.putExtra("userId",id);
                    ThirdIntent.putExtra("userPw",password);
                    ThirdIntent.putExtra("userName",name);
                    Main2Activity.this.startActivity(ThirdIntent);
                    finish();
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