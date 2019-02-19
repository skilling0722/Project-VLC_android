package com.VlcDoorLock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Pwd_Change extends AppCompatActivity {
    EditText pwdVal, pwdVal2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd__change);

        pwdVal = findViewById(R.id.pre_pwd_btn);
        pwdVal2 = findViewById(R.id.new_pwd_btn);
    }

    public void pwd_confirm(View v){
        String str= pwdVal.getText().toString();
        String str2 = pwdVal2.getText().toString();

        if (!pre_pwd_confirm(str)) {
            Toast.makeText(getApplicationContext(), "기존 비밀번호가 틀렸습니다.", Toast.LENGTH_LONG).show();
        }
        else{
            //정규형 검사
            if(pwd_test(str2)){
                Intent data = new Intent();
                data.putExtra("password", str_to_bin(str2)); //2진수문자열
                setResult(RESULT_OK, data);
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
    public String str_to_bin(String tmpStr){
        StringToBinary tmp = new StringToBinary();
        tmp.toBinary(tmpStr);
        return tmp.get();
    }

    public Boolean pre_pwd_confirm(String str){
        //MainAcitivity에서 가져오기
        Intent intent = getIntent();
        String prePwd = intent.getStringExtra("prePwd");
        str = str_to_bin(str);
        return str.equals(prePwd);
    }

}

/*
* 1. 기존 비밀번호를 검사하고
* 2. 새 비밀번호의 정규규칙검사하고
* 3. 변경으로 마무리해야함.
*
* SharedPreferences의 "Myfiles"에서 확인
* 보니까.... Pwd_Register메소드들 따로 구성해야할듯하다. JavaActivity에 넣기엔 중복이 포함됨. 그냥 java에 넣어야 될듯??




* SharedPreferences클래스를 따로 만들어서 하나만 생성되게 해야함 xx 안됨
* 상수를 저장하는 xml, java를 만들자 그냥.
*
* */