package com.kv.lcoptimized.stack;

import com.kv.lcoptimized.common.Check;

/**
 * LC 84 · Largest Rectangle in Histogram (Hard)           [NEW - monotonic stack]
 *
 * Pattern : increasing stack of indices. When bar i is lower than the top, the top's rectangle
 *           can't extend further right; its left limit is the new stack top. A sentinel height
 *           0 at i == n flushes the stack.
 * Optimal : O(n) time, O(n) space.
 *
 * Why it matters: "next smaller / greater element" appears everywhere. Once you know this,
 * LC 85 Maximal Rectangle is "run this on each row's heights" (O(rows * cols)), and
 * LC 907 Sum of Subarray Minimums / LC 1856 are the same stack with a contribution count.
 */
public class LC0084_LargestRectangleInHistogram {

    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n + 1];
        int top = 0;
        int best = 0;
        for (int i = 0; i <= n; i++) {
            int h = i == n ? 0 : heights[i];
            while (top > 0 && heights[stack[top - 1]] >= h) {
                int height = heights[stack[--top]];
                int left = top == 0 ? -1 : stack[top - 1];
                best = Math.max(best, height * (i - left - 1));
            }
            stack[top++] = i;
        }
        return best;
    }

    /** LC 85: largest rectangle of '1's in a binary matrix. */
    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[] heights = new int[matrix[0].length];
        int best = 0;
        for (char[] row : matrix) {
            for (int j = 0; j < row.length; j++) {
                heights[j] = row[j] == '1' ? heights[j] + 1 : 0;
            }
            best = Math.max(best, largestRectangleArea(heights));
        }
        return best;
    }

    public static void main(String[] args) {
        Check.eq(largestRectangleArea(new int[] {2, 1, 5, 6, 2, 3}), 10);
        Check.eq(largestRectangleArea(new int[] {2, 4}), 4);
        Check.eq(largestRectangleArea(new int[] {}), 0);
        Check.eq(largestRectangleArea(new int[] {3, 3, 3}), 9);
        Check.eq(maximalRectangle(new char[][] {
            "10100".toCharArray(), "10111".toCharArray(), "11111".toCharArray(), "10010".toCharArray()}), 6);
    }
}
