// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.drx.epi;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

class pair<First, Second>{
  public First first;
  public Second second;
  pair(First f, Second s){
    first = f;
    second = s;
  }
}

// @include
public class LRUCache {
  
  public LRUCache(int c){
    capacity = c;    
  }
  
  public boolean lookup(int isbn) {
    pair<Linked_list<Integer>.Node, Integer> it = cache_.get(isbn);
    if (it == null) {
      return false;
    }

    lookup_val = it.second;
    moveToFront(isbn, it);
    return true;
  }

  void insert(int isbn, int price) {
    pair<Linked_list<Integer>.Node, Integer> it = cache_.get(isbn);
    if (it != null) {
      moveToFront(isbn, it);
    } else {
      // Remove the least recently used.
      if (cache_.size() == capacity) {
        cache_.remove(data_.back());
      }      
      cache_.put(isbn, new pair<Linked_list<Integer>.Node, Integer>(data_.push_front(isbn), price));      
    }
  }

  public boolean erase(int isbn) {
    pair<Linked_list<Integer>.Node, Integer> it = cache_.get(isbn);
    if (it == null) {
      return false;
    }

    data_.erase(it.first);    
    cache_.remove(isbn);
    return true;
  }

  // Move the most recent accessed item to the front.
  void moveToFront(int isbn, pair<Linked_list<Integer>.Node, Integer> it) {    
    data_.erase(it.first);
    data_.push_back(isbn);
    it.first = data_.front();
  }

  public int lookup_val = 0;
  private int capacity;
  private HashMap<Integer, pair<Linked_list<Integer>.Node, Integer>> cache_ = new HashMap<Integer, pair<Linked_list<Integer>.Node, Integer>>();  
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
