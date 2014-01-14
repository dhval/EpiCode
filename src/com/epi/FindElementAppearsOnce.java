package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class FindElementAppearsOnce {
    // @include
    public static int find_element_appears_once(List<Integer> A) {
        int ones = 0, twos = 0;
        int next_ones, next_twos;
        for (int i : A) {
            next_ones = (~i & ones) | (i & ~ones & ~twos);
            next_twos = (~i & twos) | (i & ones);
            ones = next_ones;
            twos = next_twos;
        }
        return ones;
    }
    // @exclude

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 10000; ++times) {
            ArrayList<Integer> A = new ArrayList<Integer>();
            int n;
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
            } else {
                n = r.nextInt(10000) + 1;
            }
            int single = r.nextInt(n);
            for (int i = 0; i < n; ++i) {
                A.add(i);
                if (i != single) {
                    A.add(i);
                    A.add(i);
                }
            }
            System.out.println("Singleton element: " + find_element_appears_once(A));
            assert(find_element_appears_once(A) == single);
        }
    }
}
