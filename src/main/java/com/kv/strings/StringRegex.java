package com.kv.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringRegex {

    public static void main(String[] args) {
        String str = "Yellow & Black)";
        
        String str1 = "{\"statusCode:\":\"R0006\",\"statusMessage:\":\"Some Error Occured\"}";
        System.out.println(str1.replaceAll("[a-z:]", "[a-z]"));
        
        String[] strArr = str.split(" ");
        for(String s: strArr) {
            //System.out.println(s+" has special chars "+(s.indexOf("[^A-Za-z0-9]")>=0)); 
            Pattern p = Pattern.compile("[^A-Za-z0-9]");
            Matcher m = p.matcher(s);
           // boolean b = m.matches();
            boolean b = m.find();
            System.out.print(" string "+s);
        
            if (b == true)
               System.out.println(" There is a special character in my string ");
            else
                System.out.println(" There is no special char.");
            s = s.replaceAll("[^A-Za-z0-9]", "");
            
            System.out.println(" After replacement >"+s+"<");
        }
        
        String kk = "karan verma";
        kk = kk.replace("my", "your");
        System.out.println("kk = "+kk);
        
    }

}
