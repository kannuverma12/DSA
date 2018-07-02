package com.kv.stackarrayqueue;


/**
 * @author karan.verma
 *https://www.geeksforgeeks.org/maximum-absolute-difference-between-sum-of-two-contiguous-sub-arrays/
 */

/*
 * Input: [-2, -3, 4, -1, -2, 1, 5, -3]
Output: 12
Two subarrays are [-2, -3] and [4, -1, -2, 1, 5]

Input: [2, -1, -2, 1, -4, 2, 8]
Output: 16
Two subarrays are [-1, -2, 1, -4] and [2, 8] 


 */


/*Solution
 * 
 * The idea is for each index i in given array arr[0…n-1], compute maximum and minimum sum subarrays that lie in subarrays arr[0…i] and arr[i+1 …n-1]. We maintain four arrays that store the maximum and minimum sums in the subarrays arr[0…i] and arr[i+1 … n-1] for every index i in the array.

leftMax[] : An element leftMax[i] of this 
            array stores the maximum value 
            in subarray arr[0..i]

leftMin[] : An element leftMin[i] of this 
            array stores the minimum value
            in subarray arr[0..i]

rightMax[] : An element rightMax[i] of this 
             array stores the maximum value 
             in subarray arr[i+1..n-1]

rightMin[] : An element rightMin[i] of this
             array stores the minimum value
             in subarray arr[i+1..n-1] 
 * 
 */

/*
 * 
 * We can build above four arrays in O(n) time by using Kadane Algorithm.

In order to calculate maximum sum subarray that lies in arr[0…i], we run Kadane Algorithm from 0 to n-1 and to find maximum sum subarray that lies in arr[i+1 … n-1], we run Kadane Algorithm from n-1 to 0.

Kadane’s algorithm can be modified to find minimum absolute sum of a subarray as well. The idea is to change the sign of each element in the array and run Kadane Algorithm to find maximum sum subarray that lies in arr[0…i] and arr[i+1 … n-1]. Now invert the sign of maximum subarray sum found. That will be our minimum subarray sum. This idea is taken from here.
Now from above four arrays, we can easily find maximum absolute difference between the sum of two contiguous sub-arrays. For each index i, take maximum of

abs(max sum subarray that lies in arr[0…i] – min sum subarray that lies in arr[i+1…n-1])
abs(min sum subarray that lies in arr[0…i] – max sum subarray that lies in arr[i+1…n-1])
Below is the implementation of above idea.
 */
public class MaxDiffBtwSumOfTwoContiguousSubarray {

	public static void main(String[] args) {
		int a[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
        int n = a.length;
        System.out.print(findMaxAbsDiff(a, n));

	}
	
	// Find maximum subarray sum for subarray [0..i] using standard Kadane's algorithm.
    // This version of Kadane's Algorithm will  work if all numbers are negative.
    static int maxLeftSubArraySum(int a[], int size, 
                                          int sum[])
    {
        int max_so_far = a[0];
        int curr_max = a[0];
        sum[0] = max_so_far;

        for (int i = 1; i < size; i++) {
            curr_max = Math.max(a[i], curr_max + a[i]);
            max_so_far = Math.max(max_so_far, curr_max);
            sum[i] = max_so_far;
        }

        return max_so_far;
    }

    // Find maximum subarray sum for subarray [i..n] using Kadane's algorithm. This version of Kadane's
    // Algorithm will work if all numbers are negative
    static int maxRightSubArraySum(int a[], int n, int sum[])
    {
        int max_so_far = a[n];
        int curr_max = a[n];
        sum[n] = max_so_far;

        for (int i = n - 1; i >= 0; i--) {
            curr_max = Math.max(a[i], curr_max + a[i]);
            max_so_far = Math.max(max_so_far, curr_max);
            sum[i] = max_so_far;
        }

        return max_so_far;
    }

    // The function finds two non-overlapping contiguous sub-arrays such that the absolute difference
    // between the sum of two sub-array is maximum.
    static int findMaxAbsDiff(int arr[], int n)
    {
        // create and build an array that stores
        // maximum sums of subarrays that lie in arr[0...i]
        int leftMax[] = new int[n];
        maxLeftSubArraySum(arr, n, leftMax);

        // create and build an array that stores
        // maximum sums of subarrays that lie in
        // arr[i+1...n-1]
        int rightMax[] = new int[n];
        maxRightSubArraySum(arr, n - 1, rightMax);

        // Invert array (change sign) to find minumum sum subarrays.
        int invertArr[] = new int[n];
        for (int i = 0; i < n; i++)
            invertArr[i] = -arr[i];

        // create and build an array that stores
        // minimum sums of subarrays that lie in arr[0...i]
        int leftMin[] = new int[n];
        maxLeftSubArraySum(invertArr, n, leftMin);
        for (int i = 0; i < n; i++)
            leftMin[i] = -leftMin[i];

        // create and build an array that stores
        // minimum sums of subarrays that lie in arr[i+1...n-1]
        int rightMin[] = new int[n];
        maxRightSubArraySum(invertArr, n - 1, rightMin);
        for (int i = 0; i < n; i++)
            rightMin[i] = -rightMin[i];

        int result = -2147483648;
        for (int i = 0; i < n - 1; i++) {
            
        /* For each index i, take maximum of
        1. abs(max sum subarray that lies in arr[0...i] -
            min sum subarray that lies in arr[i+1...n-1])
        2. abs(min sum subarray that lies in arr[0...i] -
            max sum subarray that lies in arr[i+1...n-1]) */
            int absValue = Math.max(Math.abs(leftMax[i] - rightMin[i + 1]),
                                    Math.abs(leftMin[i] - rightMax[i + 1]));
            if (absValue > result)
                result = absValue;
        }

        return result;
    }

}
