package com.kv.lcoptimized.design;

import com.kv.lcoptimized.common.Check;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 146 · LRU Cache (Medium)                             [NEW - most-asked design-coding question]
 *
 * HashMap<key, node> + doubly linked list with sentinel head/tail (most recent at head).
 * get/put are O(1). Interviewers usually want this hand-rolled; mention that
 * LinkedHashMap(cap, 0.75f, true) + removeEldestEntry does it in 5 lines.
 *
 * L6/L7 follow-ups:
 *  - Thread safety: one lock is simple; striped locks / segmented LRU reduce contention.
 *    Caffeine uses a concurrent map + buffered access log replayed under a lock.
 *  - LFU (LC 460): map key -> node, plus map freq -> DLL, and track minFreq. O(1).
 *  - TTL expiry: lazy expiry on get + a min-heap / timing wheel for eager cleanup.
 *  - Distributed: consistent hashing across nodes; per-node LRU.
 */
public class LC0146_LRUCache {

    private static final class Node {
        int key;
        int value;
        Node prev;
        Node next;
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head = new Node();
    private final Node tail = new Node();

    public LC0146_LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity * 2);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node n = map.get(key);
        if (n == null) {
            return -1;
        }
        moveToFront(n);
        return n.value;
    }

    public void put(int key, int value) {
        Node n = map.get(key);
        if (n != null) {
            n.value = value;
            moveToFront(n);
            return;
        }
        if (map.size() == capacity) {
            Node lru = tail.prev;
            unlink(lru);
            map.remove(lru.key);
        }
        n = new Node();
        n.key = key;
        n.value = value;
        map.put(key, n);
        addFront(n);
    }

    private void moveToFront(Node n) {
        unlink(n);
        addFront(n);
    }

    private void unlink(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void addFront(Node n) {
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
    }

    public static void main(String[] args) {
        LC0146_LRUCache c = new LC0146_LRUCache(2);
        c.put(1, 1);
        c.put(2, 2);
        Check.eq(c.get(1), 1);
        c.put(3, 3);                    // evicts 2
        Check.eq(c.get(2), -1);
        c.put(4, 4);                    // evicts 1
        Check.eq(c.get(1), -1);
        Check.eq(c.get(3), 3);
        Check.eq(c.get(4), 4);
        c.put(3, 30);                   // update refreshes recency
        c.put(5, 5);                    // evicts 4
        Check.eq(c.get(4), -1);
        Check.eq(c.get(3), 30);
    }
}
