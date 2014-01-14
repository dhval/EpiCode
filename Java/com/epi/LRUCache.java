// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

// @include
public class LRUCache {
  
  public LRUCache(int c){
    capacity = c;    
  }
  
  public boolean lookup(int isbn) {
    Pair<Linked_list<Integer>.Node, Integer> it = cache_.get(isbn);
    if (it == null) {
      return false;
    }

    lookup_val = it.getSecond();
    moveToFront(isbn, it);
    return true;
  }

  void insert(int isbn, int price) {
    Pair<Linked_list<Integer>.Node, Integer> it = cache_.get(isbn);
    if (it != null) {
      moveToFront(isbn, it);
    } else {
      // Remove the least recently used.
      if (cache_.size() == capacity) {
        cache_.remove(data_.back());
      }      
      cache_.put(isbn, new Pair<Linked_list<Integer>.Node, Integer>(data_.push_front(isbn), price));      
    }
  }

  public boolean erase(int isbn) {
    Pair<Linked_list<Integer>.Node, Integer> it = cache_.get(isbn);
    if (it == null) {
      return false;
    }

    data_.erase(it.getFirst());    
    cache_.remove(isbn);
    return true;
  }

  // Move the most recent accessed item to the front.
  void moveToFront(int isbn, Pair<Linked_list<Integer>.Node, Integer> it) {    
    data_.erase(it.getFirst());
    data_.push_back(isbn);
    it.setFirst(data_.front());
  }

  public int lookup_val = 0;
  private int capacity;
  private HashMap<Integer, Pair<Linked_list<Integer>.Node, Integer>> cache_ = new HashMap<Integer, Pair<Linked_list<Integer>.Node, Integer>>();  
  private Linked_list<Integer> data_ = new Linked_list<Integer>();
// @exclude

  public static void main(String[] argv) {
    LRUCache c = new LRUCache(3);
    System.out.println("c.insert(1, 1)");
    c.insert(1, 1);
    System.out.println("c.insert(1, 10)");
    c.insert(1, 10);
    System.out.println("c.lookup(2, val)");
    assert(!c.lookup(2));
    System.out.println("c.lookup(1, val)");
    assert(c.lookup(1));
    assert(c.lookup_val == 1);
    c.erase(1);
    assert(!c.lookup(1));
  }

}
