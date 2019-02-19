package com.VlcDoorLock;

import java.util.regex.*;

public class Regular_test{
    public static void main(String[] args){
        System.out.println("RegularTestLog:");
        Regular_test ex = new Regular_test();
        System.out.println(ex.str_guess("abc1A23abcbreererefar"));
    }
    String msg;
    String reg;
    public void set(String _msg){
        this.msg = _msg;
        this.reg = "[a-zA-Z0-9]{6,18}";
    }
    public Boolean get(){
        Boolean tf = (msg!=null) ? true:false;
        return tf;
    }
    public Boolean str_guess(String _msg){
        this.set(_msg);
        return str_guess();
    }
    public Boolean str_guess(){
        //this.target에 값이 없으면 종료
        if (!this.get()){
            return false;
        }
        //값이 있을때의 판단
        Pattern ptn = Pattern.compile(this.reg);
        Boolean tf = ptn.matcher(this.msg).matches();
        return tf;
    }
}