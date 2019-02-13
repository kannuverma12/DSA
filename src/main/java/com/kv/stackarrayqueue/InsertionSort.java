package com.kv.stackarrayqueue;

import java.util.Arrays;

/**
 * 
 * @author karanverma
 *  Insertion sort is a simple sorting algorithm that works the way we sort playing cards in our hands.
 *  
 */

public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {23, 25, -12, 54, 7, -22};
        
        for(int firstUnsortedIndex = 1; firstUnsortedIndex<arr.length; firstUnsortedIndex++) {
            
            int newElement = arr[firstUnsortedIndex];
            int i;
            for(i=firstUnsortedIndex;i>0 && arr[i-1] > newElement ;i--) {
                arr[i] = arr[i-1];
            }
            arr[i] = newElement;
                    
        }
        
        System.out.println(Arrays.toString(arr));

    }
    
    // Approach 2 - GeeksforGeeks
    void sort(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=1; i<n; ++i) 
        { 
            int key = arr[i]; 
            int j = i-1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j>=0 && arr[j] > key) 
            { 
                arr[j+1] = arr[j]; 
                j = j-1; 
            } 
            arr[j+1] = key; 
        } 
    } 

}
