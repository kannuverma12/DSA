package com.kv.lcoptimized.design;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * LC 981 · Time Based Key-Value Store (Medium)            [NEW - map of sorted versions]
 *
 * get(key, t) = value with the largest timestamp <= t.
 * Timestamps per key arrive increasing (the problem guarantees it) -> append to a list and
 * binary search: set O(1), get O(log n). Without that guarantee, use TreeMap.floorEntry
 * (O(log n) both); both variants are shown.
 *
 * L6/L7 follow-ups: this is MVCC / snapshot reads. Discuss GC of old versions, range scans
 * "as of t", persistence (LSM-tree), and sharding by key.
 */
public class LC0981_TimeBasedKeyValueStore {

    private final Map<String, List<Integer>> times = new HashMap<>();
    private final Map<String, List<String>> values = new HashMap<>();

    public void set(String key, String value, int timestamp) {
        times.computeIfAbsent(key, k -> new ArrayList<>()).add(timestamp);
        values.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    public String get(String key, int timestamp) {
        List<Integer> ts = times.get(key);
        if (ts == null) {
            return "";
        }
        int lo = 0;
        int hi = ts.size();                         // first index with ts > timestamp
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (ts.get(mid) <= timestamp) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo == 0 ? "" : values.get(key).get(lo - 1);
    }

    /** Variant for out-of-order timestamps. */
    static final class Unordered {
        private final Map<String, TreeMap<Integer, String>> store = new HashMap<>();

        void set(String key, String value, int timestamp) {
            store.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
        }

        String get(String key, int timestamp) {
            TreeMap<Integer, String> versions = store.get(key);
            Map.Entry<Integer, String> e = versions == null ? null : versions.floorEntry(timestamp);
            return e == null ? "" : e.getValue();
        }
    }

    public static void main(String[] args) {
        LC0981_TimeBasedKeyValueStore kv = new LC0981_TimeBasedKeyValueStore();
        kv.set("foo", "bar", 1);
        Check.eq(kv.get("foo", 1), "bar");
        Check.eq(kv.get("foo", 3), "bar");
        kv.set("foo", "bar2", 4);
        Check.eq(kv.get("foo", 4), "bar2");
        Check.eq(kv.get("foo", 5), "bar2");
        Check.eq(kv.get("foo", 0), "");
        Check.eq(kv.get("nope", 9), "");

        Unordered u = new Unordered();
        u.set("k", "late", 10);
        u.set("k", "early", 2);
        Check.eq(u.get("k", 5), "early");
        Check.eq(u.get("k", 11), "late");
    }
}
