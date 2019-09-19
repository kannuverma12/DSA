package com.kv.fb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author karanverma
 * 
 *  Given two unsorted arrays, one with event start times and one with end times, find out if 
 *  any two events overlap
 *
 */
public class FindOverlappingEvents {

    public static void main(String[] args) {
        Interval arr[] = new Interval[4];
        arr[0] = new Interval(6, 7);
        arr[1] = new Interval(1, 2);
        arr[2] = new Interval(3, 4);
        arr[3] = new Interval(4, 7);
        System.out.println("Intervals Overlapping ? " + Arrays.toString(isOverlap(arr)));

    }

    private static Interval[] isOverlap(Interval[] arr) {
        // Interval[] ret = new Interval[arr.length];
        List<Interval> ret = new ArrayList<>();
        Arrays.sort(arr, (i1, i2) -> i1.start - i2.start);

        System.out.println("Sorted Intervals = " + Arrays.toString(arr));

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].end > arr[i].start) {
                ret.add(arr[i - 1]);
                ret.add(arr[i]);
                // ret[i-1] = arr[i-1];
                // ret[i] = arr[i];
                System.out.println("Overlapping intervals are : " + arr[i - 1].toString() +
                        " and " + arr[i].toString());
                // return true;
            }
        }
        return ret.toArray(new Interval[0]);
    }

    static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return "(" + this.start + ", " + this.end + ")";
        }
    }

}


