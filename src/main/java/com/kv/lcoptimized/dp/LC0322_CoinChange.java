package com.kv.lcoptimized.dp;

import com.kv.lcoptimized.common.Check;

import java.util.Arrays;

/**
 * LC 322 · Coin Change (Medium) + LC 518 · Coin Change II (number of ways)
 *                                                         [was: lc/medium/MinimumCoinsForGivenValue]
 *
 * 322 : unbounded knapsack, dp[v] = 1 + min over coins dp[v - c]. O(V * k) time, O(V) space.
 * 518 : count combinations - loop COINS outside, amounts inside, so each multiset is counted
 *       once. (Amounts outside counts ordered sequences, which is LC 377.)
 * Changed : main() ran the exponential recursion. There was also a -1 memo, and a table
 *           seeded with MAX_VALUE that needed overflow guards. Seeding with V+1 ("impossible")
 *           removes the guards. Greedy only works for canonical coin systems - be ready to
 *           give the counterexample {1, 3, 4}, target 6.
 */
public class LC0322_CoinChange {

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int v = 1; v <= amount; v++) {
            for (int c : coins) {
                if (c <= v) {
                    dp[v] = Math.min(dp[v], dp[v - c] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static int change(int amount, int[] coins) {
        int[] ways = new int[amount + 1];
        ways[0] = 1;
        for (int c : coins) {
            for (int v = c; v <= amount; v++) {
                ways[v] += ways[v - c];
            }
        }
        return ways[amount];
    }

    public static void main(String[] args) {
        Check.eq(coinChange(new int[] {9, 6, 5, 1}, 11), 2);
        Check.eq(coinChange(new int[] {1, 2, 5}, 11), 3);
        Check.eq(coinChange(new int[] {2}, 3), -1);
        Check.eq(coinChange(new int[] {1}, 0), 0);
        Check.eq(coinChange(new int[] {1, 3, 4}, 6), 2);
        Check.eq(change(5, new int[] {1, 2, 5}), 4);
    }
}
