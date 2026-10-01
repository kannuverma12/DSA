package com.kv.lcoptimized.graphs;

import com.kv.lcoptimized.common.Check;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC 399 · Evaluate Division (Medium)                     [NEW - weighted union-find, Google classic]
 *
 * Given a / b = k facts, answer x / y queries. Model: each variable stores its ratio to its
 * set's root: value(x) = weight[x] * value(root). Then x / y = weight[x] / weight[y] if x and y
 * share a root, otherwise -1.
 * Complexity: near O((E + Q) * alpha) with path compression. BFS/DFS per query is O(Q * (V + E)).
 *
 * Follow-ups: currency conversion with best rate (that's a path max-product -> log-transform to
 * shortest path; detect arbitrage = negative cycle with Bellman-Ford).
 */
public class LC0399_EvaluateDivision {

    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Double> weight = new HashMap<>();   // x = weight[x] * parent[x]

    private String find(String x) {
        String p = parent.get(x);
        if (!p.equals(x)) {
            String root = find(p);
            weight.put(x, weight.get(x) * weight.get(p));
            parent.put(x, root);
            return root;
        }
        return x;
    }

    private void union(String a, String b, double ratio) {         // a / b = ratio
        parent.putIfAbsent(a, a);
        weight.putIfAbsent(a, 1.0);
        parent.putIfAbsent(b, b);
        weight.putIfAbsent(b, 1.0);
        String ra = find(a);
        String rb = find(b);
        if (!ra.equals(rb)) {
            parent.put(ra, rb);
            // a = wa * ra, b = wb * rb, a = ratio * b  =>  ra = ratio * wb / wa * rb
            weight.put(ra, ratio * weight.get(b) / weight.get(a));
        }
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        LC0399_EvaluateDivision uf = new LC0399_EvaluateDivision();
        for (int i = 0; i < values.length; i++) {
            uf.union(equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }
        double[] out = new double[queries.size()];
        for (int i = 0; i < out.length; i++) {
            String x = queries.get(i).get(0);
            String y = queries.get(i).get(1);
            if (!uf.parent.containsKey(x) || !uf.parent.containsKey(y) || !uf.find(x).equals(uf.find(y))) {
                out[i] = -1.0;
            } else {
                out[i] = uf.weight.get(x) / uf.weight.get(y);
            }
        }
        return out;
    }

    public static void main(String[] args) {
        double[] r = calcEquation(
                Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c")),
                new double[] {2.0, 3.0},
                Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"),
                        Arrays.asList("a", "a"), Arrays.asList("x", "x")));
        double[] expected = {6.0, 0.5, -1.0, 1.0, -1.0};
        for (int i = 0; i < expected.length; i++) {
            Check.near(r[i], expected[i]);
        }
    }
}
