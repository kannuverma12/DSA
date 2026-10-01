package com.kv.lcoptimized.dp;

import com.kv.lcoptimized.common.Check;

import java.util.Arrays;
import java.util.List;

/**
 * LC 120 · Triangle (Medium)                              [was: lc/L45_Triangle]
 *
 * Pattern : bottom-up; best[j] = row[j] + min(best[j], best[j+1]). The answer ends in best[0].
 * Optimal : O(n^2) time, O(n) space.
 * Changed : the original was already this algorithm; it's now written as one loop that starts
 *           from the last row, and it has a test.
 */
public class LC0120_Triangle {

    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] best = new int[n + 1];
        for (int r = n - 1; r >= 0; r--) {
            List<Integer> row = triangle.get(r);
            for (int j = 0; j <= r; j++) {
                best[j] = row.get(j) + Math.min(best[j], best[j + 1]);
            }
        }
        return best[0];
    }

    public static void main(String[] args) {
        Check.eq(minimumTotal(Arrays.asList(Arrays.asList(2), Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7), Arrays.asList(4, 1, 8, 3))), 11);
        Check.eq(minimumTotal(Arrays.asList(Arrays.asList(-10))), -10);
    }
}
