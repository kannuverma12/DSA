package com.kv.lcoptimized.dp;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 118 · Pascal's Triangle (Easy) + LC 119 · Pascal's Triangle II (row k in O(k) space)
 *                                                         [was: lc/L46_PascalTriangle]
 *
 * 118 : each row from the previous one. O(n^2).
 * 119 : one array updated right-to-left in place, or directly C(k, i) = C(k, i-1) * (k-i+1) / i.
 * Changed : the GfG variant filled an n x n int table and printed from inside the loop.
 *           Replaced with LC 119, which is what interviewers actually follow up with.
 */
public class LC0118_PascalsTriangle {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> rows = new ArrayList<>();
        for (int r = 0; r < numRows; r++) {
            List<Integer> row = new ArrayList<>(r + 1);
            for (int i = 0; i <= r; i++) {
                row.add(i == 0 || i == r ? 1 : rows.get(r - 1).get(i - 1) + rows.get(r - 1).get(i));
            }
            rows.add(row);
        }
        return rows;
    }

    public static List<Integer> getRow(int k) {
        List<Integer> row = new ArrayList<>(k + 1);
        long c = 1;
        for (int i = 0; i <= k; i++) {
            row.add((int) c);
            c = c * (k - i) / (i + 1);
        }
        return row;
    }

    public static void main(String[] args) {
        Check.eq(generate(5), Arrays.asList(Arrays.asList(1), Arrays.asList(1, 1), Arrays.asList(1, 2, 1),
                Arrays.asList(1, 3, 3, 1), Arrays.asList(1, 4, 6, 4, 1)));
        Check.eq(getRow(4), Arrays.asList(1, 4, 6, 4, 1));
        Check.eq(getRow(30).get(15), 155117520);
    }
}
