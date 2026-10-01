package com.kv.lcoptimized.slidingwindow;

import com.kv.lcoptimized.common.Check;

/**
 * LC 76 · Minimum Window Substring (Hard)                 [NEW - canonical variable-window problem]
 *
 * Pattern : expand right until the window covers t, then shrink left while it still covers.
 *           `missing` = how many chars of t are still uncovered, so the check is O(1).
 * Optimal : O(|s| + |t|) time, O(alphabet) space.
 *
 * Template (memorise; it covers most "shortest/longest window such that ..." questions):
 *   for right in s: add s[right]
 *       while window valid: record answer; remove s[left++]
 *
 * L6/L7 follow-ups: t has duplicates (handled by counts); must preserve order of t -> that's
 * LC 727 Minimum Window Subsequence, a DP/two-pointer problem, not this one.
 */
public class LC0076_MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        int[] need = new int[128];
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        int missing = t.length();
        int bestStart = 0;
        int bestLen = Integer.MAX_VALUE;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            if (need[s.charAt(right)]-- > 0) {
                missing--;
            }
            while (missing == 0) {
                if (right - left + 1 < bestLen) {
                    bestLen = right - left + 1;
                    bestStart = left;
                }
                if (++need[s.charAt(left++)] > 0) {
                    missing++;
                }
            }
        }
        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + bestLen);
    }

    public static void main(String[] args) {
        Check.eq(minWindow("ADOBECODEBANC", "ABC"), "BANC");
        Check.eq(minWindow("a", "a"), "a");
        Check.eq(minWindow("a", "aa"), "");
    }
}
