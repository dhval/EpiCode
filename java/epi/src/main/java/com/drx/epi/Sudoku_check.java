// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.drx.epi;

import java.util.List;

import static java.lang.Math.sqrt;

public class Sudoku_check {
	// @include
	// Check if a partially filled matrix has any conflicts.
	public static boolean is_valid_Sudoku(List<List<Integer>> A) {
	  // Check row constraints.
	  for (int i = 0; i < A.size(); ++i) {
	    boolean[] is_present = new boolean[A.size() + 1];
	    for (int j = 0; j < A.size(); ++j) {
	      if (A.get(i).get(j) != 0 && is_present[A.get(i).get(j)] == true) {
	        return false;
	      } else {
	        is_present[A.get(i).get(j)] = true;
	      }
	    }
	  }

	  // Check column constraints.
	  for (int j = 0; j < A.size(); ++j) {
		 boolean[] is_present = new boolean[A.size() + 1];
	    for (int i = 0; i < A.size(); ++i) {
	      if (A.get(i).get(j) != 0 && is_present[A.get(i).get(j)] == true) {
	        return false;
	      } else {
	        is_present[A.get(i).get(j)] = true;
	      }
	    }
	  }

	  // Check region constraints.
	  int region_size = (int) sqrt(A.size());
	  for (int I = 0; I < region_size; ++I) {
	    for (int J = 0; J < region_size; ++J) {
	      boolean[] is_present = new boolean[A.size() + 1];
	      for (int i = 0; i < region_size; ++i) {
	        for (int j = 0; j < region_size; ++j) {
	          if (A.get(region_size * I + i).get(region_size * J + j) != 0 &&
	              is_present[A.get(region_size * I + i).get(region_size * J + j)]) {
	            return false;
	          } else {
	        	  is_present[A.get(region_size * I + i).get(region_size * J + j)] = true;
	          }
	        }
	      }
	    }
	  }
	  return true;
	}
	// @exclude
}
