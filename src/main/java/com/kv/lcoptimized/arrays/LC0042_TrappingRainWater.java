package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

/**
 * LC 42 · Trapping Rain Water (Hard)                      [was: lc/medium/P1_TappingRainWater - empty stub]
 *
 * Pattern : two pointers; the side with the smaller max bounds the water at that side.
 * Optimal : O(n) time, O(1) space.
 *
 * Progression to narrate in an interview:
 *  1. For each i, water = min(maxLeft[i], maxRight[i]) - h[i]  -> O(n^2) naive, O(n) with prefix arrays.
 *  2. Two pointers remove the arrays: if leftMax < rightMax, left side's water is decided by leftMax.
 *  3. Monotonic stack alternative computes water in horizontal layers (useful to mention).
 *
 * L6/L7 follow-ups: 2D version (LC 407) -> min-heap BFS from the border, O(mn log(mn)).
 */
public class LC0042_TrappingRainWater {

    public static int trap(int[] height) {
        int lo = 0;
        int hi = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;
        while (lo < hi) {
            if (height[lo] < height[hi]) {
                leftMax = Math.max(leftMax, height[lo]);
                water += leftMax - height[lo++];
            } else {
                rightMax = Math.max(rightMax, height[hi]);
                water += rightMax - height[hi--];
            }
        }
        return water;
    }

    public static void main(String[] args) {
        Check.eq(trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}), 6);
        Check.eq(trap(new int[] {4, 2, 0, 3, 2, 5}), 9);
        Check.eq(trap(new int[] {}), 0);
    }
}
