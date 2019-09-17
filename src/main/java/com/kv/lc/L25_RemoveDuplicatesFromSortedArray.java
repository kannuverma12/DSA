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
public class L25_RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] arr = {1, 1, 2};
        System.out.println("Original length : " + arr.length);
        System.out.println("Length after removing duplicates : " + removeDuplicates(arr, arr.length));
    }

    // emthod 1 - fails for {1,1,2}
    public static int removeDuplicates(int[] arr) {
        if (arr.length <= 2)
            return arr.length;

        int prev = 1; // point to previous
        int curr = 2; // point to current

        while (curr < arr.length) {
            if (arr[curr] == arr[prev] && arr[curr] == arr[prev - 1]) {
                curr++;
            } else {
                prev++;
                arr[prev] = arr[curr];
                curr++;
            }
        }

        return prev + 1;
    }

    // method 2 - use this
    static int removeDuplicates(int arr[], int n)
    {
        if (n == 0 || n == 1)
            return n;

        // To store index of next unique element
        int j = 0;

        // Doing same as done in Method 1
        // Just maintaining another updated index i.e. j
        for (int i = 0; i < n-1; i++)
            if (arr[i] != arr[i+1])
                arr[j++] = arr[i];

        arr[j++] = arr[n-1];

        return j;
    }

}
