package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author karanverma
 *
 *  Given an array of strings, group anagrams together.
 *  Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 *  Output:
 *  [
 *      ["ate","eat","tea"],
 *      ["nat","tan"],
 *      ["bat"]
 *  ]
 */
public class GroupAnagrams {

    public static void main(String[] args) {
//        int[] aa = {1,2,3};
//        System.out.println(Arrays.toString(aa));
//        for(int i : aa) {
//            int x = aa[i]++;
//            System.out.println("x = "+x);
//        }
//        System.out.println(Arrays.toString(aa));
        
        char x = '\u0000';
        System.out.println("x = "+(char)(x++));
        System.out.println("x = "+(char)(x));
        
        
        String[] arr = {"zat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Anagram Groups : "+Arrays.deepToString(groupAnagrams(arr).toArray()));

    }
    
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
     
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for(String str: strs){
            char[] arr = new char[26];
            //System.out.println("str : "+str);
            for(int i=0; i<str.length(); i++){
                int index = str.charAt(i)-'a';
                //System.out.println("index = "+index+", arr[index] = "+arr[index]);
                arr[index]++;
                //System.out.println("after arr[index] = "+new String(""+(char)arr[index]));
            }
            String ns = new String(arr);
            //System.out.println("arr = "+Arrays.toString(arr)+", ns = "+ns + ", value of = "+String.copyValueOf(arr));
     
            if(map.containsKey(ns)){
                //System.out.println("contains key ns = "+ns);
                map.get(ns).add(str);
            }else{
                ArrayList<String> al = new ArrayList<String>();
                //System.out.println("new ns = "+ns);
                al.add(str);
                map.put(ns, al);
            }
        }
     
        result.addAll(map.values());
     
        return result;
    }
    

}
