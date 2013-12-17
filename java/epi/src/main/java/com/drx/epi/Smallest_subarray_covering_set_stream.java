// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.drx.epi;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.ArrayList;

class Smallest_subarray_covering_set_stream{
  // @include
  public static pair<Integer, Integer> find_smallest_subarray_covering_subset(
      Scanner sin, ArrayList<String> Q) {
    Linked_list<Integer> loc = new Linked_list<Integer>();  // tracks the last occurrence (index) of each string in Q.
    HashMap<String, Linked_list<Integer>.Node> dict= new HashMap<String, Linked_list<Integer>.Node>();
    for (String s : Q) {
      dict.put(s, null);
    }
  
    pair<Integer, Integer> res = new pair<Integer, Integer>(-1, -1);
    int idx = 0;
    String s = new String();
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
          ((res.first == -1 && res.second == -1) ||
           idx - loc.front().item < res.second - res.first)) {
        res.first = loc.front().item;
        res.second= idx;
      }
      ++idx;
    }
    return res;
  }
  // @exclude
}

