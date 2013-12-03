// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

class Deletion_list_template {
  // @include
  public static <T> void deletion_from_list(node_t<T> v) {
    v.data = v.next.data;
    v.next = v.next.next;
  }
  // @exclude
  
  public static void main(String[] argv) {
    node_t<Integer> L = 
            new node_t<Integer>(1, 
                   new node_t<Integer>(2, 
                           new node_t<Integer>(3, null)));
    deletion_from_list(L);
    assert(L.data == 2 && L.next.data == 3);
  }
}
