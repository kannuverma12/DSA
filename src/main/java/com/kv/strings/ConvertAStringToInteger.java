package com.kv.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertAStringToInteger {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(myAtoi("-13+"));
        
        //System.out.println(toInt("-5-gd"));
    }
    
    public static int myAtoi(String str) {
        str = str.trim();
        String[] strArr = str.split(" ");
        String toParse = strArr[0];
        String ret = "";
        boolean isNeg = false;
        int i = 0; 
        int id = 0;
        if(toParse.matches("[-0-9a-zA-Z]+")) {
            System.out.println("alphanumeric");
            if(toParse.charAt(0) == '-') {
                isNeg = true;
                i++;
            }
            
            for(; i<toParse.length(); i++) {
                
                System.out.println("ch = "+toParse.charAt(i));
                
                if(!Character.isDigit(toParse.charAt(i))) {
                    id = i;
                    break;
                }
//                if(Character.isDigit(toParse.charAt(i))) {
//                    ret += toParse.charAt(i);
//                    id = i;
//                }
                    
//                if(Character.isLetter(toParse.charAt(i)))
//                else
//                    break;
            }
            //ret = toParse.substring(0, id);
        }else {
            System.out.println("not alphanumeric");
            //ret = strArr[0];
        }
        
        try{
            
            if(isNeg) {
                ret = toParse.substring(1, id);
                ret = "-" + ret;
            }else
                ret = toParse.substring(0, id);
            Double in = Double.parseDouble(ret);
            System.out.println("ret = "+ret);
            if(in < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            if(in > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            return in.intValue();
        }catch(Exception e){
            return 0;
        }
    }
    
    public static boolean checkPatern(String ch) {
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(ch);
        return m.find();
    }
    
    public static int toInt(String str) {
        int i = 0;
        int num = 0;
        boolean isNeg = false;

        //Check for negative sign; if it's there, set the isNeg flag
        if (str.charAt(0) == '-') {
            isNeg = true;
            i = 1;
        }

        //Process each character of the string;
        while( i < str.length()) {
            num *= 10;
            num += str.charAt(i++) - '0'; //Minus the ASCII code of '0' to get the value of the charAt(i++).
        }

        if (isNeg)
            num = -num;
        return num;
    }

}
