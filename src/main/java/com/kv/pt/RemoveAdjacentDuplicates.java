package com.kv.pt;

/**
 * 
 * @author karanverma
 *
 */
public class RemoveAdjacentDuplicates {

    public static void main(String[] args) {
        String s = "azxxzy";
        System.out.println("Remove duplicates : " + removeDuplicates(s, s.length()));
    }

    private static String removeDuplicates(String s, int n) {
        char[] arr = s.toCharArray();
        int k = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] != arr[i])
                arr[k++] = arr[i - 1];
            else {
                while (arr[i - 1] == arr[i])
                    i++;
            }
        }
        arr[k] = '\0';
        if (k != n)
            removeDuplicates(s, k);
        return s;
    }

}
