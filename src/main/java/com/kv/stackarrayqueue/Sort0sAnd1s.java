package com.kv.stackarrayqueue;

public class Sort0sAnd1s {

	public static void main(String[] args) {

		Sort0sAnd1s seg = new Sort0sAnd1s();
        int arr[] = new int[]{0, 1, 0, 1, 1, 1};
        int i, arr_size = arr.length;
 
        seg.segregate0and1(arr, arr_size);
 
        System.out.print("Array after segregation is ");
        for (i = 0; i < 6; i++)
            System.out.print(arr[i] + " ");
	}
	

	/*
	 * Do following while left < right
		a) Keep incrementing index left while there are 0s at it
		b) Keep decrementing index right while there are 1s at it
		c) If left < right then exchange arr[left] and arr[right]
	 */
	private void segregate0and1(int[] arr, int size) {
		int left = 0, right = size-1;
		while(left < right) {
			while(arr[left] == 0 && left < right)
				left++;
			while(arr[right] == 1 && left < right)
				right--;
			
			if(left < right) {
				arr[left] = 0;
				arr[right] = 1;
				left++;
				right--;
			}
		}
	}
	
	/*Less optimized way
	 * 
	 * 1) Count the number of 0s. Let count be C.
		2) Once we have count, we can put C 0s at the beginning and 1s at the remaining n – C positions in array.
	 */
	 static void method1Segregate0and1(int arr[], int n)
	{
        int count = 0; // counts the no of zeros in arr
     
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0)
                count++;
        }
 
        // loop fills the arr with 0 until count
        for (int i = 0; i < count; i++)
            arr[i] = 0;
 
        // loop fills remaining arr space with 1
        for (int i = count; i < n; i++)
            arr[i] = 1;
    }
	 
	 
	 /*
	  * More optimized
	  * 1. Take two pointer type0(for element 0) starting from beginning (index = 0) and type1(for element 1) starting from end (index = array.length-1).
			Initialize type0 = 0 and type1 = array.length-1
		2. It is intended to Put 1 to the right side of the array. Once it is done, then 0 will definitely towards left side of array.
	  */
	 static void Method3segregate0and1(int arr[]) {
	        int type0 = 0;
	        int type1 = arr.length - 1;
	         
	        while (type0 < type1) {
	            if (arr[type0] == 1) {
	                // swap
	                arr[type1] = arr[type1]+ arr[type0];
	                arr[type0] = arr[type1]-arr[type0];
	                arr[type1] = arr[type1]-arr[type0];
	                type1--;
	            } else {
	                type0++;
	            }
	        }
	 
	    }
}
