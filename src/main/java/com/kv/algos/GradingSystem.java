package com.kv.algos;

import java.util.Scanner;

public class GradingSystem {

    static int solve(int grades) {
        // Complete this function
        int ret = 0;
        // for(int i=0;i<grades.length;i++){
        if (grades < 38)
            ret = grades;
        else {
            int nextMultiple = grades + (5 - grades % 5);

            int mod = nextMultiple - grades;
            if (mod < 3) {
                ret = nextMultiple;
            } else
                ret = grades;

        }

        // }
        return ret;

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] grades = new int[n];
        for (int grades_i = 0; grades_i < n; grades_i++) {
            grades[grades_i] = in.nextInt();
        }
        for (int i = 0; i < grades.length; i++) {
            int result = solve(grades[i]);
            System.out.println(result);
        }
    }
}