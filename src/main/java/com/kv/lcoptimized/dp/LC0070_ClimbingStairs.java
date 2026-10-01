package com.kv.lcoptimized.dp;

import com.kv.lcoptimized.common.Check;

/**
 * LC 70 · Climbing Stairs + "ways to reach n with steps {1,2,3}" (GfG)
 *                                                         [was: lc/P4_WaysToReachNSteps - empty stub]
 *
 * Pattern : ways(n) = sum of ways(n - s) for each allowed step s. Keep only the last
 *           max(step) values - a rolling window.
 * Optimal : O(n * |steps|) time, O(max step) space.
 * L6/L7 follow-up: n = 10^18 (answer mod p) -> the recurrence is linear, so raise its k x k
 *           companion matrix to the n-th power: O(k^3 log n). Implemented below for {1,2,3}.
 */
public class LC0070_ClimbingStairs {

    /** LC 70 (steps 1 and 2). Fibonacci. */
    public static int climbStairs(int n) {
        int a = 1;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    /** Generic step set. */
    public static long ways(int n, int[] steps) {
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int s : steps) {
                if (i >= s) {
                    dp[i] += dp[i - s];
                }
            }
        }
        return dp[n];
    }

    /** Steps {1,2,3}, huge n, modulo mod: [f(n), f(n-1), f(n-2)] = M^n applied to [1, 0, 0]. */
    public static long tribonacciWaysMod(long n, long mod) {
        long[][] m = {{1, 1, 1}, {1, 0, 0}, {0, 1, 0}};
        long[][] r = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                r = mul(r, m, mod);
            }
            m = mul(m, m, mod);
            n >>= 1;
        }
        return r[0][0];
    }

    private static long[][] mul(long[][] a, long[][] b, long mod) {
        int k = a.length;
        long[][] c = new long[k][k];
        for (int i = 0; i < k; i++) {
            for (int t = 0; t < k; t++) {
                if (a[i][t] == 0) {
                    continue;
                }
                for (int j = 0; j < k; j++) {
                    c[i][j] = (c[i][j] + a[i][t] * b[t][j]) % mod;
                }
            }
        }
        return c;
    }

    public static void main(String[] args) {
        Check.eq(climbStairs(2), 2);
        Check.eq(climbStairs(5), 8);
        Check.eq(ways(4, new int[] {1, 2, 3}), 7L);
        Check.eq(ways(10, new int[] {1, 2, 3}), 274L);
        Check.eq(tribonacciWaysMod(10, 1_000_000_007L), 274L);
        Check.eq(tribonacciWaysMod(50, 1_000_000_007L), ways(50, new int[] {1, 2, 3}) % 1_000_000_007L);
    }
}
