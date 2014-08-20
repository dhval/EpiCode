package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BinarySearchUnknownLengthTemplate {
    // @include
    public static int binary_search_unknown_len(List<Integer> A, int k) {
        // Find the possible range where k exists.
        int p = 0;
        while (true) {
            try {
                int val = A.get((1 << p) - 1);
                if (val == k) {
                    return (1 << p) - 1;
                } else if (val > k) {
                    break;
                }
            }
            catch (Exception e) {
                break;
            }
            ++p;
        }

        // Binary search between indices 2^(p - 1) and 2^p - 2.
        int l = 1 << (p - 1), r = (1 << p) - 2;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            try {
                int val = A.get(m);
                if (val == k) {
                    return m;
                } else if (val > k) {
                    r = m - 1;
                } else {  // A[m] < k
                    l = m + 1;
                }
            }
            catch (Exception e) {
                r = m - 1;  // search the left part if out of boundary.
            }
        }
        return -1;  // nothing matched k.
    }
    // @exclude

    private static void small_test() {
        List<Integer> A = Arrays.asList(1, 2, 3);
        assert(binary_search_unknown_len(A, 3) == 2);
        assert(binary_search_unknown_len(A, 1) == 0);
        assert(binary_search_unknown_len(A, 2) == 1);
        assert(binary_search_unknown_len(A, 4) == -1);
    }

    public static void main(String[] args) {
        small_test();
        int n, k;
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
                k = r.nextInt(100000);
            } else if (args.length == 2) {
                n = Integer.parseInt(args[0]);
                k = Integer.parseInt(args[1]);
            } else {
                n = r.nextInt(1000000) + 1;
                k = r.nextInt(n << 1);
            }
            ArrayList<Integer> A = new ArrayList<Integer>();
            for (int i = 0; i < n; ++i) {
                A.add(r.nextInt(n << 1));
            }
            Collections.sort(A);
            System.out.println(n + " " + k);
            int idx = binary_search_unknown_len(A, k);
            System.out.println(idx);
            assert(idx != -1 && A.get(idx) == k || Collections.binarySearch(A, k) < 0);
        }
    }
}
