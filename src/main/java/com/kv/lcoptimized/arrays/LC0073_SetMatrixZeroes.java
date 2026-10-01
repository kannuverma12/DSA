package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

/**
 * LC 73 · Set Matrix Zeroes (Medium)                      [was: lc/medium/L20_SetMetrixZeroes]
 *
 * Pattern : use row 0 / column 0 as marker arrays; one extra flag for column 0.
 * Optimal : O(mn) time, O(1) space.
 * Changed : the original was already O(1) space and used two flags. This version needs one
 *           flag and fills bottom-up, so the markers are read before they are overwritten.
 */
public class LC0073_SetMatrixZeroes {

    public static void setZeroes(int[][] m) {
        int rows = m.length;
        int cols = m[0].length;
        boolean col0 = false;
        for (int i = 0; i < rows; i++) {
            if (m[i][0] == 0) {
                col0 = true;
            }
            for (int j = 1; j < cols; j++) {
                if (m[i][j] == 0) {
                    m[i][0] = 0;
                    m[0][j] = 0;
                }
            }
        }
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--) {
                if (m[i][0] == 0 || m[0][j] == 0) {
                    m[i][j] = 0;
                }
            }
            if (col0) {
                m[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] a = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setZeroes(a);
        Check.eq(a, new int[][] {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}});
        int[][] b = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes(b);
        Check.eq(b, new int[][] {{0, 0, 0, 0}, {0, 4, 5, 0}, {0, 3, 1, 0}});
    }
}
