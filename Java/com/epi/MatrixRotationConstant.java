// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.Random;

import static com.epi.utils.Utils.*;

public class MatrixRotationConstant {
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

	static void check_answer(int[][] A, int[][] B) {
		RotatedMatrix rA = new RotatedMatrix(A);
		for (int i = 0; i < A.length; ++i) {
			for (int j = 0; j < A.length; ++j) {
				assert (rA.ReadEntry(i, j) == B[i][j]);
			}
		}
	}

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
			int[][] B = copy(A);
			rotate_matrix(B);
			check_answer(A, B);
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
				int[][] B = copy(A);
				rotate_matrix(B);
				check_answer(A, B);
			}
		}
	}
}

// @include
class RotatedMatrix {
	private int[][] A_;

	public RotatedMatrix(int[][] A) {
		this.A_ = A;
	}

	int ReadEntry(int i, int j) {
		return A_[A_.length - 1 - j][i];
	}

	void WriteEntry(int i, int j, int v) {
		A_[A_.length - 1 - j][i] = v;
	}

	int size() {
		return A_.length;
	}

}
// @exclude
