package com.kv.strings;

/**
 * @author karanverma
 */
public class StringPermutation {

    public static void main(String[] args) {

        System.out.println("\n Method 2 String permutations  = ");
        printPermutn("abce", "");

        System.out.println("\n Method 3 String permutations unique strings= ");
        printDistinctPermutn("abce", "");

    }


    // method 2
    static void printPermutn(String str, String ans) {

        if (str.length() == 0) {
            System.out.print(ans + " ");
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            // Rest of the string after excluding the ith character
            String ros = str.substring(0, i) + str.substring(i + 1);

            // Recurvise call
            printPermutn(ros, ans + ch);
        }
    }


    // -----------Method 3 unique string ------------------
    static void printDistinctPermutn(String str, String ans) {
        if (str.length() == 0) {
            System.out.print(ans + " ");
            return;
        }

        // Make a boolean array of size '26' which stores false by default and make true
        // at the position which alphabet is being used
        boolean alpha[] = new boolean[26];

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            // Rest of the string after excluding the ith character
            String ros = str.substring(0, i) +
                    str.substring(i + 1);

            // If the character has not been used then recursive call will take place.
            // Otherwise, there will be no recursive call
            if (alpha[ch - 'a'] == false)
                printDistinctPermutn(ros, ans + ch);
            alpha[ch - 'a'] = true;
        }
    }

}
