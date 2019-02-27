package com.VlcDoorLock;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Device_Register extends AppCompatActivity {
    String unique_id;
    String bin_unique_id;

    private CameraManager mCameraManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device__register);

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

        /*
        20190219

        단말정보 얻기
         */
        try{
            unique_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

            Log.d("테스트", "ID: " + unique_id);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("오류", "단말정보 얻기 실패");
        }


        /*
        20190219

        단말정보 이진화
         */

        try{
            bin_unique_id = str_to_bin(unique_id);
            Log.d("테스트", "              바이너리 ID: " + bin_unique_id);

            bin_unique_id = bin_unique_id.replace("00000", "000001");
            Log.d("테스트", "프리앰블 방지 바이너리 ID: " + bin_unique_id);

            Log.d("테스트", "응가" + bin_unique_id.length());
            /*추가 */
            Intent data = new Intent();
            data.putExtra("deviceInfo", bin_unique_id); //2진수문자열
            setResult(RESULT_OK, data);
            finish(); //종료

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("오류", "단말정보 이진화 실패");
        }

        /*
        20190219

        단말정보 담아서 보내기
         */
        /*
        try {
            Flashlight flashlight = new Flashlight();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                flashlight.flashlight(mCameraManager, bin_unique_id, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("오류", "기기등록 플래시라이트 실패");
        }
        */

    }

    public String str_to_bin(String tmpStr){
        StringToBinary tmp = new StringToBinary();
        tmp.toBinary(tmpStr);
        return tmp.get();
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
//branch checkout