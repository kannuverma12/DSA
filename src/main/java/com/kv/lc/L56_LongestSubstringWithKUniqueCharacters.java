package com.kv.lc;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the longest substring that contains only two unique characters.
 * For example, given "abcbbbbcccbdddadacb", the longest substring that contains
 * 2 unique character is "bcbbbbcccb"
 */
public class L56_LongestSubstringWithKUniqueCharacters {
    public static void main(String[] args) {
        System.out.println("Substring : "+lengthOfLongestSubstringTwoDistinct("abcbbbbcccbdddadacb", 2));
    }

    public static int lengthOfLongestSubstringTwoDistinct(String s, int k) {
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }

            if (map.size() > k) {
                max = Math.max(max, i - start);

                while (map.size() > 2) {
                    char t = s.charAt(start);
                    int count = map.get(t);
                    if (count > 1) {
                        map.put(t, count - 1);
                    } else {
                        map.remove(t);
                    }
                    start++;
                }
            }
        }

        max = Math.max(max, s.length() - start);

        return max;
    }
}
