package com.kv.hr;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinimumSwaps2 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    static int minimumSwaps(int[] arr) {
        Map<Integer, Integer> numberToIndex = IntStream.range(0, arr.length).boxed()
                .collect(Collectors.toMap(i -> arr[i], Function.identity()));

        int swapNum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i + 1) {
                int otherIndex = numberToIndex.get(i + 1);

                numberToIndex.put(arr[i], otherIndex);
                numberToIndex.put(i + 1, i);

                swap(arr, i, otherIndex);

                swapNum++;
            }
        }
        return swapNum;

    }
    
    static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

}
