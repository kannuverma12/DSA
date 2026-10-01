package com.kv.lcoptimized.backtracking;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LC 140 · Word Break II (Hard)                           [was: lc/hard/L49_WordBreak2]
 *
 * Pattern : top-down memo on start index: sentences(i) = for each dict word w matching at i,
 *           w + " " + each sentence of sentences(i + |w|). Only try lengths up to maxWordLen.
 * Complexity: output-bound (can be exponential, e.g. "aaaa..." with {a, aa, aaa}); the memo
 *           ensures each suffix is solved once. Without it, unbreakable suffixes are
 *           re-explored exponentially often.
 * Changed : the original iterated the entire dictionary at every index (O(n * |dict| * L)),
 *           used raw generic arrays, and rebuilt paths with string += in a loop. The second
 *           method recursed without a memo.
 */
public class LC0140_WordBreakII {

    public static List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int maxLen = 0;
        for (String w : wordDict) {
            maxLen = Math.max(maxLen, w.length());
        }
        return solve(s, 0, dict, maxLen, new HashMap<>());
    }

    private static List<String> solve(String s, int start, Set<String> dict, int maxLen,
                                      Map<Integer, List<String>> memo) {
        List<String> cached = memo.get(start);
        if (cached != null) {
            return cached;
        }
        List<String> res = new ArrayList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length() && end - start <= maxLen; end++) {
            String word = s.substring(start, end);
            if (!dict.contains(word)) {
                continue;
            }
            for (String rest : solve(s, end, dict, maxLen, memo)) {
                res.add(rest.isEmpty() ? word : word + " " + rest);
            }
        }
        memo.put(start, res);
        return res;
    }

    public static void main(String[] args) {
        Check.eqUnordered(wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")),
                Arrays.asList("cats and dog", "cat sand dog"));
        Check.eqUnordered(wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")),
                Arrays.asList("pine apple pen apple", "pineapple pen apple", "pine applepen apple"));
        Check.eq(wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")).size(), 0);
    }
}
