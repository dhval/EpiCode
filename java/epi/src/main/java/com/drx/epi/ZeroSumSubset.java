// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.drx.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.drx.epi.utils.Utils.*;

public class ZeroSumSubset {
	// @include
	static List<Integer> find_0_sum_subset(List<Integer> A) {
	  List<Integer> prefix_sum = new ArrayList<Integer>(A);
	  for (int i = 0; i < prefix_sum.size(); ++i) {
		  prefix_sum.set(i, prefix_sum.get(i) + (i > 0 ? prefix_sum.get(i - 1) : 0));
		  prefix_sum.set(i, prefix_sum.get(i) % A.size());
	  }

	  List<Integer> table = new ArrayList<Integer>(A.size());
	  fill(table, A.size(), -1);
	  
	  for (int i = 0; i < A.size(); ++i) {
	    if (prefix_sum.get(i) == 0) {
	      List<Integer> ans = new ArrayList<Integer>(i + 1);
	      iota(ans, i + 1, 0);
	      return ans;
	    } else if (table.get(prefix_sum.get(i)) != -1) {
	      List<Integer> ans = new ArrayList<Integer>(i - table.get(prefix_sum.get(i)));
	      iota(ans, i - table.get(prefix_sum.get(i)), table.get(prefix_sum.get(i)) + 1);
	      return ans;
	    }
	    table.set(prefix_sum.get(i), i);
	  }
	  // @exclude
	  return Collections.emptyList();  // it should not happen
	  // @include
	}
	// @exclude

	static void check_ans(List<Integer> A, List<Integer> ans) {
	  int sum = 0;
	  for (int a : ans) {
	    sum = (sum + A.get(a)) % A.size();
	  }
	  assert(sum == 0);
	}

	public static void main(String[] args) {
	  Random gen = new Random();
	  for (int times = 0; times < 1000; ++times) {
	    int n;
	    List<Integer> A = new ArrayList<Integer>();
	    if (args.length == 1) {
	      n = Integer.valueOf(args[0]);
	      for (int i = 0; i < n; ++i) {
	        A.add(gen.nextInt(10000));
	      }
	    } else if (args.length > 1) {
	      for (int i = 1; i < args.length; ++i) {
	        A.add(Integer.valueOf(args[i]));
	      }
	    } else {
	      n = gen.nextInt(100) + 1;
	      for (int i = 0; i < n; ++i) {
	    	  A.add(gen.nextInt(10000));
	      }
	    }
	    List<Integer> ans = find_0_sum_subset(A);
	    check_ans(A, ans);
	  }
	}
}
