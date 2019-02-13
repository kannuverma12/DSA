package com.kv.stackarrayqueue;

public class FindRowWithMax1s {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix =    {{0,0,0,1},
							{0,1,1,1},
							{1,1,1,1},
							{0,0,0,0}};
		int x = rowWithMax1s(matrix);
		System.out.println("Method 1 ---> index of row with max 1s = "+x);
		
		int y = rowWithMax1sSecond(matrix);
		System.out.println("Method 2 ---> index of row with max 1s = "+y);
		
	}
	
	/* 
	 * A simple method is to do a row wise traversal of the matrix, 
	 * count the number of 1s in each row and compare the count with max. 
	 * Finally, return the index of row with maximum 1s. 
	 * The time complexity of this method is O(m*n) where m is number of rows and n is number of columns in matrix.
		We can do better. Since each row is sorted, we can use Binary Search to count of 1s in each row. 
		We find the index of first instance of 1 in each row. The count of 1s will be equal to total 
		number of columns minus the index of first 1.
		Time Complexity: O(mLogn) where m is number of rows and n is number of columns in matrix.
	 * 
	 * 
	 * A function to find the index of first index of 1 in a boolean array arr[] */
	public static int first(int arr[], int low, int high)
	{
	  if(high >= low)
	  {
	    // get the middle index  
	    int mid = low + (high - low)/2; 
	 
	    // check if the element at middle index is first 1
	    if ( ( mid == 0 || arr[mid-1] == 0) && arr[mid] == 1)
	      return mid;
	 
	    // if the element is 0, recur for right side
	    else if (arr[mid] == 0)
	      return first(arr, (mid + 1), high);
	 
	    else // If element is not first 1, recur for left side
	      return first(arr, low, (mid -1));
	  }
	  return -1;
	}
	 
	// The main function that returns index of row with maximum number of 1s. 
	public static int rowWithMax1s(int[][] mat)
	{
	    int max_row_index = 0, max = -1; // Initialize max values
	 
	    // Traverse for each row and count number of 1s by finding the index 
	    // of first 1
	    int i, index;
	    for (i = 0; i < mat.length-1; i++)
	    {
	       index = first (mat[i], 0, mat[i].length-1);
	       if (index != -1 && mat[i].length-index > max)
	       {
	           max = mat[i].length-1 - index;
	           max_row_index = i;
	       }
	    }
	 
	    return max_row_index;
	}
	
	
	/*
	 * The above solution can be optimized further. Instead of doing binary search in every row, 
	 * we first check whether the row has more 1s than max so far. If the row has more 1s, then only count 1s in the row. 
	 * Also, to count 1s in a row, we don’t do binary search in complete row, we do search in before the index of last max.
	 */
	
	// The main function that returns index of row with maximum number of 1s.
	public static int rowWithMax1sSecond(int[][] mat)
	{
	    // Initialize first row as row with max 1s
	    int max_row_index = 0;
	 
	    // The function first() returns index of first 1 in row 0.
	    // Use this index to initialize the index of leftmost 1 seen so far
	    int j = first(mat[0], 0, mat.length-1-1);
	    if (j == -1) // if 1 is not present in first row
	      j = mat.length-1 - 1;
	 
	    for (int i = 1; i < mat[0].length; i++)
	    {
	        // Move left until a 0 is found
	        while (j >= 0 && mat[i][j] == 1)
	        {
	           j = j-1;  // Update the index of leftmost 1 seen so far
	           max_row_index = i;  // Update max_row_index
	        }
	    }
	    return max_row_index;
	}

}
