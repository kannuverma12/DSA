package com.kv.lcoptimized.graphs;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LC 269 · Alien Dictionary (Hard)                        [NEW - classic Google question]
 *
 * Build edges from each ADJACENT pair of words: the first differing character c1 -> c2.
 * Then topologically sort the letters (Kahn). A cycle, or a word followed by its own strict
 * prefix ("abc" before "ab"), means the input is invalid -> "".
 * Complexity: O(total characters) to build + O(26 + E) to sort.
 *
 * Talking points: only adjacent pairs are needed (ordering is transitive); letters that never
 * appear in a comparison can go anywhere; if the interviewer wants "is the order UNIQUE?", check
 * that the queue never holds more than one letter.
 */
public class LC0269_AlienDictionary {

    public static String alienOrder(String[] words) {
        boolean[][] edge = new boolean[26][26];
        int[] indegree = new int[26];
        boolean[] present = new boolean[26];
        for (String w : words) {
            for (char c : w.toCharArray()) {
                present[c - 'a'] = true;
            }
        }
        for (int i = 0; i + 1 < words.length; i++) {
            String a = words[i];
            String b = words[i + 1];
            int len = Math.min(a.length(), b.length());
            int k = 0;
            while (k < len && a.charAt(k) == b.charAt(k)) {
                k++;
            }
            if (k == len) {
                if (a.length() > b.length()) {
                    return "";                       // "abc" before "ab" is impossible
                }
                continue;
            }
            int u = a.charAt(k) - 'a';
            int v = b.charAt(k) - 'a';
            if (!edge[u][v]) {
                edge[u][v] = true;
                indegree[v]++;
            }
        }
        Deque<Integer> queue = new ArrayDeque<>();
        int letters = 0;
        for (int c = 0; c < 26; c++) {
            if (present[c]) {
                letters++;
                if (indegree[c] == 0) {
                    queue.add(c);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            sb.append((char) ('a' + u));
            for (int v = 0; v < 26; v++) {
                if (edge[u][v] && --indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }
        return sb.length() == letters ? sb.toString() : "";
    }

    public static void main(String[] args) {
        Check.eq(alienOrder(new String[] {"wrt", "wrf", "er", "ett", "rftt"}), "wertf");
        Check.eq(alienOrder(new String[] {"z", "x"}), "zx");
        Check.eq(alienOrder(new String[] {"z", "x", "z"}), "");
        Check.eq(alienOrder(new String[] {"abc", "ab"}), "");
    }
}
