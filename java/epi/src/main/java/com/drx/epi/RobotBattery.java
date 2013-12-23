// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.drx.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class RobotBattery {

	// @include
	static int find_battery_capacity(List<Integer> h) {
		int min_height = Integer.MAX_VALUE, capacity = 0;
		for (Integer height : h) {
			capacity = max(capacity, height - min_height);
			min_height = min(min_height, height);
		}
		return capacity;
	}

	// @exclude

	// O(n^2) checking answer.
	static int check_ans(List<Integer> h) {
		int cap = 0;
		for (int i = 1; i < h.size(); ++i) {
			for (int j = 0; j < i; ++j) {
				cap = max(cap, h.get(i) - h.get(j));
			}
		}
		return cap;
	}

	public static void main(String[] args) {
		Random gen = new Random();
		for (int times = 0; times < 1000; ++times) {
			int n;
			if (args.length == 1) {
				n = Integer.valueOf(args[0]);
			} else {
				n = gen.nextInt(10000) + 1;
			}
			List<Integer> A = new ArrayList<Integer>();
			for (int i = 0; i < n; ++i) {
				A.add(abs(gen.nextInt()));
			}
			System.out.println(find_battery_capacity(A));
			assert (check_ans(A) == find_battery_capacity(A));
		}
	}

}
