package com.kv.lc;

/**
 *
 */
public class L62_RemoveVowelFromAString {
    public static void main(String[] args) {
        System.out.println(removeVowels("karanverma".toLowerCase()));
    }

    private static String removeVowels(String s){

        return s.replaceAll("[aeiouAEIOU]", "");
    }
}
