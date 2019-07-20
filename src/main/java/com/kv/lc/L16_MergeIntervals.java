package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author karanverma
 *
 *  Given a collection of intervals, merge all overlapping intervals.
 *  For example,
 *  Given [1,3],[2,6],[8,10],[15,18],
 *  return [1,6],[8,10],[15,18].
 */
public class L16_MergeIntervals {
    /*
     * The key to solve this problem is defining a Comparator first to sort the arraylist of Intevals
     */

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Interval i1 = new Interval(1,3);
        Interval i2 = new Interval(2,6);
        Interval i3 = new Interval(8,10);
        Interval i4 = new Interval(15,18);
        List<Interval> list = new ArrayList<>();
        list.add(i1);
        list.add(i2);
        list.add(i3);
        list.add(i4);
        System.out.println("Original Intervals : "+Arrays.toString(list.toArray()));
        System.out.println("Merged Intervals : "+Arrays.toString(merge(list).toArray()));
        
    }
    
    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return result;
        }

        // Comparator<Interval> comp = Comparator.comparing((Interval i)->i.start);
        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);

        Interval temp = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (temp.end >= curr.start) {
                temp.end = Math.max(curr.end, temp.end);
            } else {
                result.add(temp);
                temp = curr;
            }
        }

        result.add(temp);

        return result;
    }
    
    static class Interval{
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public String toString() {
            return "(" +start + ", "+ end + ")";
        }
    }

}
