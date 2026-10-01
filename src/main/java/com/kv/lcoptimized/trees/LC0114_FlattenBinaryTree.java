package com.kv.lcoptimized.trees;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.TreeNode;

import java.util.Arrays;

/**
 * LC 114 · Flatten Binary Tree to Linked List (Medium)    [was: lc/L41_FlattenBinaryTreeToLinkedList]
 *
 * Pattern : Morris-style. For each node with a left child, splice the left subtree between
 *           the node and its right subtree (hook the left subtree's rightmost node onto
 *           node.right).
 * Optimal : O(n) time (each edge walked a constant number of times), O(1) space.
 * Changed : the original used an explicit stack - O(h) space. The problem invites the O(1)
 *           follow-up.
 */
public class LC0114_FlattenBinaryTree {

    public static void flatten(TreeNode root) {
        for (TreeNode cur = root; cur != null; cur = cur.right) {
            if (cur.left != null) {
                TreeNode rightmost = cur.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }
                rightmost.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode t = TreeNode.of(1, 2, 5, 3, 4, null, 6);
        flatten(t);
        Check.eq(TreeNode.toLevelOrder(t), Arrays.asList(1, null, 2, null, 3, null, 4, null, 5, null, 6));
        flatten(null);
    }
}
