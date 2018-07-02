package com.kv.algos;


public class InsertionSort {
	public static void main(String... args) {
		insertionSort();
	}

	private static void insertionSort() {
		// TODO Auto-generated method stub
		int[] arr = {1,4,5};
		
		for(int i =1 ;i<arr.length-1;i++) {
			int temp = arr[i];
			if(arr[i] > arr[i-1]) {
				
				arr[i] = arr[i-1];
				arr[i-1] = temp;
				
			}
		}
		
	}

}
