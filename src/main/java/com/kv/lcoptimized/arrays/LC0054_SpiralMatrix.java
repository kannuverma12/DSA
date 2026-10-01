package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 54 · Spiral Matrix (Medium) + LC 59 · Spiral Matrix II
 *                                  [was: lc/medium/L13_SpiralMatrix, lc/medium/L14_SpiralMatrix2]
 *
 * Pattern : shrink four boundaries; guard the bottom/left passes for single row/column.
 * Optimal : O(mn) time, O(1) extra.
 * Changed : both problems share one traversal; the recursive variant was removed. LC 59
 *           worked only because the matrix was square and had no guards.
 */
public class LC0054_SpiralMatrix {

    interface Visitor {
        void visit(int r, int c);
    }

    static void spiral(int rows, int cols, Visitor v) {
        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = cols - 1;
        while (top <= bottom && left <= right) {
            for (int c = left; c <= right; c++) {
                v.visit(top, c);
            }
            for (int r = top + 1; r <= bottom; r++) {
                v.visit(r, right);
            }
            if (top < bottom && left < right) {
                for (int c = right - 1; c >= left; c--) {
                    v.visit(bottom, c);
                }
                for (int r = bottom - 1; r > top; r--) {
                    v.visit(r, left);
                }
            }
            top++;
            bottom--;
            left++;
            right--;
        }
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> out = new ArrayList<>();
        if (matrix.length > 0) {
            spiral(matrix.length, matrix[0].length, (r, c) -> out.add(matrix[r][c]));
        }
        return out;
    }

    /** LC 59. */
    public static int[][] generateMatrix(int n) {
        int[][] m = new int[n][n];
        int[] k = {1};
        spiral(n, n, (r, c) -> m[r][c] = k[0]++);
        return m;
    }

    public static void main(String[] args) {
        Check.eq(spiralOrder(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}),
                Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5));
        Check.eq(spiralOrder(new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}),
                Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7));
        Check.eq(spiralOrder(new int[][] {{1}, {2}, {3}}), Arrays.asList(1, 2, 3));
        Check.eq(generateMatrix(3), new int[][] {{1, 2, 3}, {8, 9, 4}, {7, 6, 5}});
    }
}
