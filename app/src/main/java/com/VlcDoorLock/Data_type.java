package com.VlcDoorLock;

public class Data_type {
    public String Data_type_code;

    public Data_type(){
        Data_type_code = "";
    }

    public void Set_device_registration() {
        Data_type_code = "001";
    }

    public void Set_password_change() {
        Data_type_code = "010";
    }

    public void Set_open_door() { Data_type_code = "011";}
}
