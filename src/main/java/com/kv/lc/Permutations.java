package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author karanverma
 *  
 *  Given a collection of distinct integers, return all possible permutations.
 *  Input: [1,2,3]
 *  Output:
 *  [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
 */
public class Permutations {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] arr = { 1, 2, 3 };

        System.out.println("Iterative : "+Arrays.toString(permute(arr).toArray()));
        
        System.out.println("Recursive : "+Arrays.toString(permuteRecur(arr).toArray()));
    }

    /*
     * Method 1 - Iterative
     * 
     * Loop through the array, in each iteration, a new number is added to different
     * locations of results of previous iteration. Start from an empty List.
     */

    public static List<List<Integer>> permute(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        // start from an empty list
        result.add(new ArrayList<Integer>());

        for (int i = 0; i < num.length; i++) {
            // list of list in current iteration of the array num
            ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();

            for (List<Integer> l : result) {
                // # of locations to insert is largest index + 1
                for (int j = 0; j < l.size() + 1; j++) {
                    // + add num[i] to different locations
                    l.add(j, num[i]);

                    ArrayList<Integer> temp = new ArrayList<Integer>(l);
                    current.add(temp);

                    // System.out.println(temp);

                    // - remove num[i] add
                    l.remove(j);
                }
            }

            result = new ArrayList<List<Integer>>(current);
        }

        return result;
    }

    // We can also recursively solve this problem. Swap each element with each
    // element after it.
    // Method 2 - Recursively
    public static List<List<Integer>> permuteRecur(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(0, nums, result);
        return result;
    }

    private static void helper(int start, int[] nums, List<List<Integer>> result) {
        if (start == nums.length - 1) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            swap(nums, i, start);
            helper(start + 1, nums, result);
            swap(nums, i, start);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
