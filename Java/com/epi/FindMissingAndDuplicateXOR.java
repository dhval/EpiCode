package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class FindMissingAndDuplicateXOR {
    // @include
    // Return Pair<Integer, Integer>(duplicate, missing)
    public static Pair<Integer, Integer> find_duplicate_missing(List<Integer> A) {
        int miss_XOR_dup = 0;
        for (int i = 0; i < A.size(); ++i) {
            miss_XOR_dup ^= i ^ A.get(i);
        }

        // We need to find a bit that's set to 1 in miss_XOR_dup. This assignment
        // sets all of bits in differ_bit to 0 except for the least significant
        // bit in miss_XOR_dup that's 1.
        int differ_bit = miss_XOR_dup & (~(miss_XOR_dup - 1));

        int miss_or_dup = 0;
        for (int i = 0; i < A.size(); ++i) {
            if ((i & differ_bit) != 0) {
                miss_or_dup ^= i;
            }
            if ((A.get(i) & differ_bit) != 0) {
                miss_or_dup ^= A.get(i);
            }
        }

        for (int A_i : A) {
            if (A_i == miss_or_dup) {  // find duplicate.
                return new Pair<Integer, Integer>(miss_or_dup, miss_or_dup ^ miss_XOR_dup);
            }
        }
        // miss_or_dup is missing element.
        return new Pair<Integer, Integer>(miss_or_dup ^ miss_XOR_dup, miss_or_dup);
    }
    // @exclude

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n;
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
            } else {
                n = r.nextInt(9999) + 2;
            }
            ArrayList<Integer> A = new ArrayList<Integer>();
            for (int i = 0; i < n; ++i) {
                A.add(i);
            }
            int missing_idx = r.nextInt(n);
            int missing = A.get(missing_idx);
            int dup_idx = r.nextInt(n);
            while (dup_idx == missing_idx) {
                dup_idx = r.nextInt(n);
            }
            int dup = A.get(dup_idx);
            A.set(missing_idx, dup);
            Pair<Integer, Integer> ans = find_duplicate_missing(A);
            System.out.println("times = " + times);
            System.out.println(dup + " " + missing);
            System.out.println(ans.getFirst() + " " + ans.getSecond());
            assert(ans.getFirst().equals(dup) && ans.getSecond().equals(missing));
        }
    }
}
