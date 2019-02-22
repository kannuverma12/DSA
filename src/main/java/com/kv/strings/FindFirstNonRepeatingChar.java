package com.kv.strings;

/**
 * 
 * @author karanverma
 *
 */
public class FindFirstNonRepeatingChar {
    
    public static void main(String[] args) {
        String s = "test";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int positionOfCharFromStarting = s.indexOf(c);
            int positionOfCharFromEnding = s.lastIndexOf(c);
            if (positionOfCharFromStarting == positionOfCharFromEnding) {
                System.out.println(c);
                break;
            }
        }
    }

}
