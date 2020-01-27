package com.kv.lc;

import java.util.Comparator;
import java.util.PriorityQueue;

public class L68_FindKSmallestElementsInArray {

    public static void printSmall(int arr[], int n, int k)
    {
        // For each arr[i] find whether it is a part of n-smallest with insertion sort concept
        for (int i = k; i < n; ++i) {
            // Find largest from top n-element
            int max_var = arr[k - 1];
            int pos = k - 1;
            for (int j = k - 2; j >= 0; j--) {
                if (arr[j] > max_var) {
                    max_var = arr[j];
                    pos = j;
                }
            }

            // If largest is greater than arr[i]
            // shift all element one place left
            if (max_var > arr[i]) {
                int j = pos;
                while (j < k - 1) {
                    arr[j] = arr[j + 1];
                    j++;
                }
                // make arr[k-1] = arr[i]
                arr[k - 1] = arr[i];
            }
        }
        // print result
        for (int i = 0; i < k; i++)
            System.out.print(arr[i] + " ");
    }

    // Driver function
    public static void main(String argc[])
    {
        int[] arr = { 1, 5, 8, 9, 6, 7, 3, 4, 2, 0 };
        int n = 10;
        int k = 5;
        printSmall(arr, n, k);

        int[] ret = smallestK(arr, k);
    }

    //method 2
    static int[] smallestK(int[] array, int k) {
        if (k <= 0 && k > array.length){
            throw new IllegalArgumentException();
        }
        PriorityQueue<Integer> heap = getKMaxHeap(array, k);
        return heapTointArray(heap);
    }

    /* Create max heap of smallest k elements. */
    static PriorityQueue<Integer> getKMaxHeap(int[] array,
            int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, new MaxHeapComparator());
        for (int a : array) {
            if (heap.size() < k) {  //If space remaining
                heap.add(a);
            } else if (a < heap.peek()) {   //If full and top is small
                 heap.poll();       //remove highest
                 heap.add(a);       //insert new element
            }
        }
        return heap;

    }

    /*Convert heap to int array. */
    static int[] heapTointArray(PriorityQueue<Integer> heap) {
        int[] array = new int[heap.size()];
        while (!heap.isEmpty()) {
            array[heap.size() - 1] = heap.poll();
        }
        return array;

    }

    static class MaxHeapComparator implements Comparator<Integer> {
        public int compare(Integer x, Integer y) {
            return y - x;

        }

    }
}
