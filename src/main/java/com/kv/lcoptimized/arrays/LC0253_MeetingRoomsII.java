package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LC 253 · Meeting Rooms II (Medium)                      [NEW - Google favourite, sweep line]
 *
 * Min rooms so that no two overlapping meetings share one = maximum overlap at any instant.
 *
 * Approach A (sweep, two sorted arrays): O(n log n), O(n). The cleanest to code.
 * Approach B (min-heap of end times):   O(n log n), O(n). Also tells you WHICH room is used,
 *   which matters for the usual follow-up "assign room ids" / LC 2402.
 *
 * L6/L7 follow-ups:
 *  - Stream of meetings -> TreeMap<time, delta> difference array; query max prefix sum.
 *  - Integer times in a small range -> difference array, O(n + T).
 *  - Return the busiest time window, or free slots common to all people (LC 759).
 */
public class LC0253_MeetingRoomsII {

    public static int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int e = 0;
        for (int s = 0; s < n; s++) {
            if (starts[s] < ends[e]) {
                rooms++;             // nothing has finished: need a new room
            } else {
                e++;                 // reuse the room freed by the earliest ending meeting
            }
        }
        return rooms;
    }

    public static int minMeetingRoomsHeap(int[][] intervals) {
        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        for (int[] m : sorted) {
            if (!endTimes.isEmpty() && endTimes.peek() <= m[0]) {
                endTimes.poll();
            }
            endTimes.add(m[1]);
        }
        return endTimes.size();
    }

    public static void main(String[] args) {
        int[][] a = {{0, 30}, {5, 10}, {15, 20}};
        Check.eq(minMeetingRooms(a), 2);
        Check.eq(minMeetingRoomsHeap(a), 2);
        int[][] b = {{7, 10}, {2, 4}};
        Check.eq(minMeetingRooms(b), 1);
        int[][] c = {{1, 5}, {2, 6}, {3, 7}, {5, 8}};
        Check.eq(minMeetingRooms(c), 3);
        Check.eq(minMeetingRoomsHeap(c), 3);
    }
}
