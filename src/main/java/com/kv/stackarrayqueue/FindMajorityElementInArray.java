package com.kv.stackarrayqueue;

import java.util.HashMap;

/*
 * A majority element in an array A[] of size n is an element that appears more than n/2 times 
 * (and hence there is at most one such element).
 */
public class FindMajorityElementInArray {

	public static void main(String[] args) {
		int arr[] = {1, 1, 2, 1, 3, 5, 1};
	    int n = arr.length-1 ;
	     
	    System.out.println("*******Method 1******");
	    findMajority(arr, n);
	    
	    System.out.println();
	    System.out.println("*******Method 2******");
	    printMajority(arr, arr.length);
	    
	    System.out.println();
	    System.out.println("*******Method 2******");
	    findMajority(arr);

	}

	
	// Method 1
	private static void findMajority(int[] arr, int n) {
		int maxCount = 0;
		int index = -1;
		for (int i = 0; i < n; i++) {
			int count = 0;
			for (int j = 0; j < n; j++) {
				if (arr[i] == arr[j])
					count++;
			}
			if (count > maxCount) {
				maxCount = count;
				index = i;
			}

		}
		if (maxCount >= n / 2)
			System.out.println("Majority Element is " + arr[index]);
		else
			System.out.println("No Majority Element.");
	}
	
	//Method 2
	/*
	 * This is a two step process.

		1. 	The first step gives the element that may be majority element in the array. If there is a majority 
			element in an array, then this step will definitely return majority element, otherwise it will 
			return candidate for majority element.
		2. 	Check if the element obtained from above step is majority element.This step is necessary as we are not 
			always sure that element return by first step is majority element.
	 */
	
	/*
	 * 1. Finding a Candidate :
	 * The algorithm for first phase that works in O(n) is known as Moore’s Voting Algorithm. 
	 * Basic idea of the algorithm is that if we cancel out each occurrence of an element e with all
	 * the other elements that are different from e then e will exist till end if it is a majority element.
	 * 
	 * findCandidate(a[], size)
		1.  Initialize index and count of majority element
		     maj_index = 0, count = 1
		2.  Loop for i = 1 to size – 1
		    (a) If a[maj_index] == a[i]
		          count++
		    (b) Else
		        count--;
		    (c) If count == 0
		          maj_index = i;
		          count = 1
		3.  Return a[maj_index]
		
		Above algorithm loops through each element and maintains a count of a[maj_index]. If the next element is 
		same then increment the count, if the next element is not same then decrement the count, and if the count 
		reaches 0 then changes the maj_index to the current element and set the count again to 1. So, the first
		 phase of the algorithm gives us a candidate element.
	 */
	
	static void printMajority(int a[], int size) {
		/* Find the candidate for Majority */
		int cand = findCandidate(a, size);

		/* Print the candidate if it is Majority */
		if (isMajority(a, size, cand))
			System.out.println(" " + cand + " ");
		else
			System.out.println("No Majority Element");
	}


	private static int findCandidate(int[] a, int size) {
		int maj_index = 0, count = 1;
        int i;
        for (i = 1; i < size; i++) 
        {
            if (a[maj_index] == a[i])
                count++;
            else
                count--;
            if (count == 0)
            {
                maj_index = i;
                count = 1;
            }
        }
        return a[maj_index];
	}
	
	static boolean isMajority(int a[], int size, int cand) 
    {
        int i, count = 0;
        for (i = 0; i < size; i++) 
        {
            if (a[i] == cand)
                count++;
        }
        if (count > size / 2) 
            return true;
        else
            return false;
    }
	
	// Method 3 : Using Hashmap
	private static void findMajority(int[] arr) 
    {
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
 
        for(int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                    int count = map.get(arr[i]) +1;
                    if (count > arr.length /2) {
                        System.out.println("Majority found : " + arr[i]);
                        return;
                    } else
                        map.put(arr[i], count);
 
            }
            else
                map.put(arr[i],1);
            }
            System.out.println(" No Majority element");
    }

}
