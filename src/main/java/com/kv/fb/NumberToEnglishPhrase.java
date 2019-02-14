package com.kv.fb;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


/**
 * 
 * @author karanverma
 *
 *  "Given an integer, print an English phrase that describes the integer
 *   (eg, "Two hundred and thirty four", “One Thousand, Two Hundred and Thirty Four”)
 */
public class NumberToEnglishPhrase {

    static Map<String, String> tenToNinteen = new HashMap<>();
    static Map<String, String> tens = new HashMap<>();
    static Map<String, String> digit = new HashMap<>();
    
    public static void main(String[] args) {
        int[] n = {512, 5123, 10, 9, 340};
        populateData();
        for(int x: n) 
            System.out.println(englishPhrase(x));
        
        convertToWords();
        
        convertIntoWordsUsingRecursion(1111111);
    }

    private static String englishPhrase(int n) {
        //special handling for smaller than 10
        if(n < 10) {
            return digit.get(n+"");
        }
        
        //special handling for numbers with 1 in tenth position
        Stack<String> stack = new Stack<>();
        String s = n+"";
        char[] digits = s.toCharArray();
        if(digits.length > 1) {
            if(digits[digits.length-2] == '1') {
                String lastTwo = new String(digits[digits.length-2]+"") + new String(digits[digits.length-1]+"");
                stack.push(tenToNinteen.get(lastTwo));
            } else {
                String last = digits[digits.length-1]+"";
                if(! last.equals("0")) {
                    stack.push(digit.get(last));
                }
                String secondLast = digits[digits.length-2]+"";
                int num = Integer.valueOf(secondLast)*10;
                stack.push(tens.get( num + ""));
            }
        }
        int x = 2;
        for(int i = digits.length - 3; i >=0; i--) {
            if(x == 2) stack.push("hundred and");
            if(x == 3) stack.push("thousand");
            stack.push(digit.get(digits[i]+""));
            x++;
        }
        
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty())
            sb.append(stack.pop() + " ");
        
        return sb.toString();
    }
    
    private static void populateData() {
        tenToNinteen.put("10", "ten");
        tenToNinteen.put("11", "eleven");
        tenToNinteen.put("12", "twelve");
        tenToNinteen.put("13", "thrteen");
        tenToNinteen.put("14", "forteen");
        tenToNinteen.put("15", "fifteen");
        tenToNinteen.put("16", "sixteen");
        tenToNinteen.put("17", "seventeen");
        tenToNinteen.put("18", "eighteen");
        tenToNinteen.put("19", "nineteen");
        
        
        tens.put("20", "twenty");
        tens.put("30", "thirty");
        tens.put("40", "forty");
        tens.put("50", "fifty");
        tens.put("60", "sixty");
        tens.put("70", "seventy");
        tens.put("80", "eighty");
        tens.put("90", "ninety");
        
        digit.put("1", "one");
        digit.put("2", "two");
        digit.put("3", "three");
        digit.put("4", "four");
        digit.put("5", "five");
        digit.put("6", "six");
        digit.put("7", "seven");
        digit.put("8", "eight");
        digit.put("9", "nine");
    }
    
    
    public void pw(int n, String ch)
    {
        String one[] = { " ", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine", " Ten",
                " Eleven", " Twelve", " Thirteen", " Fourteen", "Fifteen", " Sixteen", " Seventeen", " Eighteen",
                " Nineteen" };
 
        String ten[] = { " ", " ", " Twenty", " Thirty", " Forty", " Fifty", " Sixty", "Seventy", " Eighty", " Ninety" };
 
        if (n > 19)
        {
            System.out.print(ten[n / 10] + " " + one[n % 10]);
        }
        else
        {
            System.out.print(one[n]);
        }
        if (n > 0)
            System.out.print(ch);
    }
 
    public static void convertToWords()
    {
        
        int n=12333228;
        System.out.print(n);
        if (n <= 0)
        {
            System.out.println("Enter numbers greater than 0");
        }
        else
        {
            NumberToEnglishPhrase a = new NumberToEnglishPhrase();
            a.pw((n / 1000000000), " Hundred");
            a.pw((n / 10000000) % 100, " crore");
            a.pw(((n / 100000) % 100), " lakh");
            a.pw(((n / 1000) % 100), " thousand");
            a.pw(((n / 100) % 10), " hundred");
            a.pw((n % 100), " ");
        }
    }
    
    //Using recursion
    private static String convertIntoWordsUsingRecursion(long number)  
    {  
        if (number == 0)  
            return "zero";  
        if (number < 0)  
            return "minus " + convertIntoWordsUsingRecursion(Math.abs(number));  
        String words = "";  
        if ((number / 10000000) > 0)  
        {  
            words += convertIntoWordsUsingRecursion(number / 10000000) + " crores ";  
            number %= 10000000;  
        }  
        if ((number / 100000) > 0)  
        {  
            words += convertIntoWordsUsingRecursion(number / 100000) + " lacs ";  
            number %= 100000;  
        }  
        if ((number / 1000) > 0)  
        {  
            words += convertIntoWordsUsingRecursion(number / 1000) + " thousand ";  
            number %= 1000;  
        }  
        if ((number / 100) > 0)  
        {  
            words += convertIntoWordsUsingRecursion(number / 100) + " hundred ";  
            number %= 100;  
        }  
        if (number > 0)  
        {  
            String[] unitsMap = new String[] { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };  
            String[] tensMap = new String[] { "zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };  
            if (number < 20)  
                words += unitsMap[(int)number];  
            else  
            {  
                words += tensMap[(int)number / 10];  
                if ((number % 10) > 0)  
                    words += " " + unitsMap[(int)number % 10];  
            }  
        }  
        return words;  
    }

}
