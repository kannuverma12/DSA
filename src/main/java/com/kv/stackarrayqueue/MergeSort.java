package com.kv.stackarrayqueue;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {23, 25, -12, 54, 7, -22};
        mergeSort(arr, 0, arr.length);
        
        
        System.out.println(Arrays.toString(arr));

    }
    
    public static void mergeSort(int[] array, int start, int end) {
        if(end-start < 2)
            return;
        
        int mid = (end-start) / 2;
        
        mergeSort(array, start, mid);
        mergeSort(array, mid, end);
        
        merge(array, start, mid, end);
    }

    private static void merge(int[] input, int start, int mid, int end) {
        if(input[mid -1] <= input[mid])
            return;
        
        int i = start;
        int j = mid;
        int tempIndex = 0;
        
        int[] temp = new int[end -start];
        
        while(i< mid && j < end) {
            temp[tempIndex++] = (input[i] <= input[j]) ? input[i++] : input[j++];
        }
        
        //some thing i did not understand
        System.arraycopy(input, i, input, start + tempIndex, mid-i);
        System.arraycopy(temp, 0, input, start, tempIndex); 
        
    }

}
