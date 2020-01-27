package com.kv.stackarrayqueue;

import java.util.Arrays;

public class Sort_MergeSort {

    public static void main(String[] args) {
        int[] arr = {23, 25, -12, 54, 7, -22};
        mergeSort(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] array, int start, int end) {
        if (end - start < 2)
            return;

        int mid = (end - start) / 2;

        mergeSort(array, start, mid);
        mergeSort(array, mid, end);

        merge(array, start, mid, end);
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        if (arr[mid - 1] <= arr[mid])
            return;

        int i = start;
        int j = mid;
        int tempIndex = 0;

        int[] temp = new int[end - start];

        while (i < mid && j < end) {
            temp[tempIndex++] = (arr[i] <= arr[j]) ? arr[i++] : arr[j++];
        }

        //some thing i did not understand
        System.arraycopy(arr, i, arr, start + tempIndex, mid - i);
        System.arraycopy(temp, 0, arr, start, tempIndex);

    }

}
