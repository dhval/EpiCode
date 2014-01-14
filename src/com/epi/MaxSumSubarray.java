package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MaxSumSubarray {
    // @include
    public static Pair<Integer, Integer> find_maximum_subarray(List<Integer> A) {
        // A[range.first : range.second - 1] will be the maximum subarray.
        Pair<Integer, Integer> range = new Pair<Integer, Integer>(0, 0);
        int min_idx = -1, min_sum = 0, sum = 0, max_sum = 0;
        for (int i = 0; i < A.size(); ++i) {
            sum += A.get(i);
            if (sum < min_sum) {
                min_sum = sum;
                min_idx = i;
            }
            if (sum - min_sum > max_sum) {
                max_sum = sum - min_sum;
                range = new Pair<Integer, Integer>(min_idx + 1, i + 1);
            }
        }
        return range;
    }
    // @exclude

    private static List<Integer> rand_vector(int len) {
        Random r = new Random();
        ArrayList<Integer> ret = new ArrayList<Integer>();
        while (len-- != 0) {
            ret.add(r.nextInt(2001) - 1000);
        }
        return ret;
    }

    private static void check_max_sum(List<Integer> A, Pair<Integer, Integer> range) {
        int max_sum = 0;
        for (int i = range.getFirst(); i < range.getSecond(); ++i) {
            max_sum += A.get(i);
        }
        for (int i = 0; i < A.size(); ++i) {
            int sum = 0;
            for (int j = i; j < A.size(); ++j) {
                sum += A.get(j);
                assert(sum <= max_sum);
            }
        }
    }

    private static void simple_test() {
        List<Integer> B = Arrays.asList(1);
        Pair<Integer, Integer> range = find_maximum_subarray(B);
        System.out.println(range);
        check_max_sum(B, range);
        B = Arrays.asList(-5);
        range = find_maximum_subarray(B);
        System.out.println(range);
        B = Arrays.asList(0);
        range = find_maximum_subarray(B);
        System.out.println(range);
        B = Arrays.asList(0, 0);
        range = find_maximum_subarray(B);
        System.out.println(range);
        B = Arrays.asList(0, 0, 0);
        range = find_maximum_subarray(B);
        System.out.println(range);
        B = Arrays.asList(0, -5, 0);
        range = find_maximum_subarray(B);
        System.out.println(range);
    }

    public static void main(String[] args) {
        simple_test();
        Random r = new Random();
        for (int times = 0; times < 10000; ++times) {
            List<Integer> A;
            if (args.length == 0) {
                A = rand_vector(r.nextInt(10000) + 1);
            } else if (args.length == 1) {
                int n = Integer.parseInt(args[0]);
                A = rand_vector(n);
            } else {
                A = new ArrayList<Integer>();
                for (int i = 0; i < args.length; ++i) {
                    A.add(Integer.parseInt(args[i]));
                }
            }
            Pair<Integer, Integer> range = find_maximum_subarray(A);
            System.out.println(range);
            check_max_sum(A, range);
        }
    }
}
