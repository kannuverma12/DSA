package com.kv.lcoptimized.trees;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * LC 144 / 94 / 145 · Preorder, Inorder, Postorder Traversal
 *                                                         [was: lc/L35_BinaryTreePreorderTraversal]
 *
 * Iterative with an explicit stack: O(n) time, O(h) space.
 * Morris traversal: O(n) time, O(1) space. It temporarily threads each predecessor's right
 * pointer back to the current node. This is the answer to "can you do it without a stack?"
 * Changed : ArrayDeque instead of the synchronized java.util.Stack; added inorder, postorder and
 *           Morris inorder/preorder, because interviewers pick any of the three orders.
 */
public class LC0144_BinaryTreeTraversals {

    public static List<Integer> preorder(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode n = stack.pop();
            out.add(n.val);
            if (n.right != null) {
                stack.push(n.right);
            }
            if (n.left != null) {
                stack.push(n.left);
            }
        }
        return out;
    }

    public static List<Integer> inorder(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            out.add(cur.val);
            cur = cur.right;
        }
        return out;
    }

    /** Postorder = reverse of (root, right, left). */
    public static List<Integer> postorder(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode n = stack.pop();
            out.add(n.val);
            if (n.left != null) {
                stack.push(n.left);
            }
            if (n.right != null) {
                stack.push(n.right);
            }
        }
        java.util.Collections.reverse(out);
        return out;
    }

    /** Morris: O(1) extra space; restores the tree before returning. */
    public static List<Integer> morris(TreeNode root, boolean preorder) {
        List<Integer> out = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                out.add(cur.val);
                cur = cur.right;
                continue;
            }
            TreeNode pred = cur.left;
            while (pred.right != null && pred.right != cur) {
                pred = pred.right;
            }
            if (pred.right == null) {                 // first visit: thread and go left
                if (preorder) {
                    out.add(cur.val);
                }
                pred.right = cur;
                cur = cur.left;
            } else {                                  // second visit: unthread and go right
                pred.right = null;
                if (!preorder) {
                    out.add(cur.val);
                }
                cur = cur.right;
            }
        }
        return out;
    }

    public static void main(String[] args) {
        TreeNode t = TreeNode.of(1, 2, 3, 4, 5, null, 6);
        Check.eq(preorder(t), Arrays.asList(1, 2, 4, 5, 3, 6));
        Check.eq(inorder(t), Arrays.asList(4, 2, 5, 1, 3, 6));
        Check.eq(postorder(t), Arrays.asList(4, 5, 2, 6, 3, 1));
        Check.eq(morris(t, true), Arrays.asList(1, 2, 4, 5, 3, 6));
        Check.eq(morris(t, false), Arrays.asList(4, 2, 5, 1, 3, 6));
        Check.eq(TreeNode.toLevelOrder(t), Arrays.asList(1, 2, 3, 4, 5, null, 6));   // tree restored
    }
}
