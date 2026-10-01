package com.kv.lcoptimized.graphs;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LC 743 · Network Delay Time (Medium)                    [NEW - Dijkstra template]
 *
 * Dijkstra with a lazy-deletion heap: push (dist, node); skip stale entries when popped.
 * O((V + E) log V) time, O(V + E) space. Requires non-negative weights.
 *
 * Choose the right shortest-path tool (commonly probed at L6+):
 *   unweighted                 -> BFS                          O(V + E)
 *   weights in {0, 1}          -> 0-1 BFS with a deque         O(V + E)
 *   non-negative weights       -> Dijkstra                     O(E log V)
 *   negative edges / <= k hops -> Bellman-Ford (k rounds, LC 787) O(k * E)
 *   all pairs, small V         -> Floyd-Warshall               O(V^3)
 *   grid + good heuristic      -> A*
 */
public class LC0743_NetworkDelayTime {

    public static int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] t : times) {
            adj.get(t[0]).add(new int[] {t[1], t[2]});
        }
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        heap.add(new int[] {0, k});
        while (!heap.isEmpty()) {
            int[] top = heap.poll();
            int d = top[0];
            int u = top[1];
            if (d > dist[u]) {
                continue;                               // stale entry
            }
            for (int[] e : adj.get(u)) {
                int nd = d + e[1];
                if (nd < dist[e[0]]) {
                    dist[e[0]] = nd;
                    heap.add(new int[] {nd, e[0]});
                }
            }
        }
        int worst = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            worst = Math.max(worst, dist[i]);
        }
        return worst;
    }

    public static void main(String[] args) {
        Check.eq(networkDelayTime(new int[][] {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2), 2);
        Check.eq(networkDelayTime(new int[][] {{1, 2, 1}}, 2, 1), 1);
        Check.eq(networkDelayTime(new int[][] {{1, 2, 1}}, 2, 2), -1);
        Check.eq(networkDelayTime(new int[][] {{1, 2, 5}, {1, 3, 1}, {3, 2, 1}}, 3, 1), 2);
    }
}
