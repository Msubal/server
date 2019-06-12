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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Vector;

/*
* MainActivity
* Version : 1.2
* 이태영 Last Update : 3.23
*
* 로그인 다음 화면임
*
* it's rules
* 뷰 면 맨앞에 뭔지 약자로표시 ( ex LinearLayout -> LL_ )
* 단어 하나끝나면다음단어 대문자로 부탁드림
* 변수 이름을 통해서 역할이 뭔지 알수 있게 부탁드림
* 다른사람도 볼수있게 디버깅용 변수, Log 삭제 ㄴㄴ (너무많으면 주석처리 ㄱㄱ)
* 디버깅용 변수는 앞에 bug_ 부탁드림
* 디버깅 용으로 쓴 토스트메시지는 테스트 끝나면 무조건 삭제 부탁드림 (처음부터 Log 권장, 콘솔출력은 절대 ㄴㄴ)
* 기능테스트용으로 메인엑티비티 조작해도 되는데 테스트끝나면 테스트에 쓴 조작한 엑티비티 따로 저장부탁드림
* (원본 메인엑티비티는 윤동이가 실제적인 디자인 뽑고나서부터 고정임, 지금은 아무렇게나써도댐 )
  (다른사람도 테스트 해볼수있게)
* 조작은 최대한 소규모로 부탁드림
* 동적으로 뷰 생성할때 선언 후에 바로 프로퍼티 조작 메서드 떨어뜨려놓지말아주셈
* 주석은 자유롭게 ㄱㄱ 근데 너무 기본적인건 주석으로 달지 말아주셈.. 보기 너무 어지럽
* 디버깅용 "실행 코드"는 문장끝에 주석으로 //D 붙여주셈 (로그는안붙여도 okay)
* 버전은 그냥 올리고 싶을때 올리면댐 간지용
* 화면 전환은 새로운 액티비티 생성이 아니고 Activity Visiblility 조작하는 방식으로
* 일단 클라이언트 프로젝트 관리는 전부 이태영이 할것
* 클라이언트 프로젝트 동기화 문제가 발생할수 있으니까 프로젝트조작했으면 조작한 자바소스코드를 이태영한테 보내주셈
*
*
* 변수 명명할때 어디 관여하는건지 포함 부탁드림
*   Inquiry : 패스워드 카드 조회하는 레이아웃 관련
*   PasswardCard : 패스워드 카드 관련
*   Request : 비밀번호 요청 레이아웃 관련
*   Input : AR로 비밀번호 입력할 레이아웃
*
* (작성자) Methode
*  OnCreate : 이건 여러사람이 만지게 될꺼니까 되도록이면 로지컬은 따로 함수로빼주셈
* (이태영) SavePasswardCard
*       어플리케이션 껏다 켜도 PasswardCard 유지되게 internal storage 에 txt 파일 생성해서 저장
*       이 함수가 호출되면 기존의 txt파일을 삭제시고 현재 PasswardCard Class의 container를
*       기준으로 텍스트파일을 새로 씀
*       맨첫줄은 패스워드카드 갯수임
*       단순하게 PasswardCard Property를 순차적으로 String으로 한줄한줄 입력함 (Log.i있음)
*
* (이태영) LoadPasswardCard
*       텍스트파일 1줄1줄 읽어서 텍스트파일 기준으로 CreatePasswardCard 해서 결과를
*       Vector<PasswardCard> 형태로 반환함
*       당연히 addView는 따로해줘야댐
* */

public class Main4Activity extends AppCompatActivity {
    static int passwardCardExpirationCounter = 0;
    public Activity currentActivity;

    private String connectedDeviceName;
    private BluetoothAdapter btAdapter = null;
    private BluetoothDevice btDevice = null;
    private BluetoothSocket btSocket = null;
    private OutputStream btos;
    private InputStream btis;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        // 변수 선언은 여기
        currentActivity=this;

        // 레이아웃 변수 선언 및 설정은 여기


        // 비밀번호 조회 화면 최상위 레이아웃
        final LinearLayout LL_PasswardInquiry = new LinearLayout(Main4Activity.this);
        LL_PasswardInquiry.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        LL_PasswardInquiry.setOrientation(LinearLayout.VERTICAL);
        LL_PasswardInquiry.setVisibility(View.VISIBLE);

        // 비밀번호 요청 화면 최상위 레이아웃
        final LinearLayout LL_PasswardRequest =  new LinearLayout(Main4Activity.this);
        LL_PasswardRequest.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        LL_PasswardRequest.setOrientation(LinearLayout.VERTICAL);
        LL_PasswardRequest.setVisibility(View.GONE);

