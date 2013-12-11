package com.drx.epi;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

import java.util.Random;

public class Doors {
	// @include
	static boolean is_door_open(int i) {
		double sqrt_i = sqrt(i);
		int floor_sqrt_i = (int) floor(sqrt_i);
		return floor_sqrt_i * floor_sqrt_i == i;
	}
	// @exclude

	static void check_answer(int n) {
		boolean[] doors = new boolean[n + 1]; // false means closed door.
		for (int i = 1; i <= n; ++i) {
			int start = 0;
			while (start + i <= n) {
				start += i;
				doors[start] = doors[start] ? false : true;
			}
		}

		for (int i = 1; i <= n; ++i) {
			assert is_door_open(i) == doors[i];
		}
	}

	public static void main(String[] args) {
		Random gen = new Random();
		int n;
		if (args.length == 1) {
			n = Integer.valueOf(args[0]);
		} else {
			n = gen.nextInt(1000) + 1;
		}
		check_answer(n);
	}
}
