package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 */
public class L57_MedianOfTwoSortedArray {

    public static void main(String[] args) {
        int arr1[] = {9, 15};  
        int arr2[] = {5, 8, 10, 20};  
        
        System.out.println(getMedian(arr1, arr2, arr1.length, arr2.length));

        float x = find(arr1,0,arr1.length-1,arr2,0,arr2.length-1);
        System.out.println("Method 4 : Median of combined sorted array is: " + x);
        
    }


    /*
    Method 2
    Complexity of this algorithm to find median of two sorted arrays is log(max(m,n))
    where m and n are size of two arrays.

    Now, problem reduces to find index i such that A[i-1] <= B[j] and B[j-1]<=A[i] is true.

        This is where binary search comes into picture. We can start i as mid of array A,
        j = (n+m+1)/2-i and see if this i satisfies the condition. There can be three possible
        outcomes for condition.
        1. A[i-1] <= B[j] and B[j-1]<=A[i] is true, we return the index i.
        2. If B[j-1] > A[i], in this case, A[i] is too small. How can we increase it? by moving
        towards right. If i is increased, value A[i] is bound to increase, and also it will
        decrease j. In this case, B[j-1] will decrease and A[i] will increase which will
        make B[j-1]<=A[i] is true. So, limit search space for i to mid+1 to m and go to step 1.
        3. A[i-1] > B[j], means A[i-1] is too big. And we must decrease i to get A[i-1]<=B[j].
        Limit search space for i to 0 mid-1 and go to step 1
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int[] temp;
        int lenA = A.length, lenB = B.length;

        /*We want array A to be always smaller than B so that j is always greater than zero */
        if (lenA > lenB) {
            temp = A;
            A = B;
            B = temp;
        }

        int iMin = 0;
        int iMax = A.length;
        int midLength = (A.length + B.length + 1) / 2;

        int i = 0;
        int j = 0;