        // FindViewById는 전부 여기서
        // 동적 뷰 배치 최상위 레이아웃
        LinearLayout LL_dynamicArea = (LinearLayout) findViewById(R.id.dynamicLinear);
        // 비밀번호 조회 화면으로 가는 버튼
        Button BTN_PasswardInquiry = (Button) findViewById(R.id.BTN_passwardInquiry);
        // 비밀번호 요청 화면으로 가는 버튼
        Button BTN_PasswardRequest = (Button) findViewById(R.id.BTN_passwardRequest);
        // 비밀번호 입력 화면으로 가는 버튼 (유니티 어플 실행되어야 함)
        Button BTN_PasswardInput = (Button) findViewById(R.id.BTN_passwardInput);


        // OTP 요청 시설 이름 적는 칸 (미완성, 드롭다운메뉴로 바꿔야함 )
        EditText ET_request_facility = new EditText(Main4Activity.this);
        ET_request_facility.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        ET_request_facility.setHint("시설 이름 입력");

        // OTP 요청 사유 적는 칸 (서버로 전송할지 말지는 아직 결정안함)
        EditText ET_request_reason = new EditText(Main4Activity.this);
        ET_request_reason.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        ET_request_reason.setHint("사유 입력");

        // OTP 요청 버튼 , 누르면 서버랑 통신해야댐
        Button BTN_request = new Button(Main4Activity.this);

        BTN_request.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        BTN_request.setText("요청 하기");

        // 비밀번호 조회 버튼, 누르면 현재 가지고 있는 PasswardCard 목록 나와야 함
        Button BTN_inquiry = new Button(Main4Activity.this);
        BTN_inquiry.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        BTN_inquiry.setText("조회 하기");

        // addView는 전부 여기서
        LL_PasswardRequest.addView(ET_request_facility);
        LL_PasswardRequest.addView(ET_request_reason);
        LL_PasswardRequest.addView(BTN_request);
        LL_PasswardInquiry.addView(BTN_inquiry);

        LL_dynamicArea.addView(LL_PasswardInquiry);
        LL_dynamicArea.addView(LL_PasswardRequest);

        LL_PasswardInquiry.addView(PasswardCard.CreatePasswardCard(Main4Activity.this,
                new PasswardCard(Facility.GOG101,
                        11111111,
                        5
                )));//D
        LL_PasswardInquiry.addView(PasswardCard.CreatePasswardCard(Main4Activity.this,
                new PasswardCard(Facility.GOG101,
                        11111111,
                        15
                )));//D
        LL_PasswardInquiry.addView(PasswardCard.CreatePasswardCard(Main4Activity.this,
                new PasswardCard(Facility.GOG101,
                        11111111,
                        20
                )));//D

        SavePasswardCard();//D

        // 이벤트리스너는 전부 여기 몰아서 배치
        BTN_PasswardInquiry.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(this.getClass().getName(),"유니티 실행합니다 전");
                Intent intent=new Intent(Main4Activity.this,UnityPlayerActivity.class);
                startActivity(intent);
                Log.i(this.getClass().getName(),"유니티 실행합니다 후 ");

            }
        });

        BTN_PasswardRequest.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                LL_PasswardInquiry.setVisibility(View.GONE);
                LL_PasswardRequest.setVisibility(View.VISIBLE);
            }
        });

        BTN_PasswardInput.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //여기 이벤트에 유니티 어플 실행
            }
        });
        BTN_inquiry.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                LL_PasswardRequest.setVisibility(View.GONE);
                LL_PasswardInquiry.setVisibility(View.VISIBLE);

            }
        });

//        // 모든 패스워드 유효기간을 1초씩 줄임
//        final Handler handler = new Handler(){
//            public void handleMessage(Message msg){
//                for (PasswardCard element:PasswardCard.getContainer()
//                ) {
//                    element.setExpiratoin(element.getExpiratoin()-1);
//                    element.getTV_expiration().setText(
//                            PasswardCard.expiratoinTranslate(element.getExpiratoin()));
//                }
//
//            }
//        };


//        TimerTask passwardCardExpirationCalculator = new TimerTask() {
//            @Override
//            public void run() {
//                Log.i("passwardCardExpirationCounter : ", passwardCardExpirationCounter+"\n");
//                passwardCardExpirationCounter++;
//
//                Message msg = handler.obtainMessage();
//                handler.sendMessage(msg);
//                PasswardCard.CheckExpiration();
//                SavePasswardCard();
///           }
//
//            @Override
//            public boolean cancel() {
//                Log.v("passwardCardExpirationCounter ","timer cancel");
//                return super.cancel();
//            }
//        };


        // 앱 초기화 종료 시점
        Vector<LinearLayout> LL_oldPasswardCards = LoadPasswardCard();
