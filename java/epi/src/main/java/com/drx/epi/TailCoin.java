package com.drx.epi;

import java.util.Random;

public class TailCoin {
	// @include
	// Return the number of failed trails.
	static int simulate_biased_coin(int n, int trails) {
		// random num generator 
		// we use it to generate random double in [0.0, 1.0]
		Random gen = new Random(); 
		double kBias = 0.4;
		int fails = 0;
		for (int i = 0; i < trails; ++i) {
			int biased_num = 0;
			for (int j = 0; j < n; ++j) {
				biased_num += (gen.nextDouble() >= kBias ? 1 : 0);
			}

			if (biased_num < (n >> 1)) {
				++fails;
			}
		}
		return fails;
	}

	// @exclude

	public static void main(String[] args) {
		int n, trails;
		Random gen = new Random();
		if (args.length == 2) {
			n = Integer.valueOf(args[0]);
			trails = Integer.valueOf(args[1]);
		} else {
			n = gen.nextInt(1000) + 1;
			trails = gen.nextInt(1000) + 1;
		}
		int fails = simulate_biased_coin(n, trails);
		System.out.println("fails = " + fails);
		System.out.println("ratio = " + ((double) fails) / trails);
	}
}
