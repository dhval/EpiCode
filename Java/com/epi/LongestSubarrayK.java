package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class LongestSubarrayK {
    // @include
    public static Pair<Integer, Integer> find_longest_subarray_less_equal_k(List<Integer> A,
                                                      int k) {
        // Build the prefix sum according to A.
        ArrayList<Integer> prefix_sum = new ArrayList<Integer>();
        int summ = 0;
        for(int a : A) {
            summ += a;
            prefix_sum.add(summ);
        }

        ArrayList<Integer> min_prefix_sum = new ArrayList<Integer>(prefix_sum);

        for (int i = min_prefix_sum.size() - 2; i >= 0; --i) {
            min_prefix_sum.set(i, Math.min(min_prefix_sum.get(i), min_prefix_sum.get(i + 1)));
        }

        Pair<Integer, Integer> arr_idx = new Pair<Integer, Integer>(
                0, Utils.upper_bound(min_prefix_sum, k) - 1);
        for (int i = 0; i < prefix_sum.size(); ++i) {
            int idx = Utils.upper_bound(min_prefix_sum, k + prefix_sum.get(i)) - 1;
            if (idx - i - 1 > arr_idx.getSecond() - arr_idx.getFirst()) {
                arr_idx = new Pair<Integer, Integer>(i + 1, idx);
            }
        }
        return arr_idx;
    }
    // @exclude

    // O(n^2) checking answer
    private static void check_answer(List<Integer> A, Pair<Integer, Integer> ans, int k) {
        ArrayList<Integer> sum = new ArrayList<Integer>(A.size() + 1);
        sum.add(0);
        for (int i = 0; i < A.size(); ++i) {
            sum.add(sum.get(i) + A.get(i));
        }
        if (ans.getFirst() != -1 && ans.getSecond() != -1) {
            int s = 0;
            for (int i = ans.getFirst(); i <= ans.getSecond(); ++i) {
                s += A.get(i);
            }
            assert(s <= k);
            for (int i = 0; i < sum.size(); ++i) {
                for (int j = i + 1; j < sum.size(); ++j) {
                    if (sum.get(j) - sum.get(i) <= k) {
                        assert((j - i) <= (ans.getSecond() - ans.getFirst() + 1));
                    }
                }
            }
        } else {
            for (int i = 0; i < sum.size(); ++i) {
                for (int j = i + 1; j < sum.size(); ++j) {
                    assert(sum.get(j) - sum.get(i) > k);
                }
            }
        }
    }

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n, k;
            if (args.length == 2) {
                n = Integer.parseInt(args[0]);
                k = Integer.parseInt(args[1]);
            } else if (args.length == 1) {
                n = Integer.parseInt(args[0]);
                k = r.nextInt(10000);
            } else {
                n = r.nextInt(10000) + 1;
                k = r.nextInt(10000);
            }
            ArrayList<Integer> A = new ArrayList<Integer>();
            for (int i = 0; i < n; ++i) {
                A.add(r.nextInt(2001) - 1000);
            }
            //System.out.println(A);
            Pair<Integer, Integer> ans = find_longest_subarray_less_equal_k(A, k);
            System.out.println(k + " " + ans);
            check_answer(A, ans, k);
        }
    }
}
