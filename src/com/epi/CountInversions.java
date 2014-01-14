package com.drx.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class CountInversions {
    // @include
    public static <T extends Comparable<T>> int count_inversions(List<T> A) {
        return count_inversions_helper(A, 0, A.size());
    }

    private static <T extends Comparable<T>> int count_inversions_helper(List<T> A, int start, int end) {
        if (end - start <= 1) {
            return 0;
        }

        int mid = start + ((end - start) >> 1);
        return count_inversions_helper(A, start, mid) +
                count_inversions_helper(A, mid, end) + merge(A, start, mid, end);
    }

    private static <T extends Comparable<T>> int merge(List<T> A, int start, int mid, int end) {
        ArrayList<T> sorted_A = new ArrayList<T>();
        int left_start = start, right_start = mid, inver_count = 0;

        while (left_start < mid && right_start < end) {
            if (A.get(left_start).compareTo(A.get(right_start)) <= 0) {
                sorted_A.add(A.get(left_start++));
            } else {
                // A[left_start:mid - 1] will be the inversions.
                inver_count += mid - left_start;
                sorted_A.add(A.get(right_start++));
            }
        }
        sorted_A.addAll(A.subList(left_start, mid));
        sorted_A.addAll(A.subList(right_start, end));

        // Update A with sorted_A.
        for(T t : sorted_A) {
            A.set(start++, t);
        }
        return inver_count;
    }
    // @exclude

    // O(n^2) check of inversions
    private static <T extends Comparable<T>> int n_2_check(List<T> A) {
        int count = 0;
        for (int i = 0; i < A.size(); ++i) {
            for (int j = i + 1; j < A.size(); ++j) {
                if (A.get(i).compareTo(A.get(j)) > 0) {
                    ++count;
                }
            }
        }
        System.out.println(count);
        return count;
    }

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n;
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
            } else {
                n = r.nextInt(10000) + 1;
            }
            ArrayList<Integer> A = new ArrayList<Integer>();
            for (int i = 0; i < n; ++i) {
                A.add(r.nextInt(2000001) - 1000000);
            }
            assert(n_2_check(A) == count_inversions(A));
        }
    }
}
