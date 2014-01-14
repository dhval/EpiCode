package com.epi;

import java.util.ArrayList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MaxSubmatrixRectangleBruteForce {
    // O(m^3 n^3) time solution.
    public static int max_rectangle_submatrix_brute_force(ArrayList<ArrayList<Boolean>> A) {
        int max = 0;
        for (int a = 0; a < A.size(); ++a) {
            for (int b = 0; b < A.get(a).size(); ++b) {
                for (int c = a; c < A.size(); ++c) {
                    for (int d = b; d < A.get(c).size(); ++d) {
                        boolean all_1 = true;
                        int count = 0;
                        for (int i = a; i <= c; ++i) {
                            for (int j = b; j <= d; ++j) {
                                if (A.get(i).get(j) == false) {
                                    all_1 = false;
                                    count = 0;
                                    break;
                                } else {
                                    ++count;
                                }
                            }
                            if (all_1 == false) {
                                break;
                            }
                        }
                        if (all_1 == true && count > max) {
                            max = count;
                        }
                    }
                }
            }
        }
        return max;
    }
}
