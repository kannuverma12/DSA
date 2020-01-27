package com.kv.stackarrayqueue;

import java.util.Deque;
import java.util.LinkedList;

/*
 * Given an array and an integer k, find the maximum in every contiguous sub-array of size k.
 */
public class FindMaxInEachContinuousSubarray {

	public static void main(String[] args) {

		System.out.println("************ Method 1 *********");
		int arr[] = { 1, 12, 3, 4, 5, 6, 7, 8, 9, 10 };
        int k = 3;
        printKMax(arr, arr.length, k);
        
        System.out.println();
        
        System.out.println("************ Method 2 *********");
        printMax(arr, arr.length,k);
	}

	
	
	/*
	 * Run two loops. In the outer loop, take all subarrays of size k. 
	 * In the inner loop, get the maximum of the current subarray.
	 */
	private static void printKMax(int[] arr, int n, int k) {
		int j, max;
		for (int i = 0; i <= n - k; i++) {
            max = arr[i];
            for (j = 1; j < k; j++) {
                if (arr[i + j] > max)
                    max = arr[i + j];
            }
            System.out.print(max + " ");
        }
		
	}
	
	/*
	 * Method 2 (Use Self-Balancing BST)
		1) Pick first k elements and create a Self-Balancing Binary Search Tree (BST) of size k.
		2) Run a loop for i = 0 to n – k
		…..a) Get the maximum element from the BST, and print it.
		…..b) Search for arr[i] in the BST and delete it from the BST.
		…..c) Insert arr[i+k] into the BST.
		
		Time Complexity: Time Complexity of step 1 is O(kLogk). Time Complexity of steps 2(a), 2(b) and 2(c) is O(Logk).
		 Since steps 2(a), 2(b) and 2(c) are in a loop that runs n-k+1 times, time complexity of the complete algorithm 
		 is O(kLogk + (n-k+1)*Logk) which can also be written as O(nLogk).
	 */
	
	
	/*
	 * Method 3 (A O(n) method: use Dequeue)
		We create a Dequeue, Qi of capacity k, that stores only useful elements of current window of k elements.
		 An element is useful if it is in current window and is greater than all other elements on left side of it 
		 in current window. We process all array elements one by one and maintain Qi to contain useful elements of 
		 current window and these useful elements are maintained in sorted order. The element at front of the Qi is 
		 the largest and element at rear of Qi is the smallest of current window. 

		Following is the implementation of this method.
	 */

	// A Dequeue (Double ended queue) based method for printing maixmum element of
    // all subarrays of size k
    static void printMax(int arr[],int n, int k) {
        // Create a Double Ended Queue, Qi that will store indexes of array elements
        // The queue will store indexes of useful elements in every window and it will
        // maintain decreasing order of values from front to rear in Qi, i.e., 
        // arr[Qi.front[]] to arr[Qi.rear()] are sorted in decreasing order
        Deque<Integer> Qi = new LinkedList<>();
         
        /* Process first k (or first window) elements of array */
        int i;
        for(i = 0; i < k; ++i)
        {
            // For very element, the previous smaller elements are useless so remove them from Qi
            while(!Qi.isEmpty() && arr[i] >= arr[Qi.peekLast()])
                Qi.removeLast();   // Remove from rear
             
            // Add new element at rear of queue
            Qi.addLast(i);
        }
         
        // Process rest of the elements, i.e., from arr[k] to arr[n-1]
        for( ;i < n; ++i)
        {
            // The element at the front of the queue is the largest element of previous window, so print it
            System.out.print(arr[Qi.peek()] + " ");
             
            // Remove the elements which are out of this window
            while((!Qi.isEmpty()) && Qi.peek() <= i-k)
                Qi.removeFirst();
             
            // Remove all elements smaller than the currently
            // being added element (remove useless elements)
            while((!Qi.isEmpty()) && arr[i] >= arr[Qi.peekLast()])
                Qi.removeLast();
             
            // Add current element at the rear of Qi
            Qi.addLast(i);
        }
         
        // Print the maximum element of last window
        System.out.print(arr[Qi.peek()]);
    }
}
