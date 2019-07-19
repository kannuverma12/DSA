package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author karanverma
 *
 *  Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *  Input: [1,1,2]
 *  Output: [ [1,1,2], [1,2,1], [2,1,1] ]
 *  
 */
public class DP9_Permutation2 {
    /*
     * Based on Permutation, we can add a set to track if an element is duplicate and no need to swap.
     */

    public static void main(String[] args) {
        int[] arr = { 1, 1, 3 };

        System.out.println("Recursive : "+Arrays.toString(permuteUnique(arr).toArray()));
        
        System.out.println("Using sets : "+Arrays.toString(permuteUniqueUsingSet(arr).toArray()));
    }
    
    
    //Method 1 : 
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(0, nums, result);
        return result;
    }
     
    private static void helper(int start, int[] nums, List<List<Integer>> result){
        if(start == nums.length-1){
            List<Integer> list = new ArrayList<>();
            for(int num: nums){
                list.add(num);
            }
            result.add(list);
            return;
        }
     
        Set<Integer> set = new HashSet<>();
     
        for(int i=start; i<nums.length; i++){
            if(set.contains(nums[i])){
                continue;
            }
            set.add(nums[i]);
     
            swap(nums, i, start);
            helper(start+1, nums, result);
            swap(nums, i, start);
        }
    }
     
    private static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // method 2 : use sets
    public static List<List<Integer>> permuteUniqueUsingSet(int[] num) {
        List<List<Integer>> returnList = new ArrayList<>();
        returnList.add(new ArrayList<Integer>());

        for (int i = 0; i < num.length; i++) {
            Set<List<Integer>> currentSet = new HashSet<>();
            for (List<Integer> l : returnList) {
                for (int j = 0; j < l.size() + 1; j++) {
                    l.add(j, num[i]);
                    List<Integer> T = new ArrayList<>(l);
                    l.remove(j);
                    currentSet.add(T);
                }
            }
            returnList = new ArrayList<>(currentSet);
        }

        return returnList;
    }
    
    

}
