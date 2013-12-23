// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.drx.epi;

import java.util.Random;

public class MatrixRotationNaive {

	static void print_matrix(int[][] A) {
		for (int i = 0; i < A.length; ++i) {
			// simplePrint(A[i]);
			for (int j = 0; j < A.length; ++j) {
				System.out.print(String.format("A[%d, %d] = %d ", i, j, A[i][j]));
			}
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
	static void rotate_matrix(int[][] A) {
		for (int i = 0; i < (A.length >> 1); ++i) {
			for (int j = i; j < A.length - i - 1; ++j) {
				int temp = A[i][j];
				A[i][j] = A[A.length - 1 - j][i];
				A[A.length - 1 - j][i] = A[A.length - 1 - i][A.length - 1 - j];
				A[A.length - 1 - i][A.length - 1 - j] = A[j][A.length - 1 - i];
				A[j][A.length - 1 - i] = temp;
			}
		}
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
			print_matrix(A);
			rotate_matrix(A);
			check_answer(A);
			print_matrix(A);
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
				rotate_matrix(A);
				check_answer(A);
			}
		}
	}

}
