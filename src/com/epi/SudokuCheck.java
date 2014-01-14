package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SudokuCheck {
    // @include
    // Check if a partially filled matrix has any conflicts.
    public static boolean is_valid_Sudoku(int A[][]) {
        // Check row constraints.
        for (int i = 0; i < A.length; ++i) {
            boolean is_present[] = new boolean[A.length + 1];
            for (int j = 0; j < A.length; ++j) {
                if (A[i][j] != 0 && is_present[A[i][j]] == true) {
                    return false;
                } else {
                    is_present[A[i][j]] = true;
                }
            }
        }

        // Check column constraints.
        for (int j = 0; j < A.length; ++j) {
            boolean is_present[] = new boolean[A.length + 1];
            for (int i = 0; i < A.length; ++i) {
                if (A[i][j] != 0 && is_present[A[i][j]] == true) {
                    return false;
                } else {
                    is_present[A[i][j]] = true;
                }
            }
        }

        // Check region constraints.
        int region_size = (int) Math.sqrt(A.length);
        for (int I = 0; I < region_size; ++I) {
            for (int J = 0; J < region_size; ++J) {
                boolean is_present[] = new boolean[A.length + 1];
                for (int i = 0; i < region_size; ++i) {
                    for (int j = 0; j < region_size; ++j) {
                        if (A[region_size * I + i][region_size * J + j] != 0 &&
                                is_present[A[region_size * I + i][region_size * J + j]]) {
                            return false;
                        } else {
                            is_present[A[region_size * I + i][region_size * J + j]] = true;
                        }
                    }
                }
            }
        }
        return true;
    }
    // @exclude
}
