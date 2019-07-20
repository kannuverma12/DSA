package com.kv.lc;

import java.util.Arrays;

/**
 * 
 * @author karanverma
 * 
 *  Given a sorted array of integers, find the starting and ending position of a given target value. 
 *  Your algorithm's runtime complexity must be in the order of O(log n). If the target is not 
 *  found in the array, return [-1, -1]. For example, given [5, 7, 7, 8, 8, 10] and 
 *  target value 8, return [3, 4].
 */
public class L40_FindFirstAndLastPositionOfElementInSortedArray {
    /*
     * Based on the requirement of O(log n), this is a binary search problem apparently.
     */

    public static void main(String[] args) {

        int[] arr = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange(arr, 8)));
    }
    
    public static int[] searchRange(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        int first = l;
        if (l < nums.length && nums[l] == target) {// l is in boundary and is the target
            l = 0;
            r = nums.length - 1;
            while (l < r) {
                int m = l + (r - l + 1) / 2;
                if (nums[m] > target) {
                    r = m - 1;
                } else {
                    l = m;
                }
            }

            return new int[] { first, r };
        }

        return new int[] { -1, -1 };
    }

}
