package com.kv.strings;

import java.text.MessageFormat;

/**
 * 
 * @author karanverma
 *
 */
public class ReverseAString {

    public static void main(String[] args) {


        double t = 1000.0;


        String examFileName = String.valueOf(t);
        System.out.println("examFileName = "+examFileName);
        String examFinalName = examFileName.indexOf(".") > 0 ?
                examFileName.substring(0, examFileName.indexOf(".")) : examFileName;
        String ss = MessageFormat.format("exams_{0}.json",
                examFinalName);
        System.out.println("ss = "+ss);

        String sss = examFileName.split(".")[0];
        System.out.println("sss = "+sss);

        // TODO Auto-generated method stub
        System.out.println(reverse("mast am karan"));
    }
    
    public static String reverse ( String s ) {
        int length = s.length(), last = length - 1;
        char[] chars = s.toCharArray();
        for ( int i = 0; i < length/2; i++ ) {
            char c = chars[i];
            chars[i] = chars[last - i];
            chars[last - i] = c;
        }
        return new String(chars);
    }

}
