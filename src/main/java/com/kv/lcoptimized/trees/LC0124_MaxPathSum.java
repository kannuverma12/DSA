package com.kv.lcoptimized.trees;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.TreeNode;

/**
 * LC 124 · Binary Tree Maximum Path Sum (Hard)            [NEW - "return one thing, update another"]
 *
 * Pattern : post-order. gain(n) = best downward path starting at n (what the parent can use);
 *           the answer candidate at n is n.val + max(0, gainL) + max(0, gainR) (bends at n).
 * Optimal : O(n) time, O(h) space.
 *
 * This two-value recursion also solves: diameter (LC 543), longest univalue path (LC 687),
 * binary tree cameras (LC 968), and the "max leaf-to-root sum" in com.kv.trees.
 */
public class LC0124_MaxPathSum {

    public static int maxPathSum(TreeNode root) {
        int[] best = {Integer.MIN_VALUE};
        gain(root, best);
        return best[0];
    }

    private static int gain(TreeNode n, int[] best) {
        if (n == null) {
            return 0;
        }
        int left = Math.max(0, gain(n.left, best));
        int right = Math.max(0, gain(n.right, best));
        best[0] = Math.max(best[0], n.val + left + right);
        return n.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        Check.eq(maxPathSum(TreeNode.of(1, 2, 3)), 6);
        Check.eq(maxPathSum(TreeNode.of(-10, 9, 20, null, null, 15, 7)), 42);
        Check.eq(maxPathSum(TreeNode.of(-3)), -3);
        Check.eq(maxPathSum(TreeNode.of(2, -1)), 2);
    }
}
