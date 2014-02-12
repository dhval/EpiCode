package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class LongestSubarrayK {
	// @include
	public static Pair<Integer, Integer> find_longest_subarray_less_equal_k(
			List<Integer> A, int k) {
		// Build the prefix sum according to A.
		ArrayList<Integer> prefix_sum = new ArrayList<Integer>();
		int summ = 0;
		for (int a : A) {
			summ += a;
			prefix_sum.add(summ);
		}

		ArrayList<Integer> min_prefix_sum = new ArrayList<Integer>(prefix_sum);

		for (int i = min_prefix_sum.size() - 2; i >= 0; --i) {
			min_prefix_sum.set(i,
					Math.min(min_prefix_sum.get(i), min_prefix_sum.get(i + 1)));
		}

		/*Instead of doing binary search O(log N), it is possible to find the longest subarray faster.
		* The overall run time of this algorithm is O(n). The algorithm goes through the arrays, 
		* keeping track of the current candidate longest subarray and the longest subarray seen so far.
		*/
		
		//start of the current candidate subarray
		int a = 0;
		//end of the current candidate subarray
		int b = 0;
		
		//This is the length of the longest valid subarray seen so far
		int maxLen = 0;
		
		//Start and end of the longest valid subarray seen so far
		int maxA = 0;
		int maxB = -1;

		while (b < prefix_sum.size()) {
			//calculate the sum of the current candidate subarray
			int curSum = a > 0 ? min_prefix_sum.get(b) - prefix_sum.get(a - 1)
					: min_prefix_sum.get(b);
			//check if the current candidate subarray is valid
			if (curSum <= k) {
				//calculate the length of the current subarray
				int curLen = b - a + 1;
				//check if the current candidate is longer than the previous longest subarray
				if (curLen > maxLen) {
					maxLen = curLen;
					maxA = a;
					maxB = b;
				}
				//increment the end of the array
				b += 1;
			} else {
				//the sum of the current candidate subarray is too large, increment the start of the array
				a += 1;
			}
		}
		return new Pair<Integer, Integer>(maxA, maxB);
	}

	// @exclude

	// O(n^2) checking answer
	private static void check_answer(List<Integer> A,
			Pair<Integer, Integer> ans, int k) {
		ArrayList<Integer> sum = new ArrayList<Integer>(A.size() + 1);
		sum.add(0);
		for (int i = 0; i < A.size(); ++i) {
			sum.add(sum.get(i) + A.get(i));
		}
		if (ans.getFirst() != -1 && ans.getSecond() != -1) {
			int s = 0;
			for (int i = ans.getFirst(); i <= ans.getSecond(); ++i) {
				s += A.get(i);
			}
			assert (s <= k);
			for (int i = 0; i < sum.size(); ++i) {
				for (int j = i + 1; j < sum.size(); ++j) {
					if (sum.get(j) - sum.get(i) <= k) {
						assert ((j - i) <= (ans.getSecond() - ans.getFirst() + 1));
					}
				}
			}
		} else {
			for (int i = 0; i < sum.size(); ++i) {
				for (int j = i + 1; j < sum.size(); ++j) {
					assert (sum.get(j) - sum.get(i) > k);
				}
			}
		}
	}

	public static void main(String[] args) {
		Random r = new Random();
		for (int times = 0; times < 1000; ++times) {
			int n, k;
			if (args.length == 2) {
				n = Integer.parseInt(args[0]);
				k = Integer.parseInt(args[1]);
			} else if (args.length == 1) {
				n = Integer.parseInt(args[0]);
				k = r.nextInt(10000);
			} else {
				n = r.nextInt(10000) + 1;
				k = r.nextInt(10000);
			}
			ArrayList<Integer> A = new ArrayList<Integer>();
			for (int i = 0; i < n; ++i) {
				A.add(r.nextInt(2001) - 1000);
			}
			// System.out.println(A);
			Pair<Integer, Integer> ans = find_longest_subarray_less_equal_k(A,
					k);
			System.out.println(k + " " + ans);
			check_answer(A, ans, k);
		}
	}
}
