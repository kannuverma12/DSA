package com.kv.fb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class NClosePoints {
    static List<Point> list;

    public static void main(String[] args) {
        System.out.println("MEthod 1");
        //nClosePoint();
        
        System.out.println("MEthod 2");
        nClosePoint2();
    }
    
    public static void nClosePoint(){
        list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter no of points : ");
        int size = in.nextInt();
        int i;
        for (i = 0; i < size; i++) {
            Point tmp = new Point(in.nextInt(), in.nextInt());
            list.add(tmp);
        }
        System.out.print("Enter N of N closest points needed : ");
        int n = in.nextInt();
        Collections.sort(list, 
                (p1, p2) -> (int) ((Math.pow(p1.x, 2) + Math.pow(p1.y, 2)) - (Math.pow(p2.x, 2) + Math.pow(p2.y, 2))));
        
        i = 0;
        for (Point tmp : list) {
            if (i < n)
                System.out.println("Point " + (i + 1) + " : " + tmp);
            else
                break;
            i++;
        }
    }

    static class Point {
        int x, y;

        Point(int i, int j) {
            x = i;
            y = j;
        }

        @Override
        public String toString() {
            return "( " + x + " , " + y + " )";
        }
    }
    
    public static void nClosePoint2() {
     // input setup
        List<int[]> list = new ArrayList<int[]>();

        list.add(new int[]{2,3});
        list.add(new int[]{2,4});
        list.add(new int[]{5,6});
        list.add(new int[]{8,6});
        list.add(new int[]{1,0});

        // try TreeMap as it supports sort in ascending order
        // distance and point
        TreeMap<Integer, int[]> inputs = new TreeMap<Integer, int[]>();

        // for distance, we only need relative length, so root is not required.
        for (int i = 0; i < list.size(); i++) {
            inputs.put(list.get(i)[0]*list.get(i)[0] + list.get(i)[1]*list.get(i)[1], list.get(i));
        }

        // for a given 'n', return relevant elements from the list
        int n = 3;
        ArrayList<int[]> out = new ArrayList<int[]>();
        for (int i = 0; i < n; i++) {
            out.add(list.get(i));
        }

        // check
        for (int i = 0; i < out.size(); i++) {
            System.out.println(out.get(i)[0] + ", " + out.get(i)[1]);
        }
    }
}
