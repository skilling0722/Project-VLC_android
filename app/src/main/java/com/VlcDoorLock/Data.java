package com.VlcDoorLock;

public class Data {
    public String transmission_Data;

    public Data () {
        transmission_Data = "";
    }

    public void Set_data (String input) {
        this.transmission_Data = input;
    }

    //0 이 연속으로 5개 있다면 이후에 1 추가
    public void Check_data () {
        this.transmission_Data = transmission_Data.replace("00000", "000001");
    }
}

// 동시에 여러작업 22
//동시에 여러작업하기 1
//연우 수정//