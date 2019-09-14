package com.kv.pt;

/**
 * 
 * @author karanverma
 * 
 * 	Given an array, print the Next Greater Element (NGE) for every element. 
 * 	The Next greater Element for an element x is the first greater element on the right side of x in array.
 * 	 Elements for which no greater element exist, consider next greater element as -1.
 * 
 *	Examples:
 *	a) For any array, rightmost element always has next greater element as -1.
 *	b) For an array which is sorted in decreasing order, all elements have next greater element as -1.
 *	c) For the input array [4, 5, 2, 25}, the next greater elements for each element are as follows
 */
public class NextGreaterElement {
	
	
/*
 * 	Method 1 (Simple)
	Use two loops: The outer loop picks all the elements one by one. 
	The inner loop looks for the first greater element for the element picked 
	by outer loop. If a greater element is found then that element is printed 
	as next, otherwise -1 is printed.
 * 
 */

	public static void main(String[] args) {
		int arr[]= {13, 11, 21, 3};
        int n = arr.length;
        printNGE(arr, n);
        
        System.out.println();
        printNGEUsingStack(arr, n);

	}

	private static void printNGE(int[] arr, int n) {
		int next, i, j;

		for (i = 0; i < n; i++) {
			next = -1;
			for (j = i + 1; j < n; j++) {
				if (arr[i] < arr[j]) {
					next = arr[j];
					break;
				}
			}
			System.out.println(arr[i] + " -- " + next);
		}
	}
	
	/*
	 * 
	 * Method 2 (Using Stack)
		1) Push the first element to stack.
		2) Pick rest of the elements one by one and follow following steps in loop.
			….a) Mark the current element as next.
			….b) If stack is not empty, then pop an element from stack and compare it with next.
			….c) If next is greater than the popped element, then next is the next greater element for the popped element.
			….d) Keep popping from the stack while the popped element is smaller than next. next becomes the next greater element for all such popped elements
			….g) If next is smaller than the popped element, then push the popped element back.
		3) After the loop in step 2 is over, pop all the elements from stack and print -1 as next element for them.	
	 * 
	 * 
	 */
	
	
	private static void printNGEUsingStack(int[] arr, int n) {
		int i =0;
		stack s = new stack();
		s.top = -1;
		int element, next;
		
		s.push(arr[0]);
		for(i=1;i<n;i++) {
			next = arr[i];
			if(!s.isEmpty()) {
				// if stack is not empty, then pop an element from stack
				element = s.pop();
				
				/* If the popped element is smaller than next, then a) print the pair b) keep 
                popping while elements are smaller and stack is not empty */
				
				while(element < next) {
					System.out.println(element + " ---> " + next);
					if (s.isEmpty() == true)
                        break;
					element = s.pop();
				}
				
				/* If element is greater than next, then push the element back */
				if(element > next) {
					s.push(element);
				}
				
			}
			/* push next to stack so that we can find next
            greater for it */
			s.push(next);
		}
		/* After iterating over the loop, the remaining  elements in stack do not have the next greater 
        		element, so print -1 for them */
		
		 while (s.isEmpty() == false) 
	        {
	            element = s.pop();
	            next = -1;
	            System.out.println(element + " --- " + next);
	        }
	}
	
	/*
	 * Time Complexity: O(n). The worst case occurs when all elements are sorted in decreasing order. If elements are sorted in decreasing order, then every element is processed at most 4 times.
a) Initially pushed to the stack.
b) Popped from the stack when next element is being processed.
c) Pushed back to the stack because next element is smaller.
d) Popped from the stack in step 3 of algo.
	 */
	
	static class stack {
		int top;
		int items[] = new int[100];

		// Stack functions to be used by printNGE
		void push(int x) {
			if (top == 99) {
				System.out.println("Stack full");
			} else {
				items[++top] = x;
			}
		}

		int pop() {
			if (top == -1) {
				System.out.println("Underflow error");
				return -1;
			} else {
				int element = items[top];
				top--;
				return element;
			}
		}

		boolean isEmpty() {
			return (top == -1) ? true : false;
		}
	}

}
