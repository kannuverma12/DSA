package com.kv.fb;

/**
 * 
 * @author karanverma
 *
 *  Given an array and a number k, rotate the array left starting from k index.
 */
public class LeftRotateArray {
    
    public static void main(String[] args) {
        int arr[] = { 1, 3, 5, 7, 9 };
        int n = arr.length;

        int k = 2;
        leftRotate(arr, n, k);

        k = 3;
        //leftRotate(arr, n, k);

        k = 4;
        //leftRotate(arr, n, k);
    }

    static void leftRotate(int arr[], int n, int k) {
        
        //To get the starting point of rotated array
        int mod = k % n;
        System.out.println("mod = "+mod);

        // Prints the rotated array from start position
        for (int i = 0; i < n; ++i) {
            System.out.println("index = "+((i + mod) % n)+ ", Value = "+arr[(i + mod) % n] + " ");
        }

        System.out.println();
    }



}
