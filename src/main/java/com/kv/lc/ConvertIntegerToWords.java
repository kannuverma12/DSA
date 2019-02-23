package com.kv.lc;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author karanverma
 * 
 * Given an integer, print an English phrase that describes the integer
 *   (eg, "Two hundred and thirty four", “One Thousand, Two Hundred and Thirty Four”)
 *   
 *   Duplicate of NumberToEnglishPhrase.java
 *
 */
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
        
        // use this
        convertIntoWordsUsingRecursion(Long.parseLong(num));
    }
    
    private static String convertIntoWordsUsingRecursion(long number) {
        if (number == 0)
            return "zero";
        if (number < 0)
            return "minus " + convertIntoWordsUsingRecursion(Math.abs(number));
        String words = "";
        if ((number / 10000000) > 0) {
            words += convertIntoWordsUsingRecursion(number / 10000000) + " crores ";
            number %= 10000000;
        }
        if ((number / 100000) > 0) {
            words += convertIntoWordsUsingRecursion(number / 100000) + " lacs ";
            number %= 100000;
        }
        if ((number / 1000) > 0) {
            words += convertIntoWordsUsingRecursion(number / 1000) + " thousand ";
            number %= 1000;
        }
        if ((number / 100) > 0) {
            words += convertIntoWordsUsingRecursion(number / 100) + " hundred ";
            number %= 100;
        }
        if (number > 0) {
            if (number < 20)
                words += ones[(int) number];
            else {
                words += tens[(int) number / 10];
                if ((number % 10) > 0)
                    words += " " + ones[(int) number % 10];
            }
        }
        return words;
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

    // logic in block is not good practice 
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
