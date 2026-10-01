package com.kv.lcoptimized.graphs;

import com.kv.lcoptimized.common.Check;

/**
 * Disjoint Set Union (Union-Find) template                [NEW - used by LC0200, LC0305, Kruskal, ...]
 *
 * Path compression (path halving here) + union by size gives amortised O(alpha(n)) per op,
 * which is effectively constant.
 *
 * When to use it over BFS/DFS: edges arrive ONLINE (LC 305), you need connectivity queries
 * while merging (LC 721 Accounts Merge, LC 1202 Smallest String With Swaps), MST (Kruskal),
 * redundant connection (LC 684), or "number of groups after k merges".
 * Can't do deletions - for offline deletion, reverse time and add edges instead (LC 803).
 */
public class UnionFind {

    private final int[] parent;
    private final int[] size;
    private int components;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        components = n;
    }

    public int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];          // path halving
            x = parent[x];
        }
        return x;
    }

    /** Returns false if already connected (i.e. this edge closes a cycle). */
    public boolean union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) {
            return false;
        }
        if (size[ra] < size[rb]) {
            int t = ra;
            ra = rb;
            rb = t;
        }
        parent[rb] = ra;
        size[ra] += size[rb];
        components--;
        return true;
    }

    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }

    public int components() {
        return components;
    }

    public int sizeOf(int x) {
        return size[find(x)];
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(6);
        Check.isTrue(uf.union(0, 1));
        Check.isTrue(uf.union(1, 2));
        Check.isTrue(!uf.union(0, 2));              // cycle
        Check.isTrue(uf.connected(0, 2));
        Check.isTrue(!uf.connected(0, 3));
        Check.eq(uf.components(), 4);
        Check.eq(uf.sizeOf(2), 3);
    }
}
