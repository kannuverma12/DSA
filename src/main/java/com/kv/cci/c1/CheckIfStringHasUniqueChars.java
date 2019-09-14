package com.kv.cci.c1;

import java.util.HashMap;
import java.util.Map;

public class CheckIfStringHasUniqueChars {

    public static void main(String[] args) {
        System.out.println("is Unique ? "+checkIfStringHasUniqueChars("ram"));

        System.out.println("is Unique 2 ? "+checkIfStringHasUniqueCharsWithoutExtraSpace("ram"));
    }

    static boolean checkIfStringHasUniqueChars(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i=0; i<s.toCharArray().length; i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                return false;
            } else {
                map.put(ch, 1);
            }
        }

        return true;
    }

    static boolean checkIfStringHasUniqueCharsWithoutExtraSpace(String s) {
        for (int i=0; i<s.toCharArray().length; i++) {
            int firstIndex = s.indexOf(s.charAt(i));
            int lastIndex = s.indexOf(s.charAt(i));
            if (firstIndex != lastIndex) {
                return false;
            }
        }
        return true;
    }

}
