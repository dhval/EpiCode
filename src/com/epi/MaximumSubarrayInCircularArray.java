package com.drx.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MaximumSubarrayInCircularArray {
    // @include
    public static int max_subarray_sum_in_circular(List<Integer> A) {
        return Math.max(find_max_subarray(A), find_circular_max_subarray(A));
    }

    // Calculate the non-circular solution.
    private static int find_max_subarray(List<Integer> A) {
        int maximum_till = 0, maximum = 0;
        for (Integer a : A) {
            maximum_till = Math.max(a, a + maximum_till);
            maximum = Math.max(maximum, maximum_till);
        }
        return maximum;
    }

    // Calculate the solution which is circular.
    private static int find_circular_max_subarray(List<Integer> A) {
        // Maximum subarray sum starts at index 0 and ends at or before index i.
        ArrayList<Integer> maximum_begin = new ArrayList<Integer>();
        int sum = A.get(0);
        maximum_begin.add(sum);
        for (int i = 1; i < A.size(); ++i) {
            sum += A.get(i);
            maximum_begin.add(Math.max(maximum_begin.get(maximum_begin.size() - 1), sum));
        }

        // Maximum subarray sum starts at index i + 1 and ends at the last element.
        Integer maximum_end[] = new Integer[A.size()];
        maximum_end[maximum_end.length - 1] = 0;
        sum = 0;
        for (int i = A.size() - 2; i >= 0; --i) {
            sum += A.get(i + 1);
            maximum_end[i] = Math.max(maximum_end[i + 1], sum);
        }

        // Calculate the maximum subarray which is circular.
        int circular_max = 0;
        for (int i = 0; i < A.size(); ++i) {
            circular_max = Math.max(circular_max, maximum_begin.get(i) + maximum_end[i]);
        }
        return circular_max;
    }
    // @exclude

    // O(n^2) solution
    private static int check_ans(List<Integer> A) {
        int ans = 0;
        for (int i = 0; i < A.size(); ++i) {
            int sum = 0;
            for (int j = 0; j < A.size(); ++j) {
                sum += A.get((i + j) % A.size());
                ans = Math.max(ans, sum);
            }
        }
        System.out.println("correct answer = " + ans);
        return ans;
    }

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n;
            ArrayList<Integer> A = new ArrayList<Integer>();
            if (args.length > 1) {
                for (int i = 1; i < args.length; ++i) {
                    A.add(Integer.parseInt(args[i]));
                }
            } else {
                if (args.length == 1) {
                    n = Integer.parseInt(args[1]);
                } else {
                    n = r.nextInt(10000) + 1;
                }
                while (n-- != 0) {
                    A.add(r.nextInt(20001) - 10000);
                }
            }
            int ans = max_subarray_sum_in_circular(A);
            //System.out.println(A);
            System.out.println("maximum sum = " + ans);
            assert(ans == check_ans(A));
        }
    }
}
