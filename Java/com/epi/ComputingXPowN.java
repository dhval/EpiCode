package com.epi;

import java.util.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ComputingXPowN {
    // @include
    public static List<Integer> get_minimum_expression(int n) {
        if (n == 1) {
            return Arrays.asList(1);
        }

        LinkedList<ArrayList<Integer>> exp_lists = new LinkedList<ArrayList<Integer>>();
        exp_lists.addLast(new ArrayList<Integer>(){{add(1);}});  // construct the initial list with one node
        // whose value is 1.
        while (!exp_lists.isEmpty()) {
            ArrayList<Integer> exp = exp_lists.pop();
            // Try all possible combinations in list exp.
            for (int a : exp) {
                int sum = a + exp.get(exp.size() - 1);
                if (sum > n) {
                    break;  // no possible solution.
                }

                ArrayList<Integer> new_exp = new ArrayList<Integer>(exp);
                new_exp.add(sum);
                if (sum == n) {
                    return new_exp;
                }
                exp_lists.addLast(new_exp);
            }
        }
        // @exclude
        throw new RuntimeException("unknown error");  // this line should never be called.
        // @include
    }
    // @exclude

    public static void main(String[] args) {
        Random r = new Random();
        int n;
        if (args.length == 1) {
            n = Integer.parseInt(args[0]);
        } else {
            n = r.nextInt(100);
        }
        System.out.println("n = " + n);
        List<Integer> min_exp = get_minimum_expression(n);
        System.out.println(min_exp);
        System.out.println("size = " + min_exp.size());
    }
}
