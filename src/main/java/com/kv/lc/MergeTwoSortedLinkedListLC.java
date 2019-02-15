package com.kv.lc;

import com.kv.lc.DeleteNthLastNodeFromLinkedList.ListNode;

public class MergeTwoSortedLinkedListLC {
    
    ListNode head;

    public static void main(String[] args) {
        MergeTwoSortedLinkedListLC l1 = new MergeTwoSortedLinkedListLC();
        l1.push(6);
        l1.push(4);
        l1.push(2);
        l1.push(1);
        System.out.println("L1");
        l1.printList(l1.head);
        
        MergeTwoSortedLinkedListLC l2 = new MergeTwoSortedLinkedListLC();
        l2.push(9);
        l2.push(7);
        l2.push(5);
        l2.push(3);
        System.out.println("l2");
        l1.printList(l2.head);
        
        System.out.println("Merged l1 and l2");
        ListNode lMerged = mergeTwoLists(l1.head, l2.head);
        l1.printList(lMerged);
        
        
    }
    
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p = head;
     
        while(l1!=null||l2!=null){
            if(l1!=null&&l2!=null){
                if(l1.val < l2.val){
                    p.next = l1;
                    l1=l1.next;
                }else{
                    p.next=l2;
                    l2=l2.next;
                }
                p = p.next;
            }else if(l1==null){
                p.next = l2;
                break;
            }else if(l2==null){
                p.next = l1;
                break;
            }
        }
     
        return head.next;
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
