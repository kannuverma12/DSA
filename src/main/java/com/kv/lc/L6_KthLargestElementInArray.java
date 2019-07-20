package com.kv.lc;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author karanverma
 *  Find the kth largest element in an unsorted array. Note that it is the kth largest element 
 *  in the sorted order, not the kth distinct element.
 *  For example, given [3,2,1,5,6,4] and k = 2, return 5.
 */
public class L6_KthLargestElementInArray {

    public static void main(String[] args) {
        int[] arr = {3,2,1,5,6,4};
        int k = 2;
        
        System.out.println(k + " Kth Largest using queue : "+findKthLargestUsingHeap(arr, k));
    }
    
    /* Method 1
     * Time is O(nlog(n)). The problem of this solution is that sorting all elements is 
     * not necessary and is a overkill for getting just one element.
     */
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
    
    /* Method 2
     * We can use a min heap to solve this problem. The heap stores the top k largest elements.
     * The top of the heap is the Kth Largest element and all other elements are greater than 
     * the heap top. Whenever the size is greater than k, delete the min. 
     * Time complexity is O(nlog(k)). Space complexity is O(k) for storing the top k numbers
     */
    public static int findKthLargestUsingHeap(int[] nums, int k) {
        /*
         * Creates a PriorityQueue with the specified initial capacity that orders its
         * elements according to their natural ordering.
         */
        PriorityQueue<Integer> q = new PriorityQueue<>(k);
        for (int i : nums) {
            q.offer(i);

            if (q.size() > k) {
                int x = q.poll();
            }
        }

        return q.peek();
    }
    
    /* Method3
     * Average case time is O(n), worst case time is O(n^2).
     */
    public static int findKthLargestUsingQuickSort(int[] nums, int k) {
        if (k < 1 || nums == null) {
            return 0;
        }
     
        return getKth(nums.length - k +1, nums, 0, nums.length - 1);
    }
     
    public static int getKth(int k, int[] nums, int start, int end) {
     
        int pivot = nums[end];
     
        int left = start;
        int right = end;
     
        while (true) {
     
            while (nums[left] < pivot && left < right) {
                left++;
            }
     
            while (nums[right] >= pivot && right > left) {
                right--;
            }
     
            if (left == right) {
                break;
            }
     
            swap(nums, left, right);
        }
     
        swap(nums, left, end);
     
        if (k == left + 1) {
            return pivot;
        } else if (k < left + 1) {
            return getKth(k, nums, start, left - 1);
        } else {
            return getKth(k, nums, left + 1, end);
        }
    }
     
    public static void swap(int[] nums, int n1, int n2) {
        int tmp = nums[n1];
        nums[n1] = nums[n2];
        nums[n2] = tmp;
    }

}
