// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;

class SmallestSubarrayCoveringSetStream{
  // @include
  public static Pair<Integer, Integer> find_smallest_subarray_covering_subset(
      ArrayList<String> A, ArrayList<String> Q) {
    Linked_list<Integer> loc = new Linked_list<Integer>();  // tracks the last occurrence (index) of each string in Q.
    HashMap<String, Linked_list<Integer>.Node> dict= new HashMap<String, Linked_list<Integer>.Node>();
    for (String s : Q) {
      dict.put(s, null);
    }
  
    Pair<Integer, Integer> res = new Pair<Integer, Integer>(-1, -1);
    int idx = 0;
    String s = new String();
    Iterator<String> sin = A.iterator();
    while (sin.hasNext()) {
      s = sin.next();
      if (dict.containsKey(s)) {  // s is in Q.
        Linked_list<Integer>.Node it = dict.get(s);
        if (it != null) {
          loc.erase(it);
        }
        
        Linked_list<Integer>.Node back = loc.push_back(idx);
        dict.put(s, back);        
      }
  
      if (loc.size() == Q.size() &&  // found |Q| keywords.
          ((res.getFirst() == -1 && res.getSecond() == -1) ||
           idx - loc.front().item < res.getSecond() - res.getFirst())) {
        res.setFirst(loc.front().item);
        res.setSecond(idx);
      }
      ++idx;
    }
    return res;
  }
  // @exclude
}

