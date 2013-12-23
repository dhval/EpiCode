// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.drx.epi;

public class PermutationArray1 {
	// @include
	static void apply_permutation1(int[] perm, int[] A) {
	  for (int i = 0; i < A.length; ++i) {
	    if (perm[i] >= 0) {
	      int a = i;
	      int temp = A[i];
	      do {
	        int next_a = perm[a];
	        int next_temp = A[next_a];
	        A[next_a] = temp;
	        // Mark a as visited by using the sign bit.
	        perm[a] -= perm.length;
	        a = next_a; temp = next_temp;
	      } while (a != i);
	    }
	  }

	  // Restore perm back.
	  int size = perm.length;
	  for (int i = 0; i < perm.length; i++) {
		  perm[i] += size;
	  }
	}
	// @exclude
}
