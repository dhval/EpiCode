// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.drx.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.max;

public class Max_difference_k_pairs {
	// @include
	static int max_k_pairs_profits(List<Integer> A, int k) {
	  List<Integer> k_sum = new ArrayList<Integer>(k << 1);
	  fill(k_sum, k << 1, Integer.MIN_VALUE);
	  
	  for (int i = 0; i < A.size(); ++i) {
	    List<Integer> pre_k_sum = new ArrayList<Integer>(k_sum);
	    
	    for (int j = 0, sign = -1; j < k_sum.size() && j <= i; ++j, sign *= -1) {
	      int diff = sign * A.get(i) + (j == 0 ? 0 : pre_k_sum.get(j - 1));
	      k_sum.set(j, max(diff, pre_k_sum.get(j)));
	    }
	  }
	  
	  return k_sum.get(k_sum.size() - 1);  // returns the last selling profits as the answer.
	}
	// @exclude

	static void fill(List<Integer> list, int num_of_elements, int value) {
		for (int i = 1; i <= num_of_elements; ++i) {
			list.add(value);
		}
	}

	// O(n^k) checking answer.
	static int check_ans_helper(List<Integer> A, int l, int k,
	                      int pre, int ans, int max_ans) {
		
		int final_max_ans; 
	  if (l == k) {
		  final_max_ans = max(max_ans, ans);
	  } else {
		  final_max_ans = max_ans;
	    for (int i = pre; i < A.size(); ++i) {
	      final_max_ans = check_ans_helper(A, l + 1, k, i + 1,
	                       ans + (((l & 1) == 1) ? A.get(i) : -A.get(i)), final_max_ans);
	    }
	  }
	  
	  return final_max_ans;
	}

	static int check_ans(List<Integer> A, int k) {
		int ans = 0, max_ans = Integer.MIN_VALUE;

		max_ans = check_ans_helper(A, 0, k << 1, 0, ans, max_ans);
		System.out.println("max_ans = " + max_ans);
		return max_ans;
	}

	public static void main(String[] args) {
		Random gen = new Random();

		int n = 40, k = 4;
		// random tests for n = 40, k = 4 for 100 times/
		for (int times = 0; times < 100; ++times) {
			List<Integer> A = new ArrayList<Integer>();
			for (int i = 0; i < n; ++i) {
				A.add(gen.nextInt(100));
			}

			System.out.println("n = " + n + ", k = " + k);
			System.out.println(max_k_pairs_profits(A, k));
			assert (check_ans(A, k) == max_k_pairs_profits(A, k));
		}

		if (args.length == 1) {
			n = Integer.valueOf(args[0]);
			k = gen.nextInt(n >> 1) + 1;
		} else if (args.length == 2) {
			n = Integer.valueOf(args[0]);
			n = Integer.valueOf(args[1]);
		} else {
			n = gen.nextInt(10000) + 1;
			k = gen.nextInt(n >> 1) + 1;
		}

		List<Integer> A = new ArrayList<Integer>();

		for (int i = 0; i < n; ++i) {
			A.add(gen.nextInt(100));
		}

		System.out.println("n = " + n + ", k = " + k);
		System.out.println(max_k_pairs_profits(A, k));
	}

}
