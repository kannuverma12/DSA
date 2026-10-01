package com.kv.lcoptimized.trees;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 95 · Unique Binary Search Trees II (Medium)          [was: lc/DP13_UniqueBinarySeachTrees2]
 *
 * Pattern : for each root i in [lo, hi], combine every left tree of [lo, i-1] with every right
 *           tree of [i+1, hi]. Memoise on (lo, hi); subtrees are shared between results, which
 *           is fine because they are read-only.
 * Complexity: output-bound, Catalan(n) trees ~ 4^n / n^1.5.
 * Changed : the original recomputed identical (lo, hi) ranges many times; memo[lo][hi] removes
 *           that. Count-only version is dp/LC0096_UniqueBST.
 */
public class LC0095_UniqueBSTII {

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return build(1, n, new List[n + 2][n + 2]);
    }

    private static List<TreeNode> build(int lo, int hi, List<TreeNode>[][] memo) {
        List<TreeNode> res = new ArrayList<>();
        if (lo > hi) {
            res.add(null);
            return res;
        }
        if (memo[lo][hi] != null) {
            return memo[lo][hi];
        }
        for (int i = lo; i <= hi; i++) {
            for (TreeNode l : build(lo, i - 1, memo)) {
                for (TreeNode r : build(i + 1, hi, memo)) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        memo[lo][hi] = res;
        return res;
    }

    public static void main(String[] args) {
        Check.eq(generateTrees(3).size(), 5);
        Check.eq(generateTrees(1).size(), 1);
        Check.eq(generateTrees(6).size(), 132);
    }
}
