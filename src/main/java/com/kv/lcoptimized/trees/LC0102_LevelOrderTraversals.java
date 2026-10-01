package com.kv.lcoptimized.trees;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LC 102 · Level Order, LC 103 · Zigzag Level Order, LC 107 · Level Order Bottom-Up
 *   [was: lc/medium/L30_BinaryTreeLevelOrderTraversal, L31_BinaryTreeZigzagLevelOrderTraversal,
 *         L34_BinaryTreeLevelOrderTraversalReverseFromLeafLevel]
 *
 * Pattern : BFS where the queue size at the start of each round is the level size.
 * Optimal : O(n) time, O(width) space.
 * Changed : the originals used a parallel "level" queue (L30), a current/next queue pair (L34)
 *           and two stacks plus printing (L31). The level-size loop replaces all three.
 *           Zigzag fills the level with addFirst/addLast, so there's no second stack or
 *           reverse step. Bottom-up just reverses the list of levels.
 *
 * Variants that use the same loop: right side view (LC 199, last of each level), level
 * averages (LC 637), max width (LC 662, track positional indices).
 */
public class LC0102_LevelOrderTraversals {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        if (root != null) {
            q.add(root);
        }
        while (!q.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            for (int size = q.size(); size > 0; size--) {
                TreeNode n = q.poll();
                level.add(n.val);
                if (n.left != null) {
                    q.add(n.left);
                }
                if (n.right != null) {
                    q.add(n.right);
                }
            }
            levels.add(level);
        }
        return levels;
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        if (root != null) {
            q.add(root);
        }
        boolean leftToRight = true;
        while (!q.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            for (int size = q.size(); size > 0; size--) {
                TreeNode n = q.poll();
                if (leftToRight) {
                    level.addLast(n.val);
                } else {
                    level.addFirst(n.val);
                }
                if (n.left != null) {
                    q.add(n.left);
                }
                if (n.right != null) {
                    q.add(n.right);
                }
            }
            levels.add(level);
            leftToRight = !leftToRight;
        }
        return levels;
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levels = levelOrder(root);
        Collections.reverse(levels);
        return levels;
    }

    public static void main(String[] args) {
        TreeNode t = TreeNode.of(3, 9, 20, null, null, 15, 7);
        Check.eq(levelOrder(t), Arrays.asList(Arrays.asList(3), Arrays.asList(9, 20), Arrays.asList(15, 7)));
        Check.eq(zigzagLevelOrder(t), Arrays.asList(Arrays.asList(3), Arrays.asList(20, 9), Arrays.asList(15, 7)));
        Check.eq(levelOrderBottom(t), Arrays.asList(Arrays.asList(15, 7), Arrays.asList(9, 20), Arrays.asList(3)));
        Check.eq(zigzagLevelOrder(TreeNode.of(1, 2, 3, 7, 6, 5, 4)),
                Arrays.asList(Arrays.asList(1), Arrays.asList(3, 2), Arrays.asList(7, 6, 5, 4)));
        Check.eq(levelOrder(null).size(), 0);
    }
}
