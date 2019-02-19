package com.VlcDoorLock;

public class Preamble {
    public String preamble_code;

    public Preamble () {
        preamble_code = "10000001";
    }

    public void Set_Preamble(String input) {
        preamble_code = input;
    }
}
