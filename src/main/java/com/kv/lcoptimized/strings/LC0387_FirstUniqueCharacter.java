package com.kv.lcoptimized.strings;

import com.kv.lcoptimized.common.Check;

/**
 * LC 387 · First Unique Character in a String (Easy)      [was: lc/FindFirstNonRepeatingChar]
 *
 * Pattern : two passes - count, then first index with count 1.
 * Optimal : O(n) time, O(alphabet) space.
 * Changed : the original called indexOf + lastIndexOf per char - O(n^2) - and lived entirely
 *           inside main().
 *
 * L6/L7 follow-up (common at Google): the characters arrive as a STREAM and you must answer
 * "first unique so far" after each one -> doubly linked list of unique chars + index map
 * (LinkedHashSet in Java) for O(1) per character. See firstUniqueInStream().
 */
public class LC0387_FirstUniqueCharacter {

    public static int firstUniqChar(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    /** For each prefix of the stream, the first non-repeating char so far ('#' if none). */
    public static String firstUniqueInStream(String stream) {
        java.util.LinkedHashSet<Character> unique = new java.util.LinkedHashSet<>();
        boolean[] seen = new boolean[128];
        StringBuilder out = new StringBuilder();
        for (char c : stream.toCharArray()) {
            if (!seen[c]) {
                seen[c] = true;
                unique.add(c);
            } else {
                unique.remove(c);
            }
            out.append(unique.isEmpty() ? '#' : unique.iterator().next());
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Check.eq(firstUniqChar("leetcode"), 0);
        Check.eq(firstUniqChar("loveleetcode"), 2);
        Check.eq(firstUniqChar("aabb"), -1);
        Check.eq(firstUniqueInStream("aabc"), "a#bb");
    }
}
