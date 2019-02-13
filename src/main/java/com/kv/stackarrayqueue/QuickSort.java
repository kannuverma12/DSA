package com.kv.stackarrayqueue;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {23, 25, -12, 54, 7, -22};
        
        Arrays.sort(arr);
        Arrays.parallelSort(arr);
        
        quickSort(arr, 0, arr.length);
        
        System.out.println(Arrays.toString(arr));
    }
    
    public static void quickSort(int[] input, int start, int end) {
        if(end - start < 2)
            return;
        
        int pivot = partition(input, start, end);
        quickSort(input, start, pivot);
        quickSort(input, pivot+1, end);
        
        
    }

    private static int partition(int[] input, int start, int end) {
        // this is using first element as pivot
        int pivot = input[start];
        int i = start, j =end;
        
        while(i < j) {
            //empty loop
            while(i < j && input[--j] >= pivot) ;
            
            if(i < j) {
                input[i] = input[j];
            }
            
            //empty loop
            while(i <  j && input[++i] <= pivot);
            
            if(i < j) {
                input[j] = input[i];
            }
        }
        input[j] = pivot;
        
        return j;
    }

}
