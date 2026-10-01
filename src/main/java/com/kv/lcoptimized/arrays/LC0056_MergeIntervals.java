package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 56 · Merge Intervals (Medium)                        [was: lc/medium/L16_MergeIntervals]
 *
 * Pattern : sort by start, extend the last merged interval or start a new one.
 * Optimal : O(n log n) time, O(n) output.
 * Changed : Integer.compare instead of a - b (subtraction overflows for large values);
 *           does not mutate the caller's interval objects.
 *
 * L6/L7 follow-ups:
 *  - Insert interval into sorted list (LC 57) -> O(n), no sort.
 *  - Intervals arrive as a stream -> TreeMap keyed by start, merge with floor/ceiling: O(log n) each.
 *  - Min rooms / max overlap -> sweep line, see LC0253_MeetingRoomsII.
 */
public class LC0056_MergeIntervals {

    public static int[][] merge(int[][] intervals) {
        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> out = new ArrayList<>();
        for (int[] cur : sorted) {
            int[] last = out.isEmpty() ? null : out.get(out.size() - 1);
            if (last != null && cur[0] <= last[1]) {
                last[1] = Math.max(last[1], cur[1]);
            } else {
                out.add(new int[] {cur[0], cur[1]});
            }
        }
        return out.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        Check.eq(merge(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}}),
                new int[][] {{1, 6}, {8, 10}, {15, 18}});
        Check.eq(merge(new int[][] {{1, 4}, {4, 5}}), new int[][] {{1, 5}});
        Check.eq(merge(new int[][] {{1, 4}, {2, 3}}), new int[][] {{1, 4}});
    }
}
