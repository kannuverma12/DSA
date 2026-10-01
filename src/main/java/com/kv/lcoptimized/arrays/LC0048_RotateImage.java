package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

/**
 * LC 48 · Rotate Image (Medium)                           [was: lc/medium/L9_RotateImageof2DArray]
 *
 * Pattern : clockwise rotation = transpose, then reverse each row.
 * Optimal : O(n^2) time, O(1) space.
 * Changed : the 4-way cycle swap with Math.ceil on doubles was correct but error-prone to
 *           write on a whiteboard. Transpose + reverse is easy to derive and to verify.
 *           Counter-clockwise = transpose + reverse each column.
 */
public class LC0048_RotateImage {

    public static void rotate(int[][] m) {
        int n = m.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int t = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = t;
            }
        }
        for (int[] row : m) {
            for (int lo = 0, hi = n - 1; lo < hi; lo++, hi--) {
                int t = row[lo];
                row[lo] = row[hi];
                row[hi] = t;
            }
        }
    }

    public static void main(String[] args) {
        int[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(m);
        Check.eq(m, new int[][] {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}});
    }
}
