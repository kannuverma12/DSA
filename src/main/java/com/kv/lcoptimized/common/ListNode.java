package com.kv.lcoptimized.common;

/** Shared singly-linked list node (replaces the ~20 copies of ListNode in com.kv.lc). */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode of(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : vals) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    /** "1->2->3"; do not call on a cyclic list. */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ListNode p = this; p != null; p = p.next) {
            if (sb.length() > 0) {
                sb.append("->");
            }
            sb.append(p.val);
        }
        return sb.toString();
    }
}
