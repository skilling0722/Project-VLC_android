package com.VlcDoorLock;


import android.bluetooth.BluetoothClass;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.concurrent.TimeUnit;


public class Flashlight{
    private Data_type Data_type;
    private Preamble preamble;
    private Exit exit;
    private Data Data;
    private String Device_info;

    private  boolean mFlashOn;
    private  String mCameraId;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void flashlight(CameraManager mCameraManager, String bin_Data, String bin_device_info,int type) {
        if (mCameraId == null) {
            try {
                for (String id : mCameraManager.getCameraIdList()) {
                    CameraCharacteristics c = mCameraManager.getCameraCharacteristics(id);
                    Boolean flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    Integer lensFacing = c.get(CameraCharacteristics.LENS_FACING);
                    if (flashAvailable != null && flashAvailable
                            && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                        mCameraId = id;
                        break;
                    }
                }
            } catch (CameraAccessException e) {
                mCameraId = null;
                e.printStackTrace();
                return;
            }
        }

        Log.d("테스트", "@@@@@@시작@@@@@@");

        /*
        온오프 딜레이
         */
        long blinkDelay = 100;

        /*
        Preamble.java
        Exit.java

        20190218 프리앰블, 종료코드  추가
        프리앰블 10000001
        종료 10000001
         */
        try {
            get_Preamble();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("오류", "get_Preamble 호출 실패");
        }


        try {
            get_Exit();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("오류", "get_Exit 호출 실패");
        }






        /*
        Data_type.java 파일

        20190218 데이터 종류 비트 추가
        3비트
        000 - 111
        기기 등록: 001
        비번 변경: 010
        비번 등록: 011
        문 열기  : 100
         */
        try {
            get_Data_type(type);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("오류", "get_Data_type 호출 실패");
        }

         /*
        기기 정보
         */
        try {
            get_Device_info(bin_device_info);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("오류", "get_Device_info 호출 실패");
        }


        /*
        Data.java

        20190218 송신 데이터 값 추가
        Data
        Data.transmission_Data

        20190218 송신 데이터 값 Bit stuffing
        Data.Check_data()
         */
        try {
            get_Data(bin_Data);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("오류", "get_Data 호출 실패");
        }

        /*
        송신 데이터 = 프리앰블 + 데이터 타입 결정 + 데이터 + 종료 코드
         */
        String Send_data = preamble.preamble_code + Data_type.Data_type_code + Device_info + Data.transmission_Data + exit.exit_code;

        Log.d("테스트", "송신 비트 체크: " + Send_data);


        for (int i = 0; i < Send_data.length(); i++) {
            if (Send_data.charAt(i) == '0') {
                mFlashOn = false;
            } else {
                mFlashOn = true;
            }

            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mCameraManager.setTorchMode(mCameraId, mFlashOn);
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

            try {
                TimeUnit.MILLISECONDS.sleep(blinkDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        //종료전 딜레이
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //종료
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mCameraId, false);
            }

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        Log.d("테스트", "@@@@@@종료@@@@@@");
    }

    private void get_Data_type(int type){
        Data_type = new Data_type();
        try{
            if (type == 0) {
                // 기기 등록 001
                Data_type.Set_device_registration();
            } else if ( type == 1 ) {
                //비번 변경 010
                Data_type.Set_password_change();
            } else if ( type == 2 ) {
                //비번 등록 011
                Data_type.Set_password_input();
            } else if ( type == 3 ){
                Data_type.Set_open_door();
            } else {
                Log.d("테스트", "데이터 종류 비트 확인 불가 " + type);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("테스트", "데이터 종류 비트 확인 에러");
        }

        Log.d("테스트", "데이터 타입 변수 체크: " + Data_type.Data_type_code);
    }

    private void get_Preamble() {
        preamble = new Preamble();

        Log.d("테스트", "프리앰블 변수 체크: " + preamble.preamble_code);
    }

    private  void get_Exit() {
        exit = new Exit();

        Log.d("테스트", "종료 변수 체크: " + exit.exit_code);
    }

    private void get_Device_info(String bin_device_info) {
        Device_info = bin_device_info;

        Log.d("테스트", "문열기 기기정보 체크: " + Device_info);
    }


    private void get_Data(String bin_Data) {
        Data = new Data();

        Data.Set_data(bin_Data);

        Log.d("테스트", "데이터              체크: " + Data.transmission_Data);

        /*
        Data.java

        20190218 송신 데이터 값 Bit stuffing
        Data.Check_data()
         */
        Data.Check_data();

        Log.d("테스트", "bit stuffing 적용 데이터: " + Data.transmission_Data);
    }
}
