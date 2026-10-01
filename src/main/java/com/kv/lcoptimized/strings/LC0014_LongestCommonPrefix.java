package com.kv.lcoptimized.strings;

import com.kv.lcoptimized.common.Check;

/**
 * LC 14 · Longest Common Prefix (Easy)                    [was: lc/L69_LongestCommonPrefix]
 *
 * Pattern : vertical scan - compare column i across all strings; stop at first mismatch.
 * Optimal : O(S) where S = total characters examined (stops at the answer + 1 column).
 * Changed : kept the vertical scan only. The binary-search variant is O(S log m) - worse,
 *           and it built the prefix by string concatenation.
 *
 * L6/L7 follow-up: many LCP queries against a fixed set -> build a trie once; the answer
 * is the path until the first node with >1 child or an end-of-word marker.
 */
public class LC0014_LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String first = strs[0];
        for (int i = 0; i < first.length(); i++) {
            char c = first.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return first.substring(0, i);
                }
            }
        }
        return first;
    }

    public static void main(String[] args) {
        Check.eq(longestCommonPrefix(new String[] {"flower", "flow", "flight"}), "fl");
        Check.eq(longestCommonPrefix(new String[] {"dog", "racecar", "car"}), "");
        Check.eq(longestCommonPrefix(new String[] {"ab", "a"}), "a");
        Check.eq(longestCommonPrefix(new String[] {}), "");
    }
}
