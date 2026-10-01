package com.kv.lcoptimized.design;

import com.kv.lcoptimized.common.Check;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 208 · Implement Trie (Medium)                        [was: lc/ImplementTrieUsingArray, lc/ImplementTrieUsingHashmap]
 *
 * Array children (26 slots): fastest, but 26 references per node. Good for lowercase ASCII.
 * Map children: memory proportional to actual branching; any alphabet (Unicode, tokens).
 * Both: insert/search/startsWith are O(L).
 * Changed : the array version's searchNode returned null for the empty prefix, so
 *           startsWith("") was false. The map version stored a redundant `char c` per node and
 *           marked isLeaf inside the loop. Merged into one class with a pluggable child store.
 *           Added countWithPrefix(), the usual follow-up (autocomplete ranking).
 *
 * L6/L7 follow-ups: autocomplete top-3 per prefix (store top-k at each node, or DFS with a
 * heap), delete with pruning, compressed/radix trie for memory, Word Search II (LC 212).
 */
public class LC0208_Trie {

    private static final class Node {
        final Map<Character, Node> mapChildren;
        final Node[] arrChildren;
        boolean isWord;
        int prefixCount;

        Node(boolean useArray) {
            arrChildren = useArray ? new Node[26] : null;
            mapChildren = useArray ? null : new HashMap<>();
        }

        Node child(char c) {
            return arrChildren != null ? arrChildren[c - 'a'] : mapChildren.get(c);
        }

        Node childOrCreate(char c, boolean useArray) {
            Node n = child(c);
            if (n == null) {
                n = new Node(useArray);
                if (arrChildren != null) {
                    arrChildren[c - 'a'] = n;
                } else {
                    mapChildren.put(c, n);
                }
            }
            return n;
        }
    }

    private final boolean useArray;
    private final Node root;

    /** useArray = true for lowercase a-z only (faster); false for any characters. */
    public LC0208_Trie(boolean useArray) {
        this.useArray = useArray;
        this.root = new Node(useArray);
    }

    public void insert(String word) {
        if (search(word)) {
            return;                                   // keep prefix counts exact on duplicates
        }
        Node cur = root;
        cur.prefixCount++;
        for (int i = 0; i < word.length(); i++) {
            cur = cur.childOrCreate(word.charAt(i), useArray);
            cur.prefixCount++;
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        Node n = walk(word);
        return n != null && n.isWord;
    }

    public boolean startsWith(String prefix) {
        return walk(prefix) != null;
    }

    public int countWithPrefix(String prefix) {
        Node n = walk(prefix);
        return n == null ? 0 : n.prefixCount;
    }

    private Node walk(String s) {
        Node cur = root;
        for (int i = 0; i < s.length() && cur != null; i++) {
            cur = cur.child(s.charAt(i));
        }
        return cur;
    }

    public static void main(String[] args) {
        for (boolean useArray : new boolean[] {true, false}) {
            LC0208_Trie t = new LC0208_Trie(useArray);
            t.insert("apple");
            Check.isTrue(t.search("apple"));
            Check.isTrue(!t.search("app"));
            Check.isTrue(t.startsWith("app"));
            Check.isTrue(t.startsWith(""));
            t.insert("app");
            t.insert("app");
            Check.isTrue(t.search("app"));
            Check.eq(t.countWithPrefix("ap"), 2);
            Check.eq(t.countWithPrefix("b"), 0);
        }
        LC0208_Trie unicode = new LC0208_Trie(false);
        unicode.insert("héllo");
        Check.isTrue(unicode.search("héllo"));
    }
}
