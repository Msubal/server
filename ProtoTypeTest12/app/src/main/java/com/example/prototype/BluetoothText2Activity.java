package com.example.prototype;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.Set;

/*
 *
 * Version : 1.2
 * 이태영 Last Update : 3.24
 *
 * "문열림" 버튼누르면 도어락 열림
 * 솔직히 100% 다 알지는 모르고 어떤 함수가 대충 어떤거 하는지만 암
 * wrire("a") 하면 문 작동하는 신호 보내는거임
 *
 * */



public class BluetoothText2Activity extends AppCompatActivity {


    public Activity currentActivity;
    public static int passward;
    public static Intent intent2;
    private int id;
    private String password;
    private int id2;
    private String password2;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        currentActivity=this;
        id=6974;
        password="sex";



        final LinearLayout LL_PasswardInquiry = new LinearLayout(BluetoothText2Activity.this);
        LL_PasswardInquiry.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        LL_PasswardInquiry.setOrientation(LinearLayout.VERTICAL);
        LL_PasswardInquiry.setVisibility(View.VISIBLE);
        final LinearLayout LL_PasswardRequest =  new LinearLayout(BluetoothText2Activity.this);
        LL_PasswardRequest.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        LL_PasswardRequest.setOrientation(LinearLayout.VERTICAL);
        LL_PasswardRequest.setVisibility(View.GONE);
        LinearLayout LL_dynamicArea = (LinearLayout) findViewById(R.id.dynamicLinear);
        Button BTN_PasswardInquiry = (Button) findViewById(R.id.BTN_passwardInquiry);
        Button BTN_PasswardRequest = (Button) findViewById(R.id.BTN_passwardRequest);
        Button BTN_PasswardInput = (Button) findViewById(R.id.BTN_passwardInput);
        BTN_PasswardInput.setText("문 열기");
        BTN_PasswardInquiry.setText("비밀번호설정 1111");
        BTN_PasswardRequest.setText("비밀번호설정 2222");



        BTN_PasswardInput.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //write("a");
                Toast.makeText(getApplicationContext(),"문 열림",Toast.LENGTH_LONG);
                Log.i(this.getClass().getName(),"유니티 실행합니다 전");
                Intent intent=new Intent(BluetoothText2Activity.this,UnityPlayerActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("password",password);
                startActivity(intent);
                Log.i(this.getClass().getName(),"유니티 실행합니다 후 ");
                finishAfterTransition();

            }
        });

        BTN_PasswardInquiry.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                passward=1111;
                Toast.makeText(getApplicationContext(),"문 열림",Toast.LENGTH_LONG).show();
            }
        });

        BTN_PasswardRequest.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                passward=2222;
                Toast.makeText(getApplicationContext(),"문 열림",Toast.LENGTH_LONG).show();
            }
        });

    }

    public static int getPassward(){
        Log.i("유니티에서 호출했습니다", "유니티에서 호출했습니다  getPassward() "+"\n");
        return passward;
    }
    public static int openDoor(){
        Log.i("유니티에서 호출했습니다", "유니티에서 호출했습니다  OPEN DOOR! "+"\n");
        HomeActivity.write("a");
        return 1;
    }

}