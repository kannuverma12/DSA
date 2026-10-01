package com.kv.lcoptimized.trees;

import com.kv.lcoptimized.common.Check;

/**
 * LC 116 (perfect tree) + LC 117 (any tree) · Populating Next Right Pointers
 *   [was: lc/L44_PopulateNextRightPointerOfEachNode, lc/L45_PopulateNextRightPointerOfEachNode2]
 *
 * Pattern : use the already-linked level above as a linked list; build the next level with a
 *           dummy head + tail pointer.
 * Optimal : O(n) time, O(1) space, for both problems with one method.
 * Changed : the LC 116 original used two queues - O(n) space, although the problem requires
 *           constant space. The LC 117 original was O(1) but used 4 pointers; a dummy node
 *           makes it 2.
 */
public class LC0116_PopulatingNextRightPointers {

    static class Node {
        int val;
        Node left;
        Node right;
        Node next;

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static Node connect(Node root) {
        Node levelHead = root;
        while (levelHead != null) {
            Node dummy = new Node(0, null, null);
            Node tail = dummy;
            for (Node p = levelHead; p != null; p = p.next) {
                if (p.left != null) {
                    tail.next = p.left;
                    tail = tail.next;
                }
                if (p.right != null) {
                    tail.next = p.right;
                    tail = tail.next;
                }
            }
            levelHead = dummy.next;
        }
        return root;
    }

    private static String levels(Node root) {
        StringBuilder sb = new StringBuilder();
        for (Node head = root; head != null; ) {
            Node nextHead = null;
            for (Node p = head; p != null; p = p.next) {
                sb.append(p.val).append(' ');
                if (nextHead == null) {
                    nextHead = p.left != null ? p.left : p.right;
                }
            }
            sb.append("# ");
            head = nextHead;
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        Node perfect = new Node(1, new Node(2, new Node(4, null, null), new Node(5, null, null)),
                new Node(3, new Node(6, null, null), new Node(7, null, null)));
        Check.eq(levels(connect(perfect)), "1 # 2 3 # 4 5 6 7 #");
        Node sparse = new Node(1, new Node(2, new Node(4, null, null), new Node(5, null, null)),
                new Node(3, null, new Node(7, null, null)));
        Check.eq(levels(connect(sparse)), "1 # 2 3 # 4 5 7 #");
    }
}
