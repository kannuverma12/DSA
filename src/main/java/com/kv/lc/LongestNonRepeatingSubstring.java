package com.kv.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestNonRepeatingSubstring {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(lengthOfLongestSubstring("abssdcfg"));
    }
    
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        
        for(int i =0 , j = 0; j<n ; j++){
            if(map.containsKey(s.charAt(j))){
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j-i+1);
            map.put(s.charAt(j), j+1);
        }
        return ans;
    }
    
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

}
