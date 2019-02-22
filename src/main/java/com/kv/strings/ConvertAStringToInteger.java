package com.kv.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author karanverma
 *
 */
public class ConvertAStringToInteger {

    public static void main(String[] args) {
        //System.out.println(myAtoi("-13+"));
        
        // Use this method
        System.out.println(toInt("-5-gd"));
    }
    
    // Leetcode solution
    public static int toInt(String str) {
        int i = 0;
        int num = 0;
        boolean isNeg = false;

        // Check for negative sign; if it's there, set the isNeg flag
        if (str.charAt(0) == '-') {
            isNeg = true;
            i = 1;
        }

        // Process each character of the string;
        while (i < str.length()) {
            num *= 10;
            num += str.charAt(i++) - '0'; // Minus the ASCII code of '0' to get the value of the charAt(i++).
        }

        if (isNeg)
            num = -num;
        return num;
    }

    // wrong solution
    public static int myAtoi(String str) {
        str = str.trim();
        String[] strArr = str.split(" ");
        String toParse = strArr[0];
        String ret = "";
        boolean isNeg = false;
        int i = 0;
        int id = 0;
        if (toParse.matches("[-0-9a-zA-Z]+")) {
            System.out.println("alphanumeric");
            if (toParse.charAt(0) == '-') {
                isNeg = true;
                i++;
            }

            for (; i < toParse.length(); i++) {
                System.out.println("ch = " + toParse.charAt(i));

                if (!Character.isDigit(toParse.charAt(i))) {
                    id = i;
                    break;
                }
            }
        } else {
            System.out.println("not alphanumeric");
        }

        try {

            if (isNeg) {
                ret = toParse.substring(1, id);
                ret = "-" + ret;
            } else
                ret = toParse.substring(0, id);
            Double in = Double.parseDouble(ret);
            System.out.println("ret = " + ret);
            if (in < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            if (in > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            return in.intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean checkPatern(String ch) {
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(ch);
        return m.find();
    }
    
    

}
