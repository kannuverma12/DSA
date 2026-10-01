package com.kv.lcoptimized.dp;

import com.kv.lcoptimized.common.Check;

/**
 * LC 44 · Wildcard Matching (Hard)                        [was: lc/L79_WildcardMatching]
 *
 * A) Greedy with backtracking to the last '*': O(|s| * |p|) worst case, O(1) space, and fast
 *    in practice. Only the most recent star matters: a later star can absorb anything an
 *    earlier one could.
 * B) DP: O(|s| * |p|) time, O(|p|) space. Easier to prove; say so if asked for correctness.
 * Changed : the greedy was already correct; it kept a redundant `iIndex++` alongside
 *           i = iIndex + 1. Added the 1-row DP for comparison.
 */
public class LC0044_WildcardMatching {

    public static boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        int star = -1;
        int matchedByStar = 0;
        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') {
                star = j++;
                matchedByStar = i;
            } else if (star >= 0) {
                j = star + 1;
                i = ++matchedByStar;                    // let '*' swallow one more char
            } else {
                return false;
            }
        }
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }
        return j == p.length();
    }

    public static boolean isMatchDp(String s, String p) {
        int m = p.length();
        boolean[] dp = new boolean[m + 1];              // dp[j]: s[0..i) matches p[0..j)
        dp[0] = true;
        for (int j = 1; j <= m && p.charAt(j - 1) == '*'; j++) {
            dp[j] = true;
        }
        for (int i = 1; i <= s.length(); i++) {
            boolean diag = dp[0];
            dp[0] = false;
            for (int j = 1; j <= m; j++) {
                boolean up = dp[j];
                char c = p.charAt(j - 1);
                if (c == '*') {
                    dp[j] = dp[j] || dp[j - 1];         // star matches one more / matches empty
                } else {
                    dp[j] = diag && (c == '?' || c == s.charAt(i - 1));
                }
                diag = up;
            }
        }
        return dp[m];
    }

    public static void main(String[] args) {
        String[][] cases = {{"aa", "a", "false"}, {"aa", "*", "true"}, {"cb", "?a", "false"},
            {"adceb", "*a*b", "true"}, {"acdcb", "a*c?b", "false"}, {"aab", "?*", "true"}, {"", "***", "true"}};
        for (String[] c : cases) {
            Check.eq(isMatch(c[0], c[1]), Boolean.parseBoolean(c[2]));
            Check.eq(isMatchDp(c[0], c[1]), Boolean.parseBoolean(c[2]));
        }
    }
}
