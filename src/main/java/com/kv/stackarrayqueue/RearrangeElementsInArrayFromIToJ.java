package com.kv.stackarrayqueue;


/*
 * Given an array of size n where all elements are distinct and in range from 0 to n-1, 
 * change contents of arr[] so that arr[i] = j is changed to arr[j] = i.
 * 
 * Input: arr[]  = {1, 3, 0, 2};
	Output: arr[] = {2, 0, 3, 1};
	Explanation for the above output.
	Since arr[0] is 1, arr[1] is changed to 0
	Since arr[1] is 3, arr[3] is changed to 1
	Since arr[2] is 0, arr[0] is changed to 2
	Since arr[3] is 2, arr[2] is changed to 3
 */
public class RearrangeElementsInArrayFromIToJ {

	public static void main(String[] args) {

		RearrangeElementsInArrayFromIToJ arrange = new RearrangeElementsInArrayFromIToJ();
        int arr[] = {2, 0, 1};
        int n = arr.length;
 
        System.out.println("********Method 1********");
        System.out.println("Given array is ");
        arrange.printArray(arr, n);
 
        arrange.rearrangeNaive(arr, n);
 
        System.out.println("Modified array is ");
        arrange.printArray(arr, n);
        
        System.out.println();
        System.out.println("********Method 2********");
        
        System.out.println("Given array is ");
        arrange.printArray(arr, n);
 
        arrange.rearrange2(arr, n);
 
        System.out.println("Modified array is ");
        arrange.printArray(arr, n);
	}
	
	void printArray(int arr[], int n) 
    {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }
	
	// A simple method to rearrange 'arr[0..n-1]' so that 'arr[j]'
    // becomes 'i' if 'arr[i]' is 'j'
    void rearrangeNaive(int arr[], int n) 
    {
        // Create an auxiliary array of same size
        int temp[] = new int[n];
        int i;
 
        // Store result in temp[]
        for (i = 0; i < n; i++)
            temp[arr[i]] = i;
 
        // Copy temp back to arr[]
        for (i = 0; i < n; i++)
            arr[i] = temp[i];
    }
    
	// A simple method to rearrange 'arr[0..n-1]' so that 'arr[j]'
	// becomes 'i' if 'arr[i]' is 'j'
	void rearrange2(int arr[], int n) {
		for (int i = 0; i < n; i++) {

			// retrieving old value and storing with the new one
			arr[arr[i] % n] += i * n;
		}

		for (int i = 0; i < n; i++) {
			// retrieving new value
			arr[i] /= n;
		}
	}

}
