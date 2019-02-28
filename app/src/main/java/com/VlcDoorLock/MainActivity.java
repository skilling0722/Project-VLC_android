package com.VlcDoorLock;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.phone_btn:
                try {
                    phoneMenuExecute(v);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("오류", "phoneMenuExecute 호출 실패");
                }
                break;
            case R.id.door_btn:
                try {
                    doorMenuExecute(v);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("오류", "doorMenuExecute 호출 실패");
                }
                break;
            default:
                break;
        }
    }
    private void phoneMenuExecute(View v) {
        Intent intent= new Intent(MainActivity.this,PhoneActivity.class);
        startActivity(intent);
    }
    private void doorMenuExecute(View v) {
        Intent intent= new Intent(MainActivity.this,DoorActivity.class);
        startActivity(intent);
    }

    /*이거 why?*/
    private void delayedFinish() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);
    }
}
/*
1. prograssBar 비동기식으로 처리(asyn ...?)
2. 기기등록 후의 화면, 설정화면, 메뉴밑에 알림문자고정(알약처럼)
3. 후레쉬 보내기 기능, 카메라로 인식
4. 비번저장, 자동로그인 (SharedPreference): 비번창에서 데이터를 받아 shared에 저장하도록 코딩

- 기기등록 시, 휴대폰이 대상에게 가 있는지 어케 암? (수동으로?)
- ui: 버튼모양이든지, 디자인부분
-
* */