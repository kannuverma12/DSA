package com.kv.trees;


/**
 * * @author karan.verma
 * 
 * According to BST property, elements of left subtree must be smaller and
 * elements of right subtree must be greater than root. Two arrays represent
 * same BST if for every element x, the elements in left and right subtrees of x
 * appear after it in both arrays. And same is true for roots of left and right
 * subtrees. The idea is to check of if next smaller and greater elements are
 * same in both arrays. Same properties are recursively checked for left and
 * right subtrees. The idea looks simple, but implementation requires checking
 * all conditions for all elements. Following is an interesting recursive
 * implementation of the idea.
 * 
 * Solution at
 * https://www.geeksforgeeks.org/check-two-bsts-contain-set-elements/
 */

public class CheckIdenticalBSTs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	boolean isSameBSTUtil(int a[], int b[], int n, int i1, int i2, int min, int max)
	{
	   int j, k;
	 
	   /* Search for a value satisfying the constraints of min and max in a[] and 
	      b[]. If the parent element is a leaf node then there must be some 
	      elements in a[] and b[] satisfying constraint. */
	   for (j=i1; j<n; j++)
	       if (a[j]>min && a[j]<max)
	           break;
	   for (k=i2; k<n; k++)
	       if (b[k]>min && b[k]<max)
	           break;
	 
	   /* If the parent element is leaf in both arrays */
	   if (j==n && k==n)
	       return true;
	 
	   /* Return false if any of the following is true
	      a) If the parent element is leaf in one array, but non-leaf in other.
	      b) The elements satisfying constraints are not same. We either search
	         for left child or right child of the parent element (decinded by min
	         and max values). The child found must be same in both arrays */
	   if (((j==n)^(k==n)) || a[j]!=b[k])
	       return false;
	 
	   /* Make the current child as parent and recursively check for left and right
	      subtrees of it. Note that we can also pass a[k] in place of a[j] as they
	      are both are same */
	   return isSameBSTUtil(a, b, n, j+1, k+1, a[j], max) &&  // Right Subtree
	          isSameBSTUtil(a, b, n, j+1, k+1, min, a[j]);    // Left Subtree
	}
	
	


	/**
	 * Method 1: The most simple method will be to traverse first tree and store 
	 * its element in a list or array. Now, traverse 2nd tree and simultaneously 
	 * check if the current element is present in the list or not. If yes, 
	 * then mark the element in the list as negative and check for further 
	 * elements otherwise if no, then immediately terminate the traversal and print No. 
	 * If all the elements of 2nd tree is present in the list and are marked negative 
	 * then finally traverse the list to check if there are any non-negative elements 
	 * left. If Yes then it means that the first tree had some extra element 
	 * otherwise the both tree consists same set of elements.

Time Complexity: O( n * n ) , where n is the number of nodes in the BST.
Auxiliary Space: O( n ).
	 */
	
	
	/**
	 * Method 2: This method is an optimization of above approach. 
	 * If we observe carefully, we will see that in the above approach, 
	 * search for element in the list takes linear time. We can optimize this 
	 * operation to be done in constant time using a hashmap instead of list. 
	 * We insert elements of both trees in different hash sets. Finally we compare
	 *  if both hash sets contain same elements or not.
	 * 
	 */

}
