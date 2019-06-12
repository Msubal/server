package com.example.prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
* 로그인 화면인데 현재 메인엑티비티로 이동하는 기능은 구현이 안되있음
* Intent방식으로 메인액티비티 호출하면 되니까 (테스트했는데 실수로 코드파일 날려버림)
* 현재 MainActivity, LoginActivity는 테스트는 메니페스트 조작으로 부탁드림
*
* */

    public class LoginActivity extends AppCompatActivity  { //메인 activity 시작!
        private Socket socket;  //소켓생성
        BufferedReader in;      //서버로부터 온 데이터를 읽는다.
        PrintWriter out;        //서버에 데이터를 전송한다.
        EditText id;         //화면구성
        Button button;          //화면구성
        TextView output;        //화면구성
        String data;            //

            @Override
            protected void onCreate(Bundle savedInstanceState) {   //앱 시작시  초기화설정
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);

                id = (EditText) findViewById(R.id.ET_id); // 글자입력칸을 찾는다.
                //password = (EditText) findViewById(R.id.ET_passward);
                button = (Button) findViewById(R.id.BTN_login); // 버튼을 찾는다.
                //output = (TextView) findViewById(R.id.output); // 글자출력칸을 찾는다.

                new Thread() {    //worker 를 Thread 로 생성
                    public void run() { //스레드 실행구문
                        try {
                            socket = new Socket("172.30.64.132", 30000); //소켓생성
                            out = new PrintWriter(socket.getOutputStream(), true); //데이터를 전송시 stream 형태로 변환하여                                                                                                                       //전송한다.
                            in = new BufferedReader(new InputStreamReader(
                                    socket.getInputStream())); //데이터 수신시 stream을 받아들인다.

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            while (true) {
                                data = in.readLine(); // in 으로 받은 데이타를 String 형태로 읽어 data 에 저장
                                output.post(new Runnable() {
                                    public void run() {
                                        output.setText(data); //글자출력칸에 서버가 보낸 메시지를 받는다.
                                    }
                                });
                            }
                        } catch (Exception e) {
                        }
                    }
                }.start();

                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String data = id.getText().toString();//+password.getText().toString(); //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
                        Log.w("NETWORK", " " + data);
                        if (data != null) { //만약 데이타가 아무것도 입력된 것이 아니라면
                            out.println(data); //data를   stream 형태로 변형하여 전송.  변환내용은 쓰레드에 담겨 있다.
                        }
                    }
                });

            }

            @Override
            protected void onStop() {  //앱 종료시
                super.onStop();
                try {
                    socket.close(); //소켓을 닫는다.
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }


