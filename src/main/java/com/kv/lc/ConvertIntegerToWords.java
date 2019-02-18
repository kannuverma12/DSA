package com.kv.lc;

import java.util.HashMap;
import java.util.Map;

public class ConvertIntegerToWords {

    

    static int CRORE = 10000000;
    static int LAKH = 100000;
    static int THOUSAND = 1000;
    static int HUNDRED = 100;
    static int TEN = 10;
    
    static Map<Integer, String> map = new HashMap<Integer, String>();
    
    static String[] ones = {"zero", "one" , "two", "three", "four" , "five", "six", "seven" , "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "forteen", "fifteen", "sixteen", "seventeen", "eighteen", "ninteen","twenty"};

    static String[] tens = {"","","twenty", "thirty", "forty", "fifty" ,"sixty", "seventy", "eighty", "ninety"};    
    
    public static void main(String[] args) {
        
        String num = "10120000";
        parseNumbers(num);
    }

    private static String parseNumbers(String num) {

        Integer parsedNum = Integer.parseInt(num);
        
        int crore = parsedNum/CRORE;
        
        int croreModulo = parsedNum % CRORE;
        
        int lakh = croreModulo/LAKH;
        
        int lakhModulo = croreModulo%LAKH;
        
        int thousands = lakhModulo/THOUSAND;
        
        int thousandModulu = lakhModulo % THOUSAND;
        
        int hundreds = thousandModulu / HUNDRED;
        
        int hundredModulo = thousandModulu % HUNDRED;
        
        int tensp = hundredModulo /TEN;
        
        int tensModulo = hundredModulo % TEN;
        
        if(crore != 0) {
            System.out.print(convertToWords(crore) + " crore ");
        }
        
        if(lakh != 0) {
            System.out.print(convertToWords(lakh) + " lakh ");
        }
        
        if(thousands != 0) {
            System.out.print(convertToWords(thousands) + " thousand ");
        }
        
        if(hundreds != 0) {
            System.out.print(convertToWords(hundreds) + " hundred ");
        }
        
        if(tensp != 0) {
            System.out.print(tens[tensp]);
        }
        
        if(tensModulo != 0) {
            System.out.println(" "+convertToWords(tensModulo));
        }
        return null;
    }

    private static String convertToWords(int num) {
        String w = null;
        try {
            w = ones[num];
        }catch(ArrayIndexOutOfBoundsException e) {
            int tensPlace = num / 10;
            int onesPlace = num % 10;
            
            w = tens[tensPlace] + ones[onesPlace];
        }
        return w;
    }
}
