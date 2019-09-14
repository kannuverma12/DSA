package com.kv.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author karanverma
 *
 */
public class DP13_UniqueBinarySeachTrees2 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        return helper(1, n);
    }

    public static List<TreeNode> helper(int m, int n) {
        List<TreeNode> result = new ArrayList<>();
        if (m > n) {
            result.add(null);
            return result;
        }

        for (int i = m; i <= n; i++) {
            List<TreeNode> ls = helper(m, i - 1);
            List<TreeNode> rs = helper(i + 1, n);
            for (TreeNode l : ls) {
                for (TreeNode r : rs) {
                    TreeNode curr = new TreeNode(i);
                    curr.left = l;
                    curr.right = r;
                    result.add(curr);
                }
            }
        }

        return result;
    }

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
