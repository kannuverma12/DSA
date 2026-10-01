package com.kv.lcoptimized.strings;

import com.kv.lcoptimized.common.Check;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 242 · Valid Anagram (Easy)                           [was: lc/CheckIfAnagram]
 *
 * Pattern : one count array, +1 for s, -1 for t, all zero at the end.
 * Optimal : O(n) time, O(1) space for a fixed alphabet.
 * Changed : the original did indexOf + substring per character - O(n^2) time and O(n^2)
 *           garbage from string rebuilding.
 *
 * Follow-up asked on LeetCode itself: Unicode input -> use a HashMap<Integer,Integer> over
 * code points (s.codePoints()), not chars, so surrogate pairs aren't split.
 */
public class LC0242_ValidAnagram {

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnagramUnicode(String s, String t) {
        Map<Integer, Integer> count = new HashMap<>();
        s.codePoints().forEach(cp -> count.merge(cp, 1, Integer::sum));
        t.codePoints().forEach(cp -> count.merge(cp, -1, Integer::sum));
        return count.values().stream().allMatch(v -> v == 0);
    }

    public static void main(String[] args) {
        Check.isTrue(isAnagram("anagram", "nagaram"));
        Check.isTrue(!isAnagram("rat", "car"));
        Check.isTrue(isAnagram("abb", "bab"));
        Check.isTrue(isAnagramUnicode("héllo", "olléh"));
    }
}
