package com.drx.epi;

import java.util.*;

import static com.drx.epi.LargestRectangleUnderSkyline.*;
import static com.drx.epi.MaxSubmatrixRectangleBruteForce.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MaxSubmatrixRectangleImproved {
    // @include
    public static int max_rectangle_submatrix(ArrayList<ArrayList<Boolean>> A) {
        Integer table[][] = new Integer[A.size()][A.get(0).size()];

        for (int i = A.size() - 1; i >= 0; --i) {
            for (int j = A.get(i).size() - 1; j >= 0; --j) {
                table[i][j] = A.get(i).get(j) ? i + 1 < A.size() ? table[i + 1][j] + 1 : 1 : 0;
            }
        }

        // Find the max among all instances of the largest rectangle.
        int max_rect_area = 0;
        for (Integer t[] : table) {
            max_rect_area = Math.max(max_rect_area, calculate_largest_rectangle(Arrays.asList(t)));
        }
        return max_rect_area;
    }
    // @exclude

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n, m;
            if (args.length == 2) {
                n = Integer.parseInt(args[0]);
                m = Integer.parseInt(args[1]);
            } else {
                n = r.nextInt(60) + 1;
                m = r.nextInt(60) + 1;
            }
            ArrayList<ArrayList<Boolean>> A = new ArrayList<ArrayList<Boolean>>(n);
            for (int i = 0; i < n; ++i) {
                ArrayList<Boolean> last = new ArrayList<Boolean>(m);
                A.add(last);
                for (int j = 0; j < m; ++j) {
                    last.add(r.nextBoolean());
                }
            }
            //System.out.println(A);
            System.out.println(max_rectangle_submatrix(A));
            System.out.println(max_rectangle_submatrix_brute_force(A));
            assert(max_rectangle_submatrix_brute_force(A) == max_rectangle_submatrix(A));
        }
    }
}
