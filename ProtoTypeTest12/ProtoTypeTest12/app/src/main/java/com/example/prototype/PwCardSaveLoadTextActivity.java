package com.example.prototype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

/*
* MainActivity
* Version : 1.2
* 이태영 Last Update : 3.23
*
* 패스워드 저장/불러오기 테스트용 액티비티
* INPUT 누르면 파일에 쓰기
* INQUITY 누르면 파일 읽기
*
* */

public class PwCardSaveLoadTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // 레이아웃 관련 변수 선언 및 설정은 여기


        // 비밀번호 조회 화면 최상위 레이아웃
        final LinearLayout LL_PasswardInquiry = new LinearLayout(PwCardSaveLoadTextActivity.this);
        LL_PasswardInquiry.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        LL_PasswardInquiry.setOrientation(LinearLayout.VERTICAL);

        // 비밀번호 요청 화면 최상위 레이아웃
        final LinearLayout LL_PasswardRequest =  new LinearLayout(PwCardSaveLoadTextActivity.this);
        LL_PasswardRequest.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        LL_PasswardRequest.setOrientation(LinearLayout.VERTICAL);

        // FindViewById는 전부 여기서
        // 동적 뷰 배치 최상위 레이아웃
        LinearLayout LL_dynamicArea = (LinearLayout) findViewById(R.id.dynamicLinear);
        // 비밀번호 조회 화면으로 가는 버튼
        Button BTN_PasswardInquiry = (Button) findViewById(R.id.BTN_passwardInquiry);
        BTN_PasswardInquiry.setText("파일 읽기 및 패스워드 카드 레이아웃에 추가하기");
        // 비밀번호 요청 화면으로 가는 버튼
        Button BTN_PasswardRequest = (Button) findViewById(R.id.BTN_passwardRequest);
        // 비밀번호 입력 화면으로 가는 버튼
        Button BTN_PasswardInput = (Button) findViewById(R.id.BTN_passwardInput);
        BTN_PasswardInput.setText("패스워드카드 생성 및 파일에 쓰기");






        // OTP 요청 시설 이름 적는 칸 (미완성, 드롭다운메뉴로 바꿔야함 )
        EditText ET_request_facility = new EditText(PwCardSaveLoadTextActivity.this);
        ET_request_facility.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        ET_request_facility.setHint("시설 이름 입력");

        // OTP 요청 사유 적는 칸 (서버로 전송할지 말지는 아직 결정안함)
        EditText ET_request_reason = new EditText(PwCardSaveLoadTextActivity.this);
        ET_request_reason.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        ET_request_reason.setHint("사유 입력");

        // OTP 요청 버튼 , 누르면 서버랑 통신해야댐
        Button BTN_request = new Button(PwCardSaveLoadTextActivity.this);

        BTN_request.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        BTN_request.setText("요청 하기");

        // 비밀번호 조회 버튼, 누르면 현재 가지고 있는 PasswardCard 목록 나와야 함
        Button BTN_inquiry = new Button(PwCardSaveLoadTextActivity.this);
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

        LL_PasswardRequest.setVisibility(View.GONE);
        LL_PasswardInquiry.setVisibility(View.VISIBLE);

        // 이벤트리스너는 전부 여기 몰아서 배치
        BTN_PasswardInquiry.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vector<LinearLayout> bug_vLL = LoadPasswardCard();//D
                Log.i(this.getClass().getName(),"vLL사이즈 : "+bug_vLL.size());//D

                for (LinearLayout bug_LL_passwardCard:bug_vLL
                ) {
                    LL_PasswardInquiry.addView(bug_LL_passwardCard);
                }//D

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

                PasswardCard.CreatePasswardCard(PwCardSaveLoadTextActivity.this,
                        new PasswardCard(11111,11111111,3000));//D
                PasswardCard.CreatePasswardCard(PwCardSaveLoadTextActivity.this,
                        new PasswardCard(21112,11111112,4000));//D
                PasswardCard.CreatePasswardCard(PwCardSaveLoadTextActivity.this,
                        new PasswardCard(31111,11111113,5000));//D
                PasswardCard.CreatePasswardCard(PwCardSaveLoadTextActivity.this,
                        new PasswardCard(11222,11111114,6000));//D
                PasswardCard.CreatePasswardCard(PwCardSaveLoadTextActivity.this,
                        new PasswardCard(21333,11111115,7000));//D
                SavePasswardCard();




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
                                    (PwCardSaveLoadTextActivity.this, new PasswardCard
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