        while (iMin <= iMax) {
            i = (iMin + iMax) / 2;
            j = midLength - i;
            if (i < A.length && B[j - 1] > A[i]) {
                // i is too small, must increase it
                iMin = i + 1;
            } else if (i > 0 && A[i - 1] > B[j]) {
                // i is too big, must decrease it
                iMax = i - 1;
            } else {
                // i is perfect
                int maxLeft = 0;
                //If there we are at the first element on array A
                if (i == 0)
                    maxLeft = B[j - 1];
                    //If we are at te first element of array B
                else if (j == 0)
                    maxLeft = A[i - 1];
                    //We are in middle somewhere, we have to find max
                else
                    maxLeft = Integer.max(A[i - 1], B[j - 1]);

                //If length of two arrays is odd, return max of left
                if ((A.length + B.length) % 2 == 1)
                    return maxLeft;

                int minRight = 0;
                if (i == A.length)
                    minRight = B[j];
                else if (j == B.length)
                    minRight = A[i];
                else
                    minRight = Integer.min(A[i], B[j]);

                return (maxLeft + minRight) / 2.0;
            }
        }
        return -1;

    }


    //Method 3
    /*
    This problem can be converted to the problem of finding kth element, k is (A's length + B' Length)/2.
    If any of the two arrays is empty, then the kth element is the non-empty array's kth element.
    If k == 0, the kth element is the first element of A or B.

    For normal cases(all other cases), we need to move the pointer at the pace of half of
    the array size to get O(log(n)) time.
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (total % 2 == 0) {
            return (getKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, total / 2)
                    + getKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, total / 2 - 1)) / 2.0;
        } else {
            return getKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, total / 2);
        }
    }

    //k is the index starting from 0
    private int getKth(int[] nums1, int i1, int j1, int[] nums2, int i2, int j2, int k) {
        if (j1 < i1) {
            return nums2[i2 + k];
        }
        if (j2 < i2) {
            return nums1[i1 + k];
        }

        if (k == 0) {
            return Math.min(nums1[i1], nums2[i2]);
        }

        int len1 = j1 - i1 + 1;
        int len2 = j2 - i2 + 1;

        int m1 = k * len1 / (len1 + len2);
        int m2 = k - m1 - 1;

        m1 += i1;
        m2 += i2;

        if (nums1[m1] < nums2[m2]) {
            k = k - (m1 - i1 + 1);
            j2 = m2;
            i1 = m1 + 1;
        } else {
            k = k - (m2 - i2 + 1);
            j1 = m1;
            i2 = m2 + 1;
        }

        return getKth(nums1, i1, j1, nums2, i2, j2, k);
    }

    /*
    Method 4
    Say arrays are array1[] and array2[].
Calculate the median of both the arrays, say m1 and m2 for array1[] and array2[].
If m1 == m2 then return m1 or m2 as final result.
If m1 > m2 then median will be present in either of the sub arrays.
Array1[] – from first element to m1.
Array2[] – from m2 to last element.
If m2 > m1 then median will be present in either of the sub arrays.
Array1[] – from m1 to last element.
Array2[] – from first element to m2.
Repeat the steps from 1 to 5 recursively until 2 elements are left in both the arrays.
Then apply the formula to get the median
Median = (max(array1[0],array2[0]) + min(array1[1],array2[1]))/2
     */
    public static float find(int[] a, int start_a, int end_a, int[] b, int start_b, int end_b) {

        if (end_a - start_a + 1 == 2 && end_b - start_b + 1 == 2) {
            float x = Math.max(a[start_a], b[start_b]);
            float y = Math.min(a[end_a], b[end_b]);
            return (x + y) / 2;
        }

        float median_a = getMedian(a, start_a, end_a);
        float median_b = getMedian(b, start_b, end_b);

        int mid_a = (start_a + end_a) / 2;
        int mid_b = (start_b + end_b) / 2;
        if (median_a > median_b) {
            return find(a, start_a, mid_a, b, mid_b, end_b);
        } else {
            return find(a, mid_a, end_a, b, start_b, mid_b);
        }

    }

    public static float getMedian(int[] x, int start, int end) {
        int size = end - start + 1;
        double median;
        if (size % 2 == 0) {
            float m = x[start + (size / 2)];
            float n = x[start + (size - 1) / 2];
            return (m + n) / 2;
        } else {
            return x[start + (size - 1) / 2];
        }
    }
    
    /* This function returns median of ar1[] and ar2[].  
    Assumption in this function:  Both ar1[] and ar2[] are sorted arrays */
    static int getMedian(int arr1[], int arr2[], int n, int m)  
    {  
        int i = 0; /* Current index of input array ar1[] */
        int j = 0; /* Current index of input array ar2[] */
        int count;  
        int median1 = -1, median2 = -1;  
      
        // Since there are (n+m) elements, There are following two cases 
        // if n+m is odd then the middle index is median i.e. (m+n)/2 
        if((m + n) % 2 == 1) { 
            for (count = 0; count <= (n + m)/2; count++) { 
                if(i != n && j != m){ 
                    median1 = (arr1[i] > arr2[j]) ? arr2[j++] : arr1[i++]; 
                } 
                else if(i < n){ 
                    median1 = arr1[i++]; 
                } 
                // for case when j<m, 
                else{ 
                    median1 = arr1[j++]; 
                } 
            } 
            return median1; 
        } 
          
        // median will be average of elements  at index ((m+n)/2 - 1) and (m+n)/2 
        // in the array obtained after merging ar1 and ar2 
        else { 
            for (count = 0; count <= (n + m)/2; count++) { 
                median2 = median1; 
                if(i != n && j != m){ 
                    median1 = (arr1[i] > arr2[j]) ? arr2[j++] : arr1[i++]; 
                } 
                else if(i < n){ 
                    median1 = arr1[i++]; 
                } 
                // for case when j<m, 
                else{ 
                    median1 = arr1[j++]; 
                } 
            } 
            return (median1 + median2)/2; 
        } 
    } 

}
