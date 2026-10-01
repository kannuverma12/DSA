package com.kv.lcoptimized.linkedlist;

import com.kv.lcoptimized.common.Check;

/**
 * LC 138 · Copy List with Random Pointer (Medium)         [was: lc/CopyListWithRandomPointer]
 *
 * Pattern : interleave copies (A -> A' -> B -> B'), set A'.random = A.random.next, then unzip.
 * Optimal : O(n) time, O(1) extra space. A HashMap old -> new is O(n) space; start with the
 *           map in an interview, then offer interleaving as the optimisation.
 * Changed : the original already had both approaches. The HashMap version is dropped here
 *           and the three phases are labelled.
 */
public class LC0138_CopyListWithRandomPointer {

    static class Node {
        int val;
        Node next;
        Node random;

        Node(int val) {
            this.val = val;
        }
    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        for (Node p = head; p != null; p = p.next.next) {        // 1. interleave
            Node copy = new Node(p.val);
            copy.next = p.next;
            p.next = copy;
        }
        for (Node p = head; p != null; p = p.next.next) {        // 2. random pointers
            p.next.random = p.random == null ? null : p.random.next;
        }
        Node newHead = head.next;
        for (Node p = head; p != null; p = p.next) {             // 3. unzip, restoring input
            Node copy = p.next;
            p.next = copy.next;
            copy.next = copy.next == null ? null : copy.next.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        Node a = new Node(7);
        Node b = new Node(13);
        Node c = new Node(11);
        a.next = b;
        b.next = c;
        b.random = a;
        c.random = c;
        Node copy = copyRandomList(a);
        Check.isTrue(copy != a && copy.val == 7 && copy.random == null);
        Check.isTrue(copy.next.random == copy && copy.next.next.random == copy.next.next);
        Check.isTrue(a.next == b && b.next == c && c.next == null);   // input restored
    }
}
