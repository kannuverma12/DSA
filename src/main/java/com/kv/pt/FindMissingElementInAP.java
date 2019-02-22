package com.kv.pt;

/**
 * 
 * @author karanverma
 *
 */
public class FindMissingElementInAP {

	
	/*
	 * 
	 * We can solve this problem in O(Logn) time using Binary Search. The idea is to go to 
	 * the middle element. Check if the difference between middle and next to middle is equal 
	 * to diff or not, if not then the missing element lies between mid and mid+1. If the middle 
	 * element is equal to n/2th term in Arithmetic Series (Let n be the number of elements in input array), 
	 * then missing element lies in right half. Else element lies in left half.
	 * 
	 */
	
	public static void main(String[] args) {
		int arr[] = {2, 4, 8, 10, 12, 14};
		int n = arr.length;
		int diff = (arr[n-1] - arr[0])/n;

	    System.out.println("The missing element is "+ findMissing(arr,  0, n-1, diff));

	}

	private static Object findMissing(int[] arr, int low, int high, int diff) {
		if(high <= low)
			return Integer.MAX_VALUE;
		int mid = low + (high -low)/2;
		
		if(arr[mid+1] - arr[mid] != diff) {
		    System.out.println("arr[mid + diff] = "+arr[mid + diff]);
			return arr[mid + diff];
		}
		
		if(mid > 0 && (arr[mid] -arr[mid-1] != diff))
			return arr[mid-1] + diff;
		
		/*
		 * If the middle element is equal to n/2th term in Arithmetic Series (Let n be the number of elements in input array), 
		 * then missing element lies in right half
		 * */
		if(arr[mid] == arr[0]+mid*diff )
			return findMissing(arr, mid+1, high, diff);
		
		return findMissing(arr, low, mid-1, diff);


		
	}

}
