package com.kv.lcoptimized.binarysearch;

import com.kv.lcoptimized.common.Check;

/**
 * LC 74 · Search a 2D Matrix (Medium) + LC 240 · Search a 2D Matrix II
 *                                                         [was: lc/medium/L21_SearchIn2DMatrix]
 *
 * LC 74 (rows are a continuation of each other): treat as a flat sorted array. O(log(mn)).
 * LC 240 (rows sorted, cols sorted, no row chaining): staircase from the top-right corner.
 *   O(m + n). Interviewers often ask LC 240 right after LC 74, and flat binary search is wrong there.
 * Changed : added LC 240; overflow-safe mid.
 */
public class LC0074_Search2DMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int lo = 0;
        int hi = m * n - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int v = matrix[mid / n][mid % n];
            if (v == target) {
                return true;
            } else if (v < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return false;
    }

    /** LC 240: each step discards a row or a column. */
    public static boolean searchMatrixII(int[][] matrix, int target) {
        int r = 0;
        int c = matrix[0].length - 1;
        while (r < matrix.length && c >= 0) {
            int v = matrix[r][c];
            if (v == target) {
                return true;
            } else if (v > target) {
                c--;
            } else {
                r++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        Check.isTrue(searchMatrix(m, 3));
        Check.isTrue(!searchMatrix(m, 13));
        int[][] m2 = {{1, 4, 7, 11}, {2, 5, 8, 12}, {3, 6, 9, 16}, {10, 13, 14, 17}};
        Check.isTrue(searchMatrixII(m2, 5));
        Check.isTrue(!searchMatrixII(m2, 15));
    }
}
