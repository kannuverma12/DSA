package com.kv.stackarrayqueue;

import java.util.Arrays;

/**
 * 
 * @author karanverma
 * 
 * The selection sort algorithm sorts an array by repeatedly finding the minimum element 
 * (considering ascending order) from unsorted part and putting it at the beginning
 *
 */

public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {23, 25, -12, 54, 7};
        
        for(int lastUnsortedIndex = arr.length-1; lastUnsortedIndex>0; lastUnsortedIndex--) {
            
            int largest = 0;
            
            for(int i=1;i<=lastUnsortedIndex;i++) {
                if(arr[i] > arr[largest])
                    largest = i;
            }
            swap(arr, largest, lastUnsortedIndex);
        }
        System.out.println(Arrays.toString(arr));
    }
    
    // Approach 2 - GeeksforGeeks
    void sort(int arr[]) 
    { 
        int n = arr.length; 
  
        // One by one move boundary of unsorted subarray 
        for (int i = 0; i < n-1; i++) 
        { 
            // Find the minimum element in unsorted array 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (arr[j] < arr[min_idx]) 
                    min_idx = j; 
  
            // Swap the found minimum element with the first element
            int temp = arr[min_idx]; 
            arr[min_idx] = arr[i]; 
            arr[i] = temp; 
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
