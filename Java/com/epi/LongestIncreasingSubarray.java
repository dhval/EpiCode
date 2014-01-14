// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LongestIncreasingSubarray {
	// @include
	static Pair<Integer, Integer> find_longest_increasing_subarray(List<Integer> A) {
	  int max_len = 1;
	  Pair<Integer, Integer> ans = new Pair<Integer, Integer>(0, 0);
	  int i = 0;
	  while (i < A.size()) {
	    // Checks backwardly and skip if A[j] >= A[j + 1].
	    boolean is_skippable = false;
	    for (int j = i + max_len - 1; j >= i; --j) {
	      if (j + 1 >= A.size() || A.get(j) >= A.get(j + 1)) {
	        i = j + 1;
	        is_skippable = true;
	        break;
	      }
	    }

	    // Checks forwardly if it is not skippable.
	    if (is_skippable == false) {
	      i += max_len - 1;
	      while (i + 1 < A.size() && A.get(i) < A.get(i + 1)) {
	        ++i; ++max_len;
	      }
	      ans = new Pair<Integer, Integer>(i - max_len + 1, i);
	    }
	  }
	  return ans;
	}
	// @exclude

	static void simple_test() {
	  Pair<Integer, Integer> ans = find_longest_increasing_subarray(Arrays.asList(-1, -1));
	  assert(ans.getFirst() == 0 && ans.getSecond() == 0);
	  
	  ans = find_longest_increasing_subarray(Arrays.asList(1, 2));
	  assert(ans.getFirst() == 0 && ans.getSecond() == 1);
	}

	public static void main(String[] args) {
		simple_test();
		Random gen = new Random();

		for (int times = 0; times < 1000; ++times) {
			List<Integer> A = new ArrayList<Integer>();
			if (args.length > 2) {
				for (int i = 1; i < args.length; ++i) {
					A.add(Integer.valueOf(args[i]));
				}
			} else {
				int n;
				if (args.length == 1) {
					n = Integer.valueOf(args[0]);
				} else {
					n = gen.nextInt(1000000) + 1;
				}
				for (int i = 0; i < n; ++i) {
					A.add((gen.nextBoolean() ? -1 : 1) * gen.nextInt(n));
				}
			}
			Pair<Integer, Integer> result = find_longest_increasing_subarray(A);
			System.out.println(result.getFirst() + " " + result.getSecond());
			int len = 1;
			for (int i = 1; i < A.size(); ++i) {
				if (A.get(i) > A.get(i - 1)) {
					++len;
				} else {
					len = 1;
				}
				assert (len <= result.getSecond() - result.getFirst() + 1);
			}
		}
	}
}
