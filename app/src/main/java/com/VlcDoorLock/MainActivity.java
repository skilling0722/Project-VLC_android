package com.VlcDoorLock;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String fileName = "myFile";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initSharedPreferences();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pwd_btn_resume();
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.device_btn:
                device_register(v);
                break;
            case R.id.pwd_btn:
                /*등록/변경 버튼활성화를 위한 부분*/
                if(loadPwdSharedPreferences()) pwd_change(v);
                else pwd_register(v);
                break;
            case R.id.setting_btn:
                setting(v);
                break;
            case R.id.open_door_btn:
                open_door(v);
                break;
            default:
                break;
        }
    }
    public void device_register(View v){
        Toast.makeText(getApplicationContext(), "기기등록중", Toast.LENGTH_LONG).show();
        //현재화면에서 로딩바를 띄움 -> 등록완료 -> 로딩바만 종료
        setContentView(R.layout.activity_device__register);
    }
    public void pwd_register(View v){
        //비번을 폰에 캐시로 저장되는 걸 말함
        Intent intent = new Intent(getApplicationContext(), Pwd_Register.class);
        startActivityForResult(intent, 100);
        //데이터 받아오면 shared에 저장
    }
    public void pwd_change(View v){
        //비번 변경버튼을 누르면 실행됨
        Intent intent = new Intent(getApplicationContext(), Pwd_Change.class);
        intent.putExtra("prePwd", valuePwdSharedPreferences()); //기존 비밀번호 보낸다.
        startActivityForResult(intent, 111);
    }

    public void setting(View v){
        /*
        * 세팅Activity열기
        * 알림고정, 자동로그인 10데이터 받아오기
        * */
        Toast.makeText(getApplicationContext(), "setting 실행", Toast.LENGTH_LONG).show();

    }

    public void open_door(View v){
        /*
        device, pwd등록 검사 하고, 보내기
        * */
        Toast.makeText(getApplicationContext(), "open 실행", Toast.LENGTH_LONG).show();


    }
    //shared에 값을 집어넣는다. 화면을 갱신한다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //100, 111
        if(resultCode == RESULT_OK){
            SharedPreferences.Editor editor = sp.edit();
            switch(requestCode){
                case 100: case 111:
                    Toast.makeText(getApplicationContext(), "RESULT_OK 성공", Toast.LENGTH_SHORT).show();
                    String pwdInfo = data.getStringExtra("password");
                    editor.putString("password", pwdInfo);
                    editor.commit();
                    pwd_btn_resume();
                    break;

            }
        }
        else{
            Toast.makeText(getApplicationContext(), "RESULT_OK 실패", Toast.LENGTH_SHORT).show();
        }
    }

    private void initSharedPreferences(){
        sp = getSharedPreferences(fileName, 0);
    }
    private Boolean loadPwdSharedPreferences(){
        String str = sp.getString("password", "");
        if (str.equals("")) return false;
        else return true;
    }
    private String valuePwdSharedPreferences(){
        String str = sp.getString("password", "");
        if (str.equals("")) return "";
        else return str;
    }
    public void pwd_btn_resume(){
        Button btn = findViewById(R.id.pwd_btn);
        if( loadPwdSharedPreferences() ){
            //비번변경
            btn.setText("비번변경");
        }
        else{
            //비번등록
            btn.setText("비번등록");
        }
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