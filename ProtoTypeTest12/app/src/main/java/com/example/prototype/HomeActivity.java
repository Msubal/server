package com.example.prototype;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.prototype.Model.request.OtpCheck;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class HomeActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 3;
    private ArrayAdapter<String> mArrayAdapter;
    private String connectedDeviceName;
    private BluetoothAdapter btAdapter = null;
    private BluetoothDevice btDevice = null;
    private BluetoothSocket btSocket = null;


    private static OutputStream btos;
    private InputStream btis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Button loginViewButton = (Button)findViewById(R.id.loginButton);

        try {
            openBT();
            Toast.makeText(getApplicationContext(), "open Success",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "open Failed",
                    Toast.LENGTH_SHORT).show();
            //finish();
        }

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
        loginViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginViewIntent = new Intent(HomeActivity.this,MainActivity.class);

                HomeActivity.this.startActivity(loginViewIntent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("종료하시겠습니까?");

        // "예" 버튼을 누르면 실행되는 리스너
        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // 현재 액티비티를 종료한다. (MainActivity에서 작동하기 때문에 애플리케이션을 종료한다.)
            }
        });
        // "아니오" 버튼을 누르면 실행되는 리스너
        alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return; // 아무런 작업도 하지 않고 돌아간다
            }
        });
        alBuilder.setTitle("프로그램 종료");
        alBuilder.show(); // AlertDialog.Bulider로 만든 AlertDialog를 보여준다.
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
    public static void write(String str){
        try {
            btos.write(str.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}