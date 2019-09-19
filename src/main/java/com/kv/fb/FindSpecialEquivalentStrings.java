package com.kv.fb;

import java.util.ArrayList;


/**
 * 
 * @author karanverma
 *
 *  Given an array of lower case strings, the task is to find the number of strings that are special equivalent. 
 *  Two strings are special equivalent if they can be made equivalent by performing some operations 
 *  on one or both string 
 *  
 *  swapEven : swap a character at an even-numbered index with a character at another even-numbered index 
 *  swapOdd : swap a character at an odd-numbered index with a character at another odd-numbered index 
 *  
 *  Input : arr = {"abcd", "cbad", "bacd"} 
 *  Output : 2 
 *  Explanation : The 2nd string can be converted to the 1st by swapping the first and third characters. 
 *                So there are 2 distinct strings as the third string cannot be converted to the first. 
 *                
 *  string input[] = {"abcd", "acbd", "adcb", "cdba", "bcda", "badc"}; 
 *  ans =4
 */
public class FindSpecialEquivalentStrings {

    public static void main(String[] args) {

        // test set
        // String[] arr = {"abcd", "cbad", "bacd"};
        String[] arr = { "abcd", "acbd", "adcb", "cdba", "bcda", "badc" };

        // convert ArrayList form as it is beneficial to handle
        ArrayList<String> input = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            input.add(i, arr[i]);
        }

        // main loop
        for (int i = 0; i < input.size() - 1; i++) {

            for (int j = i + 1; j < input.size(); j++) {

                // Question here: any possibility that identical strings exist? (assuming no for
                // now)

                // check with swapEven
                if (input.get(i).equals(swapEven(input.get(j)))) {
                    // remove if equivalent
                    input.remove(j);
                    j--;
                    // go back to loop
                    continue;
                }

                // check with swapOdd
                if (input.get(i).equals(swapOdd(input.get(j)))) {
                    // remove if equivalent
                    input.remove(j);
                    j--;
                    // go back to loop (no need as this is end of loop)
                    // continue;
                }
            }
        }

        System.out.println("length: " + input.size());
    }

    // swap a character at even-numbered index assuming string length is 4
    static String swapEven(String in) {
        char data[] = { in.charAt(2), in.charAt(1), in.charAt(0), in.charAt(3) };
        String out = new String(data);
        return out;
    }

    // swap a character at odd-numbered index assuming string length is 4
    static String swapOdd(String in) {
        char data[] = { in.charAt(0), in.charAt(3), in.charAt(2), in.charAt(1) };
        String out = new String(data);
        return out;
    }
}
