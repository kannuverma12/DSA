package com.kv.stackarrayqueue;

import java.util.Stack;

public class ImplemenQueueUsingTwoStacks {

	public static void main(String[] args) {
		MyQueue q= new MyQueue();
        q.stack1 = new Stack<>();
        q.stack2 = new Stack<>();
        enQueue(q, 1);
        enQueue(q, 2);
        enQueue(q, 3);
         
        /* Dequeue items */
        System.out.print(deQueue(q)+" ");
        System.out.print(deQueue(q)+" ");
        System.out.println(deQueue(q)+" ");

	}
	/*
	 * Method 1 (By making enQueue operation costly) This method makes sure that oldest entered element 
	 * is always at the top of stack 1, so that deQueue operation just pops from stack1. To put the element 
	 * at top of stack1, stack2 is used.

		enQueue(q, x)
		  1) While stack1 is not empty, push everything from stack1 to stack2.
		  2) Push x to stack1 (assuming size of stacks is unlimited).
		  3) Push everything back to stack1.
		
		dnQueue(q)
		  1) If stack1 is empty then error
		  2) Pop an item from stack1 and return it
	 */
	
	/*
	 * Method 2 (By making deQueue operation costly)In this method, in en-queue operation, the new element is entered at the top of stack1. In de-queue operation, if stack2 is empty then all the elements are moved to stack2 and finally top of stack2 is returned.

		enQueue(q,  x)
		  1) Push x to stack1 (assuming size of stacks is unlimited).
		
		deQueue(q)
		  1) If both stacks are empty then error.
		  2) If stack2 is empty
		       While stack1 is not empty, push everything from stack1 to stack2.
		  3) Pop the element from stack2 and return it.
			 */
	
	static void enQueue(MyQueue q, int x)
    {
        push(q.stack1, x);
    }
	
	/* Function to push an item to stack*/
    static void push(Stack<Integer> top_ref, int new_data)
    {
        //Push the data onto the stack
        top_ref.push(new_data);
    }
    
    static int deQueue(MyQueue q)
    {
        int x;
        /* If both stacks are empty then error */
		if (q.stack1.isEmpty() && q.stack2.isEmpty()) {
			System.out.println("Q is empty");
			System.exit(0);
		}
      
        /* Move elements from stack1 to stack 2 only if stack2 is empty */
		if (q.stack2.isEmpty()) {
			while (!q.stack1.isEmpty()) {
				x = pop(q.stack1);
				push(q.stack2, x);

			}
		}
        x = pop(q.stack2);
        return x;
    }
    
	static int pop(Stack<Integer> top_ref) {
		if (top_ref.isEmpty()) {
			System.out.println("Stack Underflow");
			System.exit(0);
		}
		return top_ref.pop();
	}
	
	
	
	
	

}

class MyQueue{
	Stack<Integer> stack1 ;
    Stack<Integer> stack2 ;
}
