package com.kv.lcoptimized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Kv_2026 {

    public static void main(String... args) {
        String s = "karan";
        Map<Character, Integer> freq = new HashMap<>();
        for(Character c : s.toCharArray())
            freq.merge(c, 1, Integer::sum);

        
        Map<String, List<String>> groups = new HashMap<>();
        // groups.computeIfAbsent(key, k -> new ArrayList<>().add(word))

        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i<10; i++)
            adj.add(new ArrayList<>());
        // for(int[] e : edges) 

        PriorityQueue<Integer> minHeap = new  PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        PriorityQueue<int[]> byDist = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));


        // Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] dp = new int[10 +1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int[][] memo = new int[5][4];

        for(int[] row : memo)
            Arrays.fill(row, -1);

        int[] copy = Arrays.copyOf(dp, dp.length);


        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.reverse();
        sb.setLength(sb.length()-1);
        char[] charArr = sb.toString().toCharArray();
        Arrays.sort(charArr);
        String key = new String(charArr);



    
    }

}
