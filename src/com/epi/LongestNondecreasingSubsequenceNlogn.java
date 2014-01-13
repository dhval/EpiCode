package com.drx.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class LongestNondecreasingSubsequenceNlogn {
    // @include
    public static int longest_nondecreasing_subsequence(List<Integer> A) {
        ArrayList<Integer> tail_values = new ArrayList<Integer>();

        for (int a : A) {
            int it = Collections.binarySearch(tail_values, a);
            if (it < 0) {
                tail_values.add(a);
            } else {
                tail_values.set(it, a);
            }
        }
        return tail_values.size();
    }
    // @exclude
}
