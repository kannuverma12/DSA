package com.kv.lcoptimized.trees;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.TreeNode;

/**
 * LC 236 · Lowest Common Ancestor of a Binary Tree (Medium)  [NEW]
 * LC 235 · LCA of a BST (Medium)
 *
 * Binary tree: post-order; return the node if it is p or q; if both subtrees return non-null,
 *   the current node is the LCA. O(n) time, O(h) space.
 * BST: walk from the root; split point where p and q go different ways. O(h) time, O(1) space.
 *
 * L6/L7 follow-ups (Google asks these):
 *  - p or q might not be in the tree (LC 1644) -> count how many were actually found.
 *  - Nodes have parent pointers (LC 1650) -> this is "intersection of two linked lists".
 *  - Many LCA queries -> Euler tour + sparse-table RMQ (O(1) per query) or binary lifting
 *    (O(log n) per query) after O(n log n) preprocessing.
 */
public class LC0236_LowestCommonAncestor {

    public static TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lca(root.left, p, q);
        TreeNode right = lca(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    public static TreeNode lcaBST(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        while (cur != null) {
            if (p.val < cur.val && q.val < cur.val) {
                cur = cur.left;
            } else if (p.val > cur.val && q.val > cur.val) {
                cur = cur.right;
            } else {
                return cur;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode t = TreeNode.of(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        TreeNode five = t.left;
        TreeNode one = t.right;
        TreeNode four = t.left.right.right;
        Check.eq(lca(t, five, one).val, 3);
        Check.eq(lca(t, five, four).val, 5);

        TreeNode bst = TreeNode.of(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5);
        Check.eq(lcaBST(bst, bst.left, bst.right).val, 6);
        Check.eq(lcaBST(bst, bst.left, bst.left.right).val, 2);
    }
}
