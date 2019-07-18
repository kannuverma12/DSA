package com.kv.ib;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class I1_Arrays {

    public static void main(String[] args) {
        Integer[][] aIntegers = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        
        ArrayList<ArrayList<Integer>> m = new ArrayList<>();
        ArrayList<Integer> n = new ArrayList<>();
        n.add(1);n.add(2);n.add(3);n.add(4);
        m.add(n);
        n = new ArrayList<>();
        n.add(5);n.add(6);n.add(7);n.add(8);
        m.add(n);
        n = new ArrayList<>();
        n.add(9);n.add(10);n.add(11);n.add(12);
        m.add(n);
        
        ArrayList<ArrayList<Integer>> B = performOps(m);
        for (int i = 0; i < B.size(); i++) {
            for (int j = 0; j < B.get(i).size(); j++) {
                    System.out.print(B.get(i).get(j) + " ");
            }
        }

    }
    
    static ArrayList<ArrayList<Integer>> performOps(ArrayList<ArrayList<Integer>> A) {
        ArrayList<ArrayList<Integer>> B = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < A.size(); i++) {
            B.add(new ArrayList<Integer>());
        
            for (int j = 0; j < A.get(i).size(); j++) {
                B.get(i).add(0);
            }

            for (int j = 0; j < A.get(i).size(); j++) {
                B.get(i).set(A.get(i).size() - 1 - j, A.get(i).get(j));
            }
        }
        return B;
}
}
