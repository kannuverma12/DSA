package com.kv.lcoptimized.trees;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.TreeNode;

/**
 * LC 98 · Validate Binary Search Tree (Medium)            [was: lc/DP16_ValidateBinarySearchTree]
 *
 * Pattern : pass (low, high) bounds down; each node must lie strictly inside.
 * Optimal : O(n) time, O(h) space.
 * Changed : Integer (nullable) bounds instead of double +/-infinity. Doubles do work for
 *           32-bit ints, but they silently break if the values become long, and interviewers
 *           notice. An inorder "strictly increasing" check is an equally good alternative.
 */
public class LC0098_ValidateBST {

    public static boolean isValidBST(TreeNode root) {
        return valid(root, null, null);
    }

    private static boolean valid(TreeNode n, Integer low, Integer high) {
        if (n == null) {
            return true;
        }
        if ((low != null && n.val <= low) || (high != null && n.val >= high)) {
            return false;
        }
        return valid(n.left, low, n.val) && valid(n.right, n.val, high);
    }

    public static void main(String[] args) {
        Check.isTrue(isValidBST(TreeNode.of(2, 1, 3)));
        Check.isTrue(!isValidBST(TreeNode.of(5, 1, 4, null, null, 3, 6)));
        Check.isTrue(!isValidBST(TreeNode.of(5, 4, 6, null, null, 3, 7)));
        Check.isTrue(isValidBST(TreeNode.of(Integer.MAX_VALUE)));
        Check.isTrue(!isValidBST(TreeNode.of(1, 1)));
    }
}
