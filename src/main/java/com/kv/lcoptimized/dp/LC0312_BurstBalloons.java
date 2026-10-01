package com.kv.lcoptimized.dp;

import com.kv.lcoptimized.common.Check;

/**
 * LC 312 · Burst Balloons (Hard)                          [NEW - interval DP]
 *
 * Key insight: choose the LAST balloon k to burst in the open interval (i, j). Its neighbours
 * at that moment are exactly i and j, so the two sides are independent:
 *   dp[i][j] = max over k of dp[i][k] + a[i]*a[k]*a[j] + dp[k][j]
 * Optimal : O(n^3) time, O(n^2) space. Fill by increasing interval length.
 *
 * Interval-DP family (same loop shape): matrix-chain multiplication, minimum cost to cut a
 * stick (LC 1547), strange printer (LC 664), minimum score triangulation (LC 1039).
 */
public class LC0312_BurstBalloons {

    public static int maxCoins(int[] nums) {
        int n = nums.length + 2;
        int[] a = new int[n];
        a[0] = 1;
        a[n - 1] = 1;
        System.arraycopy(nums, 0, a, 1, nums.length);
        int[][] dp = new int[n][n];
        for (int len = 2; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + a[i] * a[k] * a[j] + dp[k][j]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        Check.eq(maxCoins(new int[] {3, 1, 5, 8}), 167);
        Check.eq(maxCoins(new int[] {1, 5}), 10);
        Check.eq(maxCoins(new int[] {}), 0);
    }
}
