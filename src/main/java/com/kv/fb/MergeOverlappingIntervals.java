package com.kv.fb;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class MergeOverlappingIntervals {

    public static void main(String[] args) {
        Interval arr[]=new Interval[4]; 
        arr[0]=new Interval(6,7); 
        arr[1]=new Interval(1,2); 
        arr[2]=new Interval(2,4); 
        arr[3]=new Interval(4,7); 
        mergeIntervals(arr); 

    }
    
 // The main function that takes a set of intervals, merges  
    // overlapping intervals and prints the result  
    
    /*
     * 1. Sort the intervals based on increasing order of 
            starting time.
        2. Push the first interval on to a stack.
        3. For each interval do the following
           a. If the current interval does not overlap with the stack 
               top, push it.
           b. If the current interval overlaps with stack top and ending
               time of current interval is more than that of stack top, 
               update stack top with the ending  time of current interval.
        4. At the end stack contains the merged intervals. 
     */
    public static void mergeIntervals(Interval arr[])  
    {  
        // Test if the given set has at least one interval  
        if (arr.length <= 0)  
            return;  
    
        // Create an empty stack of intervals  
        Stack<Interval> stack=new Stack<>(); 
    
        // sort the intervals in increasing order of start time
        Arrays.sort(arr, (i1, i2) -> i1.start - i2.start);
        
        // push the first interval to stack  
        stack.push(arr[0]);  
    
        // Start from the next interval and merge if necessary  
        for (int i = 1 ; i < arr.length; i++)  
        {  
            // get interval from stack top  
            Interval top = stack.peek();  
    
            // if current interval is not overlapping with stack top,  
            // push it to the stack  
            if (top.end < arr[i].start)  
                stack.push(arr[i]);  
    
            // Otherwise update the ending time of top if ending of current  
            // interval is more  
            else if (top.end < arr[i].end)  
            {  
                top.end = arr[i].end;  
                stack.pop();  
                stack.push(top);  
            }  
        }  
    
        // Print contents of stack  
        System.out.print("The Merged Intervals are: "); 
        while (!stack.isEmpty())  
        {  
            Interval t = stack.pop();  
            System.out.print("["+t.start+","+t.end+"] "); 
        }   
    } 
    
    
    
 // The main function that takes a set of intervals, merges  
    // overlapping intervals and prints the result  
    public static void mergeIntervals2(Interval arr[])  
    {  
        // Sort Intervals in decreasing order of  
        // start time  
        Arrays.sort(arr, (i1, i2) -> i1.start - i2.start);
        
    
        int index = 0; // Stores index of last element  
        // in output array (modified arr[])  
    
        // Traverse all input Intervals  
        for (int i=0; i<arr.length; i++)  
        {  
            // If this is not first Interval and overlaps  
            // with the previous one  
            if (index != 0 && arr[index-1].start <= arr[i].end) 
            {  
                while (index != 0 && arr[index-1].start <= arr[i].end)  
                {  
                    // Merge previous and current Intervals  
                    arr[index-1].end = Math.max(arr[index-1].end, arr[i].end);  
                    arr[index-1].start = Math.min(arr[index-1].start, arr[i].start);  
                    index--;  
                }  
            }  
            else // Doesn't overlap with previous, add to solution  
                arr[index] = arr[i];  
            index++;  
        } 
    
        // Now arr[0..index-1] stores the merged Intervals  
        System.out.print("The Merged Intervals are: "); 
        for (int i = 0; i < index; i++)  
            System.out.print("["+arr[i].start+","+arr[i].end+"]");  
    }  
    
    static class Interval 
    { 
        int start,end; 
        Interval(int start, int end) 
        { 
            this.start=start; 
            this.end=end; 
        } 
    } 

}


