package com.kv.lcoptimized.graphs;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 207 · Course Schedule + LC 210 · Course Schedule II (Medium)   [NEW - topological sort]
 *
 * Kahn's algorithm: repeatedly take nodes with in-degree 0. If fewer than n nodes come out,
 * there's a cycle. O(V + E) time and space.
 * DFS alternative: 3-colour (white/grey/black); a grey->grey edge is a cycle, and the reverse
 * post-order is a topological order. Know both; Kahn is easier to get right under pressure.
 *
 * L6/L7 follow-ups: minimum number of semesters (BFS levels = longest path in the DAG, LC 1136),
 * lexicographically smallest order (replace the queue with a min-heap), build systems /
 * parallel task scheduling with durations (longest path with weights, the critical path).
 */
public class LC0207_CourseSchedule {

    /** Returns a valid order, or an empty array if the prerequisites contain a cycle. */
    public static int[] findOrder(int n, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        int[] indegree = new int[n];
        for (int[] p : prerequisites) {
            adj.get(p[1]).add(p[0]);                // p[1] must come before p[0]
            indegree[p[0]]++;
        }
        int[] order = new int[n];
        int head = 0;
        int tail = 0;                               // order[] doubles as the BFS queue
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                order[tail++] = i;
            }
        }
        while (head < tail) {
            int u = order[head++];
            for (int v : adj.get(u)) {
                if (--indegree[v] == 0) {
                    order[tail++] = v;
                }
            }
        }
        return tail == n ? order : new int[0];
    }

    public static boolean canFinish(int n, int[][] prerequisites) {
        return findOrder(n, prerequisites).length == n;
    }

    public static void main(String[] args) {
        Check.isTrue(canFinish(2, new int[][] {{1, 0}}));
        Check.isTrue(!canFinish(2, new int[][] {{1, 0}, {0, 1}}));
        Check.eq(findOrder(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}}), new int[] {0, 1, 2, 3});
        Check.eq(findOrder(1, new int[][] {}), new int[] {0});
    }
}
