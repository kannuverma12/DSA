package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most 
 *  twice and return the new length.
 *  Do not allocate extra space for another array, you must do this by modifying the input array 
 *  in-place with O(1) extra memory.
 *  
 *  Given nums = [1,1,1,2,2,3],
 *  Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 *  It doesn't matter what you leave beyond the returned length.
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,3};
        System.out.println("Original length : "+arr.length);
        System.out.println("Length after removing duplicates : "+removeDuplicates(arr));
    }
    
    public static int removeDuplicates(int[] A) {
        if (A.length <= 2)
            return A.length;
 
        int prev = 1; // point to previous
        int curr = 2; // point to current
 
        while (curr < A.length) {
            if (A[curr] == A[prev] && A[curr] == A[prev - 1]) {
                curr++;
            } else {
                prev++;
                A[prev] = A[curr];
                curr++;
            }
        }
 
        return prev + 1;
    }

}
