// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.drx.epi;

import static com.drx.epi.utils.Utils.iota;
import static com.drx.epi.utils.Utils.shuffle;
import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.Arrays;
import java.util.Random;

public class Offline_minimum {
	// @include
	static int find_set(int[] set, int x) {
	  if (set[x] != x) {
	    set[x] = find_set(set, set[x]);  // path compression.
	  }
	  return set[x];
	}

	static void union_set(int[] set, int x, int y) {
	  int x_root = find_set(set, x), y_root = find_set(set, y);
	  set[min(x_root, y_root)] = max(x_root, y_root);
	}

	static int[] offline_minimum(int[] A, int[] E) {
	  int[] R = new int[A.length]; Arrays.fill(R, E.length);
	  int pre = 0;

	  // Initialize the collection of subsets.
	  for (int i = 0; i < E.length; ++i) {
	    for (int j = pre; j <= E[i]; ++j) {
	      R[A[j]] = i;
	    }
	    pre = E[i] + 1;
	  }

	  int[] ret = new int[E.length]; Arrays.fill(ret, -1);  // stores the answer
	  int[] set = new int[E.length + 1];  // the disjoint-set
	  iota(set, 0, set.length, 0);  // initializes the disjoint-set
	  for (int i = 0; i < A.length; ++i) {
	    if (find_set(set, R[i]) != E.length && ret[find_set(set, R[i])] == -1) {
	      ret[set[R[i]]] = i;
	      union_set(set, set[R[i]], set[R[i]] + 1);
	    }
	  }
	  return ret;
	}
	// @exclude

	// O(nm) checking method
	static int[] check_answer(int[] A, int[] E) {
	  boolean[] exist = new boolean[A.length];
	  int[] ans = new int[E.length];

	  for (int i = 0; i < E.length; ++i) {
	    int min_val = Integer.MAX_VALUE;
	    for (int j = 0; j <= E[i]; ++j) {
	      if (A[j] < min_val && !exist[A[j]]) {
	        min_val = min(A[j], min_val);
	      }
	    }
	    exist[min_val] = true;
	    ans[i] = min_val;
	  }
	  /*
	    System.out.print("ans2 = ");
	    System.out.println(Arrays.toString(ans));
	    */
	  return ans;
	}

	public static void main(String[] args) {
	  Random gen = new Random();
	  for (int times = 0; times < 1000; ++times) {
	    int n, m;
	    if (args.length == 1) {
	      n = Integer.valueOf(args[0]);
	      m = gen.nextInt(n) + 1;
	    } else if (args.length == 2) {
	    	n = Integer.valueOf(args[0]);
	    	m = Integer.valueOf(args[1]);
	    } else {
	      n = gen.nextInt(1000) + 1;
	      m = gen.nextInt(n) + 1;
	    }
	    System.out.println("n = " + n + ", m = " + m);
	    int[] A = new int[n];
	    iota(A, 0, A.length, 0);
	    shuffle(A);
	    
	    /*
	    System.out.print("A = ");
	    System.out.println(Arrays.toString(A));
	    */
	    int[] E = new int[m];
	    for (int i = 0; i < m; ++i) {
	      E[i] = gen.nextInt(n - i) + i;
	    }
	    Arrays.sort(E);
	    /*
	    System.out.print("E = ");
	    System.out.println(Arrays.toString(E));
	    */
	    int[] ans = offline_minimum(A, E);
	    /*
	    System.out.print("ans1 = ");
	    System.out.println(Arrays.toString(ans));
	    */
	    int[] tmp = check_answer(A, E);
	    assert(Arrays.equals(ans, tmp));
	  }
	}

}
