// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.drx.epi;

class OverlappingListsTemplate {
  // @include
  public static <T> node_t<T> overlapping_lists(node_t<T> L1,
                                          node_t<T> L2) {
    // Store the start of cycle if any.
    node_t<T> s1 = CheckingCycle.has_cycle(L1), s2 = CheckingCycle.has_cycle(L2);
  
    if (s1 == null && s2 == null) {
      return OverlappingListsNoCycle.overlapping_no_cycle_lists(L1, L2);
    } else if (s1 != null && s2 != null) {  // both lists have cycles.
      node_t<T> temp = s2;
      do {
        temp = temp.next;
      } while (temp != s1 && temp != s2);
      return (temp == s1) ? s1 : null;
    }
    return null;  // one list has cycle, and one list has no cycle.
  }
  // @exclude
  
  public static void main(String[] argv) {
    node_t<Integer> L1, L2;
    // L1: 1->2->3->null
    L1 =  new node_t<Integer>(
        1, new node_t<Integer>(
               2, new node_t<Integer>(3, null)));
    L2 = L1.next.next;
    assert(overlapping_lists(L1, L2).data == 3);
    // L2: 4->5->null
    L2 = new node_t<Integer>(4, new node_t<Integer>(5, null));
    assert(overlapping_lists(L1, L2) == null);
    L1.next.next.next = L1;
    assert(overlapping_lists(L1, L2) == null);
    L2.next.next = L2;
    assert(overlapping_lists(L1, L2) == null);
    L2.next.next = L1;
    assert(overlapping_lists(L1, L2) != null);
  }
}
