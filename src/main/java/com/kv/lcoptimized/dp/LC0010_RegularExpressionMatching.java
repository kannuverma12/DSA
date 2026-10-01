package com.kv.lcoptimized.dp;

import com.kv.lcoptimized.common.Check;

/**
 * LC 10 · Regular Expression Matching (Hard)              [was: lc/RegularExpressionMatching]
 *
 * Pattern : dp[i][j] = s[i..] matches p[j..]. If p[j+1] == '*': skip "x*" (dp[i][j+2]) or,
 *           when s[i] matches x, consume one char (dp[i+1][j]). Otherwise first chars must
 *           match and dp[i+1][j+1].
 * Optimal : O(|s| * |p|) time, O(|p|) space with two rolling rows.
 * Changed : the recursive version used substring() at every call (exponential time plus
 *           copying), and the memo used an enum wrapper. Kept the bottom-up DP and reduced it
 *           to two rows.
 */
public class LC0010_RegularExpressionMatching {

    public static boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[] next = new boolean[m + 1];            // row i + 1
        boolean[] cur = new boolean[m + 1];             // row i
        for (int i = n; i >= 0; i--) {
            cur[m] = i == n;
            for (int j = m - 1; j >= 0; j--) {
                boolean first = i < n && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');
                if (j + 1 < m && p.charAt(j + 1) == '*') {
                    cur[j] = cur[j + 2] || (first && next[j]);
                } else {
                    cur[j] = first && next[j + 1];
                }
            }
            boolean[] t = next;
            next = cur;
            cur = t;
        }
        return next[0];
    }

    public static void main(String[] args) {
        Check.isTrue(!isMatch("aa", "a"));
        Check.isTrue(isMatch("aa", "a*"));
        Check.isTrue(isMatch("ab", ".*"));
        Check.isTrue(isMatch("aab", "c*a*b"));
        Check.isTrue(!isMatch("mississippi", "mis*is*p*."));
        Check.isTrue(isMatch("k", ".*"));
        Check.isTrue(isMatch("", "a*b*"));
    }
}
