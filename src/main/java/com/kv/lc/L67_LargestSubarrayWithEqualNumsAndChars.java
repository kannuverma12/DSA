package com.kv.lc;

import java.util.HashMap;
import java.util.Map;

public class L67_LargestSubarrayWithEqualNumsAndChars {

    public static void main(String[] args) {
        int arr[] = {'A', 'B', 'X', 4, 6, 'X', 'a'};
        findSubArray(arr, arr.length);
    }

    // Function to find the starting and the ending index of the sub-array with equal
    // number of alphabets and numeric digits
    static void findSubArray(int arr[], int n) {
        int sum = 0;
        int maxsize = -1, startindex = 0;
        for (int i = 0; i < n; i++) {
            if (isalpha(arr[i])) {
                arr[i] = 0;
            } else {
                arr[i] = 1;
            }
        }
        // Pick a starting point as i
        for (int i = 0; i < n - 1; i++) {
            sum = (arr[i] == 0) ? -1 : 1;
            // Consider all sub-arrays starting from i
            for (int j = i + 1; j < n; j++) {
                if (arr[j] == 0)
                    sum += -1;
                else
                    sum += 1;
                // If this is a 0 sum sub-array then
                // compare it with maximum size sub-array
                // calculated so far
                if (sum == 0 && maxsize < j - i + 1) {
                    maxsize = j - i + 1;
                    startindex = i;
                }
            }
        }

        // If no valid sub-array found
        if (maxsize == -1)
            System.out.println(maxsize);
        else
            System.out.println(startindex + " " + (startindex + maxsize - 1));
    }

    static boolean isalpha(int input_char) {
        if ((input_char >= 65 && input_char <= 90)
                || (input_char >= 97 && input_char <= 122))
            return true;
        return false;
    }


    // method 2
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;
    }


}
