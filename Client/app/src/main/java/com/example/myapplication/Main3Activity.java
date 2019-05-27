package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.myapplication.Model.request.OtpRequest;
import com.example.myapplication.Model.response.ScheduleResponse;
import com.example.myapplication.Util.RetrofitService;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main3Activity extends AppCompatActivity {

    TableLayout tableLayout;
    TableRow tableRow[];

    TextView place;

    ArrayList<ScheduleResponse> schedule;

    private ArrayAdapter adapter1;
    private ArrayAdapter adapter2;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;

    int tr = 11;
    int td = 6;

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        spinner1 = (Spinner) findViewById(R.id.week);
        adapter1 = ArrayAdapter.createFromResource(this,R.array.week,android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner2 = (Spinner) findViewById(R.id.start_time);
        adapter2 = ArrayAdapter.createFromResource(this,R.array.time,android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner3 = (Spinner) findViewById(R.id.end_time);
        adapter2 = ArrayAdapter.createFromResource(this,R.array.time,android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter2);


        Intent intent = getIntent();
        schedule = (ArrayList<ScheduleResponse>) intent.getExtras().getSerializable("array");

        place = findViewById(R.id.place);
        place.setText(schedule.get(0).getName() + " " + schedule.get(0).getNumber() + " 스케쥴");

        TextView data[][] = new TextView[tr][td]; // 교시(+1칸) + 요일(+1칸)

        //통과

        for(int i=0;i<tr;i++){
            for(int j=0;j<td;j++){
                data[i][j] = new TextView(this);
                if(i==0){
                    switch(j){
                        case 0: data[i][j].setText("");break;
                        case 1: data[i][j].setText("월");break;
                        case 2: data[i][j].setText("화");break;
                        case 3: data[i][j].setText("수");break;
                        case 4: data[i][j].setText("목");break;
                        case 5: data[i][j].setText("금");break;
                    }
                }
                else
                    data[i][j].setText("o");
            }
        }

//        Log.e("schedule's size",schedule.size()+"");
        for(int i=0;i<schedule.size();i++){
            int day = schedule.get(i).getDay(); // 요일
            int time = schedule.get(i).getTime(); // 교시

//            Log.e("day",day+"");
//            Log.e("time",time+"");


             data[time][day-1].setText("x");
        }

//        for(int i=0;i<tr;i++){
//            for(int j=0;j<td;j++){
//
//                Log.e("data", data[i][j].getText()+"");
//            }
//        }


        tableLayout = (TableLayout)findViewById(R.id.ScheduleTable);
        tableLayout.removeAllViews();
        tableRow = new TableRow[tr]; // 요일 + 10교시 열의 갯수
        TextView textView[][] = new TextView[tr][td];

        for(int i=0;i<tr;i++){

            tableRow[i] = new TableRow(this);

            for(int j=0;j<td;j++){

                String massage = data[i][j].getText().toString();
                textView[i][j] = new TextView(this);
                textView[i][j].setTextSize(35);
                textView[i][j].setGravity(Gravity.CENTER);
                if (j % 2 == 0) {
                    if(i==0){
                        textView[i][j].setBackgroundColor(Color.parseColor("#9C27B0"));
                    }
                    else if (i%2==0) {
                        textView[i][j].setBackgroundColor(Color.parseColor("#3F51B5"));
                    }
                    else
                        textView[i][j].setBackgroundColor(Color.parseColor("#00BCD4"));
                }
                else{
                    if(i==0){
                        textView[i][j].setBackgroundColor(Color.parseColor("#F44336"));
                    }
                    else if(i%2==0){
                        textView[i][j].setBackgroundColor(Color.parseColor("#00BCD4"));
                    }
                    else
                        textView[i][j].setBackgroundColor(Color.parseColor("#3F51B5"));
                }



                if(j==0){
                    if(i==0){
                        textView[i][j].setText("/");
                    }
                    else {
                        textView[i][j].setText(i + "");
                        textView[i][j].setTextColor(Color.WHITE);
                    }
                }
                else{
                    textView[i][j].setTextColor(Color.BLACK);
                    textView[i][j].setText(massage);
                }

                tableRow[i].addView(textView[i][j]);

            }

            tableLayout.addView(tableRow[i]);
        }

    }

    @OnClick(R.id.sendserver_btn3)
    public void onClickServerBtn(){

        int week ;
        String word =spinner1.getSelectedItem().toString();
        switch (word){
            case "월요일" : week = 2 ; break;
            case "화요일" : week = 3 ; break;
            case "수요일" : week = 4 ; break;
            case "목요일" : week = 5 ; break;
            case "금요일" : week = 6 ; break;
        }
        int start_time = Integer.parseInt(spinner2.getSelectedItem().toString());
        int end_time = Integer.parseInt(spinner3.getSelectedItem().toString());

        OtpRequest otpRequest = new OtpRequest(schedule.get(0).getName(),schedule.get(0).getNumber(),week)

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.sendOtpSchedule()
    }

}
