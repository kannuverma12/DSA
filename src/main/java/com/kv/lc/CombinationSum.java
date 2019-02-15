package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author karanverma
 *  
 *  Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), 
 *  find all unique combinations in candidates where the candidate numbers sums to target.
 *  
 *  The same repeated number may be chosen from candidates unlimited number of times:
 *  Note:
 *  All numbers (including target) will be positive integers.
 *  The solution set must not contain duplicate combinations.
 *  
 *  Input: candidates = [2,3,6,7], target = 7, 
 *  A solution set is: [[7],[2,2,3]]
 */
public class CombinationSum {
    /*
     * The first impression of this problem should be depth-first search(DFS). 
     * To solve DFS problem, recursion is a normal implementation.
     */

    public static void main(String[] args) {
        int[] arr = {2,3,6,7};
        System.out.println(Arrays.toString(combinationSum(arr, 7).toArray()));
    }
    
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(candidates, 0, target, 0, temp, result);
        return result;
    }

    private static void helper(int[] candidates, int start, int target, int sum, List<Integer> list,
            List<List<Integer>> result) {
        if (sum > target) {
            return;
        }

        if (sum == target) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            helper(candidates, i, target, sum + candidates[i], list, result);
            list.remove(list.size() - 1);
        }
    }

}
