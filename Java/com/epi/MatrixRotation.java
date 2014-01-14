// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import static com.epi.utils.Utils.*;

import java.util.Random;

public class MatrixRotation {
	static void print_matrix(int[][] A) {
		for (int i = 0; i < A.length; ++i) {
			simplePrint(A[i]);
			System.out.println();
		}
	}

	static void check_answer(int[][] A) {
		int k = 1;
		for (int j = A.length - 1; j >= 0; --j) {
			for (int i = 0; i < A.length; ++i) {
				assert (k++ == A[i][j]);
			}
		}
	}

	// @include
	static void copy_matrix(int[][] A, 
			int A_x_s, int A_x_e, int A_y_s, int A_y_e, 
			int[][] S, int S_x, int S_y) {
		for (int i = 0; i < A_x_e - A_x_s; ++i) {
			copy(S[S_x + i], S_y, S_y + A_y_e - A_y_s, 
					A[A_x_s + i], A_y_s);
		}
	}

	static void rotate_matrix_helper(int[][] A, 
			int x_s, int x_e, int y_s, int y_e) {
		if (x_e > x_s + 1) {
			int mid_x = x_s + ((x_e - x_s) >> 1), 
				mid_y = y_s + ((y_e - y_s) >> 1);
			// Move submatrices.
			int[][] C = new int[mid_x - x_s][mid_y - y_s];
			copy_matrix(C, 0, C.length, 0, C.length, A, x_s, y_s);
			copy_matrix(A, x_s, mid_x, y_s, mid_y, A, mid_x, y_s);
			copy_matrix(A, mid_x, x_e, y_s, mid_y, A, mid_x, mid_y);
			copy_matrix(A, mid_x, x_e, mid_y, y_e, A, x_s, mid_y);
			copy_matrix(A, x_s, mid_x, mid_y, y_e, C, 0, 0);

			// Recursively rotate submatrices.
			rotate_matrix_helper(A, x_s, mid_x, y_s, mid_y);
			rotate_matrix_helper(A, x_s, mid_x, mid_y, y_e);
			rotate_matrix_helper(A, mid_x, x_e, mid_y, y_e);
			rotate_matrix_helper(A, mid_x, x_e, y_s, mid_y);
		}
	}

	static void rotate_matrix(int[][] A) {
		rotate_matrix_helper(A, 0, A.length, 0, A.length);
	}

	// @exclude

	public static void main(String[] args) {
		int n;
		if (args.length == 1) {
			n = Integer.valueOf(args[0]);
			int[][] A = new int[1 << n][1 << n];
			int k = 1;
			for (int i = 0; i < A.length; ++i) {
				for (int j = 0; j < A[i].length; ++j) {
					A[i][j] = k++;
				}
			}
			rotate_matrix(A);
			check_answer(A);
		} else {
			Random gen = new Random();

			for (int times = 0; times < 100; ++times) {
				n = gen.nextInt(10) + 1;
				int[][] A = new int[1 << n][1 << n];
				int k = 1;
				for (int i = 0; i < A.length; ++i) {
					for (int j = 0; j < A[i].length; ++j) {
						A[i][j] = k++;
					}
				}
				// print_matrix(A);
				rotate_matrix(A);
				check_answer(A);
				// print_matrix(A);
			}
		}
	}

}
