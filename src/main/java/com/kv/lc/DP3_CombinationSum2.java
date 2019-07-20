package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  @author karanverma
 *  Given a collection of candidate numbers (C) and a target number (T), find all unique combinations 
 *  in C where the candidate numbers sums to T. Each number in C may only be used ONCE in the combination.
 *  Note:
 *  1) All numbers (including target) will be positive integers.
 *  2) Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 *  3) The solution set must not contain duplicate combinations.
 */
public class DP3_CombinationSum2 {
    /*
     * This problem is an extension of Combination Sum. The difference is one number in the array 
     * can only be used ONCE.
     */

    public static void main(String[] args) {
        int[] arr = {10,1,2,7,6,1,5};
        System.out.println(Arrays.toString(combinationSum2(arr, 8).toArray()));
    }
    
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, 0, target, temp, result);
        return result;
    }
    
    public static void dfs(int[] candidates, int start, int target, List<Integer> temp, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }
        
        if (target < 0) {
            return;
        }

        int prev = -1;
        for (int i = start; i < candidates.length; i++) {
            if (prev != candidates[i]) {        // each time start from different element
                temp.add(candidates[i]);
                dfs(candidates, i + 1, target - candidates[i], temp, result); // and use next element only
                temp.remove(temp.size() - 1);
                prev = candidates[i];
            }
        }
    }

}
