package com.epi;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.ceil;

public class HouseMajority {
	// @include
	// prob is the probability that each Republican wins.
	// r is the number of Republicans wins, and n is the number of elections.
	static double house_majority_helper(double[] prob, int r, int n,
			double[][] P) {
		if (r > n) {
			return 0.0; // base case: not enough Republicans.
		} else if (r == 0 && n == 0) {
			return 1.0; // base case.
		} else if (r < 0) {
			return 0.0;
		}

		if (P[r][n] == -1.0) {
			P[r][n] = house_majority_helper(prob, r - 1, n - 1, P) * prob[n - 1] 
					+ house_majority_helper(prob, r, n - 1, P) * (1.0 - prob[n - 1]);
		}
		return P[r][n];
	}

	static double house_majority(double[] prob, int n) {
		// Initialize DP table.
		double[][] P = new double[n + 1][n + 1];
		for (int i = 0; i < P.length; i++) {
			Arrays.fill(P[i], -1.0);
		}

		// Accumulate the probabilities of majority cases.
		double prob_sum = 0.0;
		for (int r = (int) ceil(0.5 * n); r <= n; ++r) {
			prob_sum += house_majority_helper(prob, r, n, P);
		}
		return prob_sum;
	}

	// @exclude

	static void print_vector(double[] prob) {
		Arrays.sort(prob);
		for (int i = 0; i < prob.length; ++i) {
			System.out.println(String.format("%d:%d:%s", 
					i, (int) 100 * prob[i], ((i + 1) % 10 == 0) ? "\n" : " "));
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Random gen = new Random();
		int n = 435;
		double[] prob = new double[n + 1];
		prob[0] = 0.0;
		for (int i = 1; i < n + 1; ++i) {
			prob[i] = gen.nextDouble();
		}
		print_vector(prob);
		double ans = house_majority(prob, n);
		assert 0.0 <= ans && ans <= 1.0;
		System.out.println();
	}
}
