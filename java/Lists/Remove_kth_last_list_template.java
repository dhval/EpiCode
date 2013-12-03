// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

class Remove_kth_last_list_template {
  // @include
  public static <T> void remove_kth_last(Ref<node_t<T>> L, int k) {
    // Advances k steps first.
    node_t<T> ahead = L.value;
    int num = k;
    while (ahead != null && num != 0) {
      ahead = ahead.next;
      --num;
    }
  
    if (num != 0) {
      throw new IllegalArgumentException("not enough nodes in the list");
    }
  
    node_t<T> pre = null, curr = L.value;
    // Finds the k-th last node.
    while (ahead != null) {
      pre = curr;
      curr = curr.next; ahead = ahead.next;
    }
    if (pre != null) {
      pre.next = curr.next;
    } else {
      L.value = curr.next;  // special case: delete L.
    }
  }
  // @exclude
  
  public static void main(String[] argv) {
    Ref<node_t<Integer>> L = new Ref<node_t<Integer>>(new node_t<Integer>(1, new node_t<Integer>(2, new node_t<Integer>(3, null))));
    try {
      remove_kth_last(L, 4);
    }
    catch (IllegalArgumentException e) {      
      System.out.println(e.getMessage());
    }
    
    remove_kth_last(L, 2);
    assert(L.value.data == 1 && L.value.next.data == 3);
    remove_kth_last(L, 2);
    assert(L.value.data == 3 && L.value.next == null);
  }
}

