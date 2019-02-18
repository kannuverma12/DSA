package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Sort a linked list in O(n log n) time using constant space complexity.
 *  
 *  If this problem does not have the constant space limitation, we can easily sort using a sorting method from Java SDK. With the constant space limitation, we need to do some pointer manipulation.
 *  Break the list to two in the middle
 *  Recursively sort the two sub lists
 *  Merge the two sub lists
 */
public class SortLinkedList {

    public static ListNode mergeSortList(ListNode head) {
        
        if (head == null || head.next == null)
            return head;
 
        // count total number of elements
        int count = 0;
        ListNode p = head;
        while (p != null) {
            count++;
            p = p.next;
        }
 
        // break up to two list
        int middle = count / 2;
 
        ListNode l = head, r = null;
        ListNode p2 = head;
        int countHalf = 0;
        while (p2 != null) {
            countHalf++;
            ListNode next = p2.next;
 
            if (countHalf == middle) {
                p2.next = null;
                r = next;
            }
            p2 = next;
        }
 
        // now we have two parts l and r, recursively sort them
        ListNode h1 = mergeSortList(l);
        ListNode h2 = mergeSortList(r);
 
        // merge together
        ListNode merged = merge(h1, h2);
 
        return merged;
    }
 
    public static ListNode merge(ListNode l, ListNode r) {
        ListNode p1 = l;
        ListNode p2 = r;
 
        ListNode fakeHead = new ListNode(100);
        ListNode pNew = fakeHead;
 
        while (p1 != null || p2 != null) {
 
            if (p1 == null) {
                pNew.next = new ListNode(p2.val);
                p2 = p2.next;
                pNew = pNew.next;
            } else if (p2 == null) {
                pNew.next = new ListNode(p1.val);
                p1 = p1.next;
                pNew = pNew.next;
            } else {
                if (p1.val < p2.val) {
                    // if(fakeHead)
                    pNew.next = new ListNode(p1.val);
                    p1 = p1.next;
                    pNew = pNew.next;
                } else if (p1.val == p2.val) {
                    pNew.next = new ListNode(p1.val);
                    pNew.next.next = new ListNode(p1.val);
                    pNew = pNew.next.next;
                    p1 = p1.next;
                    p2 = p2.next;
 
                } else {
                    pNew.next = new ListNode(p2.val);
                    p2 = p2.next;
                    pNew = pNew.next;
                }
            }
        }
 
        // printList(fakeHead.next);
        return fakeHead.next;
    }
 
    public static void main(String[] args) {
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
 
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(5);
 
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
 
        n1 = mergeSortList(n1);
 
        printList(n1);
    }
 
    public static void printList(ListNode x) {
        if(x != null){
            System.out.print(x.val + " ");
            while (x.next != null) {
                System.out.print(x.next.val + " ");
                x = x.next;
            }
            System.out.println();
        }
 
    }
    
    static class ListNode {
        int val;
        ListNode next;
     
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
