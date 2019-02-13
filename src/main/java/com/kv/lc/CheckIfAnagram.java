package com.kv.lc;

public class CheckIfAnagram {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        boolean b = isAnagram("abb", "bab");
        System.out.println("Is Anagram  = " + b);
    }
    
    public static boolean isAnagram(String word, String anagram) {
        if (word.length() != anagram.length()) {
            return false;
        }
        char[] chars = word.toCharArray();
        for (char c : chars) {
            int index = anagram.indexOf(c);
            if (index != -1) {
                anagram = anagram.substring(0, index) + anagram.substring(index + 1, anagram.length());
            } else {
                return false;
            }
        }
        return anagram.isEmpty();
    }

}
