package com.VlcDoorLock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Device_Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device__register);
    }
}
/*
* UUID - Chrome즐겨찾기 확인, 안드로이드 고유번호로 등록하도록 함.
* 기기등록메뉴는 phone ---> arduino로 신호를 보내는 것을 말한다.
* 이 Activity에서는 다음과 같이 수행해야 한다.
* 1. 기기등록 버튼을 누른다.
* 2. 아두이노의 센서에 갖다 댄다.
* 3. vlc로 데이터를 보낸다.
*
* 먼저 할 것은 uuid가져오기, 데이터 프레임 구성하기, 신호를 보내는 함수가 필요하다. 이것들은 비번등록등에서도
* 공통적으로 쓰여진다. 따라서 따로 java파일을 생성해서 클래스로 만들 필요성이 있따.
* */
