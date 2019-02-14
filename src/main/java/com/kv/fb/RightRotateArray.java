package com.kv.fb;

import java.util.Arrays;

/**
 * 
 * @author karanverma
 *
 *  Given an array and a number n, rotate the array from last n numbers.
 *  
 *  "k=2, l=[1,2,3,4,5,6] 
 *  output: l=[5,6,1,2,3,4] In place O(1) space complexity"
 */
public class RightRotateArray {
    

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        int n = arr.length;
        int k = 3;

        rightRotate(arr, k, n);
        System.out.println(Arrays.toString(arr));

    }
    
 // Function to right rotate
    // arr[] of size n by d
    static void rightRotate(int arr[], int d, int n) {
        reverseArray(arr, 0, n - 1);
        reverseArray(arr, 0, d - 1);
        reverseArray(arr, d, n - 1);
    }

    static void reverseArray(int arr[], int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    



}
