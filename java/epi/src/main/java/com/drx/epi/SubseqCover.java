// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.drx.epi;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class SubseqCover {

  public static String rand_string(int len) {
    StringBuilder sb = new StringBuilder();
    Random gen = new Random();
    while (len-- != 0) {
      sb.append((char)(gen.nextInt((int)'z' + 1 - (int)'a') + (int)'a'));
    }
    return sb.toString();
  }

  // @include
  public static Pair<Integer, Integer> find_smallest_sequentially_covering_subset(
      ArrayList<String> A,
      ArrayList<String> Q) {
    HashMap<String, Integer> K = new HashMap<String, Integer>();  // stores the order of each Q[i].
    ArrayList<Integer> L = new ArrayList<Integer>(Q.size());
    ArrayList<Integer> D = new ArrayList<Integer>(Q.size());
    for(int i = 0; i < Q.size(); i++){
      L.add(-1);
      D.add(Integer.MAX_VALUE);
    }
    // Initialize K.
    for (int i = 0; i < Q.size(); ++i) {
      K.put(Q.get(i), i);
    }

    Pair<Integer, Integer> res = new Pair<Integer, Integer>(-1, A.size());  // default value.
    for (int i = 0; i < A.size(); ++i) {
      Integer it = K.get(A.get(i));
      if (it != null) {
        if (it == 0) {  // first one, no predecessor.
          D.set(0, 1);             // base condition.
        } else if (D.get(it - 1) != Integer.MAX_VALUE) {
          D.set(it, i - L.get(it - 1) + D.get(it - 1));
        }
        L.set(it, i);

        if (it == Q.size() - 1 &&
            D.get(D.size() - 1) < res.second - res.first + 1) {
          res.first = i - D.get(D.size() - 1) + 1;
          res.second = i;
        }
      }
    }
    return res;
  }
  // @exclude

  public static void small_test() {
    String[] A3_ = {"0", "1", "2", "3",  "4",  "5",  "6", "7", "8", "9",
                    "2", "4", "6", "10", "10", "10", "3", "2", "1", "0"};
    String[] subseq4_ = {"0", "2", "9", "4", "6"};
    ArrayList<String> A3 = new ArrayList<String>(Arrays.asList(A3_));
    ArrayList<String> subseq4 = new ArrayList<String>(Arrays.asList(subseq4_));

    Pair<Integer, Integer> rr = find_smallest_sequentially_covering_subset(A3, subseq4);
    assert(rr.first == 0 && rr.second == 12);
  }

  public static void main(String[] argv) {
    small_test();
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      ArrayList<String> A = new ArrayList<String>();
      if (argv.length == 1) {
        n = Integer.parseInt(argv[0]);
      } else {
        n = gen.nextInt(10000) + 1;
      }
      for(int i = 0; i < n; i++){
        A.add(rand_string(gen.nextInt(5) + 1));
      }
      HashSet<String> dict = new HashSet<String>(A);

      System.out.print("A = ");
      for(int i = 0; i < A.size(); i++){
        if (i != A.size() - 1)
          System.out.print(A.get(i) + ",");
        else
          System.out.print(A.get(i));
      }
      System.out.println("");
      int m = gen.nextInt(Math.min(dict.size(), 10)) + 1;
      ArrayList<String> Q = new ArrayList<String>();
      Iterator<String> it = dict.iterator();
      for(int i = 0; i < m; i++){
        if (it.hasNext())
          Q.add(it.next());
      }
      System.out.print("Q = ");
      for(int i = 0; i < Q.size(); i++){
        if (i != Q.size() - 1)
          System.out.print(Q.get(i) + ",");
        else
          System.out.print(Q.get(i));
      }
      System.out.println("");

      Pair<Integer, Integer> res = find_smallest_sequentially_covering_subset(A, Q);
      System.out.println(res.first + ", " + res.second);
      if (res.first != -1 && res.second != Q.size()) {
        if (res.first != res.second)
          System.out.println(res.first + ", " + res.second);
        dict.clear();
        dict.addAll(Q);
        for (int i = res.first; i <= res.second; ++i) {
          if (dict.contains(A.get(i))) {
            dict.remove(A.get(i));
          }
        }
        assert(dict.isEmpty());
      }
    }
  }
}
