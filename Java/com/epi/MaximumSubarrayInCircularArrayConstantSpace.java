package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MaximumSubarrayInCircularArrayConstantSpace {
    private interface IntegerComparator {
        public Integer compare(Integer o1, Integer o2);
    }

    private static class MaxComparator implements IntegerComparator {
        @Override
        public Integer compare(Integer o1, Integer o2) {
            return o1 > o2 ? o1 : o2;
        }
    }

    private static class MinComparator implements IntegerComparator {
        @Override
        public Integer compare(Integer o1, Integer o2) {
            return o1 > o2 ? o2 : o1;
        }
    }

    // @include
    public static int max_subarray_sum_in_circular(List<Integer> A) {
        // Find the max in non-circular case and circular case.
        int accumulate = 0;
        for(int a : A) {
            accumulate += a;
        }
        return Math.max(find_optimum_subarray_using_comp(A, new MaxComparator()),  // non-circular case.
                accumulate - find_optimum_subarray_using_comp(A, new MinComparator()));  // circular case.
    }

    private static int find_optimum_subarray_using_comp(List<Integer> A, IntegerComparator comp) {
        int till = 0, overall = 0;
        for (int a : A) {
            till = comp.compare(a, a + till);
            overall = comp.compare(overall, till);
        }
        return overall;
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
                    n = Integer.parseInt(args[0]);
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
