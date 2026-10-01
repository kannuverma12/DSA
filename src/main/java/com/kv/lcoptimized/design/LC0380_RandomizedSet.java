package com.kv.lcoptimized.design;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * LC 380 · Insert Delete GetRandom O(1) (Medium)          [NEW]
 *
 * ArrayList of values + HashMap value -> index. Delete swaps the victim with the LAST element,
 * then removes the tail - O(1), and it keeps the array dense so getRandom is uniform.
 *
 * Follow-ups: duplicates allowed (LC 381): map value -> Set<index>; weighted random (LC 528):
 * prefix sums + binary search; random from a stream of unknown length: reservoir sampling.
 */
public class LC0380_RandomizedSet {

    private final List<Integer> values = new ArrayList<>();
    private final Map<Integer, Integer> index = new HashMap<>();
    private final Random random;

    public LC0380_RandomizedSet(long seed) {
        random = new Random(seed);
    }

    public boolean insert(int val) {
        if (index.containsKey(val)) {
            return false;
        }
        index.put(val, values.size());
        values.add(val);
        return true;
    }

    public boolean remove(int val) {
        Integer i = index.remove(val);
        if (i == null) {
            return false;
        }
        int last = values.remove(values.size() - 1);
        if (i < values.size()) {                      // victim wasn't the last element
            values.set(i, last);
            index.put(last, i);
        }
        return true;
    }

    public int getRandom() {
        return values.get(random.nextInt(values.size()));
    }

    public static void main(String[] args) {
        LC0380_RandomizedSet s = new LC0380_RandomizedSet(7);
        Check.isTrue(s.insert(1));
        Check.isTrue(!s.remove(2));
        Check.isTrue(s.insert(2));
        Check.isTrue(s.remove(1));
        Check.isTrue(!s.insert(2));
        Check.eq(s.getRandom(), 2);
        s.insert(3);
        s.insert(4);
        int[] hits = new int[5];
        for (int i = 0; i < 30_000; i++) {
            hits[s.getRandom()]++;
        }
        Check.isTrue(hits[2] > 9_000 && hits[3] > 9_000 && hits[4] > 9_000);   // roughly uniform
    }
}
