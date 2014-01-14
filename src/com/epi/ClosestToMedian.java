package com.drx.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ClosestToMedian {
    private static <T> void nth_element(List<T> A, int n, Comparator<T> c) {
        Collections.sort(A, c);
    }

    private static <T extends Comparable<T>> void nth_element(List<T> A, int n) {
        nth_element(A, n, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });
    }

    // @include
    // Promote to double to prevent precision error.
    public static double find_median(List<Integer> A) {
        int half = A.size() >> 1;
        nth_element(A, half);
        if ((A.size() & 1) != 0) {  // A has odd number elements.
            return A.get(half);
        } else {  // A has even number elements.
            int x = A.get(half);
            nth_element(A, half - 1);
            return 0.5 * (x + A.get(half - 1));
        }
    }

    public static List<Integer> find_k_closest_to_median(List<Integer> A, int k) {
        // Find the element i where |A[i] - median| is k-th smallest.
        final double m = find_median(A);
        nth_element(A, k - 1, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Double.valueOf(Math.abs(a - m)).compareTo(Math.abs(b - m));
            }
        });
        return new ArrayList<Integer>(A.subList(0, k));
    }
    // @exclude

    private static void check_ans(List<Integer> A, List<Integer> res, int k) {
        Collections.sort(A);
        double median = ((A.size() & 1) != 0)
                ? A.get(A.size() >> 1)
                : 0.5 * (A.get((A.size() >> 1) - 1) + A.get(A.size() >> 1));
        ArrayList<Double> temp = new ArrayList<Double>();
        for (int a : A) {
            temp.add(Math.abs(median - a));
        }
        Collections.sort(temp);
        for (int r : res) {
            assert(Math.abs(r - median) <= temp.get(k - 1));
        }
    }

    private static void simple_test() {
        List<Integer> D = Arrays.asList(3, 2, 3, 5, 7, 3, 1);
        List<Integer> Dexpres = Arrays.asList(2, 3, 3);
        List<Integer> Dres = find_k_closest_to_median(D, 3);
        check_ans(D, Dres, 3);
        for (int d : Dres) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n, k;
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
                k = r.nextInt(n) + 1;
            } else if (args.length == 2) {
                n = Integer.parseInt(args[0]);
                k = Integer.parseInt(args[1]);
            } else {
                n = r.nextInt(100000) + 1;
                k = r.nextInt(n) + 1;
            }
            ArrayList<Integer> A = new ArrayList<Integer>();
            for (int i = 0; i < n; ++i) {
                A.add(r.nextInt(n << 1));
            }
            //System.out.println(A);
            List<Integer> res = find_k_closest_to_median(A, k);
            assert(res.size() == k);
            System.out.println("n = " + n + ", k = " + k);
            check_ans(A, res, k);
        }
    }
}
