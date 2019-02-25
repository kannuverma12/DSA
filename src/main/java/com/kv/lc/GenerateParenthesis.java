package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author karanverma
 *
 */
public class GenerateParenthesis {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<String> pp = generateParenthesis(3);
        System.out.println(Arrays.toString(pp.toArray()));

    }
    
    public static List<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<String>();
        dfs(result, "", n, n);
        return result;
    }
    /*
    left and right represents the remaining number of ( and ) that need to be added. 
    When left > right, there are more ")" placed than "(". Such cases are wrong and the method stops. 
    */
    public static void dfs(ArrayList<String> result, String s, int left, int right) {
        if (left > right)
            return;

        if (left == 0 && right == 0) {
            result.add(s);
            return;
        }

        if (left > 0) {
            dfs(result, s + "(", left - 1, right);
        }

        if (right > 0) {
            dfs(result, s + ")", left, right - 1);
        }
    }
    
    public static List<String> generateParenthesisMethod2(int n) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<Integer> diff = new ArrayList<Integer>();
     
        result.add("");
        diff.add(0);
     
        for (int i = 0; i < 2 * n; i++) {
            ArrayList<String> temp1 = new ArrayList<String>();
            ArrayList<Integer> temp2 = new ArrayList<Integer>();
     
            for (int j = 0; j < result.size(); j++) {
                String s = result.get(j);
                int k = diff.get(j);
     
                if (i < 2 * n - 1) {
                    temp1.add(s + "(");
                    temp2.add(k + 1);
                }
     
                if (k > 0 && i < 2 * n - 1 || k == 1 && i == 2 * n - 1) {
                    temp1.add(s + ")");
                    temp2.add(k - 1);
                }
            }
     
            result = new ArrayList<String>(temp1);
            diff = new ArrayList<Integer>(temp2);
        }
     
        return result;
    }

}
