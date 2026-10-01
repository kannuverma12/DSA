package com.kv.lcoptimized.graphs;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC 133 · Clone Graph (Medium)                           [was: lc/medium/CloneGraph]
 *
 * Pattern : BFS with an old -> new map. The map doubles as the visited set, and each clone is
 *           created the first time its original is SEEN, not when it is dequeued.
 * Optimal : O(V + E) time and space.
 * Changed : the original was already this algorithm. It now uses ArrayDeque and computeIfAbsent,
 *           with one code path for the neighbour instead of two if/else branches.
 */
public class LC0133_CloneGraph {

    static class Node {
        int val;
        List<Node> neighbors = new ArrayList<>();

        Node(int val) {
            this.val = val;
        }
    }

    public static Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> copies = new HashMap<>();
        copies.put(node, new Node(node.val));
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node nb : cur.neighbors) {
                if (!copies.containsKey(nb)) {
                    copies.put(nb, new Node(nb.val));
                    queue.add(nb);
                }
                copies.get(cur).neighbors.add(copies.get(nb));
            }
        }
        return copies.get(node);
    }

    public static void main(String[] args) {
        Node[] n = new Node[5];
        for (int i = 1; i <= 4; i++) {
            n[i] = new Node(i);
        }
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 1}};
        for (int[] e : edges) {
            n[e[0]].neighbors.add(n[e[1]]);
            n[e[1]].neighbors.add(n[e[0]]);
        }
        Node c = cloneGraph(n[1]);
        Check.isTrue(c != n[1] && c.val == 1 && c.neighbors.size() == 2);
        Node c2 = c.neighbors.get(0);
        Check.isTrue(c2 != n[2] && c2.val == 2 && c2.neighbors.get(0) == c);
    }
}
