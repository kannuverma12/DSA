package com.kv.stackarrayqueue;

import java.util.Arrays;


/**
 * 
 * @author karanverma
 *  Bubble Sort is the simplest sorting algorithm that works by repeatedly swapping the 
 *  adjacent elements if they are in wrong order.
 */
public class BubbleSort {
    
    public static void main(String... strings) {
        int[] arr = {23, 25, -12, 54, 7};
        
        for(int lastUnsortedIndex = arr.length-1; lastUnsortedIndex>0; lastUnsortedIndex--) {
            for(int i=0;i<lastUnsortedIndex;i++) {
                swap(arr, i, i+1);
            }
        }
        
        System.out.println(Arrays.toString(arr));
    }
    
    // Approach 2 - geeksforgeeks   
    void bubbleSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[i] 
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    } 

    private static void swap(int[] arr, int i, int j) {
        if(i == j)
            return;
        if(arr[i] > arr[j]) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        
    }

}
