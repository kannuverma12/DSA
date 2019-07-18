package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 *  Example:
 *  Input: 3
 *  Output: 5
 *  Explanation:
 *  Given n = 3, there are a total of 5 unique BST's:
 *  1         3     3      2      1
 *  \       /     /      / \      \
 *   3     2     1      1   3      2
 *  /     /       \                 \
 * 2     1         2                 3
 */
public class DP12_UniqueBinarySearchTrees {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(numTrees(3));
    }
    
    public static int numTrees(int n) {
        int[] count = new int[n + 1];
     
        count[0] = 1;
        count[1] = 1;
     
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= i - 1; j++) {
                count[i] = count[i] + count[j] * count[i - j - 1];
            }
        }
     
        return count[n];
    }

}
