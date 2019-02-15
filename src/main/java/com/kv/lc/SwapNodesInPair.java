package com.kv.lc;

public class SwapNodesInPair {
    ListNode head;

    public static void main(String[] args) {
        SwapNodesInPair l1 = new SwapNodesInPair();
        l1.push(6);
        l1.push(4);
        l1.push(2);
        l1.push(1);
        System.out.println("L1");
        l1.printList(l1.head);
        
        ListNode ls = swapPairs(l1.head);
        l1.printList(ls);
    }
    
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)   
            return head;
     
        ListNode h = new ListNode(0);
        h.next = head;
        ListNode p = h;
     
        while(p.next != null && p.next.next != null){
            //use t1 to track first node
            ListNode t1 = p;
            p = p.next;
            t1.next = p.next;
     
            //use t2 to track next node of the pair
            ListNode t2 = p.next.next;
            p.next.next = p;
            p.next = t2;
        }
     
        return h.next;
    }
    
    
    static public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
   }
   
   public void push(int new_data)
   {
       ListNode new_node = new ListNode(new_data);
       new_node.next = head;
       head = new_node;
   }
   
   void printList(ListNode l)
   {
       ListNode temp = l;
       while (temp != null)
       {
          System.out.print(temp.val+" ");
          temp = temp.next;
       }
       System.out.println();
   }

}
