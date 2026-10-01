package com.kv.lcoptimized.strings;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC 49 · Group Anagrams (Medium)                         [was: lc/medium/L10_GroupAnagrams]
 *
 * Pattern : canonical key -> bucket. Key = 26 letter counts (O(L)), or the sorted string
 *           (O(L log L)).
 * Optimal : O(N * L) time with count keys, O(N * L) space.
 * Changed : computeIfAbsent instead of containsKey/get/put. The count-based char[] key is kept
 *           (it was a good choice); main() no longer contains leftover experiments.
 *
 * L6/L7 follow-up: billions of strings / distributed -> map phase emits (key, word), shuffle
 * by key, reduce groups. The key function is the same.
 */
public class LC0049_GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            char[] count = new char[26];
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
            }
            groups.computeIfAbsent(new String(count), k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(groups.values());
    }

    public static void main(String[] args) {
        List<List<String>> groups = groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});
        for (List<String> g : groups) {
            Collections.sort(g);
        }
        Check.eqUnordered(groups, Arrays.asList(
                Arrays.asList("ate", "eat", "tea"), Arrays.asList("nat", "tan"), Arrays.asList("bat")));
    }
}
