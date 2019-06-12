package com.example.prototype;

import com.unity3d.player.*;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class UnityPlayerActivity extends Activity
{
    protected UnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code
    Activity currentActivity;
    public static Intent intent;
    public static Intent intent2;

    private int id;
    private String password;
    private String name;
    int week;
    int building_num;
    int start_time;
    int end_time;
    int unity_pw ;



    // Setup activity layout
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        mUnityPlayer = new UnityPlayer(this);
        setContentView(mUnityPlayer);
        mUnityPlayer.requestFocus();
        currentActivity=this;
        intent = getIntent();
        id = intent.getExtras().getInt("userId");
        password = intent.getExtras().getString("userPw");
        name = intent.getExtras().getString("userNanme");
        unity_pw = intent.getExtras().getInt("unity_pw");
        start_time = intent.getExtras().getInt("start_time");
        end_time = intent.getExtras().getInt("end_time");
        week = intent.getExtras().getInt("day" +
                "");
        building_num = intent.getExtras().getInt("building_num");



//        Log.i("이전 액티비티 받아온 값!", "id"+id+"\n");
//        Log.i("이전 액티비티 받아온 값!", "pw"+password+"\n");

        //intent2 =new Intent(UnityPlayerActivity.this,BluetoothText2Activity.class);
        intent2 =new Intent(UnityPlayerActivity.this,Main5Activity.class);


    }

    @Override protected void onNewIntent(Intent intent)
    {
        // To support deep linking, we need to make sure that the client can get access to
        // the last sent intent. The clients access this through a JNI api that allows them
        // to get the intent set on launch. To update that after launch we have to manually
        // replace the intent with the one caught here.
        setIntent(intent);
    }




    // Quit Unity
    @Override protected void onDestroy ()
    {
        Log.i("씨 바아아아아알", "onDESTROY 시작"+"\n");
        Log.i("액티비티 보내기 대기..!", "id"+id+"\n");
        Log.i("액티비티 보내기 대기..!", "pw"+password+"\n");
        intent2.putExtra("userId",id);
        intent2.putExtra("userPw",password);
        intent2.putExtra("userName",name);
        intent2.putExtra("unity_pw",unity_pw);
        intent2.putExtra("start_time",start_time);
        intent2.putExtra("end_time",end_time);
        intent2.putExtra("day",week);
        intent2.putExtra("building_num",building_num);

        startActivity(intent2);
        mUnityPlayer.destroy();
        super.onDestroy();

        //UnityPlayerActivity.this.finish(); //Stop activity




        Log.i("씨 바아아아아알", "onDESTROY 종료"+"\n");

    }

    // Pause Unity
    @Override protected void onPause()
    {
        Log.i("씨 바아아아아알", "onPause"+"\n");
        super.onPause();
        mUnityPlayer.pause();

    }

    // Resume Unity
    @Override protected void onResume()
    {
        Log.i("씨 바아아아아알", "onResume"+"\n");
        super.onResume();
        mUnityPlayer.resume();
    }



    @Override protected void onStart()
    {
        Log.i("씨 바아아아아알", "onStart"+"\n");
        super.onStart();
        mUnityPlayer.start();
    }

    @Override protected void onStop()
    {
        Log.i("씨 바아아아아알", "onStop"+"\n");
        super.onStop();
        mUnityPlayer.stop();
    }

    // Low Memory Unity
    @Override public void onLowMemory()
    {
        super.onLowMemory();
        mUnityPlayer.lowMemory();
    }

    // Trim Memory Unity
    @Override public void onTrimMemory(int level)
    {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_RUNNING_CRITICAL)
        {
            mUnityPlayer.lowMemory();
        }
    }

    // This ensures the layout will be correct.
    @Override public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }

    // Notify Unity of the focus change.
    @Override public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    // For some reason the multiple keyevent type is not supported by the ndk.
    // Force event injection by overriding dispatchKeyEvent().
    @Override public boolean dispatchKeyEvent(KeyEvent event)
    {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return mUnityPlayer.injectEvent(event);
        return super.dispatchKeyEvent(event);
    }

    // Pass any events not handled by (unfocused) views straight to UnityPlayer
    @Override public boolean onKeyUp(int keyCode, KeyEvent event)     { return mUnityPlayer.injectEvent(event); }
    @Override public boolean onKeyDown(int keyCode, KeyEvent event)   { return mUnityPlayer.injectEvent(event); }
    @Override public boolean onTouchEvent(MotionEvent event)          { return mUnityPlayer.injectEvent(event); }
    /*API12*/ public boolean onGenericMotionEvent(MotionEvent event)  { return mUnityPlayer.injectEvent(event); }
}