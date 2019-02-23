package com.kv.fb;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author karanverma
 *
 *  Given an integer, print an English phrase that describes the integer
 *   (eg, "Two hundred and thirty four", “One Thousand, Two Hundred and Thirty Four”)
 *   
 *   Duplicate of ConvertIntegerToWords.java
 */
public class NumberToEnglishPhrase {

    static Map<String, String> tenToNinteen = new HashMap<>();
    static Map<String, String> tens = new HashMap<>();
    static Map<String, String> digit = new HashMap<>();
    
    static String[] unitsMap = new String[] { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight",
            "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
            "eighteen", "nineteen" };
    static String[] tensMap = new String[] { "zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy",
            "eighty", "ninety" };
    
    public static void main(String[] args) {
        convertToWords();
        
        //use this method
        convertIntoWordsUsingRecursion(1111111);
        
        // Search for ConvertIntegerToWords.java as well for another answer
    }
    
    // Method 1 - Using recursion
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
                words += unitsMap[(int) number];
            else {
                words += tensMap[(int) number / 10];
                if ((number % 10) > 0)
                    words += " " + unitsMap[(int) number % 10];
            }
        }
        return words;
    }

    // Method 2
    public static void convertToWords() {
        int n = 12333228;
        System.out.print(n);
        if (n <= 0) {
            System.out.println("Enter numbers greater than 0");
        } else {
            NumberToEnglishPhrase a = new NumberToEnglishPhrase();
            a.pw((n / 1000000000), " Hundred");
            a.pw((n / 10000000) % 100, " crore");
            a.pw(((n / 100000) % 100), " lakh");
            a.pw(((n / 1000) % 100), " thousand");
            a.pw(((n / 100) % 10), " hundred");
            a.pw((n % 100), " ");
        }
    }
    
    public void pw(int n, String ch) {
        String one[] = unitsMap;
        String ten[] = tensMap;

        if (n > 19) {
            System.out.print(ten[n / 10] + " " + one[n % 10]);
        } else {
            System.out.print(one[n]);
        }
        if (n > 0)
            System.out.print(ch);
    }
    
}