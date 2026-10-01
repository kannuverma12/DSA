package com.kv.lcoptimized.slidingwindow;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC 30 · Substring with Concatenation of All Words (Hard) [was: lc/L55_SubstringWithAllWordConcat]
 *
 * Pattern : run `len` independent sliding windows (one per offset mod len), stepping one word
 *           at a time; shrink from the left when a word exceeds its required count.
 * Optimal : O(n * len) time (each offset scans n/len words, each substring costs len),
 *           O(#words) space.
 * Changed : the algorithm was already the optimal one; cleaned up with merge()/getOrDefault().
 *           This is a good one to explain out loud as "k interleaved windows".
 */
public class LC0030_SubstringWithConcatenationOfAllWords {

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words.length == 0) {
            return res;
        }
        int len = words[0].length();
        int total = words.length;
        Map<String, Integer> need = new HashMap<>();
        for (String w : words) {
            need.merge(w, 1, Integer::sum);
        }
        for (int offset = 0; offset < len; offset++) {
            Map<String, Integer> window = new HashMap<>();
            int left = offset;
            int count = 0;
            for (int right = offset; right + len <= s.length(); right += len) {
                String w = s.substring(right, right + len);
                if (!need.containsKey(w)) {
                    window.clear();
                    count = 0;
                    left = right + len;
                    continue;
                }
                window.merge(w, 1, Integer::sum);
                count++;
                while (window.get(w) > need.get(w)) {
                    window.merge(s.substring(left, left + len), -1, Integer::sum);
                    left += len;
                    count--;
                }
                if (count == total) {
                    res.add(left);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Check.eqUnordered(findSubstring("barfoothefoobarman", new String[] {"foo", "bar"}), Arrays.asList(0, 9));
        Check.eq(findSubstring("wordgoodgoodgoodbestword", new String[] {"word", "good", "best", "word"}).size(), 0);
        Check.eqUnordered(findSubstring("barfoofoobarthefoobarman", new String[] {"bar", "foo", "the"}),
                Arrays.asList(6, 9, 12));
    }
}
