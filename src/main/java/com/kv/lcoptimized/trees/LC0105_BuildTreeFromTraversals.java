package com.kv.lcoptimized.trees;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LC 105 · Construct Binary Tree from Preorder and Inorder (Medium)
 * LC 106 · Construct Binary Tree from Inorder and Postorder (Medium)
 *   [was: lc/medium/L33_ConstructBinaryTreeFromPreorderAndInorderTraversal,
 *         lc/medium/L32_ConstructBinaryTreeFromInorderAndPostorderTraversal]
 *
 * Pattern : root comes from pre[0] (or post[last]); its inorder index splits left/right sizes.
 * Optimal : O(n) time, O(n) space.
 * Changed : the originals linearly scanned `inorder` for the root at every call, which is
 *           O(n^2) on skewed trees. A value -> index HashMap makes it O(n). A moving cursor
 *           replaces the preStart/preEnd arithmetic.
 *
 * Assumes unique values (say so in the interview; with duplicates the tree is ambiguous).
 */
public class LC0105_BuildTreeFromTraversals {

    public static TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> idx = indexOf(inorder);
        int[] cursor = {0};
        return preIn(preorder, idx, cursor, 0, inorder.length - 1);
    }

    private static TreeNode preIn(int[] pre, Map<Integer, Integer> idx, int[] cursor, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        TreeNode root = new TreeNode(pre[cursor[0]++]);
        int mid = idx.get(root.val);
        root.left = preIn(pre, idx, cursor, lo, mid - 1);
        root.right = preIn(pre, idx, cursor, mid + 1, hi);
        return root;
    }

    public static TreeNode buildTreeInPost(int[] inorder, int[] postorder) {
        Map<Integer, Integer> idx = indexOf(inorder);
        int[] cursor = {postorder.length - 1};
        return inPost(postorder, idx, cursor, 0, inorder.length - 1);
    }

    /** Consume postorder from the back: root, then RIGHT subtree, then left. */
    private static TreeNode inPost(int[] post, Map<Integer, Integer> idx, int[] cursor, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        TreeNode root = new TreeNode(post[cursor[0]--]);
        int mid = idx.get(root.val);
        root.right = inPost(post, idx, cursor, mid + 1, hi);
        root.left = inPost(post, idx, cursor, lo, mid - 1);
        return root;
    }

    private static Map<Integer, Integer> indexOf(int[] inorder) {
        Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            idx.put(inorder[i], i);
        }
        return idx;
    }

    public static void main(String[] args) {
        Check.eq(TreeNode.toLevelOrder(buildTreePreIn(new int[] {3, 9, 20, 15, 7}, new int[] {9, 3, 15, 20, 7})),
                Arrays.asList(3, 9, 20, null, null, 15, 7));
        Check.eq(TreeNode.toLevelOrder(buildTreeInPost(new int[] {9, 3, 15, 20, 7}, new int[] {9, 15, 7, 20, 3})),
                Arrays.asList(3, 9, 20, null, null, 15, 7));
        Check.eq(TreeNode.toLevelOrder(buildTreePreIn(new int[] {1, 2, 4, 5, 3, 7, 6, 8}, new int[] {4, 2, 5, 1, 6, 7, 3, 8})),
                Arrays.asList(1, 2, 3, 4, 5, 7, 8, null, null, null, null, 6));
    }
}
