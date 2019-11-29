package com.kv.pt;

/**
 * 
 * @author karanverma
 * 
 * An element in a sorted array can be found in O(log n) time via binary search.
 * But suppose we rotate an ascending order sorted array at some pivot unknown 
 * to you beforehand. So for instance, 1 2 3 4 5 might become 3 4 5 1 2. 
 * Devise a way to find an element in the rotated array in O(log n) time
 * 
 * 	Input arr[] = {3, 4, 5, 1, 2}
 *	Element to Search = 1
 *	  1) Find out pivot point and divide the array in two sub-arrays. (pivot = 2) //Index of 5//
 *	  2) Now call binary search for one of the two sub-arrays.
 *	      (a) If element is greater than 0th element then search in left array
 *	      (b) Else Search in right array (1 will go in else as 1 < 0th element(3))
 *	  3) If element is found in selected sub-array then return index
 *	     Else return -1.
 * 
 * 
 */
public class PivotedBinarySearch {

	public static void main(String[] args) {

		// Let us search 3 in below array
	       int arr1[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
	       int n = arr1.length;
	       int key = 3;
	       System.out.println("Index of the element is : "
	                      + pivotedBinarySearch(arr1, n, key));


	       //use this method
		System.out.println(" pivoted search "+search(arr1, 0, n, key));
	}

	private static int pivotedBinarySearch(int[] arr, int n, int key) {
			int pivot = findPivot(arr, 0, n-1);
	       
	       // If we didn't find a pivot, then  array is not rotated at all
	       if (pivot == -1)
	           return binarySearch(arr, 0, n-1, key);
	       
	       // If we found a pivot, then first compare with pivot and then
	       // search in two subarrays around pivot
	       if (arr[pivot] == key)
	           return pivot;
	       // if key is greater than 0th element, then search in first half
	       if (arr[0] <= key)
	           return binarySearch(arr, 0, pivot-1, key);
	       return binarySearch(arr, pivot+1, n-1, key);
	}

	private static int binarySearch(int[] arr, int low, int high, int key) {
		if (high < low)
	           return -1;
	        
	       /* low + (high - low)/2; */      
	       int mid = (low + high)/2;  
	       if (key == arr[mid])
	           return mid;
	       if (key > arr[mid])
	           return binarySearch(arr, (mid + 1), high, key);
	       return binarySearch(arr, low, (mid -1), key);
	}

	
	/* Function to get pivot. For array   3, 4, 5, 6, 1, 2 it returns 3 (index of 6) */
	private static int findPivot(int[] arr, int low, int high) {
		// base cases
		if (high < low)
			return -1;
		if (high == low)
			return low;

		int mid = (low + high) / 2;
		if (mid < high && arr[mid] > arr[mid + 1])
			return mid;
		if (mid > low && arr[mid] < arr[mid - 1])
			return (mid - 1);
		if (arr[low] >= arr[mid])
			return findPivot(arr, low, mid - 1);
		return findPivot(arr, mid + 1, high);
	}


	private static int search(int arr[], int low, int high, int key) {
		if (low > high)
			return -1;

		int mid = (low + high) / 2;
		if (arr[mid] == key)
			return mid;

		/* If arr[l...mid] first subarray is sorted */
		if (arr[low] <= arr[mid]) {
			// As this subarray is sorted, we can quickly check if key lies in  half or other half
			if (key >= arr[low] && key <= arr[mid])
				return search(arr, low, mid - 1, key);

            /*If key not lies in first half subarray, Divide other half  into two subarrays,
           such that we can quickly check if key lies in other half */
			return search(arr, mid + 1, high, key);
		}

        /* If arr[l..mid] first subarray is not sorted,
           then arr[mid... h] must be sorted subarry*/
		if (key >= arr[mid] && key <= arr[high])
			return search(arr, mid + 1, high, key);

		return search(arr, low, mid - 1, key);
	}

}
