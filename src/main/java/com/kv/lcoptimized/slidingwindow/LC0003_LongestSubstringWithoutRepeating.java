package com.kv.lcoptimized.slidingwindow;

import com.kv.lcoptimized.common.Check;

import java.util.Arrays;

/**
 * LC 3 · Longest Substring Without Repeating Characters (Medium)
 *                                                         [was: lc/medium/L42_LongestNonRepeatingSubstring]
 *
 * Pattern : sliding window; jump the left edge directly past the previous occurrence.
 * Optimal : O(n) time, O(alphabet) space.
 * Changed : int[128] last-seen table instead of HashMap<Character,Integer> (no boxing,
 *           much faster). Ask the interviewer about the charset; use a map for full Unicode.
 */
public class LC0003_LongestSubstringWithoutRepeating {

    public static int lengthOfLongestSubstring(String s) {
        int[] last = new int[128];
        Arrays.fill(last, -1);
        int best = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            left = Math.max(left, last[c] + 1);
            last[c] = right;
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        Check.eq(lengthOfLongestSubstring("abcabcbb"), 3);
        Check.eq(lengthOfLongestSubstring("bbbbb"), 1);
        Check.eq(lengthOfLongestSubstring("pwwkew"), 3);
        Check.eq(lengthOfLongestSubstring("abba"), 2);
        Check.eq(lengthOfLongestSubstring(""), 0);
    }
}
