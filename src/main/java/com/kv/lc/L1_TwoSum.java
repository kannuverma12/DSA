package com.kv.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author karanverma
 * <p>
 * -------- Easy ----------
 * <p>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 */
public class L1_TwoSum {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(Arrays.toString(twoSum(new int[] {2, 9, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSumTwoPassHashtable(new int[] {2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSumOnePassHashtable(new int[] {2, 7, 11, 15}, 9)));
    }
    
    //Approach 1 : Brute Force
    /*
     * Time complexity : O(n^2)O(n2). For each element, we try to find its complement by looping through the rest
     * of array which takes O(n)O(n) time. Therefore, the time complexity is O(n^2)O(n 2).
     * Space complexity : O(1)O(1).
     */
    private static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }
    
    /*  Approach 2 : Two Pass Hashtable
     * 
     */
    public static int[] twoSumTwoPassHashtable(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return null;
    }

    public static int[] twoSumOnePassHashtable(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

}
