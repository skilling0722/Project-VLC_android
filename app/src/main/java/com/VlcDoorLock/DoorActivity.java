package com.VlcDoorLock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DoorActivity extends AppCompatActivity  implements View.OnClickListener{
    String fileName = "info";
    SharedPreferences Shared_Pre;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initSharedPreferences();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

        try {
            //open_door_btn_resume(); <-가져와야함
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("오류", "open_door_btn_resume 호출 실패");
        }
    }
    private void initSharedPreferences(){
        Shared_Pre = getSharedPreferences(fileName, 0);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.open_door_btn:
                try {
                    open_door(v);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("오류", "open_door 호출 실패");
                }
                break;
            default:
                break;
        }
    }


    public void open_door(View v){
        /*
        device, pwd등록 검사 하고, 보내기
        * */

        Toast.makeText(getApplicationContext(), "open 실행", Toast.LENGTH_LONG).show();
        //shared에서 비번 가져오기
        String pwd_str = Shared_Pre.getString("password", "");
        String device_str = Shared_Pre.getString("device_info", "");

        Log.d("open_door[pwd]", pwd_str);
        Log.d("open_door[device]", device_str);
        Intent open_door_pwd_intent = new Intent(DoorActivity.this, Open_door.class);
        open_door_pwd_intent.putExtra("stored_pwd", pwd_str);
        open_door_pwd_intent.putExtra("stored_device_info", device_str);
        startActivity(open_door_pwd_intent);


    }
}
