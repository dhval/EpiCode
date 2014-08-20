// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Task_assignment{
  // @include
  public static ArrayList<Pair<Integer,Integer>> task_assignment(ArrayList<Integer> A) {
    Collections.sort(A);
    ArrayList<Pair<Integer,Integer>> P = new ArrayList<Pair<Integer,Integer>>();
    for (int i = 0, j = A.size() - 1; i < j; ++i, --j) {
      P.add(new Pair<Integer,Integer>(A.get(i), A.get(j)));
    }
    return P;
  }
  // @exclude
  
  public static void main(String[] argv) {
    Random gen = new Random();
    int n;
    if (argv.length == 1) {
      n = Integer.parseInt(argv[0]);
    } else {      
      n = gen.nextInt(10000) + 1;
    }
    ArrayList<Integer> A = new ArrayList<Integer>();
    for (int i = 0; i < n; ++i) {      
      A.add(gen.nextInt(999));
    }
    ArrayList<Pair<Integer,Integer>> P = task_assignment(A);
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < P.size(); ++i) {
      if (P.get(i).getFirst() + P.get(i).getSecond() > max) {
        max = P.get(i).getFirst() + P.get(i).getSecond();
      }
    }
    System.out.println(max);    
  }
}
