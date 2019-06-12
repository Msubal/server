package com.example.prototype;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.text.Layout;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

/*
* PasswardCard Class
* 작성자 : 이태영
* Version : 1.11
* Last Update : 3.23
*
* 현재 Client가 가지고 있는 비밀번호의 목록 (다시말해 열 수 있는 시설) 관련 Class
*
*
* Property
*   facilityNumber 시설이름
*   passward   비밀번호
*   issueDate 해당 비밀번호를 받은 시점 (System.currentTimeMillis();)
*   expiration 남은 유효기간(초)
*   Continaer Client가 가지고 있는 유효한 패스워드카드
*
* Method
*   CreatePasswardCard 위의 프로퍼티로 패스워드 카드 생성 패스워드 카드는 하나의 리니어 레이아웃이다.
*   이 리니어 레이아웃은 MainActivity의 LL_PasswardInquiry 에 하나씩 추가된다
*   패스워드 카드를 꾸밀려면 여기서 해야한다
*   패스워드 카드를 만들면 자동으로 Contianer에 추가한다
*
*   facilityNameTranslate FacilityNumber는 정해진 방식을 따른 5개의 정수열이다. 이것을 적절한
*       출력의 형태로 바꿔서 반환하는 메서드이다.
*
*   expiratoinTranslate 마찬가지로 expiratoin을 적절한 출력의 형태로 바꿔서 변환한다.
*
*   <아직미구현>
*   CheckExpiration 유효기간이 만료된 패스워드카드는 삭제한다
*
*
*
* */

public class PasswardCard {
    private int facilityNumber;
    private int passward;
    private long expiratoin;
    private long issueDate;
    private TextView TV_expiration;


    private static LinearLayout.LayoutParams layoutParams;

    private static Vector<PasswardCard> Container = new Vector<PasswardCard>();

    private static final float FONT_SIZE = 20;

    public PasswardCard(int facilityNumber, int passward, long expiratoin){
        this.facilityNumber = facilityNumber;
        this.passward = passward;
        this.expiratoin = expiratoin;
        this.issueDate=System.currentTimeMillis()/1000;
        this.TV_expiration = null;
    }

    public PasswardCard(int facilityNumber, int passward, long expiratoin, long issueDate){
        this.facilityNumber = facilityNumber;
        this.passward = passward;
        this.expiratoin = expiratoin;
        this.issueDate= issueDate;
        this.TV_expiration = null;
    }


    public static LinearLayout CreatePasswardCard(Activity activity,PasswardCard passwardCard){

        layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        passwardCard.setTV_expiration(new TextView(activity));
        passwardCard.getTV_expiration().setText(
                expiratoinTranslate(passwardCard.expiratoin));

        passwardCard.getTV_expiration().setTextColor(Color.BLACK);
        passwardCard.getTV_expiration().setBackgroundColor(Color.BLUE);
        passwardCard.getTV_expiration().setTextSize(FONT_SIZE);
        passwardCard.getTV_expiration().setLayoutParams(layoutParams);

        TextView TV_fn = new TextView(activity);
        TV_fn.setText(
                facilityNameTranslate(passwardCard.facilityNumber));
        TV_fn.setTextColor(Color.BLACK);
        TV_fn.setBackgroundColor(Color.RED);
        TV_fn.setLayoutParams(layoutParams);
        TV_fn.setLayoutParams(layoutParams);


        TextView TV_pw = new TextView(activity);
        TV_pw.setText(Integer.toString(passwardCard.getPassward()));
        TV_pw.setTextColor(Color.BLACK);
        TV_pw.setBackgroundColor(Color.GREEN);
        TV_pw.setTextSize(FONT_SIZE);
        TV_pw.setLayoutParams(layoutParams);


        LinearLayout LL_passwardCard = new LinearLayout(activity);
        LL_passwardCard .setLayoutParams(layoutParams);
        LL_passwardCard .setOrientation(LinearLayout.HORIZONTAL);

        LL_passwardCard.addView(TV_fn);
        LL_passwardCard.addView(TV_pw);
        LL_passwardCard.addView(passwardCard.getTV_expiration());

        Container.add(passwardCard);


        return LL_passwardCard;
    }

