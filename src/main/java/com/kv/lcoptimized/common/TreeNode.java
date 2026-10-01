package com.kv.lcoptimized.common;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/** Shared binary tree node with LeetCode-style level-order (de)serialisation for tests. */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    /** Builds a tree from LeetCode level order, e.g. of(3, 9, 20, null, null, 15, 7). */
    public static TreeNode of(Integer... levelOrder) {
        if (levelOrder.length == 0 || levelOrder[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int i = 1;
        while (i < levelOrder.length) {
            TreeNode node = queue.poll();
            if (levelOrder[i] != null) {
                node.left = new TreeNode(levelOrder[i]);
                queue.add(node.left);
            }
            i++;
            if (i < levelOrder.length && levelOrder[i] != null) {
                node.right = new TreeNode(levelOrder[i]);
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    /** Inverse of {@link #of}: level order with nulls, trailing nulls trimmed. */
    public static List<Integer> toLevelOrder(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        for (int i = 0; i < queue.size(); i++) {
            TreeNode node = queue.get(i);
            if (node == null) {
                out.add(null);
            } else {
                out.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        while (!out.isEmpty() && out.get(out.size() - 1) == null) {
            out.remove(out.size() - 1);
        }
        return out;
    }

    @Override
    public String toString() {
        return toLevelOrder(this).toString();
    }
}
