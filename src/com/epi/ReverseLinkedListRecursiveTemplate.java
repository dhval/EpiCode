// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.epi;

class ReverseLinkedListRecursiveTemplate {

  //@include
  public static <T> node_t<T> reverse_linked_list(node_t<T> head) {
    if (head == null || head.next == null) {
      return head;
    }

    node_t<T> new_head = reverse_linked_list(head.next);
    head.next.next = head;
    head.next = null;
    return new_head;
  }
  // @exclude

  public static <T> void print(node_t<T> head) {
    if (head != null) {
      System.out.println("(" + head.data + ")");
      print(head.next);
    }
  }

  public static void main(String[] argv) {
    node_t<Integer> L1 =
        new node_t<Integer>(1, null);
    node_t<Integer> L2 =
        new node_t<Integer>(2, null);
    L1.next = L2;
    node_t<Integer> L3 =
        new node_t<Integer>(3, null);
    L2.next = L3;

    System.out.println("before reverse");
    print(L1);
    node_t<Integer> newhead = reverse_linked_list(L1);
    System.out.println("\nafter reverse");
    print(newhead);
    newhead = reverse_linked_list(newhead);
    System.out.println("\nafter another reverse");
    print(newhead);
  }
}
