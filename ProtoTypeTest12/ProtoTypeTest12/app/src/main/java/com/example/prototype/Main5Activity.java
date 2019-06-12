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

import com.example.prototype.Model.request.UserInfo;
import com.example.prototype.Model.response.LoginResponse;
import com.example.prototype.Util.RetrofitService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    TextView start_time;
    @BindView(R.id.end_time)
    TextView end_time;
    @BindView(R.id.textOTPNumber)
    TextView textOTP;
    @BindView(R.id.daytext)
    TextView daytext;

    Retrofit retrofit;

    private int id;
    private String password;
    private String name;

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

        Intent intent = getIntent();
        id = intent.getExtras().getInt("userId");
        password = intent.getExtras().getString("userPw");
        name = intent.getExtras().getString("userName");
        unity_pw = intent.getExtras().getInt("unity_pw");
        textOTP.setText(unity_pw+"");
        start_time.setText(intent.getExtras().getInt("start_time")+"교시");
        end_time.setText(intent.getExtras().getInt("end_time")+"교시");
        daytext.setText(intent.getExtras().getString("day"));
        place_num.setText(id+"");

        Button otpAccessButton = (Button)findViewById(R.id.otpAccessButton);
        Button otpProposeButton = (Button)findViewById(R.id.otpProposeButton);




        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();





        otpAccessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "OTP 비밀번호 있음 :"+unity_pw,
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
                Toast.makeText(getApplicationContext(), "더이상의 OTP를 가지지 못합니다. "+unity_pw,
                        Toast.LENGTH_SHORT).show();
            }
        });
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
                Intent loginIntent = new Intent(Main5Activity.this,MainActivity.class);
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
            write("a");
            return 1;
        }

        public static int getInputedPassward(int passward){
            Log.i("유니티에서 호출했습니다","사용자가 입력한 passward : "+passward);
            inputedpassward=passward;
            Log.i("InputedPassward :"," "+inputedpassward);
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
        public static void write(String str){
            try {
                btos.write(str.getBytes());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


}