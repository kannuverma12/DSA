package com.kv.lc;

import java.util.Arrays;
import java.util.Set;

/**
 * 
 * @author karanverma
 *
 *  Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
 *  determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *  Note:
 *  The same word in the dictionary may be reused multiple times in the segmentation.
 *  You may assume the dictionary does not contain duplicate words.
 */
public class L48_WordBreak {
    
    /*
     * The key to solve this problem by using dynamic programming approach:
     * Define an array t[] such that t[i]==true => 0-(i-1) can be segmented using dictionary
     * Initial state t[0] == true
     */

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    public boolean wordBreak(String s, Set<String> dict) {
        boolean[] t = new boolean[s.length()+1];
        t[0] = true; //set first to be true, why?
        //Because we need initial state
 
        for(int i=0; i<s.length(); i++){
            //should continue from match position
            if(!t[i]) 
                continue;
 
            for(String a: dict){
                int len = a.length();
                int end = i + len;
                if(end > s.length())
                    continue;
 
                if(t[end]) continue;
 
                if(s.substring(i, end).equals(a)){
                    t[end] = true;
                }
            }
        }
 
        return t[s.length()];
    }
    
    //method 2
    /*
     * In Solution 2, if the size of the dictionary is very large, the time is bad. Instead we 
     * can solve the problem in O(n^2) time (n is the length of the string).
     */
    public boolean wordBreak2(String s, Set<String> wordDict) {
        int[] pos = new int[s.length()+1];
     
        Arrays.fill(pos, -1);
     
        pos[0]=0;
     
        for(int i=0; i<s.length(); i++){
            if(pos[i]!=-1){
                for(int j=i+1; j<=s.length(); j++){
                    String sub = s.substring(i, j);
                    if(wordDict.contains(sub)){
                        pos[j]=i;
                    }
                } 
            }
        }
     
        return pos[s.length()]!=-1;
    }

}
