package com.kv.lcoptimized.trees;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.TreeNode;

/**
 * LC 938 · Range Sum of BST (Easy)                        [was: lc/L63_RangeSumOfBST]
 *
 * Pattern : DFS pruned by BST ordering - skip the left subtree if node <= low, the right if
 *           node >= high.
 * Optimal : O(k + h) where k = nodes in range, O(h) space.
 * Changed : BUG FIX - the original dfs() had `while (root != null)` around the body with no
 *           update to root, so it looped forever. The iterative version pushed nulls onto a raw
 *           Stack.
 *
 * L6/L7 follow-up: many range queries on a static tree -> augment each node with its subtree
 * sum and answer in O(h) via prefixSum(high) - prefixSum(low - 1).
 */
public class LC0938_RangeSumBST {

    public static int rangeSumBST(TreeNode n, int low, int high) {
        if (n == null) {
            return 0;
        }
        if (n.val < low) {
            return rangeSumBST(n.right, low, high);
        }
        if (n.val > high) {
            return rangeSumBST(n.left, low, high);
        }
        return n.val + rangeSumBST(n.left, low, high) + rangeSumBST(n.right, low, high);
    }

    public static void main(String[] args) {
        Check.eq(rangeSumBST(TreeNode.of(10, 5, 15, 3, 7, null, 18), 7, 15), 32);
        Check.eq(rangeSumBST(TreeNode.of(10, 5, 15, 3, 7, 13, 18, 1, null, 6), 6, 10), 23);
    }
}
