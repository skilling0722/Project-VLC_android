package com.VlcDoorLock;
/*
* 스트링->바이너리문자열로
* 바이너리->스트링문자열로 복원
* */
public class StringToBinary {
    String str;
    String bin;
    public StringToBinary(){
        str = "";
        bin = "";

    }
    public void set(String _str){
        this.str = _str;
    }
    public String get(){
        return this.bin;
    }
    public void toBinary(String _str) {
        this.set(_str);
        byte[] ascii_bytes = this.str.getBytes();
        StringBuilder bin = new StringBuilder();

        for (byte b : ascii_bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                if ((val & 128) == 0)
                    bin.append(0);
                else
                    bin.append(1);
                val <<= 1;
            }
            //bin.append(' ');
        }
        this.bin = bin.toString();
    }

}

/*
아두이노에 넣을 코드
public class BinarytoString {
   public static void main(String[] args)
   {
     //java solution
 String letters = "01001000 01100001 01110000 01110000 01111001 00100000 01000101 01100001 01110011 01110100 01100101 01110010 00100001";
 String s = " ";
 for(int index = 0; index < letters.length(); index+=9) {
     String temp = letters.substring(index, index+8);
     int num = Integer.parseInt(temp,2);
     char letter = (char) num;
     s = s+letter;
 }
     System.out.print(s); //  Happy Easter!
   }
 }
* */

