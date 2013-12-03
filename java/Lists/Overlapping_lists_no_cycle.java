// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

import java.lang.Math;

// @include
// Counts the list length till end.
class Overlapping_lists_no_cycle {
  public static <T> int count_len(node_t<T> L) {
    int len = 0;
    while (L != null) {
      ++len;
      L = L.next;
    }
    return len;
  }

  public static <T> void advance_list_by_k(Ref<node_t<T>> L, int k) {
    while (k-- > 0) {
      L.value = L.value.next;
    }
  }

  public static <T> node_t<T> 
  overlapping_no_cycle_lists(node_t<T> L1, node_t<T> L2) {
    // Count the lengths of L1 and L2.
    int L1_len = count_len(L1);
    int L2_len = count_len(L2);

    Ref<node_t<T>> RL1 = new Ref<node_t<T>>(L1);
    Ref<node_t<T>> RL2 = new Ref<node_t<T>>(L2);

    // Advance the longer list.
    advance_list_by_k(L1_len > L2_len ? RL1 : RL2, Math.abs(L1_len - L2_len));

    while (RL1.value != null && RL2.value != null && RL1.value != RL2.value) {
      RL1.value = RL1.value.next;
      RL2.value = RL2.value.next;
    }
    return RL1.value;  // nullptr means no overlap between L1 and L2.
  }

  public static void  main(String[] args) {
    node_t<Integer> L1 = null;
    node_t<Integer> L2 = null;
    // L1: 1->2->3->null
    L1 = new node_t<Integer>(1, new node_t<Integer>(2, new node_t<Integer>(3, null)));
    L2 = L1.next.next;
    assert(overlapping_no_cycle_lists(L1, L2).data == 3);
    // L2: 4->5->null
    L2 = new node_t<Integer>(4, new node_t<Integer>(5, null));
    assert(overlapping_no_cycle_lists(L1, L2) == null);
  }
}
// @exclude

