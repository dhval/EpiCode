package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class FindMissingAndDuplicate {
    // @include
    // Return pair<int, int>(duplicate, missing).
    public static Pair<Integer, Integer> find_duplicate_missing(List<Integer> A) {
        int sum = 0, square_sum = 0;
        for (int i = 0; i < A.size(); ++i) {
            sum += i - A.get(i);
            square_sum += i * i - A.get(i) * A.get(i);
        }
        return new Pair<Integer, Integer>((square_sum / sum - sum) >> 1, (square_sum / sum + sum) >> 1);
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
