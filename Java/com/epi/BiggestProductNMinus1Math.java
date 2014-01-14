package com.epi;

import java.util.Random;

public class BiggestProductNMinus1Math {
	// @include
	static int find_biggest_n_1_product(int[] A) {
		int zero_count = 0, pos_count = 0, neg_count = 0;
		int zero_idx = -1, s_neg_idx = -1, b_neg_idx = -1, s_pos_idx = -1;

		for (int i = 0; i < A.length; ++i) {
			if (A[i] < 0) {
				++neg_count;
				if (s_neg_idx == -1 || A[i] < A[s_neg_idx]) {
					s_neg_idx = i;
				}
				if (b_neg_idx == -1 || A[b_neg_idx] < A[i]) {
					b_neg_idx = i;
				}
			} else if (A[i] == 0) {
				zero_idx = i;
				++zero_count;
			} else { // A[i] > 0.
				++pos_count;
				if (s_pos_idx == -1 || A[i] < A[s_pos_idx]) {
					s_pos_idx = i;
				}
			}
		}

		// Try to find a number whose elimination could maximize the product of
		// the remaining (n - 1) numbers.
		int x; // stores the idx of eliminated one.
		if (zero_count >= 2) {
			return 0;
		} else if (zero_count == 1) {
			if ((neg_count & 1) > 0) {
				return 0;
			} else {
				x = zero_idx;
			}
		} else {
			if ((neg_count & 1) > 0) { // odd number negative.
				x = b_neg_idx;
			} else { // even number negative.
				if (pos_count > 0) {
					x = s_pos_idx;
				} else {
					x = s_neg_idx;
				}
			}
		}

		int product = 1;
		for (int i = 0; i < A.length; ++i) {
			if (i != x) {
				product *= A[i];
			}
		}
		return product;
	}

	// @exclude

	// n^2 checking
	static int check_ans(int[] A) {
		int max_product = Integer.MIN_VALUE;
		for (int i = 0; i < A.length; ++i) {
			int product = 1;
			for (int j = 0; j < i; ++j) {
				product *= A[j];
			}
			for (int j = i + 1; j < A.length; ++j) {
				product *= A[j];
			}
			if (product > max_product) {
				max_product = product;
			}
		}
		System.out.println(max_product);
		return max_product;
	}

	public static void main(String[] args) {
		Random gen = new Random();
		for (int times = 0; times < 100000; ++times) {
			int n;
			int[] A;
			if (args.length == 1) {
				n = Integer.valueOf(args[0]);
			} else {
				// Get a random number from [2, 11]
				n = gen.nextInt(10) + 2;
			}
			A = new int[n];
			for (int i = 0; i < n; ++i) {
				// Get a random number from [-9, 9]
				A[i] = gen.nextInt(19) - 9;
				System.out.print(A[i] + " ");

			}
			System.out.println();
			int res = find_biggest_n_1_product(A);
			System.out.println(res);
			assert res == check_ans(A);
		}
	}
}
