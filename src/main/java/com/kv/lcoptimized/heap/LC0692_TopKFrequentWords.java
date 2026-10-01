package com.kv.lcoptimized.heap;

import com.kv.lcoptimized.common.Check;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LC 692 · Top K Frequent Words + "Top K words in a large file"
 *   [was: lc/FindTopKOccurancesInALargeFile, lc/FindTopKOccurancesInALargeFileUsingHM]
 *
 * Count with a HashMap, then keep a size-k min-heap ordered by (count asc, word desc) so the
 * root is always the weakest candidate. O(N + U log k) time, O(U + k) space (U = unique words).
 * Bucket sort by count gives O(N + U) when k is large.
 *
 * Changed : BUG FIX - getWordCount() built a separate count map PER LINE and pushed each line's
 *           partial counts into the heap, so a word seen on 100 lines entered the heap 100
 *           times with count 1. The HashMap version looped forever (`while (true)` + sleep, a
 *           tail -f), sorted the whole map (O(U log U)), and hard-coded /Users/... paths.
 *           This version streams any Reader once, splits on non-letters, and breaks ties
 *           deterministically.
 *
 * L6/L7 follow-ups (very common at Google):
 *  - File bigger than RAM -> hash-partition words into P files, top-k per file, merge heaps.
 *  - Distributed -> MapReduce word count, then per-reducer top-k, then global merge.
 *  - Unbounded stream, approximate is OK -> Count-Min Sketch + heap, or Misra-Gries /
 *    Space-Saving heavy hitters with O(k) memory.
 *  - Sliding time window -> per-bucket counts in a ring buffer.
 */
public class LC0692_TopKFrequentWords {

    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String w : words) {
            count.merge(w, 1, Integer::sum);
        }
        return topK(count, k);
    }

    public static List<String> topKFromReader(BufferedReader reader, int k) throws IOException {
        Map<String, Integer> count = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null) {
            for (String token : line.toLowerCase().split("[^a-z0-9']+")) {
                if (!token.isEmpty()) {
                    count.merge(token, 1, Integer::sum);
                }
            }
        }
        return topK(count, k);
    }

    private static List<String> topK(Map<String, Integer> count, int k) {
        Comparator<Map.Entry<String, Integer>> weakestFirst = (a, b) -> a.getValue().equals(b.getValue())
                ? b.getKey().compareTo(a.getKey())
                : Integer.compare(a.getValue(), b.getValue());
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(k + 1, weakestFirst);
        for (Map.Entry<String, Integer> e : count.entrySet()) {
            heap.add(e);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        List<String> out = new ArrayList<>(heap.size());
        while (!heap.isEmpty()) {
            out.add(heap.poll().getKey());
        }
        Collections.reverse(out);
        return out;
    }

    public static void main(String[] args) throws IOException {
        Check.eq(topKFrequent(new String[] {"i", "love", "leetcode", "i", "love", "coding"}, 2),
                Arrays.asList("i", "love"));
        Check.eq(topKFrequent(new String[] {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4),
                Arrays.asList("the", "is", "sunny", "day"));
        String text = "Coding is great\nSearch Engine are engine\nGoogle is nice search engine\nBing is also a nice engine";
        Check.eq(topKFromReader(new BufferedReader(new StringReader(text)), 3), Arrays.asList("engine", "is", "nice"));
    }
}