    private static String facilityNameTranslate(int facilityNumber){

        String facilityNumberS = Integer.toString(facilityNumber);

        String name = new String();

        switch (facilityNumberS.charAt(0)){
            case '1':
                name = "공학관";
                break;
            case '2':
                name = "연구관";
                break;
            case '3':
                name = "미래관";
                break;

                default:
                    name = "정의되지 않은 건물 코드입니다";
                    break;
        }

        switch (facilityNumberS.charAt(1)){
            case '0':
                name += "  ";
                break;
            case '1':
                name += " B";
                break;
            default:
                name += "정의되지 않은 지상/지하 코드입니다";
                break;
        }

        name += facilityNumberS.charAt((2));
        name += facilityNumberS.charAt((3));
        name += facilityNumberS.charAt((4));

        return name+="호";
    }
    public static String expiratoinTranslate(long expiratoin){
        if(expiratoin>0) {
            return "유효기간 : " + (expiratoin / 3600) + "시간 " + (expiratoin % 3600 / 60) + "분 " + (expiratoin % 3600 % 60) + "초";
        }
        else return "유효기간 만료";
    }


    public int getPassward() {
        return passward;
    }
    public void setPassward(int passward) {
        this.passward = passward;
    }
    public long getExpiratoin() {
        return expiratoin;
    }
    public void setExpiratoin(long expiratoin) {
        this.expiratoin = expiratoin;
    }
    public int getFacilityNumber() {
        return facilityNumber;
    }
    public void setFacilityNumber(int facilityNumber) {
        this.facilityNumber = facilityNumber;
    }
    public long getIssueDate() {
        return issueDate;
    }
    public TextView getTV_expiration() {
        return TV_expiration;
    }
    public void setTV_expiration(TextView TV_expiration) {
        this.TV_expiration = TV_expiration;
    }
    public void setIssueDate(long issueDate) {
        this.issueDate = issueDate;
    }
    public static Vector<PasswardCard> getContainer() {
        return Container;
    }

    public static void setContainer(Vector<PasswardCard> container) {
        Container = container;
    }

    public static void CheckExpiration(){
        for (PasswardCard elements : Container
             ) {
            Container.remove(elements);
            break;
        }
    }
}

class Facility {
    public static final int MIR101  = 30101, MIR102  = 30102, MIR103  = 30103,
                     MIR201  = 30201, MIR202  = 30202, MIR203  = 30203,
                     MIR301  = 30301, MIR302  = 30302, MIR303  = 30303,
                     MIRB101 = 31101, MIRB102 = 31102, MIRB103 = 31103,
                     MIRB201 = 31201, MIRB202 = 31202, MIRB203 = 31203,
                     MIRB301 = 31301, MIRB302 = 31302, MIRB303 = 311303,
                     YON101  = 20101, YON102  = 20102, YON103  = 20103,
                     YON201  = 20201, YON202  = 20202, YON203  = 20203,
                     YON301  = 20301, YON302  = 20302, YON303  = 20303,
                     YONB101 = 21101, YONB102 = 21102, YONB103 = 21103,
                     YONB201 = 21201, YONB202 = 21202, YONB203 = 21203,
                     YONB301 = 21301, YONB302 = 21302, YONB303 = 21303,
                     GOG101  = 10101, GOG102  = 10102, GOG103  = 10103,
                     GOG201  = 10201, GOG202  = 10202, GOG203  = 10203,
                     GOG301  = 10301, GOG302  = 10302, GOG303  = 10303,
                     GOGB101 = 11101, GOGB102 = 11102, GOGB103 = 11103,
                     GOGB201 = 11201, GOGB202 = 11202, GOGB203 = 11203,
                     GOGB301 = 11301, GOGB302 = 11302, GOGB303 = 11303; }