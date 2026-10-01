package com.kv.lcoptimized.trees;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.TreeNode;

/**
 * LC 129 · Sum Root to Leaf Numbers (Medium)              [was: lc/DP11_SumRootToleafNumbers]
 *
 * Pattern : DFS carrying the number formed so far; return it at leaves, sum children otherwise.
 * Optimal : O(n) time, O(h) space.
 * Changed : the original threaded an unused `sum` accumulator through the recursion, which
 *           made the recurrence look like it double-counted. The recurrence is now cleaner.
 */
public class LC0129_SumRootToLeafNumbers {

    public static int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private static int dfs(TreeNode n, int prefix) {
        if (n == null) {
            return 0;
        }
        prefix = prefix * 10 + n.val;
        if (n.left == null && n.right == null) {
            return prefix;
        }
        return dfs(n.left, prefix) + dfs(n.right, prefix);
    }

    public static void main(String[] args) {
        Check.eq(sumNumbers(TreeNode.of(1, 2, 3)), 25);
        Check.eq(sumNumbers(TreeNode.of(4, 9, 0, 5, 1)), 1026);
        Check.eq(sumNumbers(null), 0);
    }
}
