package com.kv.lc;

public class L64_ToLowerCaseImplementation {

    public static void main(String[] args) {
        System.out.println("Converted : "+toLowerCase("KARAN"));
    }

    public static String toLowerCase(String str) {
        int diff = 'a' - 'A';
        System.out.println(diff);
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (!((arr[i] >= (int) 'a' && arr[i] <= 'z') || (arr[i] >= 'A' && arr[i] <= 'Z')))
                continue;
            if (arr[i] < 'a')
                arr[i] = (char) (arr[i] + diff);
            else
                continue;
        }
        return new String(arr);
    }
}
