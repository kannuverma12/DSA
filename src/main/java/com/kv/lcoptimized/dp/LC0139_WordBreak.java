package com.kv.lcoptimized.dp;

import com.kv.lcoptimized.common.Check;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LC 139 · Word Break (Medium)                            [was: lc/L48_WordBreak]
 *
 * Pattern : ok[i] = prefix of length i can be segmented. ok[i] |= ok[j] && s[j..i) in dict,
 *           trying only j with i - j <= maxWordLen.
 * Optimal : O(n * L) substring checks (L = max word length), each O(L) to hash -> O(n * L^2).
 *           A trie walk from each ok[j] avoids re-hashing: O(n * L).
 * Changed : the original version 1 looped the whole dictionary per index (O(n * |dict| * L)).
 *           Version 2 tried every j (O(n^2) substrings). Bounding by maxLen is the key fix.
 *           A trie variant is included since Google often asks "dictionary is huge".
 */
public class LC0139_WordBreak {

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int maxLen = 0;
        for (String w : wordDict) {
            maxLen = Math.max(maxLen, w.length());
        }
        boolean[] ok = new boolean[s.length() + 1];
        ok[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= Math.max(0, i - maxLen) && !ok[i]; j--) {
                ok[i] = ok[j] && dict.contains(s.substring(j, i));
            }
        }
        return ok[s.length()];
    }

    /** Trie version: from each reachable start, walk the trie forward, marking reachable ends. */
    public static boolean wordBreakTrie(String s, List<String> wordDict) {
        int[][] next = new int[1 + wordDict.stream().mapToInt(String::length).sum()][26];
        boolean[] end = new boolean[next.length];
        int nodes = 1;
        for (String w : wordDict) {
            int cur = 0;
            for (char c : w.toCharArray()) {
                if (next[cur][c - 'a'] == 0) {
                    next[cur][c - 'a'] = nodes++;
                }
                cur = next[cur][c - 'a'];
            }
            end[cur] = true;
        }
        boolean[] ok = new boolean[s.length() + 1];
        ok[0] = true;
        for (int start = 0; start < s.length(); start++) {
            if (!ok[start]) {
                continue;
            }
            int cur = 0;
            for (int i = start; i < s.length(); i++) {
                cur = next[cur][s.charAt(i) - 'a'];
                if (cur == 0) {
                    break;
                }
                if (end[cur]) {
                    ok[i + 1] = true;
                }
            }
        }
        return ok[s.length()];
    }

    public static void main(String[] args) {
        Check.isTrue(wordBreak("leetcode", Arrays.asList("leet", "code")));
        Check.isTrue(wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        Check.isTrue(!wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
        Check.isTrue(wordBreakTrie("applepenapple", Arrays.asList("apple", "pen")));
        Check.isTrue(!wordBreakTrie("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }
}
