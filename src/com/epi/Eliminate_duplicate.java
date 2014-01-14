// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Eliminate_duplicate{
  // @include
  public static void eliminate_duplicate(ArrayList<Integer> A) {
    Collections.sort(A);  // makes identical elements become neighbors.
    // C++ unique-like algorithm on indexes
    if (A.size() < 2)
      return;
    int result = 0;
    for(int first = 1; first < A.size(); first++){
      if (!A.get(first).equals(A.get(result)))
        A.set(++result, A.get(first));
    }
    // shrink array size
    A.subList(++result, A.size()).clear();
  }
  // @exclude
  
  public static void check_ans(ArrayList<Integer> A) {
    for (int i = 1; i < A.size(); ++i) {
      assert(!A.get(i).equals(A.get(i - 1)));
    }
  }
  
  public static void main(String[] argv) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      ArrayList<Integer> A = new ArrayList<Integer>();
      if (argv.length == 1) {
        n = Integer.parseInt(argv[0]);
      } else {        
        n = gen.nextInt(100000);
      }
      for (int i = 0; i < n; ++i) {
        A.add((n > 1)?gen.nextInt(n - 1):0);
      }
      eliminate_duplicate(A);
      check_ans(A);
    }
  }
}
