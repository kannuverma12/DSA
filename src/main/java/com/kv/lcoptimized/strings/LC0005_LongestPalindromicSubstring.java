package com.kv.lcoptimized.strings;

import com.kv.lcoptimized.common.Check;

/**
 * LC 5 · Longest Palindromic Substring (Medium)           [was: lc/L43_LongestPalindromeSubstring]
 *
 * A) Expand around 2n-1 centres: O(n^2) time, O(1) space. This is what to code in an interview.
 * B) Manacher: O(n) time, O(n) space. Know the idea (reuse the mirror radius inside the
 *    rightmost palindrome) and be able to sketch it; coding it is rarely required.
 * Changed : removed the duplicate O(n^2) variant that printed from inside the method.
 */
public class LC0005_LongestPalindromicSubstring {

    public static String longestPalindrome(String s) {
        int bestLo = 0;
        int bestLen = 0;
        for (int center = 0; center < 2 * s.length() - 1; center++) {
            int lo = center / 2;
            int hi = lo + center % 2;             // odd centre: lo == hi, even: hi = lo + 1
            while (lo >= 0 && hi < s.length() && s.charAt(lo) == s.charAt(hi)) {
                lo--;
                hi++;
            }
            if (hi - lo - 1 > bestLen) {
                bestLen = hi - lo - 1;
                bestLo = lo + 1;
            }
        }
        return s.substring(bestLo, bestLo + bestLen);
    }

    /** Manacher on "^#a#b#...#$"; radius[i] = palindrome radius centred at i in the padded string. */
    public static String manacher(String s) {
        if (s.isEmpty()) {
            return s;
        }
        char[] t = new char[2 * s.length() + 3];
        t[0] = '^';
        t[t.length - 1] = '$';
        for (int i = 0; i < s.length(); i++) {
            t[2 * i + 1] = '#';
            t[2 * i + 2] = s.charAt(i);
        }
        t[t.length - 2] = '#';
        int[] radius = new int[t.length];
        int center = 0;
        int right = 0;
        for (int i = 1; i < t.length - 1; i++) {
            if (i < right) {
                radius[i] = Math.min(right - i, radius[2 * center - i]);
            }
            while (t[i + radius[i] + 1] == t[i - radius[i] - 1]) {
                radius[i]++;
            }
            if (i + radius[i] > right) {
                center = i;
                right = i + radius[i];
            }
        }
        int best = 1;
        for (int i = 1; i < t.length - 1; i++) {
            if (radius[i] > radius[best]) {
                best = i;
            }
        }
        int start = (best - radius[best] - 1) / 2;
        return s.substring(start, start + radius[best]);
    }

    public static void main(String[] args) {
        Check.eq(longestPalindrome("babad"), "bab");
        Check.eq(longestPalindrome("cbbd"), "bb");
        Check.eq(longestPalindrome("a"), "a");
        Check.eq(longestPalindrome("aabaacbc"), "aabaa");
        Check.eq(manacher("babad"), "bab");
        Check.eq(manacher("cbbd"), "bb");
        Check.eq(manacher("forgeeksskeegfor"), "geeksskeeg");
    }
}
