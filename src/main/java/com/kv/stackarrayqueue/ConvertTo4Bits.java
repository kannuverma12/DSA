package com.kv.stackarrayqueue;

public class ConvertTo4Bits {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        byte bytes[] = new byte[] { 12 };
        String sbytes = getRealBinary(bytes);
        System.out.println(sbytes);
        System.out.println(sbytes.substring(4, 8));
    }

    public static String getRealBinary(byte[] input) {
        StringBuilder sb = new StringBuilder();

        for (byte c : input) {
            for (int n = 128; n > 0; n >>= 1) {
                if ((c & n) == 0)
                    sb.append('0');
                else
                    sb.append('1');
            }
        }

        return sb.toString();
    }

}
