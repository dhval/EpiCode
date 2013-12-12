// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.drx.epi;

import java.util.Random;

class Zipping_list_template {
  // @include
  public static <T> void connect_a_next_to_b_advance_a(Ref<node_t<T>> a,
                                     node_t<T> b) {
    node_t<T> temp = a.value.next;
    a.value.next = b;
    a.value = temp;
  }

  public static <T> node_t<T> zipping_linked_list(node_t<T> L) {
    node_t<T> slow = L, fast = L, pre_slow = null;

    // Find the middle point of L.
    while (fast != null) {
      fast = fast.next;
      if (fast != null) {
        pre_slow = slow;
        fast = fast.next; slow = slow.next;
      }
    }

    if (pre_slow == null) {
      return L;  // only contains one node in the list.
    }
    pre_slow.next = null;  // splits the list into two lists.
    node_t<T> reverse =
            Reverse_linked_list_iterative_template.reverse_linked_list(slow);
    node_t<T> curr = L;

    // Zipping the list.
    while (curr != null && reverse != null) {
      node_t<T> temp = curr.next;
      curr.next = reverse;
      curr = temp;
      // Connect curr->next to reverse, and advance curr.
      //connect_a_next_to_b_advance_a(ref_curr, reverse);
      if (curr != null) {
        // Connect reverse->next to curr, and advance reverse.
        node_t<T> temp2 = reverse.next;
        reverse.next = curr;
        reverse = temp2;
        //connect_a_next_to_b_advance_a(ref_reverse, curr);
      }
    }

    return L;
  }
  //@exclude

  public static void main(String[] argv) {
    Random gen = new Random();
    node_t<Integer> head = null;
    int n = 0;
    if (argv.length > 1) {
      for (int i = 0; i < argv.length; ++i) {
        node_t<Integer> curr =
            new node_t<Integer>(Integer.parseInt(argv[i]), null);
        curr.next = head;
        head = curr;
      }
    } else {
      if (argv.length == 1) {
        n = Integer.parseInt(argv[0]);
      } else {
        n = gen.nextInt(1000) + 1;
      }
      for (int i = n; i >= 0; --i) {
        node_t<Integer> curr = new node_t<Integer>(i, null);
        curr.next = head;
        head = curr;
      }
    }

    node_t<Integer> curr = zipping_linked_list(head);
    int idx = 0, pre = 0;
    while (curr != null) {
      if (argv.length <= 1) {
        if ((idx & 1) != 0) {
          assert(pre + curr.data == n);
        }
      }
      ++idx;
      System.out.println(curr.data);
      pre = curr.data;
      curr = curr.next;
    }
  }
}
