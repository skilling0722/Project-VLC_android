package com.VlcDoorLock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Pwd_Register extends AppCompatActivity {
    EditText pwdVal, pwdVal2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd__register);
        pwdVal = findViewById(R.id.new_pwd_btn1);
        pwdVal2 = findViewById(R.id.new_pwd_btn2);
    }

    public void pwd_confirm(View v){
        String str= pwdVal.getText().toString();
        String str2 = pwdVal2.getText().toString();


        if(!str.equals(str2)){
            Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
        }
        else{
            //정규형 검사
            if(pwd_test(str)){
                Intent data = new Intent();
                data.putExtra("password", str_to_bin(str)); //2진수문자열
                setResult(0, data);
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(), "길이는 8~16, 문자와 숫자의 조합만 가능합니다.", Toast.LENGTH_LONG).show();
            }

        }
    }
    public Boolean pwd_test(String tmpPwd){
        Regular_test pwdRegular = new Regular_test();
        return pwdRegular.str_guess(tmpPwd);
    }
    //StringToBinary.java - 문자열을 2진수문자열로 만들기
    public String str_to_bin(String tmpStr){
        StringToBinary tmp = new StringToBinary();
        tmp.toBinary(tmpStr);
        return tmp.get();
    }
}
/*
* 뒤로가기할때 오류남
* 비번정보가 MainActivity에 가면 onCreate를 다시안하고 이전xml화면만 불러오는 것같음. 뒤로갔을때,
* 다시 MainActivity가 확인할 수 있도록 만들 필요성이 있음.
* */