// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

// @include
class Interval{
  int left, right;
  Interval(int l, int r){
    left = l;
    right = r;
  }
}

class EndPoint implements Comparable<EndPoint> {
  public int compareTo(EndPoint that) {
    int a = is_left ? ptr.left : ptr.right,
              b = that.is_left ? that.ptr.left : that.ptr.right;
    if (a < b) return -1;
    if (a == b) return 0;
    return 1;
  }

  Interval ptr;
  boolean is_left;
  
  EndPoint(Interval i, boolean il){
    ptr = i;
    is_left = il;
  }
}
//@exclude

class Points_covering_intervals_alternative {
    
  //@include
  public static ArrayList<Integer> find_minimum_visits_helper(ArrayList<EndPoint> endpoints) {
    ArrayList<Integer> S = new ArrayList<Integer>();  // a minimum set of visit times.
    HashSet<Interval> covered = new HashSet<Interval>();
    ArrayList<Interval> covering = new ArrayList<Interval>();
    for (EndPoint e : endpoints) {
      if (e.is_left) {
        covering.add(e.ptr);
      } else if (!covered.contains(e.ptr)) {
        // e's interval has not been covered.
        S.add(e.ptr.right);
        // Add all intervals in covering to covered.
        covered.addAll(covering);
        covering.clear();  // e is contained in all intervals in covering.
      }
    }
    return S;
  }

  public static ArrayList<Integer> find_minimum_visits(ArrayList<Interval> I) {
    ArrayList<EndPoint> endpoints = new ArrayList<EndPoint>();
    for (int i = 0; i < I.size(); ++i) {
      endpoints.add(new EndPoint(I.get(i), true));
      endpoints.add(new EndPoint(I.get(i), false));
    }
    
    Collections.sort(endpoints); 
    return find_minimum_visits_helper(endpoints);
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
        int right = gen.nextInt(left + 100) + left;
        A.add(new Interval(left, right));
      }
      
      ArrayList<Integer> ans = find_minimum_visits(A);
      check_ans(A, ans);
    }    
  }
}
