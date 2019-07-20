package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Implement atoi to convert a string to an integer.
 *  Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below 
 *  and ask yourself what are the possible input cases.
 */
public class L38_ConvertStringToInteger {
    /*
     * 1. null or empty string
     * 2. white spaces
     * 3. +/- sign
     * 4. calculate real value
     * 5. handle min & max
     */

    public static void main(String[] args) {
        System.out.println("toInt method : "+toInt("-5.87-gd5"));
        
        System.out.println("atoi method : "+atoi("-5.78-gd"));
    }
    
    // Best Solution Leetcode solution |
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
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            num *= 10;
            num += str.charAt(i++) - '0'; // Minus the ASCII code of '0' to get the value of the charAt(i++).
        }

        if (isNeg)
            num = -num;
        return num;
    }
    
    // method 2
    public static int atoi(String str) {
        if (str == null || str.length() < 1)
            return 0;
     
        str = str.trim();
        char flag = '+';
     
        // check negative or positive
        int i = 0;
        if(str.length()>0) {
            if (str.charAt(0) == '-') {
                flag = '-';
                i++;
            } else if (str.charAt(0) == '+') {
                i++;
            }
        }
        // use double to store result
        double result = 0;
     
        // calculate value
        while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }
     
        if (flag == '-')
            result = -result;
     
        // handle max and min
        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
     
        if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
     
        return (int) result;
    }
    
    
    
    
    
    static int kvImplementation(String str) {
        str = str.trim();
        String[] strArr = str.split(" ");
        String toParse = strArr[0];
        String ret = "";
        boolean isNeg = false;
        int i = 0; 
        if(toParse.matches("[-0-9a-zA-Z]+")) {
            //System.out.println("alphanumeric");
            if(toParse.charAt(0) == '-') {
                isNeg = true;
                i++;
            }
            
            for(; i<toParse.length(); i++) {
                
                
//                if(toParse.charAt(i) == '-' && i==0) {
//                    ret += toParse.charAt(i);
//                    continue;
//                }
                //System.out.println("ch = "+toParse.charAt(i));
                if(Character.isDigit(toParse.charAt(i)))
                    ret += toParse.charAt(i);
//                if(Character.isLetter(toParse.charAt(i)))
                else
                    break;
            }
        }else {
            //System.out.println("not alphanumeric");
            ret = strArr[0];
        }
        
        try{
            //System.out.println("ret = "+ret);
            if(isNeg)
                ret = "-" + ret;
            Double in = Double.parseDouble(ret);
            if(in < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            if(in > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            return in.intValue();
        }catch(Exception e){
            return 0;
        }
    }

}
