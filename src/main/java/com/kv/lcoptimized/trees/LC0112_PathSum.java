package com.kv.lcoptimized.trees;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC 112 · Path Sum, LC 113 · Path Sum II, LC 437 · Path Sum III
 *   [was: lc/DP7_PathSum, lc/DP8_PathSum2]
 *
 * 112 : DFS subtracting from target; true at a leaf with remainder 0. O(n).
 * 113 : backtracking with one shared path list; copy only at matching leaves. O(n * h) output-bound.
 * 437 : (NEW - the usual follow-up) any downward path, not just root-to-leaf. Prefix sums on
 *       the root->node path plus a count map, un-done on the way back up. O(n) instead of O(n^2).
 * Changed : 113 had separate add/remove blocks per child; they are unified. The BFS variant of
 *           112 with two parallel LinkedLists was removed.
 */
public class LC0112_PathSum {

    public static boolean hasPathSum(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == target;
        }
        return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
    }

    public static List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> out = new ArrayList<>();
        collect(root, target, new ArrayList<>(), out);
        return out;
    }

    private static void collect(TreeNode n, int remaining, List<Integer> path, List<List<Integer>> out) {
        if (n == null) {
            return;
        }
        path.add(n.val);
        remaining -= n.val;
        if (n.left == null && n.right == null && remaining == 0) {
            out.add(new ArrayList<>(path));
        } else {
            collect(n.left, remaining, path, out);
            collect(n.right, remaining, path, out);
        }
        path.remove(path.size() - 1);
    }

    /** LC 437: number of downward paths (any start, any end) summing to target. */
    public static int pathSumIII(TreeNode root, int target) {
        Map<Long, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0L, 1);
        return count(root, 0L, target, prefixCount);
    }

    private static int count(TreeNode n, long prefix, int target, Map<Long, Integer> prefixCount) {
        if (n == null) {
            return 0;
        }
        prefix += n.val;
        int res = prefixCount.getOrDefault(prefix - target, 0);
        prefixCount.merge(prefix, 1, Integer::sum);
        res += count(n.left, prefix, target, prefixCount) + count(n.right, prefix, target, prefixCount);
        prefixCount.merge(prefix, -1, Integer::sum);          // backtrack
        return res;
    }

    public static void main(String[] args) {
        TreeNode t = TreeNode.of(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1);
        Check.isTrue(hasPathSum(t, 22));
        Check.isTrue(!hasPathSum(t, 5));
        Check.eq(pathSum(t, 22), Arrays.asList(Arrays.asList(5, 4, 11, 2), Arrays.asList(5, 8, 4, 5)));
        Check.eq(pathSumIII(TreeNode.of(10, 5, -3, 3, 2, null, 11, 3, -2, null, 1), 8), 3);
    }
}
