package com.drx.epi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class LongestNondecreasingSubsequenceN2 {
    // @include
    public static List<Integer> longest_nondecreasing_subsequence(List<Integer> A) {
        // Empty array.
        if (A.isEmpty()) {
            return A;
        }

        ArrayList<Integer> longest_length = new ArrayList<Integer>(A.size());
        ArrayList<Integer> previous_index = new ArrayList<Integer>(A.size());
        for(int i = 0; i < A.size(); i++) {
            longest_length.add(1);
            previous_index.add(-1);
        }

        int max_length_idx = 0;
        for (int i = 1; i < A.size(); ++i) {
            for (int j = 0; j < i; ++j) {
                if (A.get(i) >= A.get(j) && longest_length.get(j) + 1 > longest_length.get(i)) {
                    longest_length.set(i, longest_length.get(j) + 1);
                    previous_index.set(i, j);
                }
            }
            // Record the index where longest subsequence ends.
            if (longest_length.get(i) > longest_length.get(max_length_idx)) {
                max_length_idx = i;
            }
        }

        // Build the longest nondecreasing subsequence.
        int max_length = longest_length.get(max_length_idx);
        ArrayList<Integer> ret = new ArrayList<Integer>(max_length);
        while (max_length-- > 0) {
            ret.add(0, A.get(max_length_idx));
            max_length_idx = previous_index.get(max_length_idx);
        }
        return ret;
    }
    // @exclude
}
