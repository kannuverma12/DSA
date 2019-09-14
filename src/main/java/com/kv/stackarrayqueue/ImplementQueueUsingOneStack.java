package com.kv.stackarrayqueue;

import java.util.Stack;



/*
 * 
 * Queue can also be implemented using one user stack and one Function Call Stack. Below is modified 
 * Method 2 where recursion (or Function Call Stack) is used to implement queue using only one user defined stack.

	enQueue(x)
	  1) Push x to stack1.
	
	deQueue:
	  1) If stack1 is empty then error.
	  2) If stack1 has only one element then return it.
	  3) Recursively pop everything from the stack1, store the popped item 
	    in a variable res,  push the res back to stack1 and return res
	The step 3 makes sure that the last popped item is always returned and since the recursion stops when there
	 is only one item in stack1 (step 2), we get the last element of stack1 in dequeue() and all other items are 
	 pushed back in step
 */
public class ImplementQueueUsingOneStack {

	public static void main(String[] args) {

		/* Create a queue with items 1 2 3*/
        MyQueue1 q = new MyQueue1();
        q.stack1 = new Stack<>();
         
        enQueue(q, 1);
        enQueue(q, 2);
        enQueue(q, 3);
         
        /* Dequeue items */
        System.out.print(deQueue(q) + " ");
        System.out.print(deQueue(q) + " ");
        System.out.print(deQueue(q) + " ");
	}
	
	static int deQueue(MyQueue1 q) {
		int x, res = 0;
		if (q.stack1.isEmpty()) {
			System.out.println("Q is Empty");
			System.exit(0);
		}
		// Check if it is a last element of stack
		else if (q.stack1.size() == 1) {
			return pop(q.stack1);
		} else {
			/* pop an item from the stack1 */
			x = pop(q.stack1);

			/* store the last dequeued item */
			res = deQueue(q);

			/* push everything back to stack1 */
			push(q.stack1, x);
			return res;
		}
		return 0;
	}
	
	static int pop(Stack<Integer> top_ref) {
		if (top_ref == null) {
			System.out.println("Stack Underflow");
			System.exit(0);
		}
		return top_ref.pop();
	}
	
	static void enQueue(MyQueue1 q,int x)
    {
        push(q.stack1,x);
    }
	
	static void push(Stack<Integer> top_ref,int new_data)
    {
        /* put in the data */
        top_ref.push(new_data);
    }

	static class MyQueue1{
		Stack<Integer> stack1;
	}


}

