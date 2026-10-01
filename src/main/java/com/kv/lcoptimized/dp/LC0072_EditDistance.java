package com.kv.lcoptimized.dp;

import com.kv.lcoptimized.common.Check;

/**
 * LC 72 · Edit Distance (Medium)                          [NEW - canonical 2-string DP]
 *
 * dp[i][j] = edits to turn a[0..i) into b[0..j)
 *          = dp[i-1][j-1]                       if a[i-1] == b[j-1]
 *          = 1 + min(replace dp[i-1][j-1], delete dp[i-1][j], insert dp[i][j-1])
 * Optimal : O(mn) time, O(min(m, n)) space with one row + a saved diagonal.
 *
 * Same table shape: LCS (LC 1143), delete operation for two strings (LC 583), distinct
 * subsequences (LC 115), interleaving string (LC 97).
 * L6/L7 follow-ups: only need "distance <= k?" -> banded DP, O(k * n). Spell-checker over a
 * dictionary -> BK-tree or trie + DP rows.
 */
public class LC0072_EditDistance {

    public static int minDistance(String a, String b) {
        if (a.length() < b.length()) {
            String t = a;
            a = b;
            b = t;
        }
        int n = b.length();
        int[] dp = new int[n + 1];
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }
        for (int i = 1; i <= a.length(); i++) {
            int diag = dp[0];
            dp[0] = i;
            for (int j = 1; j <= n; j++) {
                int up = dp[j];
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[j] = diag;
                } else {
                    dp[j] = 1 + Math.min(diag, Math.min(up, dp[j - 1]));
                }
                diag = up;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Check.eq(minDistance("horse", "ros"), 3);
        Check.eq(minDistance("intention", "execution"), 5);
        Check.eq(minDistance("", "abc"), 3);
        Check.eq(minDistance("same", "same"), 0);
    }
}
