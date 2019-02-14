package com.kv.j8.streams;

import java.util.Arrays;
import java.util.List;


public class ConvertAListToArray {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        Interval arr[]=new Interval[4]; 
        arr[0]=new Interval(6,7); 
        arr[1]=new Interval(1,2); 
        arr[2]=new Interval(3,4); 
        arr[3]=new Interval(4,7); 
        System.out.println("Array  "+Arrays.toString(arr)); 
        
        
        System.out.println("Array to list");
        List<Interval> ret= Arrays.asList(arr);
        
        System.out.println("List to array");
        Interval arrnew[] = ret.toArray(new Interval[0]);;
        
        System.out.println("List to array "+Arrays.toString(arrnew));
        
        System.out.println("List to array using stream");
        Interval[] arrwithStream = ret.stream().toArray(Interval[] ::new); 
        System.out.println("List to array using stream "+Arrays.toString(arrwithStream));
        
        

    }

}

class Interval 
{ 
    int start,end; 
    Interval(int start, int end) 
    { 
        this.start=start; 
        this.end=end; 
    } 
    
    public String toString() {
        return "("+this.start+", "+ this.end+")";
    }
} 
