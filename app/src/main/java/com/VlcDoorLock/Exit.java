package com.VlcDoorLock;

public class Exit {
    public String exit_code;

    public Exit () {
        exit_code = "10000001";
    }

    public void Set_Exit(String input) {
        exit_code = input;
    }
}
