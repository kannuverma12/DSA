package com.kv.strings;

public class FormatRGBValue {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(formatRGB(12,23,44));
    }
    
    public static String formatRGB ( int r, int g, int b ) {
        return (toHex(r) + toHex(g) + toHex(b)).toUpperCase();
    }

    public static String toHex ( int c ) {
        String s = Integer.toHexString ( c );
        System.out.println("Hexcode for "+c+" is = "+ s);
        return ( s.length() == 1 ) ? "0" + s : s;
    }


}
