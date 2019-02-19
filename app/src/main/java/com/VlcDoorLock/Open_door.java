package com.VlcDoorLock;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Open_door extends AppCompatActivity {
    private CameraManager mCameraManager;
    String stored_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_door);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(getApplicationContext(), "There is no camera flash.\n The app will finish", Toast.LENGTH_LONG);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 3000);
            return;
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mCameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        }

        try{
            Intent intent = getIntent();
            stored_pwd = intent.getStringExtra("stored_pwd");
            Log.d("테스트", "저장된 비밀번호" + stored_pwd);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("오류", "stored_pwd 가져오기 실패");
        }

        try{
            Flashlight flashlight = new Flashlight();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                flashlight.flashlight(mCameraManager, stored_pwd, 3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("오류", "문 열기 플래시라이트 조작 실패");
        }

    }
}
