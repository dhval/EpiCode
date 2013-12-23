// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

public class PermutationArray2 {
	// @include
	static void apply_permutation2(int[] perm, int[] A) {
	  for (int i = 0; i < A.length; ++i) {
	    // Traverse the cycle to see if i is the min element.
	    boolean is_min = true;
	    int j = perm[i];
	    while (j != i) {
	      if (j < i) {
	        is_min = false;
	        break;
	      }
	      j = perm[j];
	    }

	    if (is_min) {
	      int a = i;
	      int temp = A[i];
	      do {
	        int next_a = perm[a];
	        int next_temp = A[next_a];
	        A[next_a] = temp;
	        a = next_a; temp = next_temp;
	      } while (a != i);
	    }
	  }
	}
	// @exclude
}
