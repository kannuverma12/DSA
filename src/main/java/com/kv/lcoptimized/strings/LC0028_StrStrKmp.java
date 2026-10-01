package com.kv.lcoptimized.strings;

import com.kv.lcoptimized.common.Check;

/**
 * LC 28 · Find the Index of the First Occurrence (Easy)   [was: lc/L74_implementStrStr]
 *
 * A) KMP : O(n + m) worst case, O(m) space. The failure function lps[i] = length of the longest
 *          proper prefix of needle[0..i] that is also a suffix.
 * B) Rabin-Karp : rolling hash, O(n + m) expected; generalises to multi-pattern / 2D matching
 *          and to "longest duplicate substring" (LC 1044) with binary search.
 * Changed : BUG FIX - the original prefix table did next[i] = next[i-1] + 1 after falling back,
 *           instead of index + 1 (wrong for needles like "aabaaab"), and the search loop
 *           restarted comparisons from j = 0, so it wasn't KMP.
 */
public class LC0028_StrStrKmp {

    public static int strStr(String haystack, String needle) {
        int m = needle.length();
        if (m == 0) {
            return 0;
        }
        int[] lps = prefixFunction(needle);
        for (int i = 0, j = 0; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = lps[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    static int[] prefixFunction(String p) {
        int[] lps = new int[p.length()];
        for (int i = 1, len = 0; i < p.length(); i++) {
            while (len > 0 && p.charAt(i) != p.charAt(len)) {
                len = lps[len - 1];
            }
            if (p.charAt(i) == p.charAt(len)) {
                len++;
            }
            lps[i] = len;
        }
        return lps;
    }

    public static int rabinKarp(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if (m == 0) {
            return 0;
        }
        final long mod = 1_000_000_007L;
        final long base = 131;
        long pow = 1;
        long hNeedle = 0;
        long hWindow = 0;
        for (int i = 0; i < m; i++) {
            pow = i == 0 ? 1 : pow * base % mod;
            hNeedle = (hNeedle * base + needle.charAt(i)) % mod;
        }
        for (int i = 0; i < n; i++) {
            if (i >= m) {                                       // drop haystack[i-m]
                hWindow = (hWindow - haystack.charAt(i - m) * pow % mod + mod) % mod;
            }
            hWindow = (hWindow * base + haystack.charAt(i)) % mod;
            if (i >= m - 1 && hWindow == hNeedle && haystack.startsWith(needle, i - m + 1)) {
                return i - m + 1;                               // verify to rule out collisions
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Check.eq(strStr("sadbutsad", "sad"), 0);
        Check.eq(strStr("leetcode", "leeto"), -1);
        Check.eq(strStr("aabaaabaaac", "aabaaac"), 4);
        Check.eq(prefixFunction("aabaaab"), new int[] {0, 1, 0, 1, 2, 2, 3});
        Check.eq(rabinKarp("sadbutsad", "but"), 3);
        Check.eq(rabinKarp("aabaaabaaac", "aabaaac"), 4);
        Check.eq(rabinKarp("abc", "d"), -1);
    }
}
