package com.kv.lc;

/**
 *  @author karanverma
 *  Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 *  (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *  You are given a target value to search.
 */
public class L7_SearchInRotatedSortedArray {
    
    /*
     * In order to use binary search on the rotated sorted array, we need to determine how to 
     * update the left and right pointers. There are two major cases as shown below:
     * Once the two cases are identified, the problem is straightforward to solve. 
     * We only need to check if the target element is in the sorted side, and based on that move 
     * left or right pointers.
     */

    public static void main(String[] args) {
        int[] arr = {4,5,6,7,0,1,2};
        System.out.println("Searching 7 - "+searchRecursive(arr, 7));
        
        System.out.println("searching 0 - "+searchIterative(arr, 0));

    }
    
    //Recursive
    public static int searchRecursive(int[] nums, int target) {

        return binarySearch(nums, 0, nums.length - 1, target);
    }
     
    public static int binarySearch(int[] nums, int left, int right, int target) {
        if (left > right)
            return -1;

        int mid = left + (right - left) / 2;

        if (target == nums[mid])
            return mid;

        if (nums[left] <= nums[mid]) {
            if (nums[left] <= target && target < nums[mid]) {
                return binarySearch(nums, left, mid - 1, target);
            } else {
                return binarySearch(nums, mid + 1, right, target);
            }
        } else {
            if (nums[mid] < target && target <= nums[right]) {
                return binarySearch(nums, mid + 1, right, target);
            } else {
                return binarySearch(nums, left, mid - 1, target);
            }
        }
    }
    
    //iterative
    public static int searchIterative(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid])
                return mid;

            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

}
