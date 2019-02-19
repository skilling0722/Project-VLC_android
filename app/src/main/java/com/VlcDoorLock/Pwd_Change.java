package com.VlcDoorLock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Pwd_Change extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd__change);
    }
}

/*
* 기존 비밀번호를 검사하고
* 새 비밀번호의 정규규칙검사하고
* 변경으로 마무리해야함.
*
* SharedPreferences의 "Myfiles"에서 확인
* 보니까.... Pwd_Register메소드들 따로 구성해야할듯하다. JavaActivity에 넣기엔 중복이 포함됨. 그냥 java에 넣어야 될듯??
* */