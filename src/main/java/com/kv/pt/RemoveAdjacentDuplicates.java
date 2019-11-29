package com.kv.pt;

/**
 * 
 * @author karanverma
 *
 */
public class RemoveAdjacentDuplicates {

    public static void main(String[] args) {
        String s = "azxxxxzy";
        System.out.println("Remove duplicates : " + removeDuplicates(s, s.length()));
        
        System.out.println("remove dup 2 : "+removeDuplicates2(s, 3));
        
        System.out.println("remove dup 3 : "+process(s));
    }

    private static String removeDuplicates(String s, int n) {
        char[] arr = s.toCharArray();
        int k = 0, i=0;
        
        int start = 0, count = 0;
        for ( i = 1; i < arr.length; i++) {
            if (arr[i - 1] != arr[i])
                arr[k++] = arr[i - 1];
            else {
                while (arr[i - 1] == arr[i]) {
                    count++;
                    i++;
                }
            }
        }
        arr[k++] = arr[i-1];  
        arr[k] = '\0';
        //if (k != n)
          //  removeDuplicates(s, k);
        return s;
    }
    
    public static String process(String s) {
        char[] chars = s.toCharArray();
        int len = 0;      // Length of result (initially empty)

        for (int i = 0; i < chars.length; i++) {
            if (len == 0 || chars[i] != chars[len - 1]) {
                chars[len++] = chars[i];   // Push one char onto "stack"
            } else {
                len--;                     // Pop one char from "stack"
            }
        }
        return new String(chars, 0, len);
    }
    
    public static String removeDuplicates2(String s, int burst)
    {
        char[] chars = s.toCharArray();
        char prev = '\0';
        int k = 0;
        int count = 0;
        for (int i = 0; i < chars.length; i++)
        {
            if (prev != chars[i]) {
                count = 0;
                chars[k++] = chars[i];
                prev = chars[i];
            }else {
                count++;
            }
            if(count >= burst) {
                i += count;
            }
                
        }

        return new String(chars).substring(0, k);
    }
    
    private static String removeDuplicatesWithBurst(String s, int n, int x) {
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
