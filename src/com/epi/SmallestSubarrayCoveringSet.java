// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import java.util.Random;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

class SmallestSubarrayCoveringSet{
      
  public static String rand_string(int len) {
    StringBuilder sb = new StringBuilder(); 
    Random gen = new Random();
    while (len-- != 0) {
      sb.append((char)(gen.nextInt((int)'z' + 1 - (int)'a') + (int)'a'));
    }  
    return sb.toString();
  }
  
  //@include
  public static Pair<Integer, Integer> find_smallest_subarray_covering_subset(
     ArrayList<String> A, ArrayList<String> Q) {
   HashSet<String> dict = new HashSet<String>(Q);
   HashMap<String, Integer> count_Q = new HashMap<String, Integer>();
   int l = 0, r = 0;
   Pair<Integer, Integer> res = new Pair<Integer, Integer>(-1, -1);
   while (r < A.size()) {
     // Keep moving r until it reaches end or count_Q has |Q| items.
     while (r < A.size() && count_Q.size() < Q.size()) {
       if (dict.contains(A.get(r))) {         
         count_Q.put(A.get(r), count_Q.containsKey(A.get(r))?count_Q.get(A.get(r))+1:1);         
       }
       ++r;
     }
  
     if (count_Q.size() == Q.size() &&  // found |Q| keywords.
         ((res.first == -1 && res.second == -1) ||
          r - 1 - l < res.second - res.first)) {
       res.first = l;
       res.second= r - 1;
     }
  
     // Keep moving l until it reaches end or count_Q has less |Q| items.
     while (l < r && count_Q.size() == Q.size()) {
       if (dict.contains(A.get(l))) {
         int it = count_Q.get(A.get(l));
         count_Q.put(A.get(l), --it);
         if (it == 0) {
           count_Q.remove(A.get(l));
           if ((res.first == -1 && res.second == -1) ||
               r - 1 - l < res.second - res.first) {
             res.first = l;
             res.second= r - 1;
           }
         }
       }
       ++l;
     }
   }
   return res;
  }
  //@exclude

  // O(n^2) solution
  public static int check_ans(ArrayList<String> A, ArrayList<String> Q) {
    HashSet<String> dict = new HashSet<String>();
    for (String s : Q) {
      dict.add(s);
    }
  
    Pair<Integer, Integer> ans = new Pair<Integer, Integer>(0, A.size() - 1);
    for (int l = 0; l < A.size(); ++l) {
      HashMap<String, Integer> count = new HashMap<String, Integer>();
      for (int r = l; r < A.size() && r - l < ans.second - ans.first; ++r) {
        if (dict.contains(A.get(r))) {                    
          count.put(A.get(r), count.containsKey(A.get(r))?count.get(A.get(r))+1:1);
        }
        if (count.size() == Q.size()) {
          if (r - l < ans.second - ans.first) {
            ans.first = l;
            ans.second= r;
          }
          break;
        }
      }
      count.clear();
    }
    
    return ans.second - ans.first;
  }

  public static void main(String[] argv) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      ArrayList<String> A = new ArrayList<String>();
      if (argv.length == 1) {
        n = Integer.parseInt(argv[0]);
      } else {        
        n = gen.nextInt(10000) + 1;
      }
      for (int i = 0; i < n; ++i) {        
        A.add(rand_string(gen.nextInt(10) + 1));
      }
      /*
      for (int i = 0; i < A.size(); ++i) {
        cout << A[i] << ' ';
      }
      cout << endl;
      */
      HashSet<String> dict = new HashSet<String>(A);            
      int m = gen.nextInt(dict.size()) + 1;
      ArrayList<String> Q = new ArrayList<String>();
      Iterator<String> it = dict.iterator();
      while(it.hasNext()){
        Q.add(it.next());
        if (--m == 0) {
          break;
        }
      }
      
      /*
      for (int i = 0; i < Q.size(); ++i) {
        cout << Q[i] << ' ';
      }
      cout << endl;
      */
      Pair<Integer, Integer> res = find_smallest_subarray_covering_subset(A, Q);
      System.out.println(res.first + ", " + res.second);
      dict.clear();
      for (int i = 0; i < Q.size(); ++i) {
        dict.add(Q.get(i));
      }
      for (int i = res.first; i <= res.second; ++i) {
        if (dict.contains(A.get(i))) {
          dict.remove(A.get(i));
        }
      }
      assert(dict.isEmpty() == true);
      Pair<Integer, Integer> res2 = SmallestSubarrayCoveringSetStream.find_smallest_subarray_covering_subset(A, Q);
      System.out.println(res2.first + ", " + res2.second);
      dict.clear();
      for (int i = 0; i < Q.size(); ++i) {
        dict.add(Q.get(i));
      }
      for (int i = res.first; i <= res.second; ++i) {
        if (dict.contains(A.get(i))) {
          dict.remove(A.get(i));
        }
      }
      assert(dict.isEmpty() == true);
      assert(res.second - res.first == res2.second - res2.first);
      assert(res.second - res.first == check_ans(A, Q));
    }    
  }
}
