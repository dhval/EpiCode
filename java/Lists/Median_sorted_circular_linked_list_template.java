// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

//import java.lang.IllegalArgumentException;
import java.util.Random;

// @include
class Median_sorted_circular_linked_list {
  public static <T extends Integer> 
  double find_median_sorted_circular_linked_list(node_t<T> r_node) {
    if (r_node == null) {
      // no node in this linked list.
      throw new IllegalArgumentException("empty list");  
    }

    // Checks all nodes are identical or not and identify the start of list.
    node_t<T> curr = r_node;
    node_t<T> start = r_node;
    int count = 0;
    do {
      ++count;
      curr = curr.next;
      // start will point to the largest element in the list.
      if (start.data.compareTo(curr.data) <= 0) {
        start = curr;
      }
    } while (curr != r_node);
    // start's next is the begin of the list.
    start = start.next;

    // Traverses to the middle of the list and return the median.
    for (int i = 0; i < ((count - 1) >> 1); ++i) {
      start = start.next;
    }
    return (count & 1) != 0 ? start.data : 0.5 * (start.data + start.next.data);
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(1000) + 1;
      }
      node_t<Integer> head = null;
      for (int i = n; i >= 0; --i) {
        node_t<Integer> curr = new node_t<Integer>(i, null);
        curr.next = head;
        head = curr;
      }
      node_t<Integer> curr = head;
      if (curr != null) {
        while (curr.next != null) {
          curr = curr.next;
        }
        curr.next = head;  // make the list as a circular list.
      }
      double res = find_median_sorted_circular_linked_list(head.next);
      System.out.println(res);
      assert(res == 0.5 * n);
    }

    // Test empty list.
    node_t<Integer> head = null;
    try {
      find_median_sorted_circular_linked_list(head);
    }
    catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

    // Test identical list.
    for (int i = 0; i < 10; ++i) {
      node_t<Integer> curr = new node_t<Integer>(5, null);
      curr.next = head;
      head = curr;
    }
    node_t<Integer> curr = head;
    if (curr != null) {
      while (curr.next != null) {
        curr = curr.next;
      }
      curr.next = head;  // make the list as a circular list.
    }
    assert(5 == find_median_sorted_circular_linked_list(head));
  }
}
// @exclude
