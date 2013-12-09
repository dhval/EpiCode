package com.drx.epi;

import java.util.Arrays;
import java.util.Random;

public class Rotate_array_test {
	public static int[] rand_vector(int len) {
		Random gen = new Random();
		int[] ret = new int[len + 1];
		while (len-- > 0) {
			ret[len] = gen.nextInt(len + 1);
		}
		return ret;
	}

	public static void check_answer(int[] A, int i, int[] rotated) {
		assert A.length == rotated.length;
		for (int idx = 0; idx < A.length; ++idx) {
			assert (rotated[(idx + i) % rotated.length] == A[idx]);
		}
	}

	public static void main(String[] args) {
		Random gen = new Random();
		for (int times = 0; times < 1000; ++times) {
			int len;
			if (args.length == 1) {
				len = Integer.valueOf(args[0]);
			} else {
				len = gen.nextInt(10000) + 1;
			}
			int[] A = rand_vector(len);
			int i = gen.nextInt(len);

			int[] B = Arrays.copyOf(A, A.length);
			Rotate_array_permutation.rotate_array(B, i);
			check_answer(A, i, B);

			int[] C = Arrays.copyOf(A, A.length);
			Rotate_array.rotate_array(C, i);
			check_answer(A, i, C);
		}
	}
}
