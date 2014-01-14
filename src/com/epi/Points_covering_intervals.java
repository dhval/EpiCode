// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Random;

// @include
class LeftComp implements Comparator<Interval>{
  public int compare(Interval a, Interval b) {
    if (a.left < b.left) return -1;
    if (a.left > b.left) return 1;
    if (a.right < b.right) return -1;
    if (a.right > b.right) return 1;
    return 0;    
  }
};

class RightComp implements Comparator<Interval>{
  public int compare(Interval a, Interval b) {
    if (a.right < b.right) return -1;
    if (a.right > b.right) return 1;
    if (a.left < b.left) return -1;
    if (a.left > b.left) return 1;
    return 0;
  }
}
// @exclude

class Points_covering_intervals {
  
  //@include
  public static ArrayList<Integer> find_minimum_visits(ArrayList<Interval> I) {
    TreeSet<Interval> L = new TreeSet<Interval>(new LeftComp());
    TreeSet<Interval> R = new TreeSet<Interval>(new RightComp());    
    
    for (Interval i : I) {
      L.add(i); R.add(i);
    }
  
    ArrayList<Integer> S = new ArrayList<Integer>();
    while (!L.isEmpty() && !R.isEmpty()) {
      int b = R.first().right;
      S.add(b);
  
      // Remove the intervals which intersect with R.cbegin().
      Iterator<Interval> it = L.iterator();
      while (it.hasNext())
      {
        Interval i = it.next();
        if (i.left > b)
          break;

        R.remove(i);
        it.remove();
      }
    }
    
    return S;
  }
  // @exclude
  
  // O(n^2) checking solution
  public static void check_ans(ArrayList<Interval> I, ArrayList<Integer> ans) {
    boolean[] is_visited = new boolean[I.size()];
    for (Integer a : ans) {
      for (int i = 0; i < I.size(); ++i) {
        if (a >= I.get(i).left && a <= I.get(i).right) {
          is_visited[i] = true;
        }
      }
    }
  
    for (boolean b : is_visited) {
      assert(b == true);
    }
  }
  
  public static void simple_test() {
    ArrayList<Interval> I = new ArrayList<Interval>();
    I.add(new Interval(1, 4));
    I.add(new Interval(2, 8));
    I.add(new Interval(3, 6));
    I.add(new Interval(3, 5));
    I.add(new Interval(7, 10));
    I.add(new Interval(9, 11));
    ArrayList<Integer> ans = find_minimum_visits(I);
    assert(ans.size() == 2 && ans.get(0) == 4 && ans.get(1) == 10);
  }
  
  public static void main(String[] argv) {
    simple_test();
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      System.out.println("Test " + times);
      int n;
      if (argv.length == 1) {
        n = Integer.parseInt(argv[0]);
      } else {
        
        n = gen.nextInt(10000) + 1;
      }
      ArrayList<Interval> A = new ArrayList<Interval>();
      for (int i = 0; i < n; ++i) {        
        int left = gen.nextInt(9999);        
        int right = gen.nextInt(left + 100) + left + 1;
        A.add(new Interval(left, right));
      }
      ArrayList<Integer> ans = find_minimum_visits(A);
      check_ans(A, ans);
    }    
  }
}
