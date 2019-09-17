package com.kv.lc;


/*
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".
Example 1:
Input: ["flower","flow","flight"]
Output: "fl"
 */
public class L69_LongestCommonPrefix {
    public static void main(String[] args) {
        String[] arr = {"flower","flow","flight"};
        System.out.println("Method 1 : "+longestCommonPrefix(arr));
        System.out.println("Method 2 : "+longestCommonPrefix2(arr));
    }

//    public static String longestCommonPrefix(String[] strs) {
//        if (strs.length == 0) return "";
//        String prefix = strs[0];
//        for (int i = 1; i < strs.length; i++)
//            while (strs[i].indexOf(prefix) != 0) {
//                prefix = prefix.substring(0, prefix.length() - 1);
//                if (prefix.isEmpty()) return "";
//            }
//        return prefix;
//    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    //Method 2
    public static String longestCommonPrefix2(String[] strs) {
        if(strs==null || strs.length ==0){
            return "";
        }

        if(strs.length == 1){
            return strs[0];
        }

        int i=0;
        while(true){
            boolean flag = true;
            for(int j=1; j<strs.length; j++){
                if(strs[j].length()<=i || strs[j-1].length() <=i
                        || strs[j].charAt(i) != strs[j-1].charAt(i)){
                    flag = false;
                    break;
                }
            }

            if(flag){
                i++;
            }else{
                break;
            }
        }

        return strs[0].substring(0, i);
    }

    // Method 3 - Using binary search

    // A Function to find the string having the
    // minimum length and returns that length
    static int findMinLength(String arr[], int n)
    {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= (n - 1); i++)
        {
            if (arr[i].length() < min) {
                min = arr[i].length();
            }
        }
        return min;
    }

    static boolean allContainsPrefix(String arr[], int n,
            String str, int start, int end)
    {
        for (int i = 0; i <= (n - 1); i++)
        {
            String arr_i = arr[i];

            for (int j = start; j <= end; j++)
                if (arr_i.charAt(j) != str.charAt(j))
                    return false;
        }
        return true;
    }

    // A Function that returns the longest common prefix
    // from the array of strings
    static String commonPrefix(String arr[], int n)
    {
        int index = findMinLength(arr, n);
        String prefix = ""; // Our resultant string

        // We will do an in-place binary search on the
        // first string of the array in the range 0 to
        // index
        int low = 0, high = index-1;
        while (low <= high) {

            // Same as (low + high)/2, but avoids
            // overflow for large low and high
            int mid = low + (high - low) / 2;

            if (allContainsPrefix(arr, n, arr[0], low,
                    mid))
            {
                // If all the strings in the input array
                // contains this prefix then append this
                // substring to our answer
                prefix = prefix + arr[0].substring(low,
                        mid + 1);

                // And then go for the right part
                low = mid + 1;
            }
            else // Go for the left part
            {
                high = mid - 1;
            }
        }

        return prefix;
    }
}
