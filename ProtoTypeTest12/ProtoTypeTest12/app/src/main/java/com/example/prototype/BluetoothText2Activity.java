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
    static int passwardCardExpirationCounter = 0;

    private static final int REQUEST_ENABLE_BT = 3;

    private ArrayAdapter<String> mArrayAdapter;

    private ListView mConversationView;

    private String connectedDeviceName;
    private BluetoothAdapter btAdapter = null;
    private BluetoothDevice btDevice = null;
    private BluetoothSocket btSocket = null;


    private static OutputStream btos;
    private InputStream btis;
    private String pass;

    public Activity currentActivity;
    public static int passward;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        currentActivity=this;

        try {
            openBT();
            Toast.makeText(getApplicationContext(), "open Success",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "open Failed",
                    Toast.LENGTH_LONG).show();
            //finish();

        }


        // 변수 선언은 여기

        BluetoothAdapter mBTAdapter = BluetoothAdapter.getDefaultAdapter();
        mArrayAdapter = new ArrayAdapter<String>(this, R.layout.message);


        // 블루투스 활성화
        if (!mBTAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        // 페어링된 기기 리스트 가져오기
        Set<BluetoothDevice> pairedDevices = mBTAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                mArrayAdapter
                        .add(device.getName() + "\n" + device.getAddress());
            }
        }

        // 리스트 뷰에 표시
        mConversationView = (ListView) findViewById(R.id.in);
        mConversationView.setAdapter(mArrayAdapter);





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
                write("a");
                Toast.makeText(getApplicationContext(),"문 열림",Toast.LENGTH_LONG);
                Log.i(this.getClass().getName(),"유니티 실행합니다 전");
                Intent intent=new Intent(BluetoothText2Activity.this,UnityPlayerActivity.class);
                startActivity(intent);
                Log.i(this.getClass().getName(),"유니티 실행합니다 후 ");

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
        write("a");
        return 1;
    }
    //블루투스 통신 개방 함수
    public void openBT() throws IOException {
        // 도어락 MAC 주소
        connectedDeviceName = "00:13:EF:02:12:7C";

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        // 현재 내 폰에서 연결된 블루투스 기기중에 도어락 골라내는 부분
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {

                if (device.getAddress().equals(connectedDeviceName)) {
                    btDevice = device;
                    break;
                }
            }
        }

        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
        btSocket = btDevice.createRfcommSocketToServiceRecord(uuid);
        btSocket.connect();

        Toast.makeText(getApplicationContext(),
                String.valueOf(btSocket.isConnected()), Toast.LENGTH_LONG)
                .show();

        btos = btSocket.getOutputStream();
        btis = btSocket.getInputStream();

    }
    // 블루투스 통신 종료 함수
    public void closeBt() throws IOException {
        btos.close();
        btis.close();
        btSocket.close();

    }
    // 기계한테 문자열(String) 보내는 함수 "a" 보내면 도어락 열리고/닫힘
    public static void write(String str) {
        try {
            btos.write(str.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}