//        for (LinearLayout elements : LL_oldPasswardCards
//             ) {
//            LL_PasswardInquiry.addView(elements);
//        }
//        Timer timer = new Timer(true);
//        timer.schedule(passwardCardExpirationCalculator,0,1000);
    }
    public int CorrectPassward(){
        Toast.makeText(this,"Passward Correct",Toast.LENGTH_LONG).show();
        Log.i(this.getClass().getName(), "유니티에서 호출했습니다 CorrectPassward() "+"\n");
        return -1;
    }
    public int inCorrectPassward(){
        Toast.makeText(this,"Passward in Correct",Toast.LENGTH_LONG).show();
        Log.i(this.getClass().getName(), "유니티에서 호출했습니다  inCorrectPassward() "+"\n");
        return -1;
    }

    public static int getPassward2(){
        Log.i("유니티에서 호출했습니다", "유니티에서 호출했습니다  getPassward() "+"\n");
        return 1111;
    }

    private void SavePasswardCard() {

        String fileName = "/PasswardCardLog.txt";
        String filePath = getApplicationContext().getDataDir().getAbsolutePath() + fileName;
        File file = new File(filePath);
        if (file.delete()) {
            Log.i(this.getClass().getName(), filePath + "삭제 되었습니다");
        } else {
            Log.i(this.getClass().getName(), filePath + "삭제 되었습니다");
        }
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            FileOutputStream fos = new FileOutputStream(filePath, true);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            fw.write(Integer.toString(PasswardCard.getContainer().size())+"\n");
            fw.flush();
            Log.i(this.getClass().getName(),"파일 씀 (첫줄은 패스워드개수) "+Integer.toString(PasswardCard.getContainer().size()));
            for (PasswardCard element : PasswardCard.getContainer()
            ) {
                fw.write(Integer.toString(element.getFacilityNumber())+"\n");
                fw.flush();
                Log.i(this.getClass().getName(),"파일 씀"+Integer.toString(element.getFacilityNumber()));

                fw.write(Integer.toString(element.getPassward())+"\n");
                fw.flush();
                Log.i(this.getClass().getName(),"파일 씀"+Integer.toString(element.getPassward()));

                fw.write(Long.toString(element.getExpiratoin())+"\n");
                fw.flush();
                Log.i(this.getClass().getName(),"파일 씀"+Long.toString(element.getExpiratoin()));

                fw.write(Long.toString(element.getIssueDate())+"\n");
                fw.flush();
                Log.i(this.getClass().getName(),"파일 씀"+Long.toString(element.getIssueDate()));

            }
            Log.i(this.getClass().getName(), "파일 쓰기 성공");
        fos.close();
        writer.close();
        } catch (IOException ie) {
            ie.printStackTrace();
            Log.i(this.getClass().getName(), "파일 쓰기 예외 발생");
            }
    }
    private Vector<LinearLayout> LoadPasswardCard() {
        Vector<LinearLayout> result = new Vector<LinearLayout>();
        String fileName = "/PasswardCardLog.txt";
        String filePath = getApplicationContext().getDataDir().getAbsolutePath() + fileName;
        Vector<String> passwardcardElements = new Vector<String>();
        File file = new File(filePath);
        StringBuffer strBuffer = new StringBuffer();
        try{
            InputStream is = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line="";

            line=reader.readLine();
            strBuffer.append(line);
            int numOfPasswardCard = Integer.parseInt(strBuffer.toString());
            strBuffer = new StringBuffer();


            int index = 0;
            Log.i(this.getClass().getName(),"텍스트에 있던 패스워드 카드 수 : "+numOfPasswardCard);
            for(int i=0;i<(numOfPasswardCard+1)*4;i++){
                line=reader.readLine();
                strBuffer.append(line);

                passwardcardElements.add(strBuffer.toString());


                if(index==4) {
                    index = 0;
                    result.add(
                            PasswardCard.CreatePasswardCard
                                    (Main4Activity.this, new PasswardCard
                                            (Integer.parseInt(passwardcardElements.get(i-4)),
                                                    Integer.parseInt(passwardcardElements.get(i-3)),
                                                    Long.parseLong(passwardcardElements.get(i-2)),
                                                    Long.parseLong(passwardcardElements.get(i-1)))
                                    )
                    );
                    Log.i(this.getClass().getName(),
                            "시설 "+Integer.parseInt(passwardcardElements.get(i-4))+
                            "비번 "+Integer.parseInt(passwardcardElements.get(i-3))+
                            "기간 "+Long.parseLong(passwardcardElements.get(i-2))+
                            "발행 "+Long.parseLong(passwardcardElements.get(i-1)));
                    Log.i(this.getClass().getName(),"result사이즈 : "+result.size());
                }

                index++;


                Log.i(this.getClass().getName(),"파일 읽음"+strBuffer.toString());
                strBuffer = new StringBuffer();

            }
            is.close();
            reader.close();
            Log.i(this.getClass().getName(),"파일 읽음");
        } catch (Exception e) {
            Log.i(this.getClass().getName(),"Exception 발생, 계층 처리 안했음 ");
            Log.i(this.getClass().getName(),"파일 읽기 실패");
        }
        return result;
    }
}