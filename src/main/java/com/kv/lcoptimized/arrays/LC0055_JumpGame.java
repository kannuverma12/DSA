package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

/**
 * LC 55 · Jump Game (Medium) + LC 45 · Jump Game II        [was: lc/medium/L15_JumpGameArray]
 *
 * Pattern : greedy reach. LC 45 is implicit BFS by levels (each level = one jump).
 * Optimal : O(n) time, O(1) space for both.
 * Changed : simplified loop (stop when i passes reach); added LC 45 since it's the
 *           standard follow-up.
 */
public class LC0055_JumpGame {

    public static boolean canJump(int[] nums) {
        int reach = 0;
        for (int i = 0; i < nums.length && i <= reach; i++) {
            reach = Math.max(reach, i + nums[i]);
            if (reach >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    /** Minimum jumps to reach the end (assumes reachable). */
    public static int jump(int[] nums) {
        int jumps = 0;
        int levelEnd = 0;
        int farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == levelEnd) {
                jumps++;
                levelEnd = farthest;
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        Check.isTrue(canJump(new int[] {2, 3, 1, 1, 4}));
        Check.isTrue(!canJump(new int[] {3, 2, 1, 0, 4}));
        Check.isTrue(canJump(new int[] {0}));
        Check.eq(jump(new int[] {2, 3, 1, 1, 4}), 2);
        Check.eq(jump(new int[] {0}), 0);
    }
